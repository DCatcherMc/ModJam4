package net.dcatcher.enderius.common.tileentities;

import net.minecraft.entity.player.EntityPlayer;
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

    protected List<String> allowedUsers = new ArrayList<String>();
    public Random rand = new Random();
    public int locX, locY, locZ;

    public TileEntityRepellent(){

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("noOfUsers", allowedUsers.size());
        for(int i = 0; i < allowedUsers.size(); i++){
            compound.setString("allowedUser_" + i, allowedUsers.get(i));
        }
        compound.setInteger("locX", locX);
        compound.setInteger("locY", locY);
        compound.setInteger("locZ", locZ);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        allowedUsers = new ArrayList<String>();
        for(int i = 0; i < compound.getInteger("noOfUsers"); i++){
            allowedUsers.add(compound.getString("allowedUser_"+i));
        }
        this.locX = compound.getInteger("locX");
        this.locY = compound.getInteger("locY");
        this.locZ = compound.getInteger("locZ");
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(worldObj.isBlockIndirectlyGettingPowered(xCoord,  yCoord, zCoord)){
            worldObj.spawnParticle("portal", xCoord + rand.nextInt(2)-1, yCoord + rand.nextInt(2)-1, zCoord + rand.nextInt(2)-1, 0d, 0d, 0d);
            List entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(10, 10, 10));
            for(Object entity : entities){
                EntityPlayer player = (EntityPlayer) entity;
                if(!getWhitelist().contains(player.getDisplayName())){
                    player.setLocationAndAngles(locX, locY, locZ, 0f, 0f);
                }
            }
        }
    }

    public void addPlayerToWhitelist(String username){
        if(allowedUsers.isEmpty())
            allowedUsers = new ArrayList<String>();
        System.out.println(allowedUsers.add(username));
    }

    public void removePlayerFromWhitelist(String username){
        allowedUsers.remove(username);
    }

    public List<String> getWhitelist() {
        return allowedUsers;
    }

    public int[] getLocs() {
        return new int[]{locX, locY, locZ};
    }
}
