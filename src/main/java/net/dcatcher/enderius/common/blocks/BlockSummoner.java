package net.dcatcher.enderius.common.blocks;

import net.dcatcher.enderius.Enderius;
import net.dcatcher.enderius.common.TileEntitySummoner;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Copyright: DCatcher
 */
public class BlockSummoner extends BlockContainer {

    protected BlockSummoner() {
        super(Material.iron);
        setBlockName("enderSummoner");
        setCreativeTab(Enderius.danTab);
        setHardness(2.0f);
        setTickRandomly(true);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntitySummoner();
    }



    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("enderius:summoner");
    }
}
