package net.dcatcher.enderius.common.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.dcatcher.enderius.common.blocks.BlockHandler;
import net.dcatcher.enderius.common.items.ItemHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Copyright: DCatcher
 */
public class RecipeList {

    public static void initialiseRecipes(){
        GameRegistry.addShapelessRecipe(new ItemStack(Items.ender_pearl, 1), new ItemStack(Items.slime_ball), new ItemStack(ItemHandler.enderSlime));
        GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.enderNucleus, 1), "EEE", "ENE", "EEE", 'E', new ItemStack(ItemHandler.enderSlime), 'N', new ItemStack(ItemHandler.enderStar));
        GameRegistry.addSmelting(new ItemStack(ItemHandler.enderSlime), new ItemStack(ItemHandler.enderiumFuel, 4), 5);

        GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.dnaSyringe), "  I", " I ", "I I", 'I', new ItemStack(Items.iron_ingot));

        GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.enderFocus), "AXP", "XOX", "SXH", 'X', new ItemStack(Items.diamond), 'A', new ItemStack(Items.diamond_axe), 'P',
                new ItemStack(Items.diamond_pickaxe), 'S', new ItemStack(Items.diamond_shovel), 'H', new ItemStack(Items.diamond_hoe), 'O', new ItemStack(ItemHandler.enderNucleus));

        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.netherStarBlock, 1), "NNN", "NNN", "NNN", 'N', new ItemStack(Items.nether_star));
        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.endStarBlock, 1), "EE", "EE", 'E', new ItemStack(ItemHandler.enderStar));

        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.enderRepulsor, 1), " E ", " I ", "IRI", 'E', new ItemStack(Items.ender_pearl),
                'I', new ItemStack(Blocks.iron_block), 'R', new ItemStack(Items.redstone));

        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.enderSummoner, 1), "WEW", " I ", "IRI", 'E', new ItemStack(Items.ender_eye),
                'I', new ItemStack(Blocks.iron_block), 'R', new ItemStack(Items.redstone), 'W', new ItemStack(Items.wheat));
        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.enderSummoner, 1), "WEW", " I ", "IRI", 'E', new ItemStack(Items.ender_eye),
                'I', new ItemStack(Blocks.iron_block), 'R', new ItemStack(Items.redstone), 'W', new ItemStack(Items.carrot));
        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.enderSummoner, 1), "WEW", " I ", "IRI", 'E', new ItemStack(Items.ender_eye),
                'I', new ItemStack(Blocks.iron_block), 'R', new ItemStack(Items.redstone), 'W', new ItemStack(Items.potato));

    }
}
