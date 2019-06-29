package mod.jtl.biomod.init;

import mod.jtl.biomod.Biomod;
import mod.jtl.biomod.ModUtil;
import mod.jtl.biomod.objects.container.WickerBinContainer;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;


//@ObjectHolder(Biomod.MODID)
public class ModGuis
{
	@ObjectHolder("biomod:wickerbin")
	public static final ContainerType<WickerBinContainer> WICKER_BIN_CONTAINER = ModUtil._null();
}
