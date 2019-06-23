package john.mod.objects.items.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nullable;

public class BioCraftingRecipes implements IRecipe
{

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		return null;
	}

	@Override
	public boolean canFit(int width, int height)
	{
		return false;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return null;
	}

	@Override
	public IRecipe setRegistryName(ResourceLocation name)
	{
		return null;
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName()
	{
		return null;
	}

	@Override
	public Class<IRecipe> getRegistryType()
	{
		return null;
	}
}
