package net.kinglybugle.augment.fluid;

import net.kinglybugle.augment.Augment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation CRUDE_OIL_OVERLAY_RL = new ResourceLocation(Augment.MOD_ID, "misc/in_crude_oil");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Augment.MOD_ID);

    public static final RegistryObject<FluidType> CRUDE_OIL_FLUID_TYPE = register("crude_oil_fluid",
            FluidType.Properties.create().canExtinguish(false).density(5).viscosity(3).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));



    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidTypes(WATER_STILL_RL, WATER_FLOWING_RL, CRUDE_OIL_OVERLAY_RL,
                0xF1330000, new Vector3f(51f / 255f, 0f / 255f, 0f / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
