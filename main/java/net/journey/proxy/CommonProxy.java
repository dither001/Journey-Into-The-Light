package net.journey.proxy;

import net.journey.JourneyBlocks;
import net.journey.JourneyChestGenerator;
import net.journey.JourneyCrops;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.achievement.event.JourneyDungeonEvent;
import net.journey.achievement.event.JourneySapphireEvent;
import net.journey.achievement.event.JourneySapphireSwordEvent;
import net.journey.blocks.tileentity.TileEntityGrindstone;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.blocks.tileentity.TileEntityKnowledgeTable;
import net.journey.blocks.tileentity.TileEntityNetherFurnace;
import net.journey.blocks.tileentity.TileEntitySenterianPortal;
import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.journey.blocks.tileentity.TileEntityTrophyTable;
import net.journey.client.BarTickHandler;
import net.journey.dimension.DimensionHelper;
import net.journey.dimension.WorldGenEssence;
import net.journey.enums.EnumParticlesClasses;
import net.journey.event.ArmorAbilityEvent;
import net.journey.event.PlayerEvent;
import net.journey.util.Config;
import net.journey.util.EntityRegistry;
import net.journey.util.JourneyFuelHandler;
import net.journey.util.LangRegistry;
import net.journey.util.recipes.JourneyBlockRecipes;
import net.journey.util.recipes.JourneyMaterialRecipes;
import net.journey.util.recipes.JourneyMiscRecipes;
import net.journey.util.recipes.JourneySmeltingRecipes;
import net.journey.util.recipes.JourneyWeaponRecipes;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class CommonProxy {

	public void updateDarkEnergy(int amount) { }
	public void updateEssence(int amount) { }
	public void updatePower(int amount) { }
	public void registerClient() { }
	public void clientInit(FMLInitializationEvent event) { }
	public void clientPreInit() { }
	public void registerSounds() { }
	public void spawnParticle(EnumParticlesClasses particle, World worldObj, double x, double y, double z, boolean b) { }
	
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(event);
		JourneyBlocks.init();
		JourneyCrops.init();
		JourneyItems.init();
		EntityRegistry.init();
		JourneyChestGenerator.init();
		//JourneyAchievements.init();
		JourneyMaterialRecipes.init();
		JourneyBlockRecipes.init();
		JourneyMiscRecipes.init();
		JourneySmeltingRecipes.init();
		JourneyWeaponRecipes.init();
		
		//BiomeDictionary.addTypes(new BiomeHell(), Type.NETHER);
		DimensionHelper.addSpawns();
		
		if(SlayerAPI.DEVMODE) LangRegistry.instance.register();
		addOreDictionary();
		SlayerAPI.registerEvent(new ArmorAbilityEvent());
		SlayerAPI.registerEvent(new PlayerEvent());
		SlayerAPI.registerEvent(new BarTickHandler());
		GameRegistry.registerFuelHandler(new JourneyFuelHandler());
		MinecraftForge.addGrassSeed(new ItemStack(JourneyCrops.tomatoSeeds), 5);
		FMLCommonHandler.instance().bus().register(new JourneySapphireSwordEvent());
		FMLCommonHandler.instance().bus().register(new JourneySapphireEvent());
		FMLCommonHandler.instance().bus().register(new JourneyDungeonEvent());
		JourneyTabs.init();
		//DimensionHelper.init();
	}
	
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new WorldGenEssence(), 2);
		SlayerAPI.registerEvent(new PlayerEvent());
		
	}
	
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {  }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) { }
    
	public void registerItemRenderer(Item itemBlock, int i, String name) { }
	
	public void registerEntityRenderer(Entity entity, int i, String name) { }
	
	public void postInit(FMLPostInitializationEvent event) { }
	
	public void serverStarting(FMLServerStartingEvent event) { }
	
	private void addOreDictionary() { }
}