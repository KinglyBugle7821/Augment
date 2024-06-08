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
    public static final RegistryObject<RecipeSerializer<InjectionMoldingMachineRecipe>> INJECTION_MOLDING_MACHINE_SERIALIZER =
            SERIALIZERS.register("injection_molding", () -> InjectionMoldingMachineRecipe.Serializer.INSTANCE);;
    public static final RegistryObject<RecipeSerializer<FusionForgeRecipe>> FUSION_FORGE_SERIALIZER =
            SERIALIZERS.register("fusion_forge", () -> FusionForgeRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<StabilizationChamberRecipe>> STABILIZATION_CHAMBER_SERIALIZER =
            SERIALIZERS.register("stabilization_chamber", () -> StabilizationChamberRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}