package net.dcatcher.enderius.common.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.dcatcher.enderius.common.EntityEnderSlime;
import net.dcatcher.enderius.common.items.ItemEnderSlime;
import net.dcatcher.enderius.common.items.ItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.event.ClickEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;

/**
 * Copyright: DCatcher
 */
public class EventListener {

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event){
        if(event.entity instanceof EntityDragon){
            if(!event.entity.worldObj.isRemote)
                event.entity.dropItem(ItemHandler.enderStar, 1);
        }else if(event.entity instanceof EntityEnderman){
            double x = event.entity.posX;
            double y = event.entity.posY;
            double z = event.entity.posZ;

            EntityEnderSlime slime = new EntityEnderSlime(event.entity.worldObj);
            slime.posX = x;
            slime.posY = y;
            slime.posZ = z;
            event.entity.worldObj.spawnEntityInWorld(slime);
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
    public void onItemTooltip(ItemTooltipEvent event){
        if(event.entityPlayer == null)
            return;
    }

    @SubscribeEvent
    public void onPlayerInteract(EntityInteractEvent event){
        if(event.entityPlayer.getCurrentEquippedItem() != null &&
                event.entityPlayer.getCurrentEquippedItem().getItem() == ItemHandler.dnaSyringe){
            ItemStack syringe = event.entityPlayer.getCurrentEquippedItem();
            NBTTagCompound nbt = new NBTTagCompound();
            syringe.writeToNBT(nbt);
            syringe.
            nbt = addNBTData(event.target, nbt);
            syringe.readFromNBT(nbt);
            syringe.getTooltip(event.entityPlayer, false).add("Entity: " + EntityList.getEntityString(event.target));
            System.out.println("Getting information from this mob!" + nbt.getString("entityID"));

        }
    }

    public NBTTagCompound addNBTData(Entity entity, NBTTagCompound nbt){
        nbt.setString("entityID", EntityList.getEntityString(entity));
        return nbt;
    }
}
