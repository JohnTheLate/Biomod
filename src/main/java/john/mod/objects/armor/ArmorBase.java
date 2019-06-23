package john.mod.objects.armor;

import john.mod.Main;
import john.mod.init.ItemInit;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class ArmorBase extends ItemArmor implements IHasModel
{

	public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	/*
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(final ItemStack stack, final EntityPlayer player, final ScaledResolution resolution, final float partialTicks, final boolean hasScreen, final int mouseX, final int mouseY) {
		final double w = resolution.getScaledWidth_double();
		final double h = resolution.getScaledHeight_double();
		GL11.glDisable(2929);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.2f);
		GL11.glDisable(3008);
		GL11.glDisable(3553);
		final Tessellator tessellator = Tessellator.getInstance();
		tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		//tessellator.startDrawingQuads();
		tessellator.getBuffer().pos(0.0, h, -90.0);
		tessellator.getBuffer().pos(w, h, -90.0);
		tessellator.getBuffer().pos(w, 0.0, -90.0);
		tessellator.getBuffer().pos(0.0, 0.0, -90.0);
		tessellator.draw();
		GL11.glEnable(3553);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		//Minecraft.getMinecraft().renderEngine.bindTexture(ItemSonarGoggles.texture);
		tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		tessellator.getBuffer().pos(0.0, h, -90.0).tex(0.0, 1.0);
		tessellator.getBuffer().pos(w, h, -90.0).tex(1.0, 1.0);
		tessellator.getBuffer().pos(w, 0.0, -90.0).tex(1.0, 0.0);
		tessellator.getBuffer().pos(0.0, 0.0, -90.0).tex(0.0, 0.0);
		tessellator.draw();
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	}
	*/
}
