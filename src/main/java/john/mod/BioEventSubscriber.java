package john.mod;

import ibxm.Player;
import john.mod.init.ItemInit;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class BioEventSubscriber
{
	@SubscribeEvent
	public void breakBlock(BlockEvent.BreakEvent event) {
		//ItemStack mainItem = event.getPlayer().inventory.getCurrentItem();
		System.out.println("Event active: breakBlock");
		if (event.getPlayer().inventory.getCurrentItem().getItem() == ItemInit.SWORD_FIRE)
		{
			System.out.println("Fire Sword found");
			if (event.getState().getBlock() == Blocks.STONE)
			{
				System.out.println("Stone Block found");
				event.getWorld().setBlockState((new BlockPos(event.getPos().getX()+1,event.getPos().getY(),event.getPos().getZ())), Blocks.LAVA.getDefaultState());
			}
		}
		else if (event.getState().getBlock() == Blocks.FIRE)
		{
			event.getPlayer().dropItem(new ItemStack(Blocks.FIRE), false);
			System.out.println("drop Fire");
		}
	}

	/*
	@SubscribeEvent
	public void livingDamageEventSub(LivingDamageEvent event)
	{
		if (event.getEntity().get .... ) // check für BioElements.FIRE
		{
			if (event.getSource() == DamageSource.ON_FIRE || event.getSource() == DamageSource.IN_FIRE)
			{
				event.setAmount(0);
			}
			else if (event.getSource() == DamageSource.LAVA)
			{
				event.setAmount(event.getAmount() * 0.5F);
			}
		}
	}
	*/
	/*
	@SubscribeEvent
	public void jumpEffect(LivingEvent.LivingJumpEvent event) // Hier muss check auf BioElements.AIR hin
	{
		System.out.println("Event active: jumpEffect");
		event.getEntity().motionY += 0.1D;

		event.getEntity().motionX *= 1.1D;
		event.getEntity().motionZ *= 1.1D;
	}
	*/
	/*
	@SubscribeEvent
	public void landingEvent(LivingFallEvent event) // Hier muss check auf BioElements.AIR hin
	{
		System.out.println("Event active: landingEvent");
		event.getEntity().fallDistance *= 0.5;
	}
	*/
	/*
	@SubscribeEvent
	public void playerTick(TickEvent.PlayerTickEvent event)
	{

	}
	*/
}