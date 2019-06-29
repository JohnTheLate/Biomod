package mod.jtl.biomod.setup;

import mod.jtl.biomod.objects.gui.WickerBinScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import static mod.jtl.biomod.init.ModGuis.WICKER_BIN_CONTAINER;

public class ClientProxy implements IProxy
{
	@Override
	public void init()
	{
		ScreenManager.registerFactory(WICKER_BIN_CONTAINER, (WickerBinScreen::new));
	}

	@Override
	public World getClientWorld()
	{
		return Minecraft.getInstance().world;
	}

	@Override
	public PlayerEntity getClientPlayer()
	{
		return Minecraft.getInstance().player;
	}
}
