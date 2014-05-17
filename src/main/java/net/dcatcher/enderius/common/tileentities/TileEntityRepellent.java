package net.dcatcher.enderius.common.tileentities;

import net.minecraft.entity.item.EntityItem;
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

    public List<String> allowedUsers = new ArrayList<String>();
    public Random rand = new Random();
    public int locX, locY, locZ;

    public TileEntityRepellent(){

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("numberSaved", blockedUsers.size());
        for(int i = 0; i < blockedUsers.size(); i++){
            compound.setString("alloweduser"+i, allowedUsers.get(i));
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        int total = compound.getInteger("numberSaved");
        System.out.println(total);
        for(int i = 0; i < total; i++){
            allowedUsers.add(compound.getString("alloweduser"+i));
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(worldObj.isBlockIndirectlyGettingPowered(xCoord,  yCoord, zCoord)){
            worldObj.spawnParticle("portal", xCoord + rand.nextInt(2)-1, yCoord + rand.nextInt(2)-1, zCoord + rand.nextInt(2)-1, 0d, 0d, 0d);
            List entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(10, 10, 10));

            for(Object entity : entities){
                EntityPlayer player = (EntityPlayer) entity;
                if(!allowedUsers.contains(player.getDisplayName())){
                    System.out.println(player.getDisplayName() + " is not in the list");
                    for(String str: allowedUsers){System.out.println(str);}
                    player.setLocationAndAngles(xCoord+20, yCoord + 1, zCoord+20, 0f, 0f);
                }
            }
        }
    }

    public void addPlayerToWhitelist(String username){
        if(!allowedUsers.contains(username))
            allowedUsers.add(username);
    }

    public void setLocationToTPOut(int x, int y, int z){
        this.locX = x;
        this.locY = y;
        this.locZ = z;
    }
}
