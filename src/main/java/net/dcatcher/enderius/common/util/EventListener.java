package net.dcatcher.enderius.common.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/**
 * Copyright: DCatcher
 */
public class EventListener {

    @SubscribeEvent
    public void onEnderDragonDeath(LivingDeathEvent event){
        if(event.entity instanceof EntityDragon){
            event.entity.dropItem()
        }
    }
}
