package net.dcatcher.modjam4.common.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

//Helped by the tutorial.. Had no idea how to do the 1.7 packets!

public class PacketHandler extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket> {

    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private LinkedList<Class<? extends AbstractPacket>> packets = new LinkedList<Class<? extends AbstractPacket>>();
    private boolean isPostInitialised = false;

    public boolean registerPacket(Class<? extends AbstractPacket> clazz){
        if(this.packets.size() > 256){
            System.out.println("Error: too many packets");
            return false;
        }

        if(this.packets.contains(clazz)){
            System.out.println("Error: already registered");
            return false;
        }

        if(this.isPostInitialised){
            return false;
        }

        this.packets.add(clazz);
        return true;
    }


    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        Class<? extends AbstractPacket> clazz = msg.getClass();
        if(!this.packets.contains(clazz)){
            throw new NullPointerException("No packet Registered for: " + clazz.getCanonicalName());
        }

        byte discrimantor = (byte)this.packets.indexOf(clazz);
        buffer.writeByte(discrimantor);
        msg.encode(ctx, buffer);
        FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
        out.add(proxyPacket);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
        
    }
}
