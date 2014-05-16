package net.dcatcher.enderius.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.dcatcher.enderius.common.blocks.BlockHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Copyright: DCatcher
 */
public class RecipeList {

    public static void initialiseRecipes(){
        GameRegistry.addShapelessRecipe(new ItemStack(Items.ender_pearl, 1), new ItemStack(Items.slime_ball), new ItemStack(ItemHandler.enderSlime));
        GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.enderNucleus, 1), "EEE", "ENE", "EEE", 'E', new ItemStack(ItemHandler.enderSlime), 'N', new ItemStack(Items.nether_star));
        GameRegistry.addSmelting(new ItemStack(ItemHandler.enderSlime), new ItemStack(ItemHandler.enderiumFuel, 4), 5);

        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.netherStarBlock, 1), "NNN", "NNN", "NNN", 'N', new ItemStack(Items.nether_star));
        GameRegistry.addShapedRecipe(new ItemStack(BlockHandler.endStarBlock, 1), "EE", "EE", 'E', new ItemStack(ItemHandler.enderStar));
    }
}
