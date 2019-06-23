package john.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static john.mod.Main.getBioData;

public class PacketMaskUpdate implements IMessage
{
	private Boolean status;

	@Override
	public void fromBytes(ByteBuf buf)
	{
		status = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(status);
	}

	public PacketMaskUpdate(Boolean onOff)
	{
		this.status = onOff;
	}

	public PacketMaskUpdate()
	{
	}

	public static class Handler implements IMessageHandler<PacketMaskUpdate, IMessage>
	{
		@Override
		public IMessage onMessage(PacketMaskUpdate message, MessageContext context)
		{
			// Always use a construct like this to actually handle your message. This ensures that
			// your 'handle' code is run on the main Minecraft thread. 'onMessage' itself
			// is called on the networking thread so it is not safe to do a lot of things
			// here.
			FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(() -> handle(message, context));
			return null;
		}

		private void handle(PacketMaskUpdate message, MessageContext context)
		{
			EntityPlayer playerEntity = Minecraft.getMinecraft().player;

			getBioData(playerEntity).setMaskActive(message.status);

			playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + "Client: Mask: " + (message.status ? "On" : "Off")), false);
		}
	}
}