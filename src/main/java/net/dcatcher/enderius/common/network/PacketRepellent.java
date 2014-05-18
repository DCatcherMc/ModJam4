package net.dcatcher.enderius.common.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dcatcher.enderius.common.tileentities.TileEntityRepellent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Copyright: DCatcher
 */
public class PacketRepellent extends AbstractPacket {

    private int tx, ty, tz;

    private int ex, ey, ez;

    public PacketRepellent(){}

    public PacketRepellent(int x, int y, int z, int ex, int ey, int ez){
        this.tx = x;
        this.ty = y;
        this.tz = z;

        this.ex = ex;
        this.ey = ey;
        this.ez = ez;
    }
    @Override
    public void encode(ChannelHandlerContext context, ByteBuf buffer) {
        buffer.writeInt(tx);
        buffer.writeInt(ty);
        buffer.writeInt(tz);
    }

    @Override
    public void decode(ChannelHandlerContext context, ByteBuf buffer) {
        tx = buffer.readInt();
        ty = buffer.readInt();
        tz = buffer.readInt();
    }

    @Override
    public void handleClient(EntityPlayer player) {

    }

    @Override
    public void handleServer(EntityPlayer player) {
        World world = player.worldObj;

        TileEntityRepellent repel = (TileEntityRepellent) world.getTileEntity(ex, ey, ez);
        if(repel != null){
            repel.locX = tx;
            repel.locY = ty;
            repel.locZ = tz;
        }
        System.out.println("Recieved Packet!");
    }
}
