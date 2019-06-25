package mod.jtl.biomod;

import com.google.common.base.Preconditions;
import mod.jtl.biomod.init.ModItemGroups;
import mod.jtl.biomod.objects.blocks.CamoWebbing;
import mod.jtl.biomod.objects.items.FirstItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@EventBusSubscriber(modid = Biomod.MODID, bus = MOD)
public class ModEventSubscriber
{

	@SubscribeEvent
	public static void onBlocksRegistry(final RegistryEvent.Register<Block> event)
	{
		Biomod.LOGGER.info("HELLO from Register Block");

		event.getRegistry().register(setup(new CamoWebbing(), "camowebbing"));
	}

	@SubscribeEvent
	public static void onItemsRegistry(final RegistryEvent.Register<Item> event)
	{
		final IForgeRegistry<Item> registry = event.getRegistry();

		Biomod.LOGGER.info("HELLO from Register Items");

		event.getRegistry().registerAll(
				//setup(new BlockItem(ModBlocks.FIRSTBLOCK, properties), "firstblock"),
				setup(new FirstItem(), "biogear")
		);

		//Following code by Cadiboo
		// We need to go over the entire registry so that we include any potential Registry Overrides
		for (final Block block : ForgeRegistries.BLOCKS.getValues()) {

			final ResourceLocation blockRegistryName = block.getRegistryName();
			Preconditions.checkNotNull(blockRegistryName, "Registry Name of Block \"" + block + "\" is null! This is not allowed!");

			// Check that the blocks is from our mod, if not, continue to the next block
			if (!blockRegistryName.getNamespace().equals(Biomod.MODID)) {
				continue;
			}

			// If you have blocks that don't have a corresponding ItemBlock, uncomment this code and create an Interface - or even better an Annotation - called NoAutomaticItemBlock with no methods and implement it on your blocks that shouldn't have ItemBlocks
			//if (!(block instanceof NoAutomaticItemBlock)) {
			//	continue;
			//}

			// Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
			final Item.Properties properties = new Item.Properties().group(ModItemGroups.BIOMOD_ITEM_GROUP);
			// Create the new BlockItem with the block and it's properties
			final BlockItem blockItem = new BlockItem(block, properties);
			// Setup the new BlockItem with the block's registry name and register it
			registry.register(setup(blockItem, blockRegistryName));
		}

	}

	//Following code by Cadiboo
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name)
	{
		return setup(entry, new ResourceLocation(Biomod.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName)
	{
		entry.setRegistryName(registryName);
		return entry;
	}
}
