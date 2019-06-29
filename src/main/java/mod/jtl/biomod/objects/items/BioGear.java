package mod.jtl.biomod.objects.items;

import mod.jtl.biomod.Biomod;
import mod.jtl.biomod.init.ModItemGroups;
import net.minecraft.item.Item;

public class BioGear extends Item
{
	public BioGear()
	{
		super(new Item.Properties()
				.group(ModItemGroups.BIOMOD_ITEM_GROUP)
		);
	}
}
