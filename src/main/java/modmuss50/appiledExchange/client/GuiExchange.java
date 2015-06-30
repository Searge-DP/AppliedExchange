package modmuss50.appiledExchange.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiExchange extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(
			"appliedexchange", "textures/gui/blockExchange.png");

	ContainerExchange containerExchange;

	public GuiExchange(ContainerExchange containerExchange) {
		super(containerExchange);
		this.xSize = 176;
		this.ySize = 166;
		this.containerExchange = (ContainerExchange) this.inventorySlots;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float arg0, int arg1, int arg2) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 166);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int arg0, int arg1) {
		String name = "Exchange Block";
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 5, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8,
				this.ySize - 96 + 2, 4210752);

		this.fontRendererObj.drawString(containerExchange.emc + " emc", 90,
				this.ySize - 96 + 2, 4210752);
		super.drawGuiContainerForegroundLayer(arg0, arg1);
	}
}
