package john.mod.util.handlers;

import john.mod.GuiElementChoice;
import john.mod.objects.blocks.machines.specialfurnace.ContainerSpecialFurnace;
import john.mod.objects.blocks.machines.specialfurnace.GuiSpecialFurnace;
import john.mod.objects.blocks.machines.specialfurnace.TileEntitySpecialFurnace;
import john.mod.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == Reference.GUI_SPECIAL_FURNACE)
		{
			return new ContainerSpecialFurnace(player.inventory, (TileEntitySpecialFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == Reference.GUI_SPECIAL_FURNACE)
		{
			return new GuiSpecialFurnace(player.inventory, (TileEntitySpecialFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		}
		else if (ID == Reference.GUI_ELEMENT_CHOICE)
		{
			//return new GuiElementChoice(player);
		}
		return null;
	}
}
