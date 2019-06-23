package john.mod.objects.blocks.machines.specialfurnace;

import john.mod.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSpecialFurnace extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/specialfurnace.png");
	private final InventoryPlayer player;
	private final TileEntitySpecialFurnace tileentity;

	public GuiSpecialFurnace(InventoryPlayer player, TileEntitySpecialFurnace tileentity)
	{
		super(new ContainerSpecialFurnace(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String tilename = this.tileentity.getDisplayName().getFormattedText();
		this.fontRenderer.drawString(tilename, (this.xSize / 2 - this.fontRenderer.getStringWidth(tilename)/2) + 3, 8, 4210752); //Get middle of GUI, middle of Name (to show it in the center)
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 332, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);;
		this.mc.getTextureManager().bindTexture(TEXTURES);
		//Video 5; 5:35 Minuten
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		if (TileEntitySpecialFurnace.isBurning(tileentity))
		{
			int k = getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 56, this.guiTop + 37 + 12 - k,176,13 - k,14, k);
		}
		int l = getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 34, 176, 14, l + 1, 16);
	}

	private int getBurnLeftScaled(int pixels) //Left as in time left, not the direction
	{
		int i = this.tileentity.getField(1);
		if (i == 0)
		{
			i = 200;
		}
		return this.tileentity.getField(0) * pixels / i;
	}

	private int getCookProgressScaled(int pixels)
	{
		int i = this.tileentity.getField(2);
		int j = this.tileentity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
