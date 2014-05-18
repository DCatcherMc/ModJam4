package net.dcatcher.enderius.client.render;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.dcatcher.enderius.common.blocks.BlockHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

/**
 * Copyright: DCatcher
 */
public class BlockRenderer implements ISimpleBlockRenderingHandler {

    public static int id = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
            if(block == BlockHandler.enderRepulsor)
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(RenderRepulsor.texture);
            if(block == BlockHandler.enderSummoner)
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(RenderSummoner.texture);

            GL11.glTranslated(0.5, 1.9, 0.5);
            GL11.glRotatef(180F, 0, 0, 1);
            GL11.glScalef(1.3F, 1.3F, 1.3F);
            RenderRepulsor.model.renderItem(0.0625F);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return id;
    }
}
