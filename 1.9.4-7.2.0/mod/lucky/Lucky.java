package mod.lucky;

import java.util.Iterator;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import mod.lucky.structure.rotation.Rotations;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.RecipeSorter;
import mod.lucky.crafting.LuckyCrafting;
import mod.lucky.tileentity.TileEntityLuckyBlock;
import java.io.File;
import mod.lucky.drop.func.DropFunction;
import mod.lucky.entity.EntityLuckyPotion;
import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import mod.lucky.entity.EntityLuckyProjectile;
import net.minecraft.block.Block;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import mod.lucky.network.ParticlePacket;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.SidedProxy;
import mod.lucky.world.LuckyTickHandler;
import mod.lucky.resources.loader.ResourceLoader;
import mod.lucky.structure.Structure;
import mod.lucky.resources.loader.PluginLoader;
import java.util.ArrayList;
import mod.lucky.item.ItemLuckyPotion;
import mod.lucky.item.ItemLuckyBow;
import mod.lucky.item.ItemLuckySword;
import mod.lucky.block.BlockLuckyBlock;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "lucky", name = "Lucky Block", version = "7.2.0", acceptedMinecraftVersions = "[1.9.4]")
public class Lucky
{
    @Mod.Instance("lucky")
    private static Lucky instance;
    public static String MODID;
    public static String VERSION;
    public static String MC_VERSION;
    public static BlockLuckyBlock lucky_block;
    public static ItemLuckySword lucky_sword;
    public static ItemLuckyBow lucky_bow;
    public static ItemLuckyPotion lucky_potion;
    public static ArrayList<PluginLoader> lucky_block_plugins;
    public static ArrayList<Structure> structures;
    private ResourceLoader resourceLoader;
    private LuckyTickHandler tickHandler;
    public static int ENTITY_ID;
    @SidedProxy(clientSide = "mod.lucky.client.ClientProxy", serverSide = "mod.lucky.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper networkHandler;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent a1) {
        Lucky.proxy.preRegister();
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent a1) {
        (Lucky.networkHandler = NetworkRegistry.INSTANCE.newSimpleChannel(/*EL:77*/"LuckyBlock")).registerMessage(/*EL:78*/(Class)ParticlePacket.Handler.class, (Class)ParticlePacket.class, 0, Side.CLIENT);
        Lucky.lucky_block_plugins = /*EL:80*/new ArrayList<PluginLoader>();
        Lucky.structures = /*EL:81*/new ArrayList<Structure>();
        (Lucky.lucky_block = /*EL:84*/(BlockLuckyBlock)new BlockLuckyBlock(Material.field_151575_d).func_149663_c("luckyBlock").func_149711_c(0.2f).func_149752_b(6000000.0f).func_149647_a(CreativeTabs.field_78030_b)).setHarvestLevel(/*EL:85*/"pickaxe", 0);
        /*SL:86*/GameRegistry.register(Lucky.lucky_block.setRegistryName("lucky_block"));
        /*SL:87*/GameRegistry.register(new ItemLuckyBlock((Block)Lucky.lucky_block).setRegistryName(Lucky.lucky_block.getRegistryName()));
        Lucky.lucky_sword = /*EL:90*/(ItemLuckySword)new ItemLuckySword().func_77655_b("luckySword").func_77637_a(CreativeTabs.field_78037_j);
        /*SL:91*/GameRegistry.register(Lucky.lucky_sword.setRegistryName("lucky_sword"));
        Lucky.lucky_bow = /*EL:94*/(ItemLuckyBow)new ItemLuckyBow().func_77655_b("luckyBow").func_77637_a(CreativeTabs.field_78037_j);
        /*SL:95*/GameRegistry.register(Lucky.lucky_bow.setRegistryName("lucky_bow"));
        Lucky.lucky_potion = /*EL:98*/(ItemLuckyPotion)new ItemLuckyPotion().func_77655_b("luckyPotion").func_77637_a(CreativeTabs.field_78037_j);
        /*SL:99*/GameRegistry.register(Lucky.lucky_potion.setRegistryName("lucky_potion"));
        /*SL:102*/EntityRegistry.registerModEntity((Class)EntityLuckyProjectile.class, "LuckyProjectile", Lucky.ENTITY_ID++, (Object)this, 128, 20, true);
        EntityList.field_75626_c.put(/*EL:103*/EntityLuckyProjectile.class, "LuckyProjectile");
        EntityList.field_75625_b.put(/*EL:104*/"LuckyProjectile", EntityLuckyProjectile.class);
        /*SL:107*/EntityRegistry.registerModEntity((Class)EntityLuckyPotion.class, "LuckyPotion", Lucky.ENTITY_ID++, (Object)this, 128, 20, true);
        EntityList.field_75626_c.put(/*EL:108*/EntityLuckyPotion.class, "LuckyPotion");
        EntityList.field_75625_b.put(/*EL:109*/"LuckyPotion", EntityLuckyPotion.class);
        /*SL:111*/DropFunction.registerFunctions();
        /*SL:112*/(this.resourceLoader = new ResourceLoader(new File("."))).registerPlugins();
        /*SL:114*/this.resourceLoader.extractDefaultResources();
        /*SL:115*/this.resourceLoader.loadAllResources();
        /*SL:117*/GameRegistry.registerTileEntity((Class)TileEntityLuckyBlock.class, "luckyBlockData");
        /*SL:118*/RecipeSorter.register("lucky:lucky_block", (Class)LuckyCrafting.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
        /*SL:119*/GameRegistry.addRecipe((IRecipe)Lucky.lucky_block.getCrafting());
        /*SL:120*/GameRegistry.addRecipe((IRecipe)Lucky.lucky_sword.getCrafting());
        /*SL:121*/GameRegistry.addRecipe((IRecipe)Lucky.lucky_bow.getCrafting());
        /*SL:122*/GameRegistry.addRecipe((IRecipe)Lucky.lucky_potion.getCrafting());
        /*SL:123*/GameRegistry.registerWorldGenerator((IWorldGenerator)Lucky.lucky_block.getWorldGenerator(), 1);
        /*SL:125*/this.tickHandler = new LuckyTickHandler();
        MinecraftForge.EVENT_BUS.register(/*EL:126*/(Object)this.tickHandler);
        /*SL:128*/Rotations.registerRotationHandlers();
        Lucky.proxy.register();
    }
    
    public static Lucky getInstance() {
        /*SL:135*/return Lucky.instance;
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent a1) {
    }
    
    public ResourceLoader getResourceLoader() {
        /*SL:146*/return this.resourceLoader;
    }
    
    public LuckyTickHandler getTickHandler() {
        /*SL:151*/return this.tickHandler;
    }
    
    public static ArrayList<Structure> getStructures() {
        /*SL:156*/return Lucky.structures;
    }
    
    public static Structure getStructure(final String v1) {
        /*SL:161*/for (final Structure a1 : Lucky.structures) {
            /*SL:162*/if (v1.equals(a1.getId())) {
                return a1;
            }
        }
        /*SL:163*/return null;
    }
    
    public static void addStructure(final Structure a1) {
        Lucky.structures.add(/*EL:168*/a1);
    }
    
    static {
        Lucky.MODID = "lucky";
        Lucky.VERSION = "7.2.0";
        Lucky.MC_VERSION = "1.9.4";
        Lucky.ENTITY_ID = 24653;
    }
}
