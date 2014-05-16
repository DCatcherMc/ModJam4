package net.dcatcher.enderius.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Copyright: DCatcher
 */
public class BlockHandler {

    public static Block netherStarBlock;
    public static Block endStarBlock;

    public static void initialiseBlocks(){
        netherStarBlock = new BlockNetherStar();
        endStarBlock = new BlockEndStar();

        GameRegistry.registerBlock(netherStarBlock, "blockNetherStar");
        GameRegistry.registerBlock(endStarBlock, "blockEndStar");


        OreDictionary.registerOre("blockNetherStar", netherStarBlock);
        OreDictionary.registerOre("blockEndStar", endStarBlock);
    }
}
