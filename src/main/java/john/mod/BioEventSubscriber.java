package john.mod;

import john.mod.init.ItemInit;
import john.mod.replacements.CustomFoodStats;
import john.mod.util.Provider;
import john.mod.util.interfaces.IElementHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static john.mod.Main.getHandler;
import static john.mod.Main.setLocalPlayerElement;
import static net.minecraft.block.BlockLiquid.LEVEL;

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
				event.getWorld().setBlockState(event.getPos(), Blocks.FLOWING_LAVA.getDefaultState().withProperty(LEVEL, Integer.valueOf(14)));
				event.setCanceled(true);
			}
		}
		else if (event.getState().getBlock() == Blocks.FIRE) //This is not functional at the moment!
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
		getHandler(event.player).setElement(BioElements.WATER);
		BioElements element = getHandler(event.player).getElement();
		final double defaultHealth = 20.D;
		final float defaultWalkSpeed = 0.1F;
		switch(element)
		{
			case FIRE:
				event.player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(22.0D);
				event.player.capabilities.setPlayerWalkSpeed(defaultWalkSpeed);
				break;

			case AIR:
				event.player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(defaultHealth);
				event.player.capabilities.setPlayerWalkSpeed(0.115F);
				break;

			case WATER:
				event.player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
				event.player.capabilities.setPlayerWalkSpeed(0.115F);
				//Swim speed requires newer forge version! http://www.minecraftforge.net/forum/topic/67289-solved-missing-swim-speed-attribute/
				break;

			default:
				event.player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(defaultHealth);
				event.player.capabilities.setPlayerWalkSpeed(defaultWalkSpeed);
				//Reset swim speed to normal once implemented at WATER
				break;
		}

		//getHandler(Minecraft.getMinecraft().player).setElement(BioElements.AIR);
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
			else if (getHandler(event.getEntity()).getElement() == BioElements.AIR && event.getSource() == DamageSource.FALL)
			{
				System.out.println("Fall damage halved");
				event.setAmount(event.getAmount() * 0.5F);
			}
			else if (getHandler(event.getEntity()).getElement() == BioElements.WATER && event.getSource() == DamageSource.DROWN)
			{
				System.out.println("Drown damage halved");
				event.setAmount(event.getAmount() * 0.5F);
				//event.getEntity().setAir(20); //this will double the time it takes to drown, but also make the graphic re-appear
			}
		}
	}

	@SubscribeEvent
	public void jumpEffect(LivingEvent.LivingJumpEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			System.out.println("jumpEvent: Entity is EntityPlayer");

			EntityPlayer evPlayer = (EntityPlayer)event.getEntity();

			//if (evPlayer.world.isRemote)
			//{
			/*if (getHandler(event.getEntity()).getElement() == BioElements.AIR) // check für BioElements.AIR
			{
				System.out.println("Element is AIR, adjusting Jump");
				event.getEntity().motionY += 3.0D;
				((EntityPlayer) event.getEntity()).getFoodStats().setFoodLevel(18);

				event.getEntity().motionX *= 5.2D;
				event.getEntity().motionZ *= 5.2D;
			}*/

			System.out.println("Element: " + getHandler(evPlayer).getElement());
			System.out.println("Element: " + getHandler(Minecraft.getMinecraft().player).getElement());

				Boolean abc = getHandler(evPlayer).getElement() == BioElements.AIR;

				if (abc) // check für BioElements.AIR
				{
					System.out.println("Element is AIR, adjusting Jump");
					evPlayer.motionX += 1.0D;
				}
				else
				{
					System.out.println("Element is not air here");
				}

			//}
		}
	}

/*	@SubscribeEvent
	public void landingEvent(LivingFallEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			System.out.println("landingEvent: Entity is EntityPlayer");
			if (getHandler(event.getEntity()).getElement() == BioElements.AIR) // check für BioElements.AIR
			{
				System.out.println("Element is AIR, adjusting fall distance");
				event.getEntity().fallDistance *= 0.5;
			}
		}
	}*/


	@SubscribeEvent
	public void playerTick(TickEvent.PlayerTickEvent event)
	{
		if (event.player.getAir() < 299 && getHandler(event.player).getElement() == BioElements.WATER)
		{
			if (event.player.ticksExisted % 2 == 0)
			{
				event.player.setAir(event.player.getAir() + 1);
			}
			//event.player.getFoodStats().addExhaustion(1.0F);
		}
	}

	@SubscribeEvent
	public void entityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			System.out.println("entityJoinWorld: Entity is EntityPlayer");
			EntityPlayer evPlayer = (EntityPlayer)event.getEntity();
			FoodStats oldFoodStats = evPlayer.getFoodStats();
			if (oldFoodStats instanceof CustomFoodStats || !(oldFoodStats.getClass() == FoodStats.class)) // Check to prevent nesting
			{
				System.out.println("oldFootStats are already CustomFoodStats, no reflection to be done");
			}
			else
			{
				CustomFoodStats newFoodStats = new CustomFoodStats(oldFoodStats, getHandler(evPlayer).getElement() == BioElements.ICE); // Creates a new CustomFoodStats, but gives the old FoodStats of the player to it

				System.out.println("Starting Reflection");
				ReflectionHelper.setPrivateValue(EntityPlayer.class, evPlayer, newFoodStats, 15); // Replaces the original foodstats of the player with the new CustomFoodStats
				System.out.println("Reflection done?");
			}


		}
	}
}