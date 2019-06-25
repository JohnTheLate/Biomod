package mod.jtl.biomod.setup;

import mod.jtl.biomod.objects.blocks.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup
{
	public ItemGroup itemGroup = new ItemGroup("biomod")
	{
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(ModBlocks.FIRSTBLOCK);
		}
	};

	public void init()
	{

	}
}
