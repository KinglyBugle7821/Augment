package net.kinglybugle.augment.screen.reinforcedtier;

import com.mojang.blaze3d.systems.RenderSystem;
import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.blocks.entity.advancedtier.AdvancedInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.reinforcedtier.ReinforcedInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.simpletier.SimpleInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.screen.renderer.EnergyInfoArea;
import net.kinglybugle.augment.screen.renderer.FluidTankRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.FluidStack;

public class ReinforcedInjectionMoldingMachineScreen extends AbstractContainerScreen<ReinforcedInjectionMoldingMachineMenu> {
    public static final int ARROW_X = 102;
    public static final int ARROW_Y = 14;
    public static final int FILLED_ARROW_X = 176;
    public static final int FILLED_ARROW_Y = 0;
    public static final int FILLED_ARROW_WIDTH = 8;

    public static final int ENERGY_BAR_X = 146;
    public static final int ENERGY_BAR_Y = 11;
    public static final int ENERGY_BAR_WIDTH = 16;
    public static final int ENERGY_BAR_HEIGHT = 65;

    public static final int FLUID_BAR_X = 122;
    public static final int FLUID_BAR_Y = 11;
    public static final int FLUID_BAR_WIDTH = 16;
    public static final int FLUID_BAR_HEIGHT = 65;

    private FluidTankRenderer renderer;

    private static final ResourceLocation TEXTURE = new ResourceLocation(Augment.MOD_ID, "textures/gui/container/injection_molding_machine_gui.png");
    public EnergyInfoArea energyInfoArea;
    public ReinforcedInjectionMoldingMachineScreen(ReinforcedInjectionMoldingMachineMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        assignEnergyInfoArea();
        assignFluidRenderer();
    }

    private void assignFluidRenderer() {
        renderer = new FluidTankRenderer(ReinforcedInjectionMoldingMachineBlockEntity.FLUID_CAPACITY, true, FLUID_BAR_WIDTH, FLUID_BAR_HEIGHT);
    }

    private void assignEnergyInfoArea() {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        energyInfoArea = new EnergyInfoArea(x + ENERGY_BAR_X, y + ENERGY_BAR_Y, menu.blockEntity.getEnergyStorage());
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
        energyInfoArea.draw(pGuiGraphics.pose());
        renderer.render(pGuiGraphics.pose(), x + FLUID_BAR_X, y + FLUID_BAR_Y, menu.getFluidStack());
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
        energyInfoArea.draw(guiGraphics.pose());

        ReinforcedInjectionMoldingMachineBlockEntity blockEntity = menu.getBlockEntity();

        if (mouseX >= leftPos + ENERGY_BAR_X && mouseX < leftPos + ENERGY_BAR_X + ENERGY_BAR_WIDTH &&
                mouseY >= topPos + ENERGY_BAR_Y && mouseY < topPos + ENERGY_BAR_Y + ENERGY_BAR_HEIGHT) {
            int power = blockEntity.getEnergyStorage().getEnergyStored();
            double powerStoredkFE = (double) power / 1000;
            String formattedStoredPower = String.format("%.2f", powerStoredkFE);
            double powerCapacitykFE = (double) ReinforcedInjectionMoldingMachineBlockEntity.ENERGY_CAPACITY / 1000;
            String formattedCapacityPower = String.format("%.2f", powerCapacitykFE);
            guiGraphics.renderTooltip(this.font, Component.translatable("gui.augment.screen.energy_stored", formattedStoredPower, formattedCapacityPower), mouseX, mouseY);
        }
        if (mouseX >= leftPos + FLUID_BAR_X && mouseX < leftPos + FLUID_BAR_X + FLUID_BAR_WIDTH &&
                mouseY >= topPos + FLUID_BAR_Y && mouseY < topPos + FLUID_BAR_Y + FLUID_BAR_HEIGHT) {
            FluidStack fluidStack = blockEntity.getFluidStack();
            double fluidStoredkFE = (double) fluidStack.getAmount() / 1000;
            String formattedFluidStored = String.format("%.2f", fluidStoredkFE);
            double fluidCapacitykFE = (double) ReinforcedInjectionMoldingMachineBlockEntity.FLUID_CAPACITY / 1000;
            String formattedFluidCapacity = String.format("%.2f", fluidCapacitykFE);
            guiGraphics.renderTooltip(this.font, Component.translatable("gui.augment.screen.fluid_stored", formattedFluidStored, formattedFluidCapacity), mouseX, mouseY);
        }
    }
}
