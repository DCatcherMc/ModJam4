package net.dcatcher.modjam4.common.network;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

public class PacketHandler extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket> {



    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {

    }
}
