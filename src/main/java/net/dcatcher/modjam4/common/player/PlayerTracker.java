package net.dcatcher.modjam4.common.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * Copyright: DCatcher
 */
public class PlayerTracker {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.EntityConstructing event){
        if(event.entity instanceof EntityPlayer && DCPlayerProperties.getProps(event.entity) != null){
            DCPlayerProperties.register((EntityPlayer)event.entity);
        }
    }

    public void onPlayerDeath(LivingDeathEvent event){
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer){
            
        }
    }
}
