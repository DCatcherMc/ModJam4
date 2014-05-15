package net.dcatcher.modjam4.common.items;

import net.minecraft.item.Item;

/**
 * Copyright: DCatcher
 */
public class ItemHandler {

    public static Item bookOfLeveling;

    public static void initialiseItems(){
        bookOfLeveling = new ItemBookOfLevelling();
    }
}
