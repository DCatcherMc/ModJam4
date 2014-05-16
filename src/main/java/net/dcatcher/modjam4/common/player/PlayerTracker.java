package net.dcatcher.modjam4.common.player;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.dcatcher.modjam4.ModJam4;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

/**
 * Copyright: DCatcher
 */
public class PlayerTracker {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onEntityConstructing(EntityEvent.EntityConstructing event){
        if (event.entity instanceof EntityPlayer && DCPlayerProperties.getProps(event.entity) == null){
            System.out.println("Initialising PlayerData");
            DCPlayerProperties.register((EntityPlayer)event.entity);
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






        /**Entity entity = event.entity;

        if (entity instanceof EntityLivingBase){
            EntityLivingBase living = (EntityLivingBase) entity;
            if (living instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer) living;
                NBTTagCompound playerData = ModJam4.proxy.getLevels(player.getDisplayName());
                if (playerData != null){
                    // player.getExtendedProperties(DCPlayerProperties.IDENTIFIER).loadNBTData(playerData);
                    // DCPlayerProperties.getProps(event.entity).sync();
                }
            }
        }

         */
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        // DCPlayerProperties.getProps(event.player).sync();
    }

}
