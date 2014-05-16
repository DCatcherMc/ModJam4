package net.dcatcher.modjam4.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.dcatcher.modjam4.common.CommonProxy;
import net.dcatcher.modjam4.common.EntityEnderSlime;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderSlime;

/**
 * Copyright: DCatcher
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderSlime.class, new RenderSlime(new ModelSlime(20), new ModelSlime(0), 0.5F));
    }
}
