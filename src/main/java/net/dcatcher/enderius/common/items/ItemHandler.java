package net.dcatcher.enderius.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Copyright: DCatcher
 */
public class ItemHandler {

    public static Item bookOfLeveling;
    public static Item enderSlime;
    public static Item enderFocus;

    public static void initialiseItems(){
        bookOfLeveling = new ItemBookOfLevelling();
        enderSlime = new ItemEnderSlime();
        enderFocus = new ItemEnderFocus();

        GameRegistry.registerItem(bookOfLeveling, "bookOfLevelling");
        GameRegistry.registerItem(enderSlime, "enderSlime");
    }
}
