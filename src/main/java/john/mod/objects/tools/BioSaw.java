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

		// Idea: Ussals have an inventory of their own, containing 3 fields, presented in a Y shape.
		// The upper two fields can be filled with saws, to allow for mining while riding
		// The lower, central field can be filled with a chest, afterwards unlocking between 9 and 27 new
		// inventory fields that act as a mobile storage (compare donkeys)
		// The inventory background for the Y positioned 3 fields could be a stylicised ussal crab in the
		// Minecraft GUI shades of grey (2-3 shades only)
	}
}
