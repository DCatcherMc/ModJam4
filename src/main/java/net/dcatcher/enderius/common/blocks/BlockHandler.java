package net.dcatcher.enderius.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.dcatcher.enderius.common.items.ItemHandler;
import net.dcatcher.enderius.common.tileentities.TileEntityRepulsor;
import net.dcatcher.enderius.common.tileentities.TileEntitySummoner;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Copyright: DCatcher
 */
public class BlockHandler {

    public static Block netherStarBlock;
    public static Block endStarBlock;
    public static Block enderSummoner;
    public static Block enderRepulsor;

    public static void initialiseBlocks(){
        netherStarBlock = new BlockNetherStar();
        endStarBlock = new BlockEndStar();
        enderSummoner = new BlockSummoner();
        enderRepulsor = new BlockRepulsor();

        GameRegistry.registerBlock(netherStarBlock, "blockNetherStar");
        GameRegistry.registerBlock(endStarBlock, "blockEndStar");
        GameRegistry.registerBlock(enderSummoner, "enderSummoner");
        GameRegistry.registerBlock(enderRepulsor, "enderRepulsor");

        OreDictionary.registerOre("blockNetherStar", netherStarBlock);
        OreDictionary.registerOre("blockEndStar", endStarBlock);
        OreDictionary.registerOre("endStar", ItemHandler.enderStar);
    }


    public static void registerTileEntities(){
        GameRegistry.registerTileEntity(TileEntitySummoner.class, "enderiusSummoner");
        GameRegistry.registerTileEntity(TileEntityRepulsor.class, "enderiusRepulsor");
    }
}
