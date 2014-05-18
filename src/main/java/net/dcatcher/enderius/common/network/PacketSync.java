package net.dcatcher.enderius.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dcatcher.enderius.common.tileentities.TileEntityRepulsor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Copyright: DCatcher
 */
public class PacketSync extends AbstractPacket {

    World world;
    NBTTagCompound nbt;
    private int x, y, z;
    public PacketSync(){
    }

    public PacketSync(World world,int x, int y, int z){
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public void encode(ChannelHandlerContext context, ByteBuf buffer) {
        nbt = new NBTTagCompound();
        TileEntityRepulsor te = (TileEntityRepulsor) world.getTileEntity(x, y, z);
        te.writeToNBT(nbt);
        ByteBufUtils.writeTag(buffer, nbt);
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
    }

    @Override
    public void decode(ChannelHandlerContext context, ByteBuf buffer) {
        nbt = ByteBufUtils.readTag(buffer);
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
    }

    @Override
    public void handleClient(EntityPlayer player) {
        TileEntity tile = player.worldObj.getTileEntity(x, y, z);
        if(tile != null){
            tile.readFromNBT(nbt);
            player.worldObj.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
            player.worldObj.markTileEntityChunkModified(tile.xCoord, tile.yCoord, tile.zCoord, tile);
        }
    }

    @Override
    public void handleServer(EntityPlayer player) {
        TileEntity tile = player.worldObj.getTileEntity(x, y, z);
        if(tile != null){
            tile.readFromNBT(nbt);
            player.worldObj.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
            player.worldObj.markTileEntityChunkModified(tile.xCoord, tile.yCoord, tile.zCoord, tile);
        }
    }
}
