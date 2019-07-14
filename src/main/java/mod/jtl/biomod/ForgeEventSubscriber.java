package mod.jtl.biomod;

import mod.jtl.biomod.capability.IBioData;
import mod.jtl.biomod.capability.Provider;
import mod.jtl.biomod.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.AttachCapabilitiesEvent;
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
	public static void livingJump(final LivingEvent.LivingJumpEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)event.getEntity();

			IBioData playerData = player.getCapability(BIO_CAP).orElse(null);

			playerData.setElement(BioElements.AIR);

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
