package net.dcatcher.enderius.common.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dcatcher.enderius.common.player.DCPlayerProperties;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Copyright: DCatcher
 */
public class PacketSync extends AbstractPacket {

    private DCPlayerProperties props;
    private int levelBow, levelSword, xpBow, xpSword;

    public PacketSync(){
        //Yay for an empty one... apparently it's necessary?
    }

    public PacketSync(int levelBow, int levelSword, int xpBow, int xpSword){
        this.levelBow = levelBow;
        this.levelSword = levelSword;
        this.xpBow = xpBow;
        this.xpSword = xpSword;
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
        levelBow = buffer.readInt();
        levelSword = buffer.readInt();
        xpBow = buffer.readInt();
        xpSword = buffer.readInt();
    }

    @Override
    public void handleClient(EntityPlayer player) {
        DCPlayerProperties props = DCPlayerProperties.getProps(player);
        props.levelBow = levelBow;
        props.levelSword = levelSword;
        props.xpBow = xpBow;
        props.xpSword = xpSword;
    }

    @Override
    public void handleServer(EntityPlayer player) {

    }
}
