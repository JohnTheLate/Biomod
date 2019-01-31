package john.mod;

import net.minecraft.client.Minecraft;

import static john.mod.Main.getHandler;

public class WaitForPlayerInitThread implements Runnable
{
	//This class will loop in a thread of its own to check for the player not being null to implement its effects.

	private BioElements element;

	WaitForPlayerInitThread(BioElements element)
	{
		this.element = element;
	}

	@Override
	public void run()
	{
		try
		{
			while (Minecraft.getMinecraft().player == null)
			{
				System.out.println("player is null, sleeping for 2 seconds");
				Thread.sleep(2000);
			}
			getHandler(Minecraft.getMinecraft().player).setElement(element);
		}
		catch (InterruptedException ie)
		{
			ie.printStackTrace();
		}
		System.out.println("Runnable finished");
	}
}