package net.dcatcher.modjam4.common.player;

import net.dcatcher.modjam4.ModJam4;
import net.dcatcher.modjam4.common.network.PacketSync;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
        levelBow = levelSword = 1;
    }

    public static DCPlayerProperties getProps(Entity player){
        return (DCPlayerProperties)player.getExtendedProperties(IDENTIFIER);
    }

    public static void register(EntityPlayer player){
        player.registerExtendedProperties(IDENTIFIER, new DCPlayerProperties(player));
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
    public void init(Entity entity, World world) {}

    public void addXp(int type, int ammout){
        switch(type){

        }

        sync();
    }

    public int getLevelBow() {
        return levelBow;
    }

    public void setLevelBow(int levelBow) {
        this.levelBow = levelBow;
        sync();
    }

    public int getLevelSword() {
        return levelSword;
    }

    public void setLevelSword(int levelSword) {
        this.levelSword = levelSword;
        sync();
    }

    public int getXpBow() {
        return xpBow;
    }

    public void setXpBow(int xpBow) {
        this.xpBow = xpBow;
        sync();
    }

    public int getXpSword() {
        return xpSword;
    }

    public void setXpSword(int xpSword) {
        this.xpSword = xpSword;
        sync();
    }

    public void sync(){
        ModJam4.packetHandler.sendTo(new PacketSync(player), (EntityPlayerMP)player);
    }
}
