package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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
    public boolean onItemUse(ItemStack stack, EntityPlayer entityPlayer, World world, int x,
                             int y, int z, int side, float px, float py, float pz) {
        removeBlocks(world, x, y, z, side);
        return true;
    }

    public void removeBlocks(World world, int x, int y, int z, int side){
        switch(side){
            case 0 || 1:
                int startXa = x - 1;
                int startZa = z + 1;
                for(int a = 0; a < 3; x++){
                    for(int b = 3; b < 3; b++){
                        world.setBlockToAir(startXa + a, y, startZa + b);
                    }
                }
                break;
            case 2 | 3:
                int startXb = x - 1;
                int startYb = y + 1;
                break;
            case 4 | 5:
                int startZc = z - 1;
                int startYc = y + 1;
                break;
        }
    }
}
