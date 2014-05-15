package net.dcatcher.modjam4.common.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.dcatcher.modjam4.ModJam4;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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
            System.out.println("PLAYERLOGGEDIN");
        }
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event){
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer){
            NBTTagCompound playerData = new NBTTagCompound();
            DCPlayerProperties.getProps(event.entity).saveNBTData(playerData);
            System.out.println("PLAYER DIED");
        }
    }

    @SubscribeEvent
    public void onLivingEntityJoinWorld(EntityJoinWorldEvent event){
        Entity entity = event.entity;

        if(entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)entity;
            NBTTagCompound playerData = ModJam4.proxy.getLevels(player.getDisplayName());
            if(playerData != null){
                DCPlayerProperties.getProps(player).loadNBTData(playerData);
                DCPlayerProperties.getProps(player).sync();
                System.out.println("ENTITY JOINED WORLD");
            }
        }
    }

    @SubscribeEvent
    public void onPlayerLogin(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event){
        DCPlayerProperties.getProps(event.player).sync();
        System.out.println("PLAYER LOGGED IN");
    }

    @SubscribeEvent
    public void onPlayerChangedDimension(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
        DCPlayerProperties.getProps(event.player).sync();
        System.out.println("PLAYER CHANGED DIMENSION");
    }

    @SubscribeEvent
    public void onPlayerRespawn(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event) {
        DCPlayerProperties.getProps(event.player).sync();
        System.out.println("PLAYER RESPAWNED");
    }
}
