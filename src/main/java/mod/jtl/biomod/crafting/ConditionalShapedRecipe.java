package mod.jtl.biomod.crafting;

import com.google.common.base.Throwables;
import com.google.gson.JsonObject;
import mod.jtl.biomod.init.ModCrafting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.lang.reflect.Field;

public class ConditionalShapedRecipe extends ShapedRecipe
{

	public ConditionalShapedRecipe(ResourceLocation resourceLocation, String group, int recipeWidth,
								   int recipeHeight, NonNullList<Ingredient> ingredients, ItemStack recipeOutput)
	{
		super(resourceLocation, group, recipeWidth, recipeHeight, ingredients, recipeOutput);
	}

	@Override
	public boolean matches(CraftingInventory inv, World worldIn)
	{
		return super.matches(inv, worldIn);
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv)
	{
		final ItemStack output = super.getCraftingResult(inv);

		if (findPlayer(inv).isCreative() || findPlayer(inv).isBurning())
		{
			output.setCount(output.getCount() * 2);
		}

		return output;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.CONDITIONAL_SHAPED;
	}


	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ConditionalShapedRecipe>
	{
		@Override
		public ConditionalShapedRecipe read(final ResourceLocation recipeID, final JsonObject json) {
			final String group = JSONUtils.getString(json, "group", "");
			final RecipeUtil.ShapedPrimer primer = RecipeUtil.parseShaped(json);
			final ItemStack result = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "result"), true);




			return new ConditionalShapedRecipe(recipeID, group, primer.getRecipeWidth(), primer.getRecipeHeight(), primer.getIngredients(), result);
		}


		@Override
		public ConditionalShapedRecipe read(final ResourceLocation recipeID, final PacketBuffer buffer) {
			final int width = buffer.readVarInt();
			final int height = buffer.readVarInt();
			final String group = buffer.readString(Short.MAX_VALUE);
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);

			for (int i = 0; i < ingredients.size(); ++i) {
				ingredients.set(i, Ingredient.read(buffer));
			}

			final ItemStack result = buffer.readItemStack();

			return new ConditionalShapedRecipe(recipeID, group, width, height, ingredients, result);
		}

		@Override
		public void write(final PacketBuffer buffer, final ConditionalShapedRecipe recipe) {
			buffer.writeVarInt(recipe.getRecipeWidth());
			buffer.writeVarInt(recipe.getRecipeHeight());
			buffer.writeString(recipe.getGroup());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.write(buffer);
			}

			buffer.writeItemStack(recipe.getRecipeOutput());
		}
	}

	private static PlayerEntity findPlayer(CraftingInventory inv) {
		try {
			Container container = inv.field_70465_c;
			if (container instanceof PlayerContainer) {
				return ((PlayerContainer) container).player;
			} else if (container instanceof WorkbenchContainer) {
				return ((WorkbenchContainer) container).player;
			} else {
        // don't know the player
				return null;
			}
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}
}
