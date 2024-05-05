package net.kinglybugle.augment.recipes;

import net.kinglybugle.augment.Augment;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Augment.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CoalFiredBoilerRecipe>> COAL_FIRED_BOILING_SERIALIZER =
            SERIALIZERS.register("coal_fired_boiler", () -> CoalFiredBoilerRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}