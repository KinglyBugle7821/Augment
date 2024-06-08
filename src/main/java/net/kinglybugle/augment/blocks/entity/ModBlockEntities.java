package net.kinglybugle.augment.blocks.entity;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.kinglybugle.augment.blocks.entity.advancedtier.AdvancedCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.advancedtier.AdvancedFusionForgeBlockEntity;
import net.kinglybugle.augment.blocks.entity.advancedtier.AdvancedInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.basictier.BasicCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.basictier.BasicFusionForgeBlockEntity;
import net.kinglybugle.augment.blocks.entity.basictier.BasicInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.beams.SecondaryAtmosphereLaserBeamBlockEntity;
import net.kinglybugle.augment.blocks.entity.beams.SourceAtmosphereLaserBeamBlockEntity;
import net.kinglybugle.augment.blocks.entity.highgradetier.HighGradeCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.highgradetier.HighGradeFusionForgeBlockEntity;
import net.kinglybugle.augment.blocks.entity.highgradetier.HighGradeInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.quantumtier.QuantumCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.quantumtier.QuantumFusionForgeBlockEntity;
import net.kinglybugle.augment.blocks.entity.quantumtier.QuantumInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.reinforcedtier.ReinforcedCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.reinforcedtier.ReinforcedFusionForgeBlockEntity;
import net.kinglybugle.augment.blocks.entity.reinforcedtier.ReinforcedInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.simpletier.SimpleCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.simpletier.SimpleFusionForgeBlockEntity;
import net.kinglybugle.augment.blocks.entity.simpletier.SimpleInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.titaniumtier.TitaniumAstralLaserBlockEntity;
import net.kinglybugle.augment.blocks.entity.titaniumtier.TitaniumCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.titaniumtier.TitaniumFusionForgeBlockEntity;
import net.kinglybugle.augment.blocks.entity.titaniumtier.TitaniumInjectionMoldingMachineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Augment.MOD_ID);

    public static final RegistryObject<BlockEntityType<SimpleCoalFiredBoilerBlockEntity>> SIMPLE_COAL_FIRED_BOILER_BE = BLOCK_ENTITIES.register("simple_coal_fired_boiler_be", () -> BlockEntityType.Builder.of(SimpleCoalFiredBoilerBlockEntity::new, ModBlocks.SIMPLE_COAL_FIRED_BOILER.get()).build(null));
    public static final RegistryObject<BlockEntityType<SimpleInjectionMoldingMachineBlockEntity>> SIMPLE_INJECTION_MOLDING_MACHINE_BE = BLOCK_ENTITIES.register("simple_injection_molding_machine_be", () -> BlockEntityType.Builder.of(SimpleInjectionMoldingMachineBlockEntity::new, ModBlocks.SIMPLE_INJECTION_MOLDING_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<SimpleFusionForgeBlockEntity>> SIMPLE_FUSION_FORGE_BE = BLOCK_ENTITIES.register("simple_fusion_forge_be", () -> BlockEntityType.Builder.of(SimpleFusionForgeBlockEntity::new, ModBlocks.SIMPLE_FUSION_FORGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BasicCoalFiredBoilerBlockEntity>> BASIC_COAL_FIRED_BOILER_BE = BLOCK_ENTITIES.register("basic_coal_fired_boiler_be", () -> BlockEntityType.Builder.of(BasicCoalFiredBoilerBlockEntity::new, ModBlocks.BASIC_COAL_FIRED_BOILER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BasicInjectionMoldingMachineBlockEntity>> BASIC_INJECTION_MOLDING_MACHINE_BE = BLOCK_ENTITIES.register("basic_injection_molding_machine_be", () -> BlockEntityType.Builder.of(BasicInjectionMoldingMachineBlockEntity::new, ModBlocks.BASIC_INJECTION_MOLDING_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BasicFusionForgeBlockEntity>> BASIC_FUSION_FORGE_BE = BLOCK_ENTITIES.register("basic_fusion_forge_be", () -> BlockEntityType.Builder.of(BasicFusionForgeBlockEntity::new, ModBlocks.BASIC_FUSION_FORGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<AdvancedCoalFiredBoilerBlockEntity>> ADVANCED_COAL_FIRED_BOILER_BE = BLOCK_ENTITIES.register("advanced_coal_fired_boiler_be", () -> BlockEntityType.Builder.of(AdvancedCoalFiredBoilerBlockEntity::new, ModBlocks.ADVANCED_COAL_FIRED_BOILER.get()).build(null));
    public static final RegistryObject<BlockEntityType<AdvancedInjectionMoldingMachineBlockEntity>> ADVANCED_INJECTION_MOLDING_MACHINE_BE = BLOCK_ENTITIES.register("advanced_injection_molding_machine_be", () -> BlockEntityType.Builder.of(AdvancedInjectionMoldingMachineBlockEntity::new, ModBlocks.ADVANCED_INJECTION_MOLDING_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<AdvancedFusionForgeBlockEntity>> ADVANCED_FUSION_FORGE_BE = BLOCK_ENTITIES.register("advanced_fusion_forge_be", () -> BlockEntityType.Builder.of(AdvancedFusionForgeBlockEntity::new, ModBlocks.ADVANCED_FUSION_FORGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<ReinforcedCoalFiredBoilerBlockEntity>> REINFORCED_COAL_FIRED_BOILER_BE = BLOCK_ENTITIES.register("reinforced_coal_fired_boiler_be", () -> BlockEntityType.Builder.of(ReinforcedCoalFiredBoilerBlockEntity::new, ModBlocks.REINFORCED_COAL_FIRED_BOILER.get()).build(null));
    public static final RegistryObject<BlockEntityType<ReinforcedInjectionMoldingMachineBlockEntity>> REINFORCED_INJECTION_MOLDING_MACHINE_BE = BLOCK_ENTITIES.register("reinforced_injection_molding_machine_be", () -> BlockEntityType.Builder.of(ReinforcedInjectionMoldingMachineBlockEntity::new, ModBlocks.REINFORCED_INJECTION_MOLDING_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<ReinforcedFusionForgeBlockEntity>> REINFORCED_FUSION_FORGE_BE = BLOCK_ENTITIES.register("reinforced_fusion_forge_be", () -> BlockEntityType.Builder.of(ReinforcedFusionForgeBlockEntity::new, ModBlocks.REINFORCED_FUSION_FORGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<HighGradeCoalFiredBoilerBlockEntity>> HIGH_GRADE_COAL_FIRED_BOILER_BE = BLOCK_ENTITIES.register("high_grade_coal_fired_boiler_be", () -> BlockEntityType.Builder.of(HighGradeCoalFiredBoilerBlockEntity::new, ModBlocks.HIGH_GRADE_COAL_FIRED_BOILER.get()).build(null));
    public static final RegistryObject<BlockEntityType<HighGradeInjectionMoldingMachineBlockEntity>> HIGH_GRADE_INJECTION_MOLDING_MACHINE_BE = BLOCK_ENTITIES.register("high_grade_injection_molding_machine_be", () -> BlockEntityType.Builder.of(HighGradeInjectionMoldingMachineBlockEntity::new, ModBlocks.HIGH_GRADE_INJECTION_MOLDING_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<HighGradeFusionForgeBlockEntity>> HIGH_GRADE_FUSION_FORGE_BE = BLOCK_ENTITIES.register("high_grade_fusion_forge_be", () -> BlockEntityType.Builder.of(HighGradeFusionForgeBlockEntity::new, ModBlocks.HIGH_GRADE_FUSION_FORGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<TitaniumCoalFiredBoilerBlockEntity>> TITANIUM_COAL_FIRED_BOILER_BE = BLOCK_ENTITIES.register("titanium_coal_fired_boiler_be", () -> BlockEntityType.Builder.of(TitaniumCoalFiredBoilerBlockEntity::new, ModBlocks.TITANIUM_COAL_FIRED_BOILER.get()).build(null));
    public static final RegistryObject<BlockEntityType<TitaniumInjectionMoldingMachineBlockEntity>> TITANIUM_INJECTION_MOLDING_MACHINE_BE = BLOCK_ENTITIES.register("titanium_injection_molding_machine_be", () -> BlockEntityType.Builder.of(TitaniumInjectionMoldingMachineBlockEntity::new, ModBlocks.TITANIUM_INJECTION_MOLDING_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<TitaniumFusionForgeBlockEntity>> TITANIUM_FUSION_FORGE_BE = BLOCK_ENTITIES.register("titanium_fusion_forge_be", () -> BlockEntityType.Builder.of(TitaniumFusionForgeBlockEntity::new, ModBlocks.TITANIUM_FUSION_FORGE.get()).build(null));
    public static final RegistryObject<BlockEntityType<TitaniumAstralLaserBlockEntity>> TITANIUM_ASTRAL_LASER_BE = BLOCK_ENTITIES.register("titanium_astral_laser_be", () -> BlockEntityType.Builder.of(TitaniumAstralLaserBlockEntity::new, ModBlocks.TITANIUM_ASTRAL_LASER.get()).build(null));
    public static final RegistryObject<BlockEntityType<StabilizationChamberBlockEntity>> STABILIZATION_CHAMBER_BE = BLOCK_ENTITIES.register("stabilization_chamber_be", () -> BlockEntityType.Builder.of(StabilizationChamberBlockEntity::new, ModBlocks.STABILIZATION_CHAMBER.get()).build(null));

    public static final RegistryObject<BlockEntityType<QuantumCoalFiredBoilerBlockEntity>> QUANTUM_COAL_FIRED_BOILER_BE = BLOCK_ENTITIES.register("quantum_coal_fired_boiler_be", () -> BlockEntityType.Builder.of(QuantumCoalFiredBoilerBlockEntity::new, ModBlocks.TITANIUM_COAL_FIRED_BOILER.get()).build(null));
    public static final RegistryObject<BlockEntityType<QuantumInjectionMoldingMachineBlockEntity>> QUANTUM_INJECTION_MOLDING_MACHINE_BE = BLOCK_ENTITIES.register("quantum_injection_molding_machine_be", () -> BlockEntityType.Builder.of(QuantumInjectionMoldingMachineBlockEntity::new, ModBlocks.TITANIUM_INJECTION_MOLDING_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<QuantumFusionForgeBlockEntity>> QUANTUM_FUSION_FORGE_BE = BLOCK_ENTITIES.register("quantum_fusion_forge_be", () -> BlockEntityType.Builder.of(QuantumFusionForgeBlockEntity::new, ModBlocks.TITANIUM_FUSION_FORGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<SourceAtmosphereLaserBeamBlockEntity>> SOURCE_ATMOSPHERE_LASER_BEAM_BE = BLOCK_ENTITIES.register("source_atmosphere_laser_beam_be", () -> BlockEntityType.Builder.of(SourceAtmosphereLaserBeamBlockEntity::new, ModBlocks.SOURCE_ATMOSPHERE_LASER_BEAM.get()).build(null));
public static final RegistryObject<BlockEntityType<SecondaryAtmosphereLaserBeamBlockEntity>> SECONDARY_ATMOSPHERE_LASER_BEAM_BE = BLOCK_ENTITIES.register("secondary_atmosphere_laser_beam_be", () -> BlockEntityType.Builder.of(SecondaryAtmosphereLaserBeamBlockEntity::new, ModBlocks.SECONDARY_ATMOSPHERE_LASER_BEAM.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}