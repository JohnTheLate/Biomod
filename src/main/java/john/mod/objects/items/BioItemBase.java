package john.mod.objects.items;

import john.mod.Main;

public class BioItemBase extends ItemBase
{
	public BioItemBase(String name)
	{
		super(name);
		setCreativeTab(Main.BIOTAB);
	}
}
