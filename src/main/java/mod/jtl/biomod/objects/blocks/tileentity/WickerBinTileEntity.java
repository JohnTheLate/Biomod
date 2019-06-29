package mod.jtl.biomod.objects.blocks.tileentity;

import mod.jtl.biomod.init.ModTileEntityTypes;
import mod.jtl.biomod.objects.container.WickerBinContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullConsumer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WickerBinTileEntity extends TileEntity implements INamedContainerProvider
{
	private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler).cast();

	//public WickerBinTileEntity(TileEntityType<?> type)
	//{
	//	super(type);
	//}

	public WickerBinTileEntity()
	{
		super(ModTileEntityTypes.WICKER_BIN_TILE_ENTITY);
	}

	@Override
	public void read(CompoundNBT tag)
	{
		CompoundNBT invTag = tag.getCompound("inv");
		handler.ifPresent(new NonNullConsumer<IItemHandler>()
		{
			@Override
			public void accept(@Nonnull IItemHandler h)
			{
				((INBTSerializable<INBT>) h).deserializeNBT(invTag);
			}
		});
		createHandler().deserializeNBT(invTag);
		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag)
	{
		handler.ifPresent(new NonNullConsumer<IItemHandler>()
		{
			@Override
			public void accept(@Nonnull IItemHandler h)
			{
				CompoundNBT compound = WickerBinTileEntity.this.createHandler().serializeNBT();
				tag.put("inv", compound);
			}
		});
		return super.write(tag);
	}

	private ItemStackHandler createHandler()
	{
		return new ItemStackHandler(9)
		{
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}

			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack)
			{
				return true; //stack.getItem() == Items.EMERALD;
			}

			@Nonnull
			@Override
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
			{
				//if (stack.getItem() != Items.EMERALD)
				//{
				//	return stack;
				//}
				return super.insertItem(slot, stack, simulate);
			}
		};
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
	{
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return handler.cast();
		}
		return super.getCapability(cap, side);
	}


	@Override
	public ITextComponent getDisplayName()
	{
		return new StringTextComponent(getType().getRegistryName().getPath());
	}

	@Nullable
	@Override
	public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity)
	{
		return new WickerBinContainer(i, world, pos, playerInventory, playerEntity);
	}
}
