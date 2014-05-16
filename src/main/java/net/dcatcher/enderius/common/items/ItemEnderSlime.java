package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Copyright: DCatcher
 */
public class ItemEnderSlime extends net.minecraft.item.Item {

    public ItemEnderSlime(){
        super();
        setUnlocalizedName("enderSlime");
        setCreativeTab(Enderius.danTab);
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("dcatcher_modjam4:enderslime");
    }
}
