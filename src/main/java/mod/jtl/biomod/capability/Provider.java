package mod.jtl.biomod.capability;

import mod.jtl.biomod.Biomod;
import mod.jtl.biomod.capability.IBioPlayerDataHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class Provider implements ICapabilitySerializable<NBTTagCompound>
{
    IBioPlayerDataHandler instance = Main.CAPABILITY_BIO_PLAYER_DATA.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == Main.CAPABILITY_BIO_PLAYER_DATA;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return hasCapability(capability, facing) ? Main.CAPABILITY_BIO_PLAYER_DATA.<T>cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return (NBTTagCompound) Main.CAPABILITY_BIO_PLAYER_DATA.getStorage().writeNBT(Main.CAPABILITY_BIO_PLAYER_DATA, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        Main.CAPABILITY_BIO_PLAYER_DATA.getStorage().readNBT(Main.CAPABILITY_BIO_PLAYER_DATA, instance, null, nbt);
    }
}
