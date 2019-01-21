package john.mod.util.handlers;

import john.mod.objects.blocks.machines.specialfurnace.TileEntitySpecialFurnace;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntitySpecialFurnace.class, "special_furnace");
	}
}
