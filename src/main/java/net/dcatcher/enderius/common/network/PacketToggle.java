package net.dcatcher.enderius.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import net.dcatcher.enderius.common.tileentities.TileEntityRepellent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

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
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
    }

    @Override
    public void decode(ChannelHandlerContext context, ByteBuf buffer) {
        this.username = ByteBufUtils.readUTF8String(buffer);
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    @Override
    public void handleClient(EntityPlayer player) {
        World worldObj = player.worldObj;
        TileEntityRepellent repel = (TileEntityRepellent) worldObj.getTileEntity(x, y, z);
        if(repel != null){
            if(repel.getWhitelist().contains(username))
                repel.getWhitelist().remove(username);
            else
                repel.getWhitelist().add(username);
        }
    }

    @Override
    public void handleServer(EntityPlayer player) {
        World worldObj = player.worldObj;
        TileEntityRepellent repel = (TileEntityRepellent) worldObj.getTileEntity(x, y, z);
        if(repel.getWhitelist().contains(username))
            repel.getWhitelist().remove(username);
        else
            repel.getWhitelist().add(username);


        player.getEntityWorld().markBlockForUpdate(x, y, z);
        player.getEntityWorld().markTileEntityChunkModified(x, y, z, repel);
    }
}
