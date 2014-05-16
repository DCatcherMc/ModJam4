package net.dcatcher.enderius.client.render;

import net.dcatcher.enderius.common.EntityEnderSlime;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Copyright: DCatcher
 */
public class RenderEnderSlime extends RenderSlime {

    private static final ResourceLocation texture = new ResourceLocation("enderius", "textures/mobs/enderslime.png");

    private ModelBase scale;

    public RenderEnderSlime(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
        super(par1ModelBase, par2ModelBase, par3);
    }


    protected int shouldRenderPass(EntityEnderSlime enderSlime, int par2, float par3)
    {
        if (enderSlime.isInvisible())
        {
            return 0;
        }
        else if (par2 == 0)
        {
            this.setRenderPassModel(this.scale);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        }
        else
        {
            if (par2 == 1)
            {
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            return -1;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return texture;
    }
}
