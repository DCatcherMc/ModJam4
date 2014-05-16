package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Copyright: DCatcher
 */
public class ItemEnderNucleus extends net.minecraft.item.Item {

    public ItemEnderNucleus(){
        super();
        setUnlocalizedName("enderNucleus");
        setCreativeTab(Enderius.danTab);
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("enderius:endercore");
    }
}
