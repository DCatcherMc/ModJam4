package net.dcatcher.modjam4;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.dcatcher.modjam4.common.items.ItemHandler;
import net.dcatcher.modjam4.common.util.DCCreativeTab;
import net.minecraft.creativetab.CreativeTabs;

/**
 * TODO:
 *  Name this main mod something different.
 */

@Mod(modid="DCatcher_ModJam4", name="To be decided!", version="${version}")
public class ModJam4 {

    public static CreativeTabs danTab = new DCCreativeTab();

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event){
        ItemHandler.initialiseItems();
    }

    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event){


    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event){

    }
}
