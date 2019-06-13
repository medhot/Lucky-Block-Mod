package mod.lucky.util;

import mod.lucky.drop.DropContainer;
import java.util.ArrayList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.BlockPos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTBase;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import java.util.Random;

public class LuckyFunction
{
    public static int[] potionEffectList;
    public static int[] potionEffectListGood;
    public static int[] potionEffectListBad;
    public static int[] mobEggList;
    public static int[] mobIDList;
    public static String[] mobNameList;
    private static Random random;
    
    public static NBTTagCompound getRandomFireworksRocket() {
        final Random random = /*EL:63*/new Random();
        final NBTTagCompound nbtTagCompound = /*EL:65*/new NBTTagCompound();
        final NBTTagCompound nbtTagCompound2 = /*EL:66*/new NBTTagCompound();
        final NBTTagCompound nbtTagCompound3 = /*EL:67*/new NBTTagCompound();
        final NBTTagList list = /*EL:68*/new NBTTagList();
        /*SL:71*/nbtTagCompound3.func_74774_a("Type", (byte)random.nextInt(5));
        /*SL:72*/nbtTagCompound3.func_74757_a("Flicker", random.nextBoolean());
        /*SL:73*/nbtTagCompound3.func_74757_a("Trail", random.nextBoolean());
        final int n = /*EL:74*/random.nextInt(4) + 1;
        final int[] v0 = /*EL:75*/new int[n];
        /*SL:76*/for (int v = 0; v < n; ++v) {
            /*SL:78*/v0[v] = Integer.valueOf(ItemDye.field_150922_c[random.nextInt(14)]);
        }
        /*SL:80*/nbtTagCompound3.func_74783_a("Colors", v0);
        /*SL:83*/list.func_74742_a((NBTBase)nbtTagCompound3);
        /*SL:86*/nbtTagCompound2.func_74782_a("Explosions", (NBTBase)list);
        /*SL:87*/nbtTagCompound2.func_74774_a("Flight", (byte)(random.nextInt(2) + 1));
        /*SL:90*/nbtTagCompound.func_74782_a("Fireworks", (NBTBase)nbtTagCompound2);
        /*SL:91*/return nbtTagCompound;
    }
    
    public static int getRandomPotionDamage() {
        /*SL:96*/return calculatePotionDamage(getRandomPotionEffect(), 0);
    }
    
    public static int calculatePotionDamage(final int a1, final int a2) {
        int v1 = /*EL:103*/0;
        int v2 = /*EL:104*/0;
        final int v3 = LuckyFunction.random.nextInt(/*EL:105*/3);
        /*SL:106*/if (v3 == 1) {
            v1 = 32;
        }
        /*SL:107*/if (v3 == 2) {
            v2 = 64;
        }
        int v4 = /*EL:110*/0;
        /*SL:111*/if (a2 == 0) {
            /*SL:113*/if (LuckyFunction.random.nextInt(2) == 0) {
                v4 = 16384;
            }
        }
        else/*SL:115*/ if (a2 == 1) {
            v4 = 0;
        }
        else/*SL:116*/ if (a2 == 2) {
            v4 = 16384;
        }
        /*SL:118*/return a1 + v1 + v2 + v4;
    }
    
