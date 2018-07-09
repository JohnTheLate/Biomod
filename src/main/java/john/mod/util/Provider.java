package john.mod.util;

import john.mod.Main;
import john.mod.util.interfaces.IElementHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class Provider implements ICapabilitySerializable<NBTTagCompound>
{
    IElementHandler instance = Main.CAPABILITY_ELEMENT.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == Main.CAPABILITY_ELEMENT;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return hasCapability(capability, facing) ? Main.CAPABILITY_ELEMENT.<T>cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return (NBTTagCompound) Main.CAPABILITY_ELEMENT.getStorage().writeNBT(Main.CAPABILITY_ELEMENT, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        Main.CAPABILITY_ELEMENT.getStorage().readNBT(Main.CAPABILITY_ELEMENT, instance, null, nbt);
    }
}
