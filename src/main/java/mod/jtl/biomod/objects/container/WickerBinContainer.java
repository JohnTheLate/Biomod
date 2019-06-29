package mod.jtl.biomod.objects.container;

import mod.jtl.biomod.init.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.NonNullConsumer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

import static mod.jtl.biomod.init.ModGuis.WICKER_BIN_CONTAINER;

public class WickerBinContainer extends Container
{
	private TileEntity tileEntity;
	private PlayerEntity playerEntity;
	private IItemHandler playerInventory;
	//private IInventory wickerBinInventory;

	public WickerBinContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player)
	{
		super(WICKER_BIN_CONTAINER, windowId);
		tileEntity = world.getTileEntity(pos);
		this.playerEntity = player;
		//this.wickerBinInventory = wickerBinInventory;


		tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(new NonNullConsumer<IItemHandler>()
		{
			@Override
			public void accept(@Nonnull IItemHandler h)
			{
				//WickerBinContainer.this.addSlot(new SlotItemHandler(h, 0, 8, 20));
				for(int x = 0; x < 9; ++x) {
					WickerBinContainer.this.addSlot(new SlotItemHandler(h, x, 8 + x * 18, 20));
					//this.addSlot(new Slot(inventory, x, 8 + x * 18, 109));
				}
			}
		});

		//for(int x = 0; x < 9; ++x) {
		//	this.addSlot(new Slot(wickerBinInventory, x, 8 + x * 18, 20));
		//}

		for(int y = 0; y < 3; ++y) {
			for(int x = 0; x < 9; ++x) {
				this.addSlot(new Slot(inventory, x + y * 9 + 9, 8 + x * 18, y * 18 + 51));
			}
		}

		for(int x = 0; x < 9; ++x) {
			this.addSlot(new Slot(inventory, x, 8 + x * 18, 109));
		}
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn)
	{
		return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.WICKERBIN);
	}

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	 * inventory and the other inventory(s).
	 */
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < 9) {
				if (!this.mergeItemStack(itemstack1, 9, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 9, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	/**
	 * Called when the container is closed.
	 */
	/* public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
		this.wickerBinInventory.closeInventory(playerIn);
	} */

}
