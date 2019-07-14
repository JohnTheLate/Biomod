package mod.jtl.biomod.capability;

import mod.jtl.biomod.BioElements;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class Storage implements IStorage<IBioData>
{
	@Override
	public INBT writeNBT(Capability<IBioData> capability, IBioData instance, Direction side) {
		final CompoundNBT tag = new CompoundNBT();
		if (instance.getElement() == null)
		{
			System.out.println("Element is null?! Setting element to NONE");
			tag.putString("element", BioElements.NONE.name());
		}
		else
		{
			tag.putString("element", instance.getElement().name());
		}
		tag.putBoolean("active", instance.getMaskActive());
		tag.putInt("charge", instance.getMaskCharge());
		System.out.println("Tag written: Element:" + tag.getString("element") + "Status:" + tag.getBoolean("active") + "Charge:" + tag.getInt("charge"));
		return tag;
	}

	@Override
	public void readNBT(Capability<IBioData> capability, IBioData instance, Direction side, INBT nbt) {
		final CompoundNBT tag = (CompoundNBT) nbt;
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
		instance.setMaskCharge(tag.getInt("charge"));
	}
}