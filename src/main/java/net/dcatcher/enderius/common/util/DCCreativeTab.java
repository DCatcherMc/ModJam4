package net.dcatcher.enderius.common.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Copyright: DCatcher
 */
public class DCCreativeTab extends CreativeTabs {
    public DCCreativeTab() {
        super("DCatcherModjam");
    }

    @Override
    public Item getTabIconItem() {
        return Items.enchanted_book;
    }
}
