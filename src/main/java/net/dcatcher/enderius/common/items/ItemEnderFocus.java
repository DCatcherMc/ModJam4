package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x,
                             int y, int z, int side, float px, float py, float pz) {
        switch(side){
            case 0:
            case 1:
                int startXa = x - 1;
                int startZa = z -1 ;
                for(int a = 0; a < 3; a++){
                    for(int b = 0; b < 3; b++){
                        if(world.getBlock(startXa + a, y, startZa + b) != Blocks.bedrock && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                            if(!world.isRemote)
                                player.entityDropItem(new ItemStack(world.getBlock(startXa + a, y, startZa + b)), 0f);
                            world.setBlockToAir(startXa + a, y, startZa + b);
                        }
                    }
                }
                player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                break;

            case 2:
            case 3:
                int startXb = x - 1;
                int startYb = y - 1;

                for(int a = 0; a < 3; a++){
                    for(int b = 0; b < 3; b++){
                        if(world.getBlock(startXb + a, startYb + b, z) != Blocks.bedrock && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                            if(!world.isRemote)
                                player.entityDropItem(new ItemStack(world.getBlock(startXb + a, startYb + b, z)), 0f);
                            world.setBlockToAir(startXb + a, startYb + b, z);
                        }
                    }
                }
                player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                break;

            case 4:
            case 5:
                int startZc = z - 1;
                int startYc = y - 1;
                for(int a = 0; a < 3; a++){
                    for(int b = 0; b < 3; b++){
                        if(world.getBlock(x, startYc + a, startZc + b) != Blocks.bedrock && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                            if(!world.isRemote)
                                player.entityDropItem(new ItemStack(world.getBlock(x, startYc + a, startZc + b)), 0f);
                            world.spawnParticle();
                            world.setBlockToAir(x, startYc + a, startZc + b);
                        }
                    }
                }
                player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                break;
        }
        return true;
    }
}