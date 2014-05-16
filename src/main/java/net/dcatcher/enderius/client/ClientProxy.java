package net.dcatcher.enderius.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.dcatcher.enderius.client.render.RenderEnderSlime;
import net.dcatcher.enderius.common.CommonProxy;
import net.dcatcher.enderius.common.EntityEnderSlime;
import net.minecraft.client.model.ModelSlime;

/**
 * Copyright: DCatcher
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderSlime.class, new RenderEnderSlime(new ModelSlime(16), new ModelSlime(0), 0.5F));
    }
}
