package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * Copyright: DCatcher
 */
public class ItemDNASyringe extends Item {

    public ItemDNASyringe(){
        super();
        setUnlocalizedName("dnaSyringe");
        setCreativeTab(Enderius.danTab);
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("enderius:dnasyringe");
    }
}
