package net.dcatcher.enderius.common.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractPacket {

    public abstract void encode(ChannelHandlerContext context, ByteBuf buffer);

    public abstract void decode(ChannelHandlerContext context, ByteBuf buffer);

    public abstract void handleClient(EntityPlayer player);

    public abstract void handleServer(EntityPlayer player);
}
