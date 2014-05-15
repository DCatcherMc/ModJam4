package net.dcatcher.modjam4.common.items;

import net.dcatcher.modjam4.ModJam4;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * Copyright: DCatcher
 */
public class ItemBookOfLevelling extends net.minecraft.item.Item {

    public ItemBookOfLevelling(){
        setCreativeTab(ModJam4.danTab);
        setUnlocalizedName("itemBookOfLevelling");
    }

    @Override
    public void registerIcons(IIconRegister icons) {
        this.itemIcon = icons.registerIcon("dcatcher_modjam4:bookOfLeveling");

    }
}
