package net.dcatcher.modjam4.common.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dcatcher.modjam4.common.player.DCPlayerProperties;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Copyright: DCatcher
 */
public class PacketSync extends AbstractPacket {

    public PacketSync(){
        //Yay for an empty one... apparently it's necessary?
    }

    private int levelBow,
                levelSword,
                xpBow,
                xpSword;

    public PacketSync(int levelBow, int levelSword, int xpBow, int xpSword){
        this.levelBow = levelBow;
        this.levelSword = levelSword;
        this.xpBow = xpBow;
        this.xpSword = xpSword;
    }
    @Override
    public void encode(ChannelHandlerContext context, ByteBuf buffer) {
        buffer.writeInt(levelBow);
        buffer.writeInt(levelSword);
        buffer.writeInt(xpBow);
        buffer.writeInt(xpSword);
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
        DCPlayerProperties props = DCPlayerProperties.getProps(player);
        props.setLevelBow(this.levelBow);
        props.setLevelSword(this.levelSword);
        props.setXpBow(this.xpBow);
        props.setXpSword(this.xpSword);
    }
}
