package net.dcatcher.modjam4;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.dcatcher.modjam4.common.CommonProxy;
import net.dcatcher.modjam4.common.items.ItemHandler;
import net.dcatcher.modjam4.common.network.PacketPipeline;
import net.dcatcher.modjam4.common.player.PlayerTracker;
import net.dcatcher.modjam4.common.util.DCCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

/**
 * TODO:
 *  Name this main mod something different.
 */

@Mod(modid="DCatcher_ModJam4", name="To be decided!", version="${version}")
public class ModJam4 {

    @Mod.Instance("DCatcher_ModJam4")
    public static ModJam4 instance;

    public static CreativeTabs danTab = new DCCreativeTab();

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    @SidedProxy(clientSide = "net.dcatcher.modjam4.client.ClientProxy", serverSide = "net.dcatcher.modjam4.common.CommonProxy")
    public static CommonProxy proxy = new CommonProxy();

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event){
        PlayerTracker tracker = new PlayerTracker();
        MinecraftForge.EVENT_BUS.register(tracker);
        FMLCommonHandler.instance().bus().register(tracker);
        ItemHandler.initialiseItems();
    }

    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event){
        packetPipeline.init();
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event){
        packetPipeline.postInit();
    }
}
