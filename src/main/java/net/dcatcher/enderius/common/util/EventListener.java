package net.dcatcher.enderius.common.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.dcatcher.enderius.common.items.ItemEnderSlime;
import net.dcatcher.enderius.common.items.ItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

/**
 * Copyright: DCatcher
 */
public class EventListener {

    @SubscribeEvent
    public void onEnderDragonDeath(LivingDeathEvent event){
        if(event.entity instanceof EntityDragon){
            if(!event.entity.worldObj.isRemote)
                event.entity.dropItem(ItemHandler.enderStar, 1);
        }
    }

    @SubscribeEvent
    public void onPlayerDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event){
        if(event.fromDim == 0 && event.toDim == 1){
            for(Object entity : event.player.worldObj.loadedEntityList){
                Entity current = (Entity)entity;
                if(entity instanceof EntityDragon){
                    return;
                }
            }
            event.player.worldObj.spawnEntityInWorld(new EntityDragon(event.player.worldObj));
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(EntityInteractEvent event){
        if(event.entityPlayer.getCurrentEquippedItem() != null &&
                event.entityPlayer.getCurrentEquippedItem().getItem() instanceof ItemEnderSlime){
            ItemStack slime =  event.entityPlayer.getCurrentEquippedItem();
            slime.;
            System.out.println("Getting information from this mob!");
        }
    }
}
