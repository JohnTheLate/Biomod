package john.mod.tabs;

import john.mod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class NTutTab extends CreativeTabs 
{
	public NTutTab(String label) 
	{ 
		super("ntuttab");
		this.setBackgroundImageName("ntut.png"); 
	}
	public ItemStack getTabIconItem() 
	{ 
		return new ItemStack(ItemInit.INGOT_METEOR);
	}
}
