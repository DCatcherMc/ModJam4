package net.dcatcher.enderius.common.util;

import net.dcatcher.enderius.common.items.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Copyright: DCatcher
 */
public class DCCreativeTab extends CreativeTabs {
    public DCCreativeTab() {
        super("Enderius");
    }

    @Override
    public Item getTabIconItem() {
        return ItemHandler.enderStar;
    }
}
