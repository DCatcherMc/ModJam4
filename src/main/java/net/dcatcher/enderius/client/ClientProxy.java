package net.dcatcher.enderius.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.dcatcher.enderius.client.render.BlockRenderer;
import net.dcatcher.enderius.client.render.RenderEnderSlime;
import net.dcatcher.enderius.client.render.RenderRepulsor;
import net.dcatcher.enderius.client.render.RenderSummoner;
import net.dcatcher.enderius.common.CommonProxy;
import net.dcatcher.enderius.common.EntityEnderSlime;
import net.dcatcher.enderius.common.tileentities.TileEntityRepulsor;
import net.dcatcher.enderius.common.tileentities.TileEntitySummoner;
import net.minecraft.client.model.ModelSlime;

/**
 * Copyright: DCatcher
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders(){
        RenderingRegistry.registerBlockHandler(new BlockRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderSlime.class, new RenderEnderSlime(new ModelSlime(16), new ModelSlime(0), 0.5F));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySummoner.class, new RenderSummoner());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRepulsor.class, new RenderRepulsor());

    }
}
