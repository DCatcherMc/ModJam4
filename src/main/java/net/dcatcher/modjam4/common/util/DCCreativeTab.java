package net.dcatcher.modjam4.common.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Copyright: DCatcher
 */
public class DCCreativeTab extends CreativeTabs {
    public DCCreativeTab() {
        super("DCatcher's ModJam");
    }

    @Override
    public Item getTabIconItem() {
        return Items.enchanted_book;
    }
}
