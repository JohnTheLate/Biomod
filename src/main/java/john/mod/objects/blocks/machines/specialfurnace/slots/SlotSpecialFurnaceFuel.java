package john.mod.objects.blocks.machines.specialfurnace.slots;

import john.mod.objects.blocks.machines.specialfurnace.TileEntitySpecialFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSpecialFurnaceFuel extends Slot
{

	public SlotSpecialFurnaceFuel(IInventory inventoryIn, int index, int xPosition, int yPosition)
	{
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return TileEntitySpecialFurnace.isItemFuel(stack);
	}

	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return super.getItemStackLimit(stack);
	}
}
