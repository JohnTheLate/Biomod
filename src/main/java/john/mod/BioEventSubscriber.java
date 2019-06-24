package john.mod;

import john.mod.init.ItemInit;
import john.mod.objects.kanohi.Kanohi;
import john.mod.packets.PacketMaskUpdate;
import john.mod.packets.PacketMaskUse;
import john.mod.packets.PacketUpdateElement;
import john.mod.proxy.ClientProxy;
import john.mod.replacements.CustomFoodStats;
import john.mod.util.Provider;
import john.mod.util.Reference;
import john.mod.util.handlers.PacketHandler;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

import static john.mod.Main.getBioData;
import static john.mod.Main.getElement;
import static net.minecraft.block.BlockLiquid.LEVEL;

@Mod.EventBusSubscriber
public class BioEventSubscriber
{
	// Attribute modifiers - these serve to define modifiers (multiplicative for speed, additive for health; depends on operationIn)
	public static final AttributeModifier moveSpeedBonus = new AttributeModifier(UUID.fromString("1a04f945-3de1-4dc6-a001-0aa1c9d0ba49"),"waterAirMoveBonus",0.15D,2);
	public static final AttributeModifier moveSpeedMalus = new AttributeModifier(UUID.fromString("f273d827-c85a-4a41-9558-a596a0a1a0ab"),"earthMoveMalus",-0.1D,2);
	public static final AttributeModifier healthBonus = new AttributeModifier(UUID.fromString("99de4a4f-ac98-42df-9a4a-99f34eb72446"),"fireHealthBonus",2.0D,0);
	public static final AttributeModifier healthMalus = new AttributeModifier(UUID.fromString("198e61b7-f346-4d4a-9a14-f16bb235c8a9"),"waterHealthMalus",-2.0D,0);

	public static final AttributeModifier moveSpeedBonusKakama = new AttributeModifier(UUID.fromString("dd66c466-5a1c-4be0-a223-cc2dacc80fe9"),"KakamaMoveBonus",10.0D,2);

	@SubscribeEvent
	public void playerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		System.out.println("Event active: playerLogin");
		EntityPlayer evPlayer = event.player;
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
				CustomFoodStats newFoodStats = new CustomFoodStats(oldFoodStats, getElement(evPlayer) == BioElements.ICE); // Creates a new CustomFoodStats, but gives the old FoodStats of the player to it

