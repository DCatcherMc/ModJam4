package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Copyright: DCatcher
 */
public class ItemBookOfLevelling extends net.minecraft.item.Item {

    public ItemBookOfLevelling(){
        setCreativeTab(Enderius.danTab);
        setUnlocalizedName("itemBookOfLevelling");
    }

    @Override
    public void registerIcons(IIconRegister icons) {
        this.itemIcon = icons.registerIcon("enderius:bookOfLeveling");

    }
}
