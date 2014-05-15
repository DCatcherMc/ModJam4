package net.dcatcher.modjam4.common.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Copyright: DCatcher
 */
public class DCPlayerProperties implements IExtendedEntityProperties {

    private int levelBow, levelSword;

    EntityPlayer player;

    private int xpBow, xpSword;

    public DCPlayerProperties(EntityPlayer player){
        this.player = player;
        levelBow = 1;
        levelSword = 1;
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

    }

    @Override
    public void init(Entity entity, World world) {

    }

    public void addXp(String type){

    }
}
