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
        int modifier = 1;

        switch(side){
            case 0:
                modifier = 1;
            case 1:
                modifier = -1;
                int startXa = x - 1;
                int startZa = z -1 ;
                for(int dist = 0; dist < 10; dist=(dist++ * modifier)){
                    for(int a = 0; a < 3; a++){
                        for(int b = 0; b < 3; b++){
                            if(world.getBlock(startXa + a, y+dist, startZa + b) != Blocks.bedrock && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                                if(!world.isRemote)
                                    player.entityDropItem(new ItemStack(world.getBlock(startXa + a, y+dist, startZa + b)), 0f);
                                world.setBlockToAir(startXa + a, y+dist, startZa + b);
                            }
                        }
                    }
                    player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                }
                break;

            case 2:
                modifier = 1;
            case 3:
                modifier = -1;
                int startXb = x - 1;
                int startYb = y - 1;
                for(int dist = 0; dist < 10; dist=(dist++ * modifier)){
                for(int a = 0; a < 3; a++){
                        for(int b = 0; b < 3; b++){
                            if(world.getBlock(startXb + a, startYb + b, z) != Blocks.bedrock && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                                if(!world.isRemote)
                                    player.entityDropItem(new ItemStack(world.getBlock(startXb + a, startYb + b, z+dist)), 0f);
                                world.setBlockToAir(startXb + a, startYb + b, z+dist);
                            }
                        }
                    }
                    player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                }
                break;

            case 4:
                modifier = 1;
            case 5:
                modifier = -1;
                int startZc = z - 1;
                int startYc = y - 1;
                for(int dist = 0; dist < 10; dist=(dist++ * modifier)){
                    for(int a = 0; a < 3; a++){
                        for(int b = 0; b < 3; b++){
                            Block current = world.getBlock(x+dist, startYc+a, startZc + b);
                            if(current != Blocks.bedrock && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                                if(!world.isRemote)
                                    player.entityDropItem(new ItemStack(current), 0f);
                                world.setBlockToAir(x + dist, startYc + a, startZc + b);
                            }
                        }
                    }
                    player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                }
                break;
        }
        return true;
    }
}