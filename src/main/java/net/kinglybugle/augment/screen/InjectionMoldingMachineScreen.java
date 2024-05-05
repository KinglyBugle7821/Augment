package net.kinglybugle.augment.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.kinglybugle.augment.Augment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class InjectionMoldingMachineScreen extends AbstractContainerScreen<InjectionMoldingMachineMenu> {
    public static final int ARROW_X = 102;
    public static final int ARROW_Y = 14;
    public static final int FILLED_ARROW_X = 176;
    public static final int FILLED_ARROW_Y = 0;
    public static final int FILLED_ARROW_WIDTH = 8;

    private static final ResourceLocation TEXTURE = new ResourceLocation(Augment.MOD_ID, "textures/gui/container/injection_molding_machine_gui.png");
    public InjectionMoldingMachineScreen(InjectionMoldingMachineMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();

    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(pGuiGraphics, x, y);
    }
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + ARROW_X, y + ARROW_Y, FILLED_ARROW_X, FILLED_ARROW_Y, FILLED_ARROW_WIDTH, menu.getScaledProgress());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
