package mod.jtl.biomod;

import mod.jtl.biomod.objects.blocks.FirstBlock;
import mod.jtl.biomod.objects.blocks.ModBlocks;
import mod.jtl.biomod.objects.items.FirstItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@EventBusSubscriber(modid = Biomod.MODID, bus = MOD)
public class ModEventSubscriber
{

	@SubscribeEvent
	public static void onBlocksRegistry(final RegistryEvent.Register<Block> event)
	{
		// register a new block here
		Biomod.LOGGER.info("HELLO from Register Block");


		event.getRegistry().register(new FirstBlock());
	}

	@SubscribeEvent
	public static void onItemsRegistry(final RegistryEvent.Register<Item> event)
	{
		// register a new item here
		Biomod.LOGGER.info("HELLO from Register Items");

		Item.Properties properties = new Item.Properties()
				.group(Biomod.setup.itemGroup);
		event.getRegistry().register(new BlockItem(ModBlocks.FIRSTBLOCK, properties).setRegistryName("firstblock"));
		event.getRegistry().register(new FirstItem());
	}

	//Cadiboo tutorial
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
