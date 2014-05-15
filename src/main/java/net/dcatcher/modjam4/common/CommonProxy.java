package net.dcatcher.modjam4.common;

import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright: DCatcher
 */
public class CommonProxy {

    public static final Map<String, NBTTagCompound> deathMap = new HashMap<String, NBTTagCompound>();

    public void saveLevels(String name, NBTTagCompound tag){
        deathMap.put(name, tag);
    }

    public NBTTagCompound getLevels(String name){
        return deathMap.remove(name);
    }

    public void registerRenders(){}
}
