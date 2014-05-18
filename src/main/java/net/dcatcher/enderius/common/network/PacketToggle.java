package net.dcatcher.enderius.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

import java.io.BufferedInputStream;
import java.nio.ByteBuffer;

/**
 * Copyright: DCatcher
 */
public class PacketToggle extends AbstractPacket {

    private int x, y, z;
    private String username;

    public PacketToggle(){

    }

    public PacketToggle(String username, int x, int y, int z){
        this.username = username;
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public void encode(ChannelHandlerContext context, ByteBuf buffer) {
        ByteBufUtils.writeUTF8String(buffer, username);
    }

    @Override
    public void decode(ChannelHandlerContext context, ByteBuf buffer) {
        username = ByteBufUtils.readUTF8String(buffer);
    }

    @Override
    public void handleClient(EntityPlayer player) {

    }

    @Override
    public void handleServer(EntityPlayer player) {

    }
}
