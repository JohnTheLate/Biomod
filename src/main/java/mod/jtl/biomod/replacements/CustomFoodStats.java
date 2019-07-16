package mod.jtl.biomod.replacements;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.FoodStats;

public class CustomFoodStats extends FoodStats
{
	private boolean isIce;
	public FoodStats oldStats;

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
	public void consume(Item foodItem, ItemStack stack)
	{
		oldStats.consume(foodItem, stack);
	}

	@Override
	public void read(CompoundNBT compound)
	{
		oldStats.read(compound);
	}

	@Override
	public void write(CompoundNBT compound)
	{
		oldStats.write(compound);
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
		{															// Halves exhaustion gained from any activity but passive regeneration of health,
			oldStats.addExhaustion(exhaustion*0.5F);		// which is called from inside the oldStats using that class's addExhaustion method
		}															// For testing purposes; will be *0.5 later
		else
		{
			oldStats.addExhaustion(exhaustion);
		}
		System.out.println("custom stats! Ice status: " + isIce + exhaustion);
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

	public boolean getIsIce()
	{
		return isIce;
	}

	public void setIceStatus(Boolean status)
	{
		isIce = status;
	}
}
