package john.mod.packets;

import io.netty.buffer.ByteBuf;
import john.mod.BioElements;
import john.mod.BioEventSubscriber;
import john.mod.replacements.CustomFoodStats;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.FoodStats;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static john.mod.Main.CAPABILITY_BIO_PLAYER_DATA;

public class PacketUpdateElement implements IMessage
{
	private BioElements element;

	@Override
	public void fromBytes(ByteBuf buf)
	{
		element = BioElements.values()[buf.readByte()];
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeByte(element.ordinal()); //Maximum of 256 different values possible.
	}

	public PacketUpdateElement(BioElements element)
	{
		this.element = element;
	}

	public PacketUpdateElement()
	{
	}

	public static class Handler implements IMessageHandler<PacketUpdateElement, IMessage>
	{
		@Override
		public IMessage onMessage(PacketUpdateElement message, MessageContext context)
		{
			// Always use a construct like this to actually handle your message. This ensures that
			// your 'handle' code is run on the main Minecraft thread. 'onMessage' itself
			// is called on the networking thread so it is not safe to do a lot of things
			// here.
			FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(() -> handle(message, context));
			return null;
		}

		private void handle(PacketUpdateElement message, MessageContext context)
		{
			EntityPlayerMP playerEntity = context.getServerHandler().player;

			if (playerEntity.hasCapability(CAPABILITY_BIO_PLAYER_DATA, null))
			{
				playerEntity.getCapability(CAPABILITY_BIO_PLAYER_DATA, null).setElement(message.element);
			}

			BioEventSubscriber.elementalInitialisation(playerEntity, message.element);

			FoodStats foodStats = playerEntity.getFoodStats();

			if (foodStats.getClass() == CustomFoodStats.class)
			{
				if (message.element == BioElements.ICE)
				{
					((CustomFoodStats)foodStats).setIceStatus(true);
				}
				else
				{
					((CustomFoodStats)foodStats).setIceStatus(false);
				}
			}

			playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Selected element from packet: " + message.element.name()), false);
		}
	}
}