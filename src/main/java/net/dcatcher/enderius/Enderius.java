package net.dcatcher.enderius;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.dcatcher.enderius.common.CommonProxy;
import net.dcatcher.enderius.common.EntityEnderSlime;
import net.dcatcher.enderius.common.blocks.BlockHandler;
import net.dcatcher.enderius.common.items.ItemHandler;
import net.dcatcher.enderius.common.util.RecipeList;
import net.dcatcher.enderius.common.network.PacketPipeline;
import net.dcatcher.enderius.common.util.DCCreativeTab;
import net.dcatcher.enderius.common.util.EnderiusConfiguration;
import net.dcatcher.enderius.common.util.EventListener;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;

@Mod(modid="Enderius", name="Enderius", version="${version}")
public class Enderius {

    @Mod.Instance("Enderius")
    public static Enderius instance;

    public static CreativeTabs danTab = new DCCreativeTab();

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    @SidedProxy(clientSide = "net.dcatcher.enderius.client.ClientProxy", serverSide = "net.dcatcher.enderius.common.CommonProxy")
    public static CommonProxy proxy;
    public static List<String> spawnerBlacklist;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
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

        try{
            EnderiusConfiguration.checkBlacklist(event.getModConfigurationDirectory());
        }catch(Exception e){
            FMLLog.getLogger().error("Blacklist could not be read");
        }

    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){
        packetPipeline.init();
        proxy.registerRenders();
        RecipeList.initialiseRecipes();
        BlockHandler.registerTileEntities();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
    }


    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        packetPipeline.postInit();
    }
}
