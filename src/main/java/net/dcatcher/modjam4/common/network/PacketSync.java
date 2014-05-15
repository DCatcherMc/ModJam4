package net.dcatcher.modjam4.common.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dcatcher.modjam4.common.player.DCPlayerProperties;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Copyright: DCatcher
 */
public class PacketSync extends AbstractPacket {

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

    }

    @Override
    public void decode(ChannelHandlerContext context, ByteBuf buffer) {
        buffer.writeInt(levelBow);
        buffer.writeInt(levelSword);
        buffer.writeInt(xpBow);
        buffer.writeInt(xpSword);
    }

    @Override
    public void handleClient(EntityPlayer player) {
        DCPlayerProperties props = DCPlayerProperties.getProps(player);
        props.setLevelSword();
    }

    @Override
    public void handleServer(EntityPlayer player) {

    }
}
