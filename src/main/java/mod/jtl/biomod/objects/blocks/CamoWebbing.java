package mod.jtl.biomod.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CamoWebbing extends Block
{
	public CamoWebbing()
	{
		super(Properties.create(Material.WOOL)
				.sound(SoundType.CLOTH)
				.hardnessAndResistance(2.0F, 6.0F)
		);
	}
}
