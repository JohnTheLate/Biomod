package mod.jtl.biomod.init;

import mod.jtl.biomod.Biomod;
import mod.jtl.biomod.objects.items.FirstItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Biomod.MODID)
public final class ModItems
{
	public static final Item BIOGEAR = new FirstItem();
}
