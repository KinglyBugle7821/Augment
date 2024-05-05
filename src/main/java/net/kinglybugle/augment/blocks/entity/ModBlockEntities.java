package net.kinglybugle.augment.blocks.entity;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Augment.MOD_ID);

    public static final RegistryObject<BlockEntityType<CoalFiredBoilerBlockEntity>> COAL_FIRED_BOILER_BE = BLOCK_ENTITIES.register("coal_fired_boiler_be", () -> BlockEntityType.Builder.of(CoalFiredBoilerBlockEntity::new, ModBlocks.COAL_FIRED_BOILER.get()).build(null));
    public static final RegistryObject<BlockEntityType<InjectionMoldingMachineBlockEntity>> INJECTION_MOLDING_MACHINE_BE = BLOCK_ENTITIES.register("injection_molding_machine_be", () -> BlockEntityType.Builder.of(InjectionMoldingMachineBlockEntity::new, ModBlocks.INJECTION_MOLDING_MACHINE.get()).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}