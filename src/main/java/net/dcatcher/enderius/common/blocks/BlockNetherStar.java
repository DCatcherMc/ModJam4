package net.dcatcher.enderius.common.blocks;

import net.dcatcher.enderius.Enderius;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Copyright: DCatcher
 */
public class BlockNetherStar extends Block {

    protected BlockNetherStar() {
        super(Material.rock);
        setBlockName("blockNetherStar");
        setCreativeTab(Enderius.danTab);
        setHardness(2.0f);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("enderius:blocknetherstar");
    }
}
