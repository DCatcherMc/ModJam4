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

    public static final String IDENTIFIER = "DCModJam_data";

    public DCPlayerProperties(EntityPlayer player){
        this.player = player;
        levelBow = 1;
        levelSword = 1;
    }

    public static DCPlayerProperties getProps(Entity player){
        return (DCPlayerProperties)player.getExtendedProperties(IDENTIFIER);
    }

    public static void register(EntityPlayer player){
        player.registerExtendedProperties(DCPlayerProperties.IDENTIFIER, new DCPlayerProperties(player));
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("levelSword", levelSword);
        nbt.setInteger("levelBow", levelBow);
        nbt.setInteger("xpSword", xpSword);
        nbt.setInteger("xpBow", xpBow);
        compound.setTag(IDENTIFIER, nbt);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound nbt = compound.getCompoundTag(IDENTIFIER);
        levelSword = nbt.getInteger("levelSword");
        levelBow = nbt.getInteger("levelBow");
        xpSword = nbt.getInteger("xpSword");
        xpBow = nbt.getInteger("xpBow");
    }

    @Override
    public void init(Entity entity, World world) {
        xpSword = xpBow = 0;
        levelBow = levelSword = 1;
    }

    public void addXp(int type, int ammout){
        switch(type){

        }
    }
}
