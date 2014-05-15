package net.dcatcher.modjam4.common.player;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.dcatcher.modjam4.ModJam4;
import net.dcatcher.modjam4.common.network.PacketSync;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import sun.net.www.content.text.plain;

/**
 * Copyright: DCatcher
 */
public class PlayerTracker {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(DCPlayerProperties.IDENTIFIER) == null){
            event.entity.registerExtendedProperties(DCPlayerProperties.IDENTIFIER, new DCPlayerProperties((EntityPlayer) event.entity));
        }
    }

    @SubscribeEvent
    public void onLivingEntityDeath(LivingDeathEvent event){
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer){
            NBTTagCompound playerData = new NBTTagCompound();
            event.entity.getExtendedProperties(DCPlayerProperties.IDENTIFIER).saveNBTData(playerData);
            ModJam4.proxy.saveLevels(((EntityPlayer) event.entity).getDisplayName(), playerData);

        }
    }

    @SubscribeEvent
    public void onLivingEntityJoinWorld(EntityJoinWorldEvent event){
        DCPlayerProperties data = DCPlayerProperties.getProps(event.entity);
        NBTTagCompound savedData = ModJam4.proxy.getLevels(((EntityPlayer) event.entity).getDisplayName()); 
        if(savedData != null){
            data.loadNBTData(savedData);
        }
        ModJam4.packetHandler.sendTo(new PacketSync((EntityPlayer) event.entity), (EntityPlayerMP)event.entity);
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        DCPlayerProperties.getProps(event.player).sync();
    }

}
