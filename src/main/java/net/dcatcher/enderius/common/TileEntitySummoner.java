package net.dcatcher.enderius.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

/**
 * Copyright: DCatcher
 */
public class TileEntitySummoner extends TileEntity {

    public int entityID;
    public NBTTagCompound data;
    public Random rand = new Random();
    public int cooldown = 100;

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

            if(cooldown == 0){
                cooldown = 100;
                if(!worldObj.isRemote){
                    Entity ent = EntityList.createEntityByID(entityID, worldObj);
                    int x = xCoord + (rand.nextInt(10)-5);
                    int z = zCoord + (rand.nextInt(10)-5);
                    int y = yCoord + 1;
                    ent.setLocationAndAngles(x, y, z, 0f, 0f);
                    worldObj.spawnEntityInWorld(ent);
                }
            }
    }

    public void setData(int id){
        this.entityID = id;
    }
}
