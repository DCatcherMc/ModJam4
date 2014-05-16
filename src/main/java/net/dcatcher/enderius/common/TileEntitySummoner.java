package net.dcatcher.enderius.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

/**
 * Copyright: DCatcher
 */
public class TileEntitySummoner extends TileEntity {

    public String entityID;
    public Random rand = new Random();
    public int cooldown = 20;

    public TileEntitySummoner(){

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    public void updateEntity() {
        super.updateEntity();

            cooldown--;

            //if(cooldown == 0){
                cooldown = 20;

                Entity createdEntity = EntityList.createEntityByID(91, worldObj);
                createdEntity.posX = xCoord + (rand.nextInt(10)-5);
                createdEntity.posZ = zCoord + (rand.nextInt(10)-5);
                createdEntity.posY = yCoord + 1;
                createdEntity.forceSpawn = true;
            //}
    }
}
