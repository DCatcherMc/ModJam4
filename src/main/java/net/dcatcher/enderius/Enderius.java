package net.dcatcher.enderius;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLContainer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.dcatcher.enderius.common.CommonProxy;
import net.dcatcher.enderius.common.EntityEnderSlime;
import net.dcatcher.enderius.common.blocks.BlockHandler;
import net.dcatcher.enderius.common.items.ItemHandler;
import net.dcatcher.enderius.common.items.RecipeList;
import net.dcatcher.enderius.common.network.PacketPipeline;
import net.dcatcher.enderius.common.util.DCCreativeTab;
import net.dcatcher.enderius.common.util.EventListener;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraftforge.common.MinecraftForge;

/**
 * TODO:
 *  Name this main mod something different.
 */

@Mod(modid="Enderius", name="Enderius", version="${version}")
public class Enderius {

    @Mod.Instance("Enderius")
    public static Enderius instance;

    public static CreativeTabs danTab = new DCCreativeTab();

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    @SidedProxy(clientSide = "net.dcatcher.enderius.client.ClientProxy", serverSide = "net.dcatcher.enderius.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event){
        //Initialise Items and Blocks
        ItemHandler.initialiseItems();
        BlockHandler.initialiseBlocks();

        EntityRegistry.registerGlobalEntityID(EntityEnderSlime.class, "EnderSlime", EntityRegistry.findGlobalUniqueEntityId(), (255 << 16), (255 << 16) + (200 << 8));
        for(BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()){
            if(biome != null)
                EntityRegistry.addSpawn(EntityEnderSlime.class, 1, 1, 3, EnumCreatureType.monster, biome);
        }

        //EventListener initialise
        EventListener listener = new EventListener();
        MinecraftForge.EVENT_BUS.register(listener);
        FMLCommonHandler.instance().bus().register(listener);
        //Initialise Recipes
        RecipeList.initialiseRecipes();

    }

    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event){
        packetPipeline.init();
        proxy.registerRenders();
    }


    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event){
        packetPipeline.postInit();
    }
}
