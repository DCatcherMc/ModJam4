package net.dcatcher.enderius.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.dcatcher.enderius.common.TileEntitySummoner;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Copyright: DCatcher
 */
public class BlockHandler {

    public static Block netherStarBlock;
    public static Block endStarBlock;
    public static Block enderSummoner;

    public static void initialiseBlocks(){
        netherStarBlock = new BlockNetherStar();
        endStarBlock = new BlockEndStar();
        enderSummoner = new BlockSummoner();

        GameRegistry.registerBlock(netherStarBlock, "blockNetherStar");
        GameRegistry.registerBlock(endStarBlock, "blockEndStar");
        GameRegistry.registerBlock(enderSummoner, "enderSummoner");


        OreDictionary.registerOre("blockNetherStar", netherStarBlock);
        OreDictionary.registerOre("blockEndStar", endStarBlock);
    }


    public static void registerTileEntities(){
        GameRegistry.registerTileEntity(TileEntitySummoner.class, "enderiusSummoner");
    }
}
