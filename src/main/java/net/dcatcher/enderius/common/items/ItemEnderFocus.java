package net.dcatcher.enderius.common.items;

import net.dcatcher.enderius.Enderius;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
        int abc = 1;
        if(player.isSneaking())
            abc = 1;
        else
            abc = 10;

        switch(side){
            case 0:
                int startXa0 = x - 1;
                int startZa0 = z -1 ;
                for(int dist = 0; dist < abc; dist++){
                    for(int a = 0; a < 3; a++){
                        for(int b = 0; b < 3; b++){
                            Block current = world.getBlock(startXa0 + a, y+dist, startZa0 + b);
                            if((!Enderius.blockBlacklist.contains(current.getUnlocalizedName())  && player.inventory.hasItem(ItemHandler.enderiumFuel))){
                                if(!world.isRemote && current != Blocks.bedrock)
                                    world.func_147480_a(startXa0 + a, y+dist, startZa0 + b, true); //Break Block!
                            }
                        }
                    }
                    player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                }
                break;
            case 1:
                int startXa1 = x - 1;
                int startZa1 = z -1 ;
                for(int dist = 0; dist < abc; dist++){
                    for(int a = 0; a < 3; a++){
                        for(int b = 0; b < 3; b++){
                            Block current = world.getBlock(startXa1 + a, y-dist, startZa1 + b);
                            if(!Enderius.blockBlacklist.contains(current.getUnlocalizedName()) && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                                if(!world.isRemote && current != Blocks.bedrock)
                                    world.func_147480_a(startXa1 + a, y - dist, startZa1 + b, true);
                            }
                        }
                    }
                    player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                }
                break;

            case 2:
                int startXb = x - 1;
                int startYb = y - 1;
                for(int dist = 0; dist < abc; dist++){
                    for(int a = 0; a < 3; a++){
                        for(int b = 0; b < 3; b++){
                            Block current = world.getBlock(startXb + a, startYb + b, z +dist);
                            if(!Enderius.blockBlacklist.contains(current.getUnlocalizedName()) && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                                if(!world.isRemote && current != Blocks.bedrock)
                                    world.func_147480_a(startXb + a, startYb + b, z +dist, true);
                            }
                        }
                    }
                    player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                }
                break;
            case 3:
                int startXb1 = x - 1;
                int startYb1 = y - 1;
                for(int dist = 0; dist < abc; dist++){
                    for(int a = 0; a < 3; a++){
                        for(int b = 0; b < 3; b++){
                            Block current = world.getBlock(startXb1 + a, startYb1 + b, z-dist);
                            if(!Enderius.blockBlacklist.contains(current.getUnlocalizedName()) && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                                if(!world.isRemote && current != Blocks.bedrock)
                                    world.func_147480_a(startXb1 + a, startYb1 + b, z - dist, true);
                            }
                        }
                    }
                    player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                }
                break;

            case 4:
                int startZc = z - 1;
                int startYc = y - 1;
                for(int dist = 0; dist < abc; dist++){
                    for(int a = 0; a < 3; a++){
                        for(int b = 0; b < 3; b++){
                            Block current = world.getBlock(x+dist, startYc+a, startZc + b);
                            if(!Enderius.blockBlacklist.contains(current.getUnlocalizedName()) && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                                if(!world.isRemote && current != Blocks.bedrock)
                                    world.func_147480_a(x + dist, startYc + a, startZc + b, true);
                            }
                        }
                    }
                    player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                }
                break;
            case 5:
                int startZc1 = z - 1;
                int startYc1 = y - 1;
                for(int dist = 0; dist < abc; dist++){
                    for(int a = 0; a < 3; a++){
                        for(int b = 0; b < 3; b++){
                            Block current = world.getBlock(x-dist, startYc1+a, startZc1 + b);
                            if(!Enderius.blockBlacklist.contains(current.getUnlocalizedName()) && player.inventory.hasItem(ItemHandler.enderiumFuel)){
                                if(!world.isRemote && current != Blocks.bedrock)
                                    world.func_147480_a(x - dist, startYc1 + a, startZc1 + b, true);
                            }
                        }
                    }
                    player.inventory.consumeInventoryItem(ItemHandler.enderiumFuel);
                }
                break;
        }
        player.playSound("mob.endermen.portal", 1f, 1f);
        return true;
    }
}