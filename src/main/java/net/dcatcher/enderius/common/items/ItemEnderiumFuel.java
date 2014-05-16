package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * Copyright: DCatcher
 */
public class ItemEnderiumFuel extends Item {

    public ItemEnderiumFuel(){
        super();
        setUnlocalizedName("enderiumFuel");
        setCreativeTab(Enderius.danTab);
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("enderius:enderiumfuel");
    }
}
