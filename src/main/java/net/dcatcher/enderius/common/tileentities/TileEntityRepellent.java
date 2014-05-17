package net.dcatcher.enderius.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: DCatcher
 */
public class TileEntityRepellent extends TileEntity {

    List<String> allowedUsers = new ArrayList<String>();

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
            allowedUsers.add("alloweduser"+i);
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();


    }
}
