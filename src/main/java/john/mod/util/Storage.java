package john.mod.util;

import john.mod.BioElements;
import john.mod.util.interfaces.IBioPlayerDataHandler;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class Storage implements Capability.IStorage<IBioPlayerDataHandler> {

    @Override
    public NBTBase writeNBT (Capability<IBioPlayerDataHandler> capability, IBioPlayerDataHandler instance, EnumFacing side)
    {
        final NBTTagCompound tag = new NBTTagCompound();
        if (instance.getElement() == null)
		{
			System.out.println("Tag is null?! Setting element to NONE");
			tag.setString("element", BioElements.NONE.name());
		}
		else
		{
			tag.setString("element", instance.getElement().name());
		}
		tag.setBoolean("active", instance.getMaskActive());
        tag.setInteger("charge", instance.getMaskCharge());
        System.out.println("Tag written: Element:" + tag.getString("element") + "Status:" + tag.getBoolean("active") + "Charge:" + tag.getInteger("charge"));
        return tag;
    }

    @Override
    public void readNBT (Capability<IBioPlayerDataHandler> capability, IBioPlayerDataHandler instance, EnumFacing side, NBTBase nbt)
    {
		final NBTTagCompound tag = (NBTTagCompound) nbt;
    	try
		{
			instance.setElement(BioElements.valueOf(tag.getString("element")));
		}
		catch (IllegalArgumentException e) // If no matching String is found in the enum, an IllegalArgumentException will be thrown
		{
			System.out.println("An error occurred: " + e.getMessage());
			instance.setElement(BioElements.NONE);
			System.out.println("Element was set to NONE.");
		}
		instance.setMaskActive(tag.getBoolean("active"));
    	instance.setMaskCharge(tag.getInteger("charge"));
	}
}