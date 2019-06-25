package mod.jtl.biomod.setup;

import net.minecraft.world.World;

public class ServerProxy implements IProxy
{
	@Override
	public void init()
	{

	}

	@Override
	public World getClientWorld()
	{
		throw new IllegalStateException("This is the ServerProxy - Only run this on the client!");
	}
}
