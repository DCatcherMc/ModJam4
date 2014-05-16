package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Copyright: DCatcher
 */
public class ItemEnderFocus extends Item {

    public ItemEnderFocus(){
        super();
        setCreativeTab(Enderius.danTab);
        setMaxStackSize(1);
        setUnlocalizedName("enderFocus");
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("enderius:enderfocus");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer entityPlayer, World world, int par4,
                             int par5, int par6, int par7, float par8, float par9, float par10) {
        Block clicked = world.getBlock(par4, par5, par6);

        for(int i = 1; i < 4; i++){
            for(int j = 1; j < 4; j++){

            }
        }

        return false;
    }
}
