package net.dcatcher.enderius.common.blocks;

import net.dcatcher.enderius.Enderius;
import net.dcatcher.enderius.common.tileentities.TileEntityRepellent;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Copyright: DCatcher
 */
public class BlockRepellent extends BlockContainer {

    private IIcon blockBottom;
    private IIcon blockTop;

    protected BlockRepellent() {
        super(Material.iron);
        setCreativeTab(Enderius.danTab);
        setBlockName("enderRepellent");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityRepellent();
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

    /**
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hx, float hy, float hz, int meta) {
        TileEntityRepellent repel = (TileEntityRepellent) world.getTileEntity(x, y, z);
        repel.addPlayerToBlacklist(world.getClosestPlayer((double)x, (double)y, (double)z, -1).getDisplayName());
        return meta;
    }
    */

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float px, float py, float pz) {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te !=null && !player.isSneaking() && !world.isRemote){
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
