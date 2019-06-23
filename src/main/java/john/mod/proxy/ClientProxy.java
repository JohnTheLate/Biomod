package john.mod.proxy;

import john.mod.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy
{
	public static KeyBinding key_mask = new KeyBinding("key.mask",Keyboard.KEY_M, "key.biomod.category");
	public static KeyBinding key_hud = new KeyBinding("key.hud", Keyboard.KEY_H, "key.biomod.category");
	public static KeyBinding key_element = new KeyBinding("key.element", Keyboard.KEY_J, "key.biomod.category");

	@Override
	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	@Override
	public void registerVariantRenderer(Item item, int meta, String filename, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
	}
}
