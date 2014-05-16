package net.dcatcher.modjam4.common.player;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.dcatcher.modjam4.ModJam4;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

/**
 * Copyright: DCatcher
 */
public class PlayerTracker {

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event){
        if (event.entity instanceof EntityClientPlayerMP && event.entity.getExtendedProperties(DCPlayerProperties.IDENTIFIER) == null){
            System.out.println("Initialising PlayerData" );
            event.entity.registerExtendedProperties(DCPlayerProperties.IDENTIFIER, new DCPlayerProperties((EntityPlayer) event.entity));
            System.out.println("+_+ " + DCPlayerProperties.getProps(event.entity).getLevelBow());
        }
    }
    /**
    @SubscribeEvent
    public void onLivingEntityDeath(LivingDeathEvent event){
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer){
            NBTTagCompound playerData = new NBTTagCompound();
            event.entity.getExtendedProperties(DCPlayerProperties.IDENTIFIER).saveNBTData(playerData);
            ModJam4.proxy.saveLevels(((EntityPlayer) event.entity).getDisplayName(), playerData);

        }
    }
*/

    @SubscribeEvent
    public void onLivingEntityJoinWorld(EntityJoinWorldEvent event){

        Entity entity = event.entity;

        if (entity instanceof EntityLivingBase){
            EntityLivingBase living = (EntityLivingBase) entity;
            if (living instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer) living;
                NBTTagCompound playerData = ModJam4.proxy.getLevels(player.getDisplayName());
                if (playerData != null){
                    DCPlayerProperties.getProps(event.entity).sync();
                    System.out.println("JOINED WORLD AND PROXY WAS NOT NULL!");
                    player.getExtendedProperties(DCPlayerProperties.IDENTIFIER).loadNBTData(playerData);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        //DCPlayerProperties.getProps(event.player).sync();
        //System.out.println("^THAT PACKET WAS A PLAYERLOGIN ONE REALLY :P");
    }

}
