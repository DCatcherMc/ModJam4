package net.dcatcher.modjam4.common.player;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import net.dcatcher.modjam4.ModJam4;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.omg.CORBA.portable.IDLEntity;

/**
 * Copyright: DCatcher
 */
public class PlayerTracker {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onEntityConstructing(EntityEvent.EntityConstructing event){
        if(event.entity instanceof EntityPlayer){
            if(DCPlayerProperties.getProps(event.entity) == null){
                event.entity.registerExtendedProperties(DCPlayerProperties.IDENTIFIER, new DCPlayerProperties((EntityPlayer)event.entity));
                System.out.println("PLAYER CONSTRUCTING " + DCPlayerProperties.getProps(event.entity).getLevelBow());
            }
        }
    }

    @SubscribeEvent
    public void onLivingEntityJoinWorld(EntityJoinWorldEvent event){
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer){
            DCPlayerProperties.loadData((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event){
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer){
            NBTTagCompound playerData = new NBTTagCompound();
            DCPlayerProperties.getProps(event.entity).saveNBTData(playerData);
            ModJam4.proxy.saveLevels(((EntityPlayer) event.entity).getDisplayName(), playerData);
            System.out.println("PLAYER DIED " + DCPlayerProperties.getProps(event.entity).getLevelBow());
        }
    }

    @SubscribeEvent
    public void onPlayerChangedDimension(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
        DCPlayerProperties.getProps(event.player).sync();
        System.out.println("PLAYER CHANGED DIMENSION " + DCPlayerProperties.getProps(event.player).getLevelBow());
    }

    @SubscribeEvent
    public void onPlayerRespawn(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event) {
        DCPlayerProperties.getProps(event.player).sync();
        System.out.println("PLAYER RESPAWNED " + DCPlayerProperties.getProps(event.player).getLevelBow());
    }

}
