package net.dcatcher.enderius.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Copyright: DCatcher
 */
public class ItemHandler {

    public static Item enderSlime;
    public static Item enderFocus;
    public static Item enderiumFuel;
    public static Item enderNucleus;
    public static Item enderStar;

    public static void initialiseItems(){
        enderSlime = new ItemEnderSlime();
        enderFocus = new ItemEnderFocus();
        enderiumFuel = new ItemEnderiumFuel();
        enderNucleus = new ItemEnderNucleus();
        enderStar = new ItemEnderStar();


        GameRegistry.registerItem(enderSlime, "enderSlime");
        GameRegistry.registerItem(enderFocus, "enderFocus");
        GameRegistry.registerItem(enderiumFuel, "enderiumFuel");
        GameRegistry.registerItem(enderNucleus, "enderNucleus");
    }
}
