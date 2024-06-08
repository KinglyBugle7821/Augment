package net.kinglybugle.augment.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.kinglybugle.augment.blocks.entity.simpletier.SimpleInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.recipes.InjectionMoldingMachineRecipe;
import net.kinglybugle.augment.screen.simpletier.SimpleInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.simpletier.SimpleInjectionMoldingMachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class InjectionMoldingMachineCategory implements IRecipeCategory<InjectionMoldingMachineRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Augment.MOD_ID, "injection_molding");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Augment.MOD_ID,
            "textures/gui/container/injection_molding_machine_gui.png");
    public static final RecipeType<InjectionMoldingMachineRecipe> INJECTION_MOLDING_MACHINE_RECIPE_TYPE =
            new RecipeType<>(UID, InjectionMoldingMachineRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    public InjectionMoldingMachineCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0,0,176,83);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.SIMPLE_INJECTION_MOLDING_MACHINE.get()));
    }

    @Override
    public RecipeType<InjectionMoldingMachineRecipe> getRecipeType() {
        return INJECTION_MOLDING_MACHINE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.augment.injection_molding_machine_display");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, InjectionMoldingMachineRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, SimpleInjectionMoldingMachineMenu.DYE_SLOT_X, SimpleInjectionMoldingMachineMenu.DYE_SLOT_Y).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, SimpleInjectionMoldingMachineMenu.RESIN_SLOT_X, SimpleInjectionMoldingMachineMenu.RESIN_SLOT_Y).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, SimpleInjectionMoldingMachineScreen.FLUID_BAR_X, SimpleInjectionMoldingMachineScreen.FLUID_BAR_Y).addIngredients(ForgeTypes.FLUID_STACK, List.of(recipe.getFluid())).setFluidRenderer(SimpleInjectionMoldingMachineBlockEntity.FLUID_CAPACITY, false, SimpleInjectionMoldingMachineScreen.FLUID_BAR_WIDTH, SimpleInjectionMoldingMachineScreen.FLUID_BAR_HEIGHT);

        builder.addSlot(RecipeIngredientRole.OUTPUT, SimpleInjectionMoldingMachineMenu.OUTPUT_SLOT_X, SimpleInjectionMoldingMachineMenu.OUTPUT_SLOT_Y).addItemStack(recipe.getResultItem(null));
    }
}
