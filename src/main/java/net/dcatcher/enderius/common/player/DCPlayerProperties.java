package net.dcatcher.enderius.common.player;

import net.dcatcher.enderius.Enderius;
import net.dcatcher.enderius.common.network.PacketSync;
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

    private EntityPlayer player;

    public static final String IDENTIFIER = "DCMODJAM";

    public int levelSword, levelBow, xpSword, xpBow;

    public DCPlayerProperties(EntityPlayer player)
    {
        this.player = player;
        levelBow = levelSword = 1;
    }

    public static DCPlayerProperties getProps(Entity player){
        return (DCPlayerProperties) player.getExtendedProperties(IDENTIFIER);
    }

    public static void register(EntityPlayer player){
        player.registerExtendedProperties(DCPlayerProperties.IDENTIFIER, new DCPlayerProperties(player));
    }




    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("levelBow", getLevelBow());
        nbt.setInteger("levelSword", getLevelSword());
        nbt.setInteger("xpBow", getXpBow());
        nbt.setInteger("xpSword", getXpSword());
        compound.setTag(IDENTIFIER, nbt);

    }

    @Override
    public void loadNBTData(NBTTagCompound nbtTagCompound) {
        NBTTagCompound nbt = nbtTagCompound.getCompoundTag(IDENTIFIER);
        levelBow = nbt.getInteger("levelBow");
        levelSword = nbt.getInteger("levelSword");
        xpBow = nbt.getInteger("xpBow");
        xpSword = nbt.getInteger("xpSword");
    }

    @Override
    public void init(Entity entity, World world) {
        levelBow = levelSword = 1;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public void reset() {
        levelBow = levelSword = 1;
        xpBow = xpSword = 0;
        sync();
    }

    public final boolean sync(){
        if(!player.worldObj.isRemote){
            //wEnderius.packetPipeline.sendTo(new PacketSync(levelBow, levelSword, xpBow, xpSword), (EntityPlayerMP) player);
            System.out.println("SENDING A SYNC PACKET");
            return true;
        }
        return false;
    }

    public int getLevelBow() {
        return levelBow;
    }

    public int getLevelSword() {
        return levelSword;
    }

    public int getXpBow() {
        return xpBow;
    }

    public int getXpSword() {
        return xpSword;
    }
}