				System.out.println("Starting Reflection");
				ReflectionHelper.setPrivateValue(EntityPlayer.class, evPlayer, newFoodStats, 15); // Replaces the original foodstats of the player with the new CustomFoodStats
				System.out.println("Reflection done?");
				System.out.println("Is ice:" + ((CustomFoodStats)evPlayer.getFoodStats()).isIce());
			}
		}
		else
		if (event.getEntity() instanceof EntityFishHook)
		{
			//Used to allow Water to fish better (Lure level always 1 higher than enchanted - i.e. starting at 1 at unenchanted up to 4 at max enchanted (Ench.Lvl. 3).
			System.out.println("Fish hook detected");
			EntityFishHook evHook = (EntityFishHook)event.getEntity();
			if (getElement(evHook.getAngler()) == BioElements.WATER)
			{
				EntityPlayer evPlayer = evHook.getAngler();
				if (evPlayer.getHeldItemMainhand().getItem() == Items.FISHING_ROD)
				{
					//System.out.println("enchant value (main hand): " + EnchantmentHelper.getFishingSpeedBonus(evPlayer.getHeldItem(EnumHand.MAIN_HAND)));
					evHook.setLureSpeed(1 + EnchantmentHelper.getFishingSpeedBonus(evPlayer.getHeldItem(EnumHand.MAIN_HAND)));
				}
				else if (evPlayer.getHeldItemOffhand().getItem() == Items.FISHING_ROD)
				{
					//System.out.println("enchant value (off-hand): " + EnchantmentHelper.getFishingSpeedBonus(evPlayer.getHeldItem(EnumHand.OFF_HAND)));
					evHook.setLureSpeed(1 + EnchantmentHelper.getFishingSpeedBonus(evPlayer.getHeldItem(EnumHand.OFF_HAND)));
				}
			}
		}
	}

	@SubscribeEvent
	public void playerTick(TickEvent.PlayerTickEvent event)
	{
		EntityPlayer player = event.player;

		//Only on Server
		if (!player.world.isRemote && player.getAir() < 299)
		{
			if (getElement(player) == BioElements.WATER && player.ticksExisted % 2 == 0) //Water can breathe for longer
			{
				player.setAir(player.getAir() + 1);
			}
			else if (getElement(player) == BioElements.STONE && player.getAir() % 10 == 0) //Stone can breathe for shorter
			{
				player.setAir(player.getAir() - 2);
			}
		}

		//Client and Server
		if (player.isOnLadder() && getElement(player) == BioElements.AIR) //Air is supposed to climb faster
		{
			if (player.motionY <= -0.15D)
			{
				player.move(MoverType.SELF, 0,-0.15,0); //faster going down
			}

			if (player.collidedHorizontally)
			{
				player.motionY = 0.4D;
			}
		}

		//Used to reduce the sprinting speed of Earth element
		if (getElement(player) == BioElements.EARTH)
		{
			if (player.isSprinting() && !player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(moveSpeedMalus))
			{
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(moveSpeedMalus);
			}
			if (!player.isSprinting() && player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(moveSpeedMalus))
			{
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(moveSpeedMalus);
			}
		}

		if (!(player.inventory.armorItemInSlot(3).getItem() instanceof Kanohi) && getBioData(player).getMaskActive() == true)
		{
			getBioData(player).setMaskActive(false);
			player.sendStatusMessage(new TextComponentString(TextFormatting.RED + event.side.name() + " Mask unequipped, setting to: " + (getBioData(player).getMaskActive() ? "On" : "Off")), false);
		}

		if (getBioData(player).getMaskActive())
		{
			Item mask = player.inventory.armorItemInSlot(3).getItem();

			//Toa Masks; Pakari, Akaku functionality elsewhere

			//Server only
			if (!player.world.isRemote)
			{
				if (mask == ItemInit.KANOHI_HAU)
				{
					//Shielding
				}
				else if (mask == ItemInit.KANOHI_MIRU)
				{
					player.fallDistance = 0;
					//getBioData(player).modifyMaskCharge(+5);
					changeMaskCharge((EntityPlayerMP)player, +5);
				}
				else if (mask == ItemInit.KANOHI_KAUKAU)
				{
					if (player.getAir() < 299)
					{
						player.setAir(299);
						//getBioData(player).modifyMaskCharge(-2);
						changeMaskCharge((EntityPlayerMP)player, -2);
					}
					else if (player.getAir() == 300)
					{
						//getBioData(player).modifyMaskCharge(+4);
						changeMaskCharge((EntityPlayerMP)player, +4);
					}
				}
				else if (mask == ItemInit.KANOHI_PAKARI)
				{
					changeMaskCharge((EntityPlayerMP)player, +2);
				}

				//Turaga Masks; Mahiki, Matatu, Rau, Komau elsewhere
				else if (mask == ItemInit.KANOHI_HUNA)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 30, 1, false, false));
					//getBioData(player).modifyMaskCharge(-20);
					changeMaskCharge((EntityPlayerMP)player, -20);
				}
				else if (mask == ItemInit.KANOHI_RURU)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 30, 1, false, false));
					//getBioData(player).modifyMaskCharge(-2);
					changeMaskCharge((EntityPlayerMP)player, -2);
				}
			}

			//Server and Client
			if (mask == ItemInit.KANOHI_KAKAMA)
			{
				if (player.isSprinting())
				{
					if (!player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(moveSpeedBonusKakama))
					{
						player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(moveSpeedBonusKakama);
						player.stepHeight += 0.5F;
					}
					if (!player.world.isRemote) //Server only
					{
						//getBioData(player).modifyMaskCharge(-10);
						changeMaskCharge((EntityPlayerMP)player, -10);
						player.getFoodStats().addStats(0, 0.1F);
					}
				}
				else
				{
					if (player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(moveSpeedBonusKakama))
					{
						player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(moveSpeedBonusKakama);
						player.stepHeight -= 0.5F;
					}
					if (!player.world.isRemote) //Server only
					{
						//getBioData(player).modifyMaskCharge(+2);
						changeMaskCharge((EntityPlayerMP)player, +2);
					}
				}
			}

		}
		else
		{
			//getBioData(player).modifyMaskCharge(+10);
			if (!player.world.isRemote)
			{
				changeMaskCharge((EntityPlayerMP)player, +10);
			}

			if (player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(moveSpeedBonusKakama))
			{
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(moveSpeedBonusKakama);
			}
		}

		if (player.ticksExisted % 100 == 0)
		{
			player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + event.side.name() + " Mask: " + (getBioData(player).getMaskActive() ? "On" : "Off")  + " Charge: " + getBioData(player).getMaskCharge()), false);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onKeyInput(InputEvent.KeyInputEvent event)
	{
		//DEBUG
		//System.out.println("Key Input Event");

		EntityPlayer player = Minecraft.getMinecraft().player;

		// check each enumerated key binding type for pressed and take appropriate action

		if (ClientProxy.key_mask.isPressed() && player.inventory.armorItemInSlot(3).getItem() instanceof Kanohi)
		{
			// DEBUG
			System.out.println("Key binding =" + ClientProxy.key_mask.getKeyDescription());

			Item mask = player.inventory.armorItemInSlot(3).getItem();

			if (mask != ItemInit.KANOHI_MAHIKI)
			{
				if (getBioData(player).getMaskActive())
				{
					PacketHandler.INSTANCE.sendToServer(new PacketMaskUse(false));
					//getBioData(player).setMaskActive(false); //Handled on Server packet now
				}
				else
				{
					PacketHandler.INSTANCE.sendToServer(new PacketMaskUse(true));
					//getBioData(player).setMaskActive(true); //Handled on Server packet now
				}
			}
			else
			{
				//Open illusions GUI - will be added in the FAR future
			}
		}

		if (ClientProxy.key_hud.isPressed())
		{
			//DEBUG
			System.out.println("Key binding =" + ClientProxy.key_hud.getKeyDescription());

			//PacketHandler.INSTANCE.sendToServer(new PacketSendKey());
			//elementalInitialisation(Minecraft.getMinecraft().player, BioElements.FIRE);
			PacketHandler.INSTANCE.sendToServer(new PacketUpdateElement(BioElements.FIRE));
			getBioData(player).setElement(BioElements.FIRE);

			player.openGui(Main.instance, Reference.GUI_ELEMENT_CHOICE, player.world, 0, 0, 0);
		}

		if (ClientProxy.key_element.isPressed())
		{
			//DEBUG
			System.out.println("Key binding =" + ClientProxy.key_hud.getKeyDescription());

			//PacketHandler.INSTANCE.sendToServer(new PacketSendKey());
			//elementalInitialisation(Minecraft.getMinecraft().player, BioElements.AIR);
			PacketHandler.INSTANCE.sendToServer(new PacketUpdateElement(BioElements.AIR));
			getBioData(player).setElement(BioElements.AIR);
		}
	}

	@SubscribeEvent
	public void leftClickBlock(PlayerInteractEvent.LeftClickBlock event)
	{
		EntityPlayer evPlayer = event.getEntityPlayer();

		//Used to allow Fire element to collect fire. Cannot use BlockBreakEvent.
		if (getElement(evPlayer) == BioElements.FIRE)
		{
			BlockPos pos = event.getPos().offset(event.getFace());
			if (event.getWorld().getBlockState(pos).getBlock() == Blocks.FIRE && evPlayer.inventory.getCurrentItem().getItem() //random here to give 50% chance: && RANDOM.nextBoolean()
					!= ItemInit.SWORD_FIRE && evPlayer.inventory.getCurrentItem().getItem() != ItemInit.STAFF_FIRE)
			{
				System.out.println("drop Fire");

				if (evPlayer.getHeldItem(EnumHand.MAIN_HAND).isEmpty())
				{
					evPlayer.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ItemInit.BIO_FIRE_ITEM));
				}
				else if (!evPlayer.inventory.addItemStackToInventory(new ItemStack(ItemInit.BIO_FIRE_ITEM)))
				{
					evPlayer.dropItem(new ItemStack(ItemInit.BIO_FIRE_ITEM), false);
				}
			}
		}
	}

	@SubscribeEvent
	public void breakBlock(BlockEvent.BreakEvent event)
	{
		//ItemStack mainItem = event.getPlayer().inventory.getCurrentItem(); //What was this for?
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
	}

	@SubscribeEvent
	public void harvestChecker(net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck event)
	{
		//Ice can harvest snow and ice without tools
		if (getElement(event.getEntityPlayer()) == BioElements.ICE)
		{
			if (event.getTargetBlock().getMaterial() == Material.ICE || event.getTargetBlock().getMaterial() == Material.SNOW)
			{
				event.setCanHarvest(true);
			}
		}
	}

	@SubscribeEvent
	public void breakingSpeed(net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed event)
	{
		//Earth and Stone harvest things faster: Earth 20% everything, Stone 25% Sand and Stone.
		EntityPlayer player = event.getEntityPlayer();

		if (getElement(player) == BioElements.STONE)
		{
			if (event.getState().getMaterial() == Material.ROCK || event.getState().getMaterial() == Material.SAND)
			{
				event.setNewSpeed(event.getOriginalSpeed() * 1.25F);
			}
		}
		else if (getElement(player) == BioElements.EARTH)
		{
			event.setNewSpeed(event.getOriginalSpeed() * 1.2F);
		}

		if (getBioData(player).getMaskActive())
		{
			Item mask = player.inventory.armorItemInSlot(3).getItem();

			if (mask == ItemInit.KANOHI_PAKARI)
			{
				event.setNewSpeed(event.getNewSpeed() * 2);
				if (!player.world.isRemote)
				{
					changeMaskCharge((EntityPlayerMP)player, -7);
				}
				//getBioData(player).modifyMaskCharge(-5);
				if (player.ticksExisted % 20 == 0)
				{
					player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "Speeds: New: " + event.getNewSpeed() + " Old: " + event.getOriginalSpeed()), false);
				}
			}
		}

	}

	@SubscribeEvent //Runs on server only
	public void livingHurtEventSub(LivingHurtEvent event)  //this method checks player elements and damage types to determine whether to affect the damage received
	{
		//System.out.println("Event active: livingDamageEventSub");
		if (event.getEntity() instanceof EntityPlayer)
		{
			//System.out.println("Entity is EntityPlayer");
			if (getElement(event.getEntity()) == BioElements.FIRE) //Fire receives reduced lava damage and none from fire at all
			{
				//System.out.println("Check for Element succeeded");
				if (event.getSource() == DamageSource.ON_FIRE || event.getSource() == DamageSource.IN_FIRE)
				{
					System.out.println("Fire damage nullified");
					event.setAmount(0);
					event.getEntity().extinguish();
					event.setCanceled(true);
				}
				else if (event.getSource() == DamageSource.LAVA)
				{
					System.out.println("Lava damage halved");
					event.setAmount(event.getAmount() * 0.5F);
				}
			}
			else if (getElement(event.getEntity()) == BioElements.ICE) //Ice receives extra lava damage
			{
				if (event.getSource() == DamageSource.LAVA)
				{
					System.out.println("Lava damage increased");
					event.setAmount(event.getAmount() + 1.0F);
				}
			}
			else if (event.getSource() == DamageSource.FALL && (getElement(event.getEntity()) == BioElements.AIR)) //Air receives reduced fall damage
			{
				System.out.println("Fall damage halved and reduced by half a heart");
				event.setAmount(event.getAmount() * 0.5F - 1.0F);
				if (event.getAmount() == 0)
				{
					event.setCanceled(true);
				}
			}
			else if (getElement(event.getEntity()) == BioElements.WATER && event.getSource() == DamageSource.DROWN) //Water receives reduced drown damage
			{
				System.out.println("Drown damage halved");
				event.setAmount(event.getAmount() * 0.5F);
				//event.getEntity().setAir(20); //this will double the time it takes to drown, but also make the graphic re-appear
			}

			if (getBioData(event.getEntity()).getMaskActive() && ((EntityPlayer)event.getEntity()).inventory.armorItemInSlot(3).getItem() == ItemInit.KANOHI_KAKAMA) //Kakama gives reduced fall damage
			{
				System.out.println("Fall damage reduced by 25% and two hearts");
				event.setAmount(event.getAmount() * 0.75F - 4.0F);
				if (event.getAmount() == 0)
				{
					event.setCanceled(true);
				}
			}
		}
		//Check for Pakari on attacker
		Entity causer = event.getSource().getTrueSource();
		if (causer instanceof EntityPlayerMP)
		{
			if (getBioData(causer).getMaskActive() && ((EntityPlayer)causer).inventory.armorItemInSlot(3).getItem() == ItemInit.KANOHI_PAKARI)
			{
				event.setAmount(event.getAmount() + 4);
				changeMaskCharge((EntityPlayerMP)causer, -400);
				((EntityPlayer)causer).sendStatusMessage(new TextComponentString(TextFormatting.DARK_GRAY + "Damage: " + event.getAmount()), false);
			}
		}
	}

	@SubscribeEvent
	public void jumpEffect(LivingEvent.LivingJumpEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer evPlayer = (EntityPlayer)event.getEntity();

			System.out.println("Element: " + getElement(evPlayer));
			System.out.println("Element: " + getElement(Minecraft.getMinecraft().player));

			if (getElement(evPlayer) == BioElements.AIR) // check für BioElements.AIR
			{
				//System.out.println("Element is AIR, adjusting Jump");
				evPlayer.motionY += 0.1D;
			}
			//else
			//{
			//System.out.println("Element is not air here");
			//}

			if (getBioData(evPlayer).getMaskActive() && evPlayer.inventory.armorItemInSlot(3).getItem() == ItemInit.KANOHI_MIRU && getBioData(evPlayer).getMaskCharge() >= 1000)
			{
				evPlayer.motionY += 0.7D;
				evPlayer.motionX *= 4.0D;
				evPlayer.motionZ *= 4.0D;
				//getBioData(evPlayer).modifyMaskCharge(-1000);
				if (!evPlayer.world.isRemote)
				{
					changeMaskCharge((EntityPlayerMP)evPlayer, -1000);
				}
			}
		}
	}

