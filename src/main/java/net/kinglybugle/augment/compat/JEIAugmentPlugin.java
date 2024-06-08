package net.kinglybugle.augment.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.recipes.InjectionMoldingMachineRecipe;
import net.kinglybugle.augment.screen.advancedtier.AdvancedInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.basictier.BasicInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.highgradetier.HighGradeInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.quantumtier.QuantumInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.reinforcedtier.ReinforcedInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.simpletier.SimpleInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumInjectionMoldingMachineScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIAugmentPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Augment.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new InjectionMoldingMachineCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<InjectionMoldingMachineRecipe> injectionMoldingMachineRecipes = recipeManager.getAllRecipesFor(InjectionMoldingMachineRecipe.Type.INSTANCE);
        registration.addRecipes(InjectionMoldingMachineCategory.INJECTION_MOLDING_MACHINE_RECIPE_TYPE, injectionMoldingMachineRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(SimpleInjectionMoldingMachineScreen.class, SimpleInjectionMoldingMachineScreen.ARROW_X,
                SimpleInjectionMoldingMachineScreen.ARROW_Y, SimpleInjectionMoldingMachineScreen.FILLED_ARROW_WIDTH, 26,
                InjectionMoldingMachineCategory.INJECTION_MOLDING_MACHINE_RECIPE_TYPE);
        registration.addRecipeClickArea(BasicInjectionMoldingMachineScreen.class, BasicInjectionMoldingMachineScreen.ARROW_X,
                BasicInjectionMoldingMachineScreen.ARROW_Y, BasicInjectionMoldingMachineScreen.FILLED_ARROW_WIDTH, 26,
                InjectionMoldingMachineCategory.INJECTION_MOLDING_MACHINE_RECIPE_TYPE);
        registration.addRecipeClickArea(AdvancedInjectionMoldingMachineScreen.class, AdvancedInjectionMoldingMachineScreen.ARROW_X,
                AdvancedInjectionMoldingMachineScreen.ARROW_Y, AdvancedInjectionMoldingMachineScreen.FILLED_ARROW_WIDTH, 26,
                InjectionMoldingMachineCategory.INJECTION_MOLDING_MACHINE_RECIPE_TYPE);
        registration.addRecipeClickArea(ReinforcedInjectionMoldingMachineScreen.class, ReinforcedInjectionMoldingMachineScreen.ARROW_X,
                ReinforcedInjectionMoldingMachineScreen.ARROW_Y, ReinforcedInjectionMoldingMachineScreen.FILLED_ARROW_WIDTH, 26,
                InjectionMoldingMachineCategory.INJECTION_MOLDING_MACHINE_RECIPE_TYPE);
        registration.addRecipeClickArea(HighGradeInjectionMoldingMachineScreen.class, HighGradeInjectionMoldingMachineScreen.ARROW_X,
                HighGradeInjectionMoldingMachineScreen.ARROW_Y, HighGradeInjectionMoldingMachineScreen.FILLED_ARROW_WIDTH, 26,
                InjectionMoldingMachineCategory.INJECTION_MOLDING_MACHINE_RECIPE_TYPE);
        registration.addRecipeClickArea(TitaniumInjectionMoldingMachineScreen.class, TitaniumInjectionMoldingMachineScreen.ARROW_X,
                TitaniumInjectionMoldingMachineScreen.ARROW_Y, TitaniumInjectionMoldingMachineScreen.FILLED_ARROW_WIDTH, 26,
                InjectionMoldingMachineCategory.INJECTION_MOLDING_MACHINE_RECIPE_TYPE);
        registration.addRecipeClickArea(QuantumInjectionMoldingMachineScreen.class, QuantumInjectionMoldingMachineScreen.ARROW_X,
                QuantumInjectionMoldingMachineScreen.ARROW_Y, QuantumInjectionMoldingMachineScreen.FILLED_ARROW_WIDTH, 26,
                InjectionMoldingMachineCategory.INJECTION_MOLDING_MACHINE_RECIPE_TYPE);
    }
}
