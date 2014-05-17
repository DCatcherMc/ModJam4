package net.dcatcher.enderius.common;

import net.dcatcher.enderius.Enderius;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.List;
import java.util.Random;

/**
 * Copyright: DCatcher
 */
public class TileEntitySummoner extends TileEntity {

    public String entityID;
    public NBTTagCompound data;
    public Random rand = new Random();
    public int cooldown = 100;

    public TileEntitySummoner(){
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if(entityID != null){
            compound.setString("entityID", entityID);
            System.out.println("Saving EntityID: " + entityID);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.entityID = compound.getString("entityID");
        System.out.println("Loaded EntityID: " + entityID);
    }

    @Override
    public void updateEntity() {

        if(!worldObj.isRemote){
            boolean shouldSpawn = checkNoOfMobs();
            if(shouldSpawn){
                cooldown--;
            }
            if(cooldown == 0 && entityID != null && worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.dragon_egg){
                cooldown = 20;
                System.out.println("Finished Cooooldown");
                Entity ent = EntityList.createEntityByName(entityID, worldObj);
                int x = xCoord + (rand.nextInt(6)-3);
                int z = zCoord + (rand.nextInt(6)-3);
                int y = yCoord + 1;
                ent.setLocationAndAngles(x, y, z, 0f, 0f);
                if(entityID.equals("Skeleton")){
                    ent.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
                }
                if(shouldSpawn){
                    worldObj.spawnEntityInWorld(ent);
                    System.out.println("Spawning a mob of id: " + entityID);
                }
            }
        }
        super.updateEntity();
    }

    public void setData(String id){
        this.entityID = id;
    }

    public boolean checkBlacklist(Entity toCheck){
        List<String> blacklist = Enderius.spawnerBlacklist;

        if(blacklist != null){
            for(String current : blacklist){
                if(EntityList.getEntityString(toCheck).equalsIgnoreCase(current)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkNoOfMobs(){
        List entities = worldObj.getEntitiesWithinAABB(EntityLiving.class, getRenderBoundingBox().expand(4, 4, 4));
        if(entities.isEmpty())
            return true;
        int total = 0;
        for(Object ent : entities){
            if(ent instanceof EntityLiving){
                total++;
            }
        }

        return total < 10;
    }
}
