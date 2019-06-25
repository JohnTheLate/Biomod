package mod.jtl.biomod.objects.blocks.tileentity;

import mod.jtl.biomod.init.ModTileEntityTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TelescopeTileEntity extends TileEntity implements ITickableTileEntity
{
	private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler).cast();

	public TelescopeTileEntity(TileEntityType<?> type)
	{
		super(type);
	}

	public TelescopeTileEntity()
	{
		super(ModTileEntityTypes.TELESCOPE_TILE_ENTITY);
	}

	@Override
	public void tick()
	{
		if (world.isRemote)
		{
			System.out.println("Telescope is ticking!");
		}
	}

	@Override
	public void read(CompoundNBT tag)
	{
		CompoundNBT invTag = tag.getCompound("inv");
		handler.ifPresent(h -> ((INBTSerializable<INBT>) h).deserializeNBT(invTag));
		createHandler().deserializeNBT(invTag);
		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag)
	{
		handler.ifPresent(h ->
		{
			CompoundNBT compound = createHandler().serializeNBT();
			tag.put("inv", compound);
		});
		return super.write(tag);
	}

	private ItemStackHandler createHandler()
	{
		return new ItemStackHandler(1)
		{
			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack)
			{
				return stack.getItem() == Items.EMERALD;
			}

			@Nonnull
			@Override
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
			{
				if (stack.getItem() == Items.EMERALD)
				{
					return stack;
				}
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


}
