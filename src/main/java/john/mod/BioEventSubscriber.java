package john.mod;

import john.mod.init.ItemInit;
import john.mod.util.Provider;
import john.mod.util.interfaces.IElementHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static john.mod.Main.getHandler;

@Mod.EventBusSubscriber
public class BioEventSubscriber {

	@SubscribeEvent
	public void breakBlock(BlockEvent.BreakEvent event)
	{
		//ItemStack mainItem = event.getPlayer().inventory.getCurrentItem();
		System.out.println("Event active: breakBlock");
		if (event.getPlayer().inventory.getCurrentItem().getItem() == ItemInit.SWORD_FIRE)
		{
			System.out.println("Fire Sword found");
			if (event.getState().getBlock() == Blocks.STONE)
			{
				System.out.println("Stone Block found");
				event.getWorld().setBlockState((new BlockPos(event.getPos().getX() + 1, event.getPos().getY(), event.getPos().getZ())), Blocks.LAVA.getDefaultState());
			}
		}
		else if (event.getState().getBlock() == Blocks.FIRE)
		{
			event.getPlayer().dropItem(new ItemStack(Blocks.FIRE), false);
			System.out.println("drop Fire");
		}
		System.out.println("Player element value: " + getHandler(event.getPlayer()).getElement());
	}

	@SubscribeEvent
	public void attachCapabilities(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityPlayer)
		{
			event.addCapability(new ResourceLocation("ntm", "/"), new Provider());
		}
	}

	@SubscribeEvent
	public void playerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		System.out.println("Event active: playerLogin");
		final IElementHandler player = getHandler(event.player);
		player.setElement(BioElements.ICE);
	}

	@SubscribeEvent
	public void livingDamageEventSub(LivingDamageEvent event)
	{
		System.out.println("Event active: livingDamageEventSub");
		if (event.getEntity() instanceof EntityPlayer)
		{
			System.out.println("Entity is EntityPlayer");
			if (getHandler(event.getEntity()).getElement() == BioElements.FIRE) // check für BioElements.FIRE
			{
				System.out.println("Check for Element succeeded");
				if (event.getSource() == DamageSource.ON_FIRE || event.getSource() == DamageSource.IN_FIRE)
				{
					System.out.println("Fire damage nullified");
					event.setAmount(0);
					event.getEntity().extinguish();
				}
				else if (event.getSource() == DamageSource.LAVA)
				{
					System.out.println("Lava damage halved");
					event.setAmount(event.getAmount() * 0.5F);
				}
			}
		}
	}

	@SubscribeEvent
	public void jumpEffect(LivingEvent.LivingJumpEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			System.out.println("jumpEvent: Entity is EntityPlayer");
			if (getHandler(event.getEntity()).getElement() == BioElements.AIR) // check für BioElements.AIR
			{
				System.out.println("Element is AIR, adjusting Jump");
				event.getEntity().motionY += 0.2D;

				event.getEntity().motionX *= 1.2D;
				event.getEntity().motionZ *= 1.2D;
			}
		}
	}

	@SubscribeEvent
	public void landingEvent(LivingFallEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			System.out.println("landingEvent: Entity is EntityPlayer");
			if (getHandler(event.getEntity()).getElement() == BioElements.AIR) // check für BioElements.AIR
			{
				System.out.println("Element is AIR, adjusting fall distance");
				event.getEntity().fallDistance *= 0.5;
			}
		}
	}


	@SubscribeEvent
	public void playerTick(TickEvent.PlayerTickEvent event) {
		//FoodStats param =
		if (getHandler(event.player).getElement() == BioElements.ICE)
		{
			event.player.getFoodStats().addExhaustion(-1.0F);
			//event.player.getFoodStats().
		}
	}

}