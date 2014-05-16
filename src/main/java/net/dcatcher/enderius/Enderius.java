package net.dcatcher.enderius;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.dcatcher.enderius.common.CommonProxy;
import net.dcatcher.enderius.common.EntityEnderSlime;
import net.dcatcher.enderius.common.items.ItemHandler;
import net.dcatcher.enderius.common.network.PacketPipeline;
import net.dcatcher.enderius.common.util.DCCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * TODO:
 *  Name this main mod something different.
 */

@Mod(modid="Enderius", name="Enderius", version="${version}")
public class Enderius {

    @Mod.Instance("Enderius")
    public static Enderius instance;

    public static CreativeTabs danTab = new DCCreativeTab();

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    @SidedProxy(clientSide = "net.dcatcher.enderius.client.ClientProxy", serverSide = "net.dcatcher.enderius.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event){
        //PlayerTracker tracker = new PlayerTracker();
        //MinecraftForge.EVENT_BUS.register(tracker);
        //FMLCommonHandler.instance().bus().register(tracker);
        ItemHandler.initialiseItems();

        EntityRegistry.registerGlobalEntityID(EntityEnderSlime.class, "EnderSlime", EntityRegistry.findGlobalUniqueEntityId(), (255 << 16), (255 << 16) + (200 << 8));
        EntityRegistry.addSpawn(EntityEnderSlime.class, 1, 1, 1, EnumCreatureType.monster);
        EntityRegistry.addSpawn(EntityEnderSlime.class, 1, 1, 1, EnumCreatureType.monster);
    }

    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event){
        packetPipeline.init();
        proxy.registerRenders();
    }


    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event){
        packetPipeline.postInit();
    }
}
