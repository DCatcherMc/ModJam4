package net.dcatcher.enderius.common.blocks;

import net.dcatcher.enderius.Enderius;
import net.dcatcher.enderius.common.tileentities.TileEntityRepellent;
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
public class BlockRepellent extends BlockContainer {

    protected BlockRepellent() {
        super(Material.iron);
        setCreativeTab(Enderius.danTab);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityRepellent();
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float px, float py, float pz) {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te == null || player.isSneaking())
            return false;

        player.openGui(Enderius.instance, 0, world, x, y, z);
        return true;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("enderius:repellent_side");
    }
}
