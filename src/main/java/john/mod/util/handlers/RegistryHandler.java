package john.mod.util.handlers;

import john.mod.BioEventSubscriber;
import john.mod.init.BiomeInit;
import john.mod.init.BlockInit;
import john.mod.init.EntityInit;
import john.mod.init.ItemInit;
import john.mod.util.Storage;
import john.mod.util.interfaces.IElementHandler;
import john.mod.util.interfaces.IHasModel;
import john.mod.world.gen.WorldGenCustomOres;
import john.mod.world.types.WorldTypeMataNui;
import john.mod.world.types.WorldTypeSingleBiome;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ItemInit.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : BlockInit.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries()
	{
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);

		BiomeInit.registerBiomes();

		EntityInit.registerEntities();
		RenderHandler.registerEntityRenders();

		MinecraftForge.EVENT_BUS.register(new BioEventSubscriber());

		CapabilityManager.INSTANCE.register(IElementHandler.class, new Storage(), DefaultElementHandler.class);


/*		// Be sure not to use getFields as that only fetches public fields.
		Field[] fieldList = EntityPlayer.class.getDeclaredFields();
		for (int i = 0; i < fieldList.length; i++) {
			System.out.println("[" + i + "] " + fieldList[ i ]);
		}
		// This line is optional, but it makes it easier to find the field indexes
		// by stopping Minecraft once they are printed.
		FMLCommonHandler.instance().exitJava(0, true); */


		System.out.println("Registered pre inits");
	}

	public static void initRegistries()
	{
		SoundsHandler.registerSounds();
	}

	public static void postInitRegistries()
	{
		WorldType SINGLEBIOME = new WorldTypeSingleBiome();
		WorldType MATANUI = new WorldTypeMataNui();
	}
}
