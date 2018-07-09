package john.mod.util;

import john.mod.BioElements;
import john.mod.util.interfaces.IElementHandler;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class Storage implements Capability.IStorage<IElementHandler> {

    @Override
    public NBTBase writeNBT (Capability<IElementHandler> capability, IElementHandler instance, EnumFacing side)
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
        System.out.println("Tag written: " + tag.getString("element"));
        return tag;
    }

    @Override
    public void readNBT (Capability<IElementHandler> capability, IElementHandler instance, EnumFacing side, NBTBase nbt)
    {
    	try
		{
			final NBTTagCompound tag = (NBTTagCompound) nbt;
			instance.setElement(BioElements.valueOf(tag.getString("element")));
		}
		catch (IllegalArgumentException e) // If no matching String is found in the enum, an IllegalArgumentException will be thrown
		{
			System.out.println("An error occurred: " + e.getMessage());
			instance.setElement(BioElements.NONE);
			System.out.println("Element was set to NONE.");
		}
	}
}