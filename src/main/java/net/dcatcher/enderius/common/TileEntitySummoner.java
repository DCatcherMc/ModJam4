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
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    public void updateEntity() {
        super.updateEntity();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        cooldown--;
        if(cooldown == 0 && entityID != null){
            cooldown = 100;
            //System.out.println("Finished Cooooldown");
            if(!worldObj.isRemote){
                Entity ent = EntityList.createEntityByName(entityID, worldObj);
                int x = xCoord + (rand.nextInt(10)-5);
                int z = zCoord + (rand.nextInt(10)-5);
                int y = yCoord + 1;
                ent.setLocationAndAngles(x, y, z, 0f, 0f);
                System.out.println("Spawning a mob of id: " + entityID);
                if(entityID.equals("Skeleton")){
                    ent.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
                }
                //if(!checkBlacklist(ent))
                    worldObj.spawnEntityInWorld(ent);
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
}
