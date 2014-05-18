package net.dcatcher.enderius.common.blocks;

import net.dcatcher.enderius.Enderius;
import net.dcatcher.enderius.client.render.RenderRepulsor;
import net.dcatcher.enderius.common.network.PacketSync;
import net.dcatcher.enderius.common.tileentities.TileEntityRepulsor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Copyright: DCatcher
 */
public class BlockRepulsor extends BlockContainer {

    private IIcon blockBottom;
    private IIcon blockTop;

    protected BlockRepulsor() {
        super(Material.iron);
        setCreativeTab(Enderius.danTab);
        setBlockName("repulsor");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityRepulsor();
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return RenderRepulsor.renderID;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        switch(side){
            case 0:
                return blockBottom;
            case 1:
                return blockTop;
            default:
                return blockIcon;
        }    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te != null){
            PacketSync sync = new PacketSync(world, x, y, z);
            Enderius.packetPipeline.sendToAll(sync);
            Enderius.packetPipeline.sendToServer(sync);
        }
    }

    /**
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hx, float hy, float hz, int meta) {
        TileEntityRepulsor repel = (TileEntityRepulsor) world.getTileEntity(x, y, z);
        repel.addPlayerToBlacklist(world.getClosestPlayer((double)x, (double)y, (double)z, -1).getDisplayName());
        return meta;
    }
    */



    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float px, float py, float pz) {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te !=null && !player.isSneaking() && !world.isRemote){
            PacketSync sync = new PacketSync(world, x, y, z);
            Enderius.packetPipeline.sendToAll(sync);
            Enderius.packetPipeline.sendToServer(sync);
            player.openGui(Enderius.instance, 0, world, x, y, z);
            world.markTileEntityChunkModified(x, y, z, te);
            return true;
        }
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("enderius:summoner_side");
        this.blockBottom = reg.registerIcon("enderius:summoner_bottom");
        this.blockTop = reg.registerIcon("enderius:repellent_top");
    }
}
