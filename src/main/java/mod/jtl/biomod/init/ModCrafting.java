package mod.jtl.biomod.init;

import mod.jtl.biomod.ModUtil;
import mod.jtl.biomod.crafting.ConditionalShapedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.common.crafting.IngredientNBT;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import mod.jtl.biomod.Biomod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Manages this mod's recipes and ingredients and removes recipes.
 */
public class ModCrafting {


	@SuppressWarnings("unused")
	@ObjectHolder(Biomod.MODID)
	@Mod.EventBusSubscriber(modid = Biomod.MODID, bus = Bus.MOD)
	public static class Recipes {
		public static IRecipeSerializer<ConditionalShapedRecipe> CONDITIONAL_SHAPED = ModUtil._null();

		@SubscribeEvent
		public static void register(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
			event.getRegistry().registerAll(
					new ConditionalShapedRecipe.Serializer().setRegistryName(new ResourceLocation(Biomod.MODID, "conditional_shaped"))
			);
		}
	}
}