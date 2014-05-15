package net.dcatcher.modjam4.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dcatcher.modjam4.common.player.DCPlayerProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Copyright: DCatcher
 */
public class PacketSync extends AbstractPacket {

    private NBTTagCompound data;

    public PacketSync(){
        //Yay for an empty one... apparently it's necessary?
    }

    private int levelBow,
                levelSword,
                xpBow,
                xpSword;

    public PacketSync(EntityPlayer player){
        data = new NBTTagCompound();
        DCPlayerProperties.getProps(player).saveNBTData(data);
    }
    @Override
    public void encode(ChannelHandlerContext context, ByteBuf buffer) {
        ByteBufUtils.writeTag(buffer, data);
    }

    @Override
    public void decode(ChannelHandlerContext context, ByteBuf buffer) {
        data = ByteBufUtils.readTag(buffer);
    }

    @Override
    public void handleClient(EntityPlayer player) {
        DCPlayerProperties.getProps(player).loadNBTData(data);
    }

    @Override
    public void handleServer(EntityPlayer player) {

    }
}
