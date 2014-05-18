package net.dcatcher.enderius.common.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Copyright: DCatcher
 */
public class ContainerRepellent extends Container {

    TileEntityRepulsor tileEntity;

    public ContainerRepellent(TileEntityRepulsor te){
        this.tileEntity = te;
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }
}
