package john.mod.packets;

import io.netty.buffer.ByteBuf;
import john.mod.util.handlers.PacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static john.mod.Main.CAPABILITY_BIO_PLAYER_DATA;
import static john.mod.Main.getBioData;

public class PacketMaskUse implements IMessage
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

	public PacketMaskUse(Boolean onOff)
	{
		this.status = onOff;
	}

	public PacketMaskUse()
	{
	}

	public static class Handler implements IMessageHandler<PacketMaskUse, IMessage>
	{
		@Override
		public IMessage onMessage(PacketMaskUse message, MessageContext context)
		{
			// Always use a construct like this to actually handle your message. This ensures that
			// your 'handle' code is run on the main Minecraft thread. 'onMessage' itself
			// is called on the networking thread so it is not safe to do a lot of things
			// here.
			FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(() -> handle(message, context));
			return null;
		}

		private void handle(PacketMaskUse message, MessageContext context)
		{
			EntityPlayerMP playerEntity = context.getServerHandler().player;

			getBioData(playerEntity).setMaskActive(message.status);

			playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + "Server: Mask: " + (message.status ? "On" : "Off")), false);

			PacketHandler.INSTANCE.sendTo(new PacketMaskUpdate(message.status), playerEntity);
		}
	}
}