/*	@SubscribeEvent
	public void landingEvent(LivingFallEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			System.out.println("landingEvent: Entity is EntityPlayer");
			if (getElement(event.getEntity()) == BioElements.AIR) // check für BioElements.AIR
			{
				System.out.println("Element is AIR, adjusting fall distance");
				event.getEntity().fallDistance = 0;
			}
		}
	}*/

	public static void changeMaskCharge(EntityPlayerMP player,  int modifier)
	{
		if (!getBioData(player).modifyMaskCharge(modifier)) //Reduces mask charge, if dips below 0, returns false - in that case, turn off mask
		{
			PacketHandler.INSTANCE.sendTo(new PacketMaskUpdate(false), player);
		}
	}

	public static void elementalInitialisation(EntityPlayer player, BioElements element)
	{
		//final double defaultHealth = 20.D;
		//final float defaultWalkSpeed = 0.1F;

		// Get rid of any possible modifiers
		if (player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(moveSpeedBonus))
		{
			player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(moveSpeedBonus);
		}
		if (player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(moveSpeedMalus))
		{
			player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(moveSpeedMalus);
		}
		if (player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).hasModifier(healthBonus))
		{
			player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(healthBonus);
		}
		if (player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).hasModifier(healthMalus))
		{
			player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(healthMalus);
		}
		// Apply where needed
		switch(element)
		{
			case FIRE:
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(healthBonus);

				if (player.getHealth() == player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue())
				{
					player.setHealth(player.getHealth() + 2); //This is to heal the player up by the 2 HP/1 heart if the element was just switched to.
				}
				break;

			case AIR:
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(moveSpeedBonus);
				break;

			case WATER:
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(healthMalus);
				player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(moveSpeedBonus);
				//Swim speed requires newer forge version! http://www.minecraftforge.net/forum/topic/67289-solved-missing-swim-speed-attribute/
				break;

			case EARTH:
				//evPlayer.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(moveSpeedMalus);
				break;

			default:
				break;
		}
	}










	/* @SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderingEvent (RenderWorldLastEvent event)
	{
		event.get

		// your positions. You might want to shift them a bit too
		int sX = player.getPosition().getX();
		int sY = player.getPosition().getY();
		int sZ = player.getPosition().getZ();
		// Usually the player
		Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
		//Interpolating everything back to 0,0,0. These are transforms you can find at RenderEntity class
		double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)evt.getPartialTicks();
		double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)evt.getPartialTicks();
		double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)evt.getPartialTicks();
		//Apply 0-our transforms to set everything back to 0,0,0
		Tessellator.getInstance().getBuffer().setTranslation(-d0, -d1, -d2);
		//Your render function which renders boxes at a desired position. In this example I just copy-pasted the one on TileEntityStructureRenderer
		renderBox(Tessellator.getInstance(), Tessellator.getInstance().getBuffer(), sX, sY, sZ, sX + 1, sY + 1, sZ + 1, 125,125,125);
		//When you are done rendering all your boxes reset the offsets. We do not want everything that renders next to still be at 0,0,0 :)
		Tessellator.getInstance().getBuffer().setTranslation(0, 0, 0);

	}

	private void renderBox(Tessellator p_190055_1_, BufferBuilder p_190055_2_, double p_190055_3_, double p_190055_5_, double p_190055_7_, double p_190055_9_, double p_190055_11_, double p_190055_13_, int p_190055_15_, int p_190055_16_, int p_190055_17_)
	{
		GlStateManager.glLineWidth(2.0F);
		p_190055_2_.begin(3, DefaultVertexFormats.POSITION_COLOR);
		p_190055_2_.pos(p_190055_3_, p_190055_5_, p_190055_7_).color((float)p_190055_16_, (float)p_190055_16_, (float)p_190055_16_, 0.0F).endVertex();
		p_190055_2_.pos(p_190055_3_, p_190055_5_, p_190055_7_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_9_, p_190055_5_, p_190055_7_).color(p_190055_16_, p_190055_17_, p_190055_17_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_9_, p_190055_5_, p_190055_13_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_3_, p_190055_5_, p_190055_13_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_3_, p_190055_5_, p_190055_7_).color(p_190055_17_, p_190055_17_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_3_, p_190055_11_, p_190055_7_).color(p_190055_17_, p_190055_16_, p_190055_17_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_9_, p_190055_11_, p_190055_7_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_9_, p_190055_11_, p_190055_13_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_3_, p_190055_11_, p_190055_13_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_3_, p_190055_11_, p_190055_7_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_3_, p_190055_11_, p_190055_13_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_3_, p_190055_5_, p_190055_13_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_9_, p_190055_5_, p_190055_13_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_9_, p_190055_11_, p_190055_13_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_9_, p_190055_11_, p_190055_7_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_9_, p_190055_5_, p_190055_7_).color(p_190055_16_, p_190055_16_, p_190055_16_, p_190055_15_).endVertex();
		p_190055_2_.pos(p_190055_9_, p_190055_5_, p_190055_7_).color((float)p_190055_16_, (float)p_190055_16_, (float)p_190055_16_, 0.0F).endVertex();
		p_190055_1_.draw();
		GlStateManager.glLineWidth(1.0F);
	} */
}