package net.dcatcher.enderius.common.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Copyright: DCatcher
 */
public class ContainerRepellent extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return false;
    }
}
