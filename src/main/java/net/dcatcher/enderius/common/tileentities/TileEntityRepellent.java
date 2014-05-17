package net.dcatcher.enderius.common.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright: DCatcher
 */
public class TileEntityRepellent extends TileEntity {

    List<String> allowedUsers = new ArrayList<String>();
    public Random rand = new Random();
    public int locX, locY, locZ;

    public TileEntityRepellent(){

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("numberSaved", allowedUsers.size());
        for(int i = 0; i < allowedUsers.size(); i++){
            compound.setString("alloweduser"+i, allowedUsers.get(i));
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        int total = compound.getInteger("numberSaved");
        for(int i = 0; i < total; i++){
            allowedUsers.add(compound.getString("alloweduser"+i));
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        List entities = worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, getRenderBoundingBox().expand(10, 10, 10));

        for(Object entity : entities){
            EntityPlayer player = (EntityPlayer) entity;
            if(!allowedUsers.contains(player.getDisplayName())){
                //player.setLocationAndAngles(locX, locY, locZ, 0f, 0f);
            }
        }
    }

    public void setLocationToTPOut(int x, int y, int z){
        this.locX = x;
        this.locY = y;
        this.locZ = z;
    }
}
