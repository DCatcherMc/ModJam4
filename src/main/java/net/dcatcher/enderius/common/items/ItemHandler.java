package net.dcatcher.enderius.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import org.w3c.dom.NodeList;

/**
 * Copyright: DCatcher
 */
public class ItemHandler {

    //public static Item bookOfLeveling;
    public static Item enderSlime;
    public static Item enderFocus;
    public static Item enderiumFuel;

    public static void initialiseItems(){
        //bookOfLeveling = new ItemBookOfLevelling();
        enderSlime = new ItemEnderSlime();
        enderFocus = new ItemEnderFocus();
        enderiumFuel = new ItemEnderiumFuel();

        //GameRegistry.registerItem(bookOfLeveling, "bookOfLevelling");
        GameRegistry.registerItem(enderSlime, "enderSlime");
        GameRegistry.registerItem(enderFocus, "enderFocus");
        GameRegistry.registerItem(enderiumFuel, "Enderium Fuel");
    }
}
