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

            if(cooldown == 0 && entityID != 0){
                cooldown = 20;

                /**
                Entity createdEntity = EntityList.createEntityByID(entityID, worldObj);
                if(!(createdEntity instanceof EntityLivingBase)){
                    System.out.println("error,not entitylivingbase");
                    return;
                }

                EntityLivingBase ent = (EntityLivingBase)createdEntity;

                ent.readEntityFromNBT((NBTTagCompound) data.copy());
                */

                EntitySheep sheep = new EntitySheep(worldObj);
                int x = xCoord + (rand.nextInt(10)-5);
                int z = zCoord + (rand.nextInt(10)-5);
                int y = yCoord + 1;

                sheep.setLocationAndAngles(x, y, z, 0f, 0f);
                worldObj.spawnEntityInWorld(sheep);
            }
    }

    public void setData(NBTTagCompound tag, int id){
        this.data = tag;
        this.entityID = id;
    }
}