    @Deprecated
    public static EntityLiving getMobByNameOrId(final World a1, final String a2, final int a3) {
        /*SL:125*/if (a2.equals(LuckyFunction.mobNameList[0]) || a3 == LuckyFunction.mobIDList[0]) {
            return (EntityLiving)new EntityCreeper(a1);
        }
        /*SL:126*/if (a2.equals(LuckyFunction.mobNameList[1]) || a3 == LuckyFunction.mobIDList[1]) {
            return (EntityLiving)new EntitySkeleton(a1);
        }
        /*SL:127*/if (a2.equals(LuckyFunction.mobNameList[2]) || a3 == LuckyFunction.mobIDList[2]) {
            return (EntityLiving)new EntitySpider(a1);
        }
        /*SL:128*/if (a2.equals(LuckyFunction.mobNameList[3]) || a3 == LuckyFunction.mobIDList[3]) {
            return (EntityLiving)new EntityGiantZombie(a1);
        }
        /*SL:129*/if (a2.equals(LuckyFunction.mobNameList[4]) || a3 == LuckyFunction.mobIDList[4]) {
            return (EntityLiving)new EntityZombie(a1);
        }
        /*SL:130*/if (a2.equals(LuckyFunction.mobNameList[5]) || a3 == LuckyFunction.mobIDList[5]) {
            return (EntityLiving)new EntitySlime(a1);
        }
        /*SL:131*/if (a2.equals(LuckyFunction.mobNameList[6]) || a3 == LuckyFunction.mobIDList[6]) {
            return (EntityLiving)new EntityGhast(a1);
        }
        /*SL:132*/if (a2.equals(LuckyFunction.mobNameList[7]) || a3 == LuckyFunction.mobIDList[7]) {
            return (EntityLiving)new EntityPigZombie(a1);
        }
        /*SL:133*/if (a2.equals(LuckyFunction.mobNameList[8]) || a3 == LuckyFunction.mobIDList[8]) {
            return (EntityLiving)new EntityEnderman(a1);
        }
        /*SL:134*/if (a2.equals(LuckyFunction.mobNameList[9]) || a3 == LuckyFunction.mobIDList[9]) {
            return (EntityLiving)new EntityCaveSpider(a1);
        }
        /*SL:135*/if (a2.equals(LuckyFunction.mobNameList[10]) || a3 == LuckyFunction.mobIDList[10]) {
            return (EntityLiving)new EntitySilverfish(a1);
        }
        /*SL:136*/if (a2.equals(LuckyFunction.mobNameList[11]) || a3 == LuckyFunction.mobIDList[11]) {
            return (EntityLiving)new EntityBlaze(a1);
        }
        /*SL:137*/if (a2.equals(LuckyFunction.mobNameList[12]) || a3 == LuckyFunction.mobIDList[12]) {
            return (EntityLiving)new EntityMagmaCube(a1);
        }
        /*SL:138*/if (a2.equals(LuckyFunction.mobNameList[13]) || a3 == LuckyFunction.mobIDList[13]) {
            return (EntityLiving)new EntityDragon(a1);
        }
        /*SL:139*/if (a2.equals(LuckyFunction.mobNameList[14]) || a3 == LuckyFunction.mobIDList[14]) {
            return (EntityLiving)new EntityWither(a1);
        }
        /*SL:140*/if (a2.equals(LuckyFunction.mobNameList[15]) || a3 == LuckyFunction.mobIDList[15]) {
            return (EntityLiving)new EntityWitch(a1);
        }
        /*SL:143*/if (a2.equals(LuckyFunction.mobNameList[16]) || a3 == LuckyFunction.mobIDList[16]) {
            return (EntityLiving)new EntityBat(a1);
        }
        /*SL:144*/if (a2.equals(LuckyFunction.mobNameList[17]) || a3 == LuckyFunction.mobIDList[17]) {
            return (EntityLiving)new EntityPig(a1);
        }
        /*SL:145*/if (a2.equals(LuckyFunction.mobNameList[18]) || a3 == LuckyFunction.mobIDList[18]) {
            return (EntityLiving)new EntitySheep(a1);
        }
        /*SL:146*/if (a2.equals(LuckyFunction.mobNameList[19]) || a3 == LuckyFunction.mobIDList[19]) {
            return (EntityLiving)new EntityCow(a1);
        }
        /*SL:147*/if (a2.equals(LuckyFunction.mobNameList[20]) || a3 == LuckyFunction.mobIDList[20]) {
            return (EntityLiving)new EntityChicken(a1);
        }
        /*SL:148*/if (a2.equals(LuckyFunction.mobNameList[21]) || a3 == LuckyFunction.mobIDList[21]) {
            return (EntityLiving)new EntitySquid(a1);
        }
        /*SL:149*/if (a2.equals(LuckyFunction.mobNameList[22]) || a3 == LuckyFunction.mobIDList[22]) {
            return (EntityLiving)new EntityWolf(a1);
        }
        /*SL:150*/if (a2.equals(LuckyFunction.mobNameList[23]) || a3 == LuckyFunction.mobIDList[23]) {
            return (EntityLiving)new EntityMooshroom(a1);
        }
        /*SL:151*/if (a2.equals(LuckyFunction.mobNameList[24]) || a3 == LuckyFunction.mobIDList[24]) {
            return (EntityLiving)new EntitySnowman(a1);
        }
        /*SL:152*/if (a2.equals(LuckyFunction.mobNameList[25]) || a3 == LuckyFunction.mobIDList[25]) {
            return (EntityLiving)new EntityOcelot(a1);
        }
        /*SL:153*/if (a2.equals(LuckyFunction.mobNameList[26]) || a3 == LuckyFunction.mobIDList[26]) {
            return (EntityLiving)new EntityIronGolem(a1);
        }
        /*SL:154*/if (a2.equals(LuckyFunction.mobNameList[27]) || a3 == LuckyFunction.mobIDList[27]) {
            return (EntityLiving)new EntityHorse(a1);
        }
        /*SL:155*/if (a2.equals(LuckyFunction.mobNameList[28]) || a3 == LuckyFunction.mobIDList[28]) {
            return (EntityLiving)new EntityVillager(a1);
        }
        /*SL:157*/return null;
    }
    
    public static Entity getEntity(final World a1, final int a2, final String a3) {
        /*SL:162*/if (!EntityList.field_75627_a.containsKey(a2)) {
            /*SL:164*/return EntityList.func_75620_a(a3, a1);
        }
        /*SL:168*/return EntityList.func_75616_a(a2, a1);
    }
    
