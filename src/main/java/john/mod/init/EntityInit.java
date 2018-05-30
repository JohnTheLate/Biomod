package john.mod.init;

import john.mod.Main;
import john.mod.entity.EntityCentaur;
import john.mod.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
	public static void registerEntities()
	{
		registerEntity("centaur", EntityCentaur.class, Reference.ENTITY_CENTAUR, 50,9728080, 0);
	}

	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
	}
}
