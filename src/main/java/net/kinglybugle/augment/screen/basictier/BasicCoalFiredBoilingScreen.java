package net.kinglybugle.augment.screen.basictier;

import com.mojang.blaze3d.systems.RenderSystem;
import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.blocks.entity.basictier.BasicCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.simpletier.SimpleCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.screen.renderer.EnergyInfoArea;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class BasicCoalFiredBoilingScreen extends AbstractContainerScreen<BasicCoalFiredBoilingMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Augment.MOD_ID, "textures/gui/container/coal_fired_boiler_gui.png");
    private EnergyInfoArea energyInfoArea;

    public BasicCoalFiredBoilingScreen(BasicCoalFiredBoilingMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        assignEnergyInfoArea();
    }

    private void assignEnergyInfoArea() {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        energyInfoArea = new EnergyInfoArea(x + 146, y + 11, menu.blockEntity.getEnergyStorage());
    }

    @Override
    public void renderBg(@NotNull GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(pGuiGraphics, x, y);
        energyInfoArea.draw(pGuiGraphics.pose());
    }
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 71, y + 32, 176, 0, menu.getScaledProgress() + 1, 16);
        }
    }
    private static final int ENERGY_LEFT = 146;
    private static final int ENERGY_TOP = 11;
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
        energyInfoArea.draw(guiGraphics.pose());

        BasicCoalFiredBoilerBlockEntity blockEntity = menu.getBlockEntity();

        if (mouseX >= leftPos + ENERGY_LEFT && mouseX < leftPos + ENERGY_LEFT + 16 &&
                mouseY >= topPos + ENERGY_TOP && mouseY < topPos + ENERGY_TOP + 65) {
            int power = blockEntity.getEnergyStorage().getEnergyStored();
            double powerStoredkFE = (double) power / 1000;
            String formattedStoredPower = String.format("%.2f", powerStoredkFE);
            double powerCapacitykFE = (double) BasicCoalFiredBoilerBlockEntity.CAPACITY / 1000;
            String formattedCapacityPower = String.format("%.2f", powerCapacitykFE);
            guiGraphics.renderTooltip(this.font, Component.translatable("gui.augment.screen.energy_stored", formattedStoredPower, formattedCapacityPower), mouseX, mouseY);
        }
    }
}
