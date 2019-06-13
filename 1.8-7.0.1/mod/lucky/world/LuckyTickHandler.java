package mod.lucky.world;

import mod.lucky.drop.func.DropProcessData;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IChatComponent;
import mod.lucky.Lucky;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraftforge.event.world.WorldEvent;
import mod.lucky.drop.func.DropProcessor;
import java.util.concurrent.ConcurrentHashMap;

public class LuckyTickHandler
{
    private ConcurrentHashMap<Integer, Object> delayDrops;
    private boolean shownUpdateVersion;
    private DropProcessor dropProcessor;
    
    public LuckyTickHandler() {
        this.shownUpdateVersion = false;
        try {
            this.delayDrops = new ConcurrentHashMap<Integer, Object>();
            this.dropProcessor = new DropProcessor();
        }
        catch (Exception ex) {}
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load v-9) {
        try {
            final URL url = /*EL:45*/new URL("http://www.minecraftascending.com/projects/lucky_block/download/version/version_log.txt");
            final BufferedReader bufferedReader = /*EL:46*/new BufferedReader(new InputStreamReader(url.openStream()));
            final int intValue = /*EL:48*/Integer.valueOf(Lucky.VERSION.replace(".", ""));
            final int intValue2 = /*EL:49*/Integer.valueOf(Lucky.MC_VERSION.replace(".", ""));
            String line;
            /*SL:52*/while ((line = bufferedReader.readLine()) != null) {
                final String[] split = /*EL:54*/line.split("\\|");
                final int intValue3 = /*EL:55*/Integer.valueOf(split[0].replace(".", ""));
                final int intValue4 = /*EL:56*/Integer.valueOf(split[1].replace(".", ""));
                /*SL:58*/if (intValue4 >= intValue2 && intValue3 > intValue) {
                    final String a1 = /*EL:60*/split[2];
                    final IChatComponent v1 = /*EL:61*/IChatComponent.Serializer.func_150699_a(a1);
                    /*SL:62*/MinecraftServer.func_71276_C().func_71203_ab().func_148539_a(ChatComponentProcessor.func_179985_a((ICommandSender)null, v1, (Entity)null));
                    /*SL:63*/break;
                }
            }
        }
        catch (Exception ex) {
            /*SL:69*/ex.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void onServerTick(final TickEvent.ServerTickEvent v0) {
        try {
            /*SL:78*/for (int v = 0; v > -1; ++v) {
                /*SL:80*/if (this.delayDrops.containsKey(v) && this.delayDrops.get(v) instanceof DelayLuckyDrop) {
                    final DelayLuckyDrop a1 = /*EL:82*/this.delayDrops.get(v);
                    /*SL:83*/a1.update();
                    /*SL:84*/if (a1.finished()) {
                        /*SL:86*/this.delayDrops.remove(v);
                        /*SL:87*/if (this.delayDrops.containsKey(v + 1)) {
                            this.delayDrops.put(v, 0);
                        }
                    }
                }
                /*SL:90*/if (!this.delayDrops.containsKey(v)) {
                    break;
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:95*/"Lucky Block: Error processing delay drop");
            /*SL:96*/v2.printStackTrace();
            /*SL:97*/this.delayDrops.clear();
        }
    }
    
    @SubscribeEvent
    public void onChunkSave(final ChunkDataEvent.Save v-2) {
        try {
            /*SL:106*/if (this.delayDrops.size() > 0) {
                boolean b = /*EL:108*/false;
                final NBTTagList v0 = /*EL:109*/new NBTTagList();
                /*SL:111*/for (int v = 0; v > -1; ++v) {
                    /*SL:113*/if (this.delayDrops.containsKey(v) && this.delayDrops.get(v) instanceof DelayLuckyDrop) {
                        final DelayLuckyDrop a1 = /*EL:115*/this.delayDrops.get(v);
                        /*SL:116*/if (v-2.getChunk().func_177412_p().func_175726_f(a1.getProcessData().getHarvestBlockPos()) == v-2.getChunk()) {
                            /*SL:118*/v0.func_74742_a((NBTBase)a1.writeToNBT());
                            /*SL:119*/b = true;
                        }
                    }
                    /*SL:122*/if (!this.delayDrops.containsKey(v)) {
                        break;
                    }
                }
                /*SL:125*/if (b) {
                    v-2.getData().func_74782_a("LuckyBlockDelayDrops", (NBTBase)v0);
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:130*/"Lucky Block: Error saving chunk properties");
            /*SL:131*/ex.printStackTrace();
            /*SL:132*/this.delayDrops.clear();
        }
    }
    
    @SubscribeEvent
    public void onChunkLoad(final ChunkDataEvent.Load v-1) {
        try {
            /*SL:141*/if (v-1.getData().func_74764_b("LuckyBlockDelayDrops")) {
                final NBTTagList v0 = /*EL:143*/v-1.getData().func_150295_c("LuckyBlockDelayDrops", 10);
                /*SL:144*/for (int v = 0; v < v0.func_74745_c(); ++v) {
                    final DelayLuckyDrop a1 = /*EL:146*/new DelayLuckyDrop(Lucky.lucky_block.getDropProcessor(), null, 0L);
                    /*SL:147*/a1.readFromNBT(v0.func_150305_b(v), v-1.getChunk().func_177412_p());
                    /*SL:148*/this.addDelayDrop(a1);
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:154*/"Lucky Block: Error loading chunk properties");
            /*SL:155*/v2.printStackTrace();
            /*SL:156*/this.delayDrops.clear();
        }
    }
    
    public void addDelayDrop(final DropProcessor a1, final DropProcessData a2, final float a3) {
        /*SL:162*/this.addDelayDrop(new DelayLuckyDrop(a1, a2, (long)(a3 * 20.0f)));
    }
    
    public void addDelayDrop(final DelayLuckyDrop v2) {
        /*SL:167*/for (int a1 = 0; a1 > -1; ++a1) {
            /*SL:169*/if (!this.delayDrops.containsKey(a1) || !(this.delayDrops.get(a1) instanceof DelayLuckyDrop)) {
                /*SL:171*/this.delayDrops.put(a1, v2);
                /*SL:172*/break;
            }
        }
    }
}
