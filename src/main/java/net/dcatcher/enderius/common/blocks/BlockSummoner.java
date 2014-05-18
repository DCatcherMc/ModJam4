package net.dcatcher.enderius.common.blocks;

import net.dcatcher.enderius.Enderius;
import net.dcatcher.enderius.client.render.RenderSummoner;
import net.dcatcher.enderius.common.tileentities.TileEntitySummoner;
import net.dcatcher.enderius.common.items.ItemHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
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
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return RenderSummoner.renderID;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntitySummoner();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float px, float py, float pz) {
        if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == ItemHandler.dnaSyringe){
            TileEntitySummoner summoner = (TileEntitySummoner)world.getTileEntity(x, y, z);
            ItemStack syringe = player.getCurrentEquippedItem();
            NBTTagCompound nbt = new NBTTagCompound();
            String entityID = syringe.writeToNBT(nbt).getString("entityID");
            summoner.setData(entityID);
            System.out.println(entityID);
            return true;
        }
        return false;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        switch(side){
            case 0:
                return iconBottom;
            case 1:
                return iconTop;
            default:
                return blockIcon;
        }
    }


    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("enderius:summoner_side");
        this.iconBottom = reg.registerIcon("enderius:summoner_bottom");
        this.iconTop = reg.registerIcon("enderius:summoner_top");
    }
}
