package john.mod.replacements;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;

public class CustomFoodStats extends FoodStats
{
	boolean isIce;
	FoodStats oldStats;

	public CustomFoodStats(FoodStats oldStats, boolean isIce)
	{
		this.isIce = isIce;
		this.oldStats = oldStats;
	}

	@Override
	public void addStats(int foodLevelIn, float foodSaturationModifier)
	{
		oldStats.addStats(foodLevelIn, foodSaturationModifier);
	}

	@Override
	public void addStats(ItemFood foodItem, ItemStack stack)
	{
		oldStats.addStats(foodItem, stack);
	}

	@Override
	public void onUpdate(EntityPlayer player)
	{
		oldStats.onUpdate(player);
	}

	@Override
	public void readNBT(NBTTagCompound compound)
	{
		oldStats.readNBT(compound);
	}

	@Override
	public void writeNBT(NBTTagCompound compound)
	{
		oldStats.writeNBT(compound);
	}

	@Override
	public int getFoodLevel()
	{
		return oldStats.getFoodLevel();
	}

	@Override
	public boolean needFood()
	{
		return oldStats.needFood();
	}

	@Override
	public void addExhaustion(float exhaustion)
	{
		if (isIce)
		{													// Halves exhaustion gained from any activity but passive regeneration of health,
			oldStats.addExhaustion(exhaustion*0.5F);		// which is called from inside the oldStats using that class's addExhaustion method
		}													// For testing purposes; will be *0.5 later
		else
		{
			oldStats.addExhaustion(exhaustion);
		}
	}

	@Override
	public float getSaturationLevel()
	{
		return oldStats.getSaturationLevel();
	}

	@Override
	public void setFoodLevel(int foodLevelIn)
	{
		oldStats.setFoodLevel(foodLevelIn);
	}

	@Override
	public void setFoodSaturationLevel(float foodSaturationLevelIn)
	{
		oldStats.setFoodSaturationLevel(foodSaturationLevelIn);
	}
}
