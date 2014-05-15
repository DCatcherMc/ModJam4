package net.dcatcher.modjam4.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Copyright: DCatcher
 */
public class ItemHandler {

    public static Item bookOfLeveling;

    public static void initialiseItems(){
        bookOfLeveling = new ItemBookOfLevelling();

        GameRegistry.registerItem(bookOfLeveling, "bookOfLevelling");
    }
}
