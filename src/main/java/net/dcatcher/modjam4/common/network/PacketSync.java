package net.dcatcher.modjam4.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dcatcher.modjam4.common.player.DCPlayerProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Copyright: DCatcher
 */
public class PacketSync extends AbstractPacket {

    private DCPlayerProperties props;
    private int levelBow;
    private int levelSword;
    private int xpBow;
    private int xpSword;

    public PacketSync(){
        //Yay for an empty one... apparently it's necessary?
    }

    public PacketSync(EntityPlayer player){
        props = DCPlayerProperties.getProps(player);
    }
    @Override
    public void encode(ChannelHandlerContext context, ByteBuf buffer) {
        buffer.writeInt(props.getLevelBow());
        buffer.writeInt(props.getLevelSword());
        buffer.writeInt(props.getXpBow());
        buffer.writeInt(props.getXpSword());
    }

    @Override
    public void decode(ChannelHandlerContext context, ByteBuf buffer) {
        this.levelBow = buffer.readInt();
        this.levelSword = buffer.readInt();
        this.xpBow = buffer.readInt();
        this.xpSword = buffer.readInt();
    }

    @Override
    public void handleClient(EntityPlayer player) {
        DCPlayerProperties props = DCPlayerProperties.getProps(player);
        props.setLevelBow(levelBow);
        props.setLevelSword(levelSword);
        props.setXpBow(xpBow);
        props.setXpSword(xpSword);
    }

    @Override
    public void handleServer(EntityPlayer player) {

    }
}
