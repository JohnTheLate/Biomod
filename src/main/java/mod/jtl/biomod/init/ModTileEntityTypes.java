package mod.jtl.biomod.init;

import mod.jtl.biomod.Biomod;
import mod.jtl.biomod.ModUtil;
import mod.jtl.biomod.objects.blocks.tileentity.WickerBinTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

//@ObjectHolder(Biomod.MODID)
public class ModTileEntityTypes
{
	@ObjectHolder("biomod:wickerbin")
	public static final TileEntityType<WickerBinTileEntity> WICKER_BIN_TILE_ENTITY = ModUtil._null();
}