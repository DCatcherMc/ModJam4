package net.dcatcher.enderius.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dcatcher.enderius.Enderius;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

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
        super.readFromNBT(compound);
        if(entityID != null)
            compound.setString("entityID", entityID);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.entityID = compound.getString("entityID");
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public void updateEntity() {
        super.updateEntity();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

        boolean shouldSpawn = checkNoOfMobs();


        cooldown--;
        if(cooldown == 0 && entityID != null){
            cooldown = 20;
            //System.out.println("Finished Cooooldown");
            if(!worldObj.isRemote){
                Entity ent = EntityList.createEntityByName(entityID, worldObj);
                int x = xCoord + (rand.nextInt(8)-4);
                int z = zCoord + (rand.nextInt(8)-4);
                int y = yCoord + 1;
                ent.setLocationAndAngles(x, y, z, 0f, 0f);
                if(entityID.equals("Skeleton")){
                    ent.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
                }
                if(!checkBlacklist(ent) && shouldSpawn){
                    worldObj.spawnEntityInWorld(ent);
                    System.out.println("Spawning a mob of id: " + entityID);
                }
            }
        }
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
        List entities = worldObj.getEntitiesWithinAABB(Entity.class, getRenderBoundingBox().expand(9, 9, 9));
        if(entities.isEmpty())
            return true;
        int total = 0;
        for(Object ent : entities){
            if(ent instanceof Entity){
                total++;
            }
        }

        return total < 10;

    }
}
