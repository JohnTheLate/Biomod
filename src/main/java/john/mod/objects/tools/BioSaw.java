package john.mod.objects.tools;

import john.mod.Main;
import john.mod.objects.items.BioItemBase;
import net.minecraft.item.Item;

public class BioSaw extends BioItemBase
{
	protected float efficiency;
	protected Item.ToolMaterial toolMaterial;

	public BioSaw(String name, ToolMaterial material)
	{
		super(name);
		this.efficiency = material.getEfficiency();
		setCreativeTab(Main.BIOTAB);
	}
}
