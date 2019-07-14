package mod.jtl.biomod.capability;

import mod.jtl.biomod.ModUtil;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Provider implements ICapabilitySerializable<INBT>
{
	@CapabilityInject(IBioData.class)
    public static final Capability<IBioData> BIO_CAP = ModUtil._null();

	private LazyOptional<IBioData> instance = LazyOptional.of(DefaultBioData::new);

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
	{
		return BIO_CAP.orEmpty(cap, instance);
		//return cap == BIO_CAP ? LazyOptional.of(() -> instance).cast() : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT()
	{
		return BIO_CAP.getStorage().writeNBT(BIO_CAP, instance.orElse(null), null);
	}

	@Override
	public void deserializeNBT(INBT nbt)
	{
		BIO_CAP.getStorage().readNBT(BIO_CAP, instance.orElse(null), null, nbt);
	}
}
