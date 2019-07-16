package mod.jtl.biomod;

import mod.jtl.biomod.capability.IBioData;
import mod.jtl.biomod.capability.Provider;
import mod.jtl.biomod.init.ModItems;
import mod.jtl.biomod.replacements.CustomFoodStats;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Items;
import net.minecraft.util.FoodStats;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import static mod.jtl.biomod.capability.Provider.BIO_CAP;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;

@EventBusSubscriber(modid = Biomod.MODID, bus = FORGE)
public class ForgeEventSubscriber
{
	@SubscribeEvent
	public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof PlayerEntity)
		{
			event.addCapability(new ResourceLocation(Biomod.MODID, "biodata"), new Provider());
		}
	}

	@SubscribeEvent
	public void entityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)event.getEntity();

			FoodStats oldFoodStats = player.getFoodStats();

			if (oldFoodStats instanceof CustomFoodStats || !(oldFoodStats.getClass() == FoodStats.class)) // Check to prevent nesting
			{
				System.out.println("oldFootStats are already CustomFoodStats, no replacement to be done");
			}
			else
			{
				CustomFoodStats newFoodStats = new CustomFoodStats(oldFoodStats, player.getCapability(BIO_CAP).orElse(null).getElement() == BioElements.ICE); // Creates a new CustomFoodStats, but gives the old FoodStats of the player to it

				System.out.println("Is ice: " + ((CustomFoodStats) player.getFoodStats()).getIsIce());
			}
		}
		else if (event.getEntity() instanceof FishingBobberEntity)
		{
			//Used to allow Water to fish better (Lure level always 1 higher than enchanted - i.e. starting at 1 at unenchanted up to 4 at max enchanted (Ench.Lvl. 3).
			System.out.println("Fish hook detected");
			FishingBobberEntity evHook = (FishingBobberEntity)event.getEntity();
			if (evHook.getAngler().getCapability(BIO_CAP).orElse(null).getElement() == BioElements.WATER)
			{
				PlayerEntity evPlayer = evHook.getAngler();
				if (evPlayer.getHeldItemMainhand().getItem() == Items.FISHING_ROD)
				{
					//System.out.println("enchant value (main hand): " + EnchantmentHelper.getFishingSpeedBonus(evPlayer.getHeldItem(EnumHand.MAIN_HAND)));
					evHook.setLureSpeed(1 + EnchantmentHelper.getFishingSpeedBonus(evPlayer.getHeldItem(Hand.MAIN_HAND)));
				}
				else if (evPlayer.getHeldItemOffhand().getItem() == Items.FISHING_ROD)
				{
					//System.out.println("enchant value (off-hand): " + EnchantmentHelper.getFishingSpeedBonus(evPlayer.getHeldItem(EnumHand.OFF_HAND)));
					evHook.setLureSpeed(1 + EnchantmentHelper.getFishingSpeedBonus(evPlayer.getHeldItem(Hand.OFF_HAND)));
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void livingJump(final LivingEvent.LivingJumpEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)event.getEntity();

			IBioData playerData = player.getCapability(BIO_CAP).orElse(null);

			playerData.setElement(BioElements.STONE);

			if (playerData.getElement() == BioElements.AIR)
			{
				player.setMotion(new Vec3d(player.getMotion().getX(), player.getMotion().getY() + 0.1D, player.getMotion().getZ()));
			}

			if (playerData.getMaskActive() && player.inventory.armorItemInSlot(3).getItem() == ModItems.KANOHI_MIRU && playerData.getMaskCharge() >= 1000)
			{
				player.setMotion(new Vec3d(player.getMotion().getX() * 4.0D, player.getMotion().getY() + 0.7D, player.getMotion().getZ() * 4.0D));
				if (!player.world.isRemote)
				{
					//changeMaskCharge((PlayerEntityMP)player, -1000);
				}
			}
		}
	}
}
