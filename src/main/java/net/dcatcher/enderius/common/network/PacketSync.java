package net.dcatcher.enderius.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dcatcher.enderius.common.player.DCPlayerProperties;
import net.dcatcher.enderius.common.tileentities.TileEntityRepellent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Copyright: DCatcher
 */
public class PacketSync extends AbstractPacket {

    TileEntityRepellent te;

    public PacketSync(){
    }

    public PacketSync(TileEntityRepellent te){
       this.te = te;
    }
    @Override
    public void encode(ChannelHandlerContext context, ByteBuf buffer) {
        NBTTagCompound nbt = new NBTTagCompound();
        te.writeToNBT(nbt);
        ByteBufUtils.writeTag(buffer, nbt);
    }

    @Override
    public void decode(ChannelHandlerContext context, ByteBuf buffer) {
        NBTTagCompound nbt = ByteBufUtils.readTag(buffer);
        te.readFromNBT(nbt);
    }

    @Override
    public void handleClient(EntityPlayer player) {
       
    }

    @Override
    public void handleServer(EntityPlayer player) {

    }
}
