package john.mod.objects.blocks.machines.specialfurnace;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class ContainerSpecialFurnace extends Container
{
	private final TileEntitySpecialFurnace tileentity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;

	public ContainerSpecialFurnace(InventoryPlayer player, TileEntitySpecialFurnace tileentity)
	{
		this.tileentity = tileentity;

		this.addSlotToContainer(new Slot(tileentity, 0,  burnTime, burnTime));
	}
}
