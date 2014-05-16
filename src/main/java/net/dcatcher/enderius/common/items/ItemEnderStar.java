package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Copyright: DCatcher
 */
public class ItemEnderStar extends net.minecraft.item.Item {

    public ItemEnderStar(){
        super();
        setUnlocalizedName("enderStar");
        setCreativeTab(Enderius.danTab);
    }


    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("enderius:enderstar");
    }
}
