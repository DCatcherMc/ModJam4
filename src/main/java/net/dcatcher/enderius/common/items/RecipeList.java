package net.dcatcher.enderius.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Copyright: DCatcher
 */
public class RecipeList {

    public static void initialiseRecipes(){
        GameRegistry.addShapelessRecipe(new ItemStack(Items.ender_pearl, 1), new ItemStack(Items.slime_ball), new ItemStack(ItemHandler.enderSlime));
        GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.bookOfLeveling, 1), "EEE", "E E", "EEE", 'E', new ItemStack(ItemHandler.enderSlime));
    }
}
