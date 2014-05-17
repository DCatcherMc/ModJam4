package net.dcatcher.enderius.common.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;
import java.util.Random;

/**
 * Copyright: DCatcher
 */
public class TileEntityRepellent extends TileEntity {

    public String blockedUser;
    public Random rand = new Random();

    public TileEntityRepellent(){

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if(blockedUser != null)
            compound.setString("blockedUser", blockedUser);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        blockedUser = compound.getString("blockedUser");
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(worldObj.isBlockIndirectlyGettingPowered(xCoord,  yCoord, zCoord)){
            worldObj.spawnParticle("portal", xCoord + rand.nextInt(2)-1, yCoord + rand.nextInt(2)-1, zCoord + rand.nextInt(2)-1, 0d, 0d, 0d);
            List entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(10, 10, 10));
            for(Object entity : entities){
                EntityPlayer player = (EntityPlayer) entity;
                if(player.getDisplayName().equals(blockedUser)){
                    player.setLocationAndAngles(xCoord + 20, yCoord + 1, zCoord + 20, 0f, 0f);
                }
            }
        }
    }

    public void addPlayerToBlacklist(String username){
        this.blockedUser = username;
    }
}
