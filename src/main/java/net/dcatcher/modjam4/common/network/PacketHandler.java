package net.dcatcher.modjam4.common.network;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;

import java.util.*;

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
        ByteBuf payload = msg.payload();
        byte discriminator = payload.readByte();

        Class<? extends AbstractPacket> clazz = this.packets.get(discriminator);
        if(clazz == null)
            throw new NullPointerException("No packet registered for discriminator: " + discriminator);
        AbstractPacket pkt = clazz.newInstance();
        pkt.decode(ctx, payload.slice());

        EntityPlayer player;
        switch(FMLCommonHandler.instance().getEffectiveSide()){
            case CLIENT:
                player = this.getClientPlayer();
                pkt.handleClient(player);
                break;
            case SERVER:
                INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
                player = ((NetHandlerPlayServer) netHandler).playerEntity;
                pkt.handleServer(player);
                break;
            default:
        }
        out.add(pkt);
    }


    public void init(){
        this.channels = NetworkRegistry.INSTANCE.newChannel("DCatcherModJam", this);
        this.registerPacket(PacketSync.class);
    }

    public void postInit(){
        if(this.isPostInitialised)
            return;

        this.isPostInitialised = true;
        Collections.sort(this.packets, new Comparator<Class<? extends AbstractPacket>> (){

            @Override
            public int compare(Class<? extends AbstractPacket> clazz1, Class<? extends AbstractPacket> clazz2){
                int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
                if(com == 0)
                    com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
                return com;
            }
        });
    }

    @SideOnly(Side.CLIENT)
    private EntityPlayer getClientPlayer(){
        return Minecraft.getMinecraft().thePlayer;
    }

    public void sendTo(AbstractPacket packet, EntityPlayerMP player) {
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        this.channels.get(Side.SERVER).writeAndFlush(packet);
    }

    public void sendToServer(AbstractPacket packet){
        this.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        this.channels.get(Side.CLIENT).writeAndFlush(packet);
    }

}
