package mod.jtl.biomod.objects.items;

import mod.jtl.biomod.Biomod;
import net.minecraft.item.Item;

public class FirstItem extends Item
{
	public FirstItem()
	{
		super(new Item.Properties()
				.group(Biomod.setup.itemGroup)
				.maxStackSize(7));
		//setRegistryName("biogear");
	}
}
