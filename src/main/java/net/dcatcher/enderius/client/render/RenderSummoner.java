package net.dcatcher.enderius.client.render;

import com.sun.xml.internal.ws.policy.sourcemodel.ModelNode;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.dcatcher.enderius.client.ModelSummoner;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Copyright: DCatcher
 */
public class RenderSummoner extends TileEntitySpecialRenderer {

    public static int renderID = RenderingRegistry.getNextAvailableRenderId();

    private ModelSummoner model = new ModelSummoner();
    public static final ResourceLocation texture = new ResourceLocation("enderius:", "textures/models/summoner.png");

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        GL11.glRotatef((0 * 90.0F), 0.0F, 1.0F, 0.0F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        bindTexture(texture);
        model.render(tile, 0.0625F);
        GL11.glPopMatrix();
    }
}
