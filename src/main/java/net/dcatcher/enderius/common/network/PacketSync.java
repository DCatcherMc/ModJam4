package net.dcatcher.enderius.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dcatcher.enderius.common.player.DCPlayerProperties;
import net.dcatcher.enderius.common.tileentities.TileEntityRepellent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Copyright: DCatcher
 */
public class PacketSync extends AbstractPacket {

    TileEntityRepellent te;
    NBTTagCompound nbt;

    public PacketSync(){
    }

    public PacketSync(TileEntityRepellent te){
       this.te = te;
    }
    @Override
    public void encode(ChannelHandlerContext context, ByteBuf buffer) {
        nbt = new NBTTagCompound();
        te.writeToNBT(nbt);
        ByteBufUtils.writeTag(buffer, nbt);
    }

    @Override
    public void decode(ChannelHandlerContext context, ByteBuf buffer) {
        nbt = ByteBufUtils.readTag(buffer);
    }

    @Override
    public void handleClient(EntityPlayer player) {
        TileEntity tile = player.worldObj.getTileEntity(te.xCoord, te.yCoord, te.zCoord);
        tile.readFromNBT(nbt);
        player.worldObj.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
        player.worldObj.markTileEntityChunkModified(tile.xCoord, tile.yCoord, tile.zCoord, tile);    }

    @Override
    public void handleServer(EntityPlayer player) {
        TileEntity tile = player.getEntityWorld().getTileEntity(te.xCoord, te.yCoord, te.zCoord);
        tile.readFromNBT(nbt);
        player.worldObj.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
        player.worldObj.markTileEntityChunkModified(tile.xCoord, tile.yCoord, tile.zCoord, tile);

    }
}
