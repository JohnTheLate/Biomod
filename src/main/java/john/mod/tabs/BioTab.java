package john.mod.tabs;

import john.mod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BioTab extends CreativeTabs
{
	public BioTab(String label)
	{ 
		super("biotab");
		this.setBackgroundImageName("biotab.png");
	}
	public ItemStack getTabIconItem() 
	{ 
		return new ItemStack(ItemInit.SWORD_FIRE);
	}
}
