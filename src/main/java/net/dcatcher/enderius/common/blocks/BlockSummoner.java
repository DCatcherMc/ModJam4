package net.dcatcher.enderius.common.blocks;

import net.dcatcher.enderius.Enderius;
import net.dcatcher.enderius.common.TileEntitySummoner;
import net.dcatcher.enderius.common.items.ItemEnderSlime;
import net.dcatcher.enderius.common.items.ItemHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Copyright: DCatcher
 */
public class BlockSummoner extends BlockContainer {

    private IIcon iconTop, iconBottom;


    protected BlockSummoner() {
        super(Material.iron);
        setBlockName("enderSummoner");
        setCreativeTab(Enderius.danTab);
        setHardness(2.0f);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntitySummoner();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float px, float py, float pz) {
        if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == ItemHandler.enderSlime){
            TileEntitySummoner summoner = (TileEntitySummoner)world.getTileEntity(x, y, z);
            ItemStack slime = player.getCurrentEquippedItem();
            summoner.setData(slime.getDisplayName());
            System.out.println("Added Data to summoner");
            return true;
        }
        return false;
    }

    @Override
    public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_) {
        return super.getIcon(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_, p_149673_5_);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("enderius:summoner_side");
        this.iconBottom = reg.registerIcon("enderius:summoner_side");
        this.iconTop = reg.registerIcon("enderius:summoner_side");
    }
}