    public static int getRandomStatusEffect() {
        final String[] v1 = /*EL:174*/Potion.func_180141_c();
        final Potion v2 = /*EL:175*/Potion.func_180142_b(v1[LuckyFunction.random.nextInt(v1.length)]);
        /*SL:176*/return v2.func_76396_c();
    }
    
    public static int getRandomPotionEffect() {
        /*SL:181*/return LuckyFunction.random.nextInt(14) + 1;
    }
    
    public static int getRandomPotionEffectGood() {
        /*SL:186*/return LuckyFunction.potionEffectListGood[LuckyFunction.random.nextInt(LuckyFunction.potionEffectListGood.length)];
    }
    
    public static int getRandomPotionEffectBad() {
        /*SL:191*/return LuckyFunction.potionEffectListBad[LuckyFunction.random.nextInt(LuckyFunction.potionEffectListBad.length)];
    }
    
    public static int getRandomMobEgg() {
        final Object[] v1 = EntityList.field_75627_a.values().toArray();
        final EntityList.EntityEggInfo v2 = /*EL:197*/(EntityList.EntityEggInfo)v1[LuckyFunction.random.nextInt(v1.length)];
        /*SL:198*/return v2.field_75613_a;
    }
    
    public static int getPlayerDirection(final EntityPlayer a1, final int a2) {
        int v1 = /*EL:203*/(int)a1.field_70177_z;
        final int v2 = /*EL:204*/360 / a2;
        /*SL:205*/if (v1 < 0) {
            v1 += 360;
        }
        /*SL:206*/v1 += v2 / 2;
        /*SL:207*/v1 %= 360;
        /*SL:208*/return v1 / v2;
    }
    
    public static int adjustHeight(final World a2, final int a3, final int a4, final int a5, final int v1) {
        boolean v2 = /*EL:213*/false;
        int v3 = /*EL:214*/a5;
        int v4 = /*EL:215*/0;
        /*SL:216*/for (int a6 = a5; a6 < a5 + 16; ++a6) {
            /*SL:218*/if (a2.func_180495_p(new BlockPos(a4, a6, v1)).func_177230_c().func_149662_c()) {
                /*SL:220*/v4 = 0;
                /*SL:221*/v3 = a6 + 1;
            }
            else {
                /*SL:225*/++v4;
            }
            /*SL:228*/if (v4 == a3) {
                /*SL:230*/v2 = true;
                /*SL:231*/break;
            }
        }
        /*SL:235*/if (v2) {
            /*SL:237*/return v3;
        }
        /*SL:241*/return -1;
    }
    
    public static NBTTagList getNBTTagListFromStringArray(final String[] v1) {
        final NBTTagList v2 = /*EL:247*/new NBTTagList();
        /*SL:248*/for (final String a1 : v1) {
            /*SL:250*/v2.func_74742_a((NBTBase)new NBTTagString(a1));
        }
        /*SL:252*/return v2;
    }
    
    public static String[] getStringArrayFromNBTTagList(final NBTTagList v1) {
        final String[] v2 = /*EL:257*/new String[(v1 == null) ? 0 : v1.func_74745_c()];
        /*SL:258*/for (int a1 = 0; a1 < v2.length; ++a1) {
            /*SL:260*/v2[a1] = v1.func_150307_f(a1);
        }
        /*SL:262*/return v2;
    }
    
    public static ArrayList<DropContainer> getDropsFromStringArray(final String[] v-4) {
        final ArrayList<DropContainer> list = /*EL:267*/new ArrayList<DropContainer>();
        /*SL:268*/for (final String v1 : v-4) {
            final DropContainer a1 = /*EL:270*/new DropContainer();
            /*SL:271*/a1.readFromString(v1);
            /*SL:272*/list.add(a1);
        }
        /*SL:274*/return list;
    }
    
    static {
        LuckyFunction.potionEffectList = new int[] { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 14 };
        LuckyFunction.potionEffectListGood = new int[] { 1, 5, 6, 8, 10, 12, 13, 14, 16, 14 };
        LuckyFunction.potionEffectListBad = new int[] { 2, 7, 18, 19 };
        LuckyFunction.mobEggList = new int[] { 50, 51, 52, 54, 55, 56, 57, 58, 59, 60, 61, 62, 65, 66, 90, 91, 92, 93, 94, 95, 96, 98, 100, 120 };
        LuckyFunction.mobIDList = new int[] { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 120 };
        LuckyFunction.mobNameList = new String[] { "creeper", "skeleton", "spider", "giant zombie", "zombie", "slime", "ghast", "zombie pigman", "enderman", "cave spider", "silverfish", "blaze", "magma cube", "ender dragon", "wither", "witch", "bat", "pig", "sheep", "cow", "chicken", "squid", "wolf", "mooshroom", "snow golem", "ocelot", "iron golem", "horse", "villager" };
        LuckyFunction.random = new Random();
    }
}
