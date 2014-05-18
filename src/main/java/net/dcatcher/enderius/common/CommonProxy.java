package net.dcatcher.enderius.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.dcatcher.enderius.client.gui.GuiRepellent;
import net.dcatcher.enderius.common.tileentities.ContainerRepellent;
import net.dcatcher.enderius.common.tileentities.TileEntityRepellent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright: DCatcher
 */
public class CommonProxy implements IGuiHandler{

    public static final Map<String, NBTTagCompound> deathMap = new HashMap<String, NBTTagCompound>();

    public void saveLevels(String name, NBTTagCompound tag){
        deathMap.put(name, tag);
    }

    public NBTTagCompound getLevels(String name){
        return deathMap.remove(name);
    }

    public void registerRenders(){}



    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te instanceof TileEntityRepellent)
            return new GuiRepellent((TileEntityRepellent) te);

        return null;
    }
}
