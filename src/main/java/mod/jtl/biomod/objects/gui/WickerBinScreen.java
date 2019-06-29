package mod.jtl.biomod.objects.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import mod.jtl.biomod.Biomod;
import mod.jtl.biomod.init.ModBlocks;
import mod.jtl.biomod.objects.container.WickerBinContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class WickerBinScreen extends ContainerScreen<WickerBinContainer>
{
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Biomod.MODID, "textures/gui/wickerbin.png");

	public WickerBinScreen(WickerBinContainer container, PlayerInventory inventory, ITextComponent name)
	{
		super(container, inventory, ModBlocks.WICKERBIN.getNameTextComponent());
		this.ySize = 133;
	}

	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
	}

	/**
	 * Draws the background layer of this container (behind the items).
	 */
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.blit(i, j, 0, 0, this.xSize, this.ySize);
	}
}
