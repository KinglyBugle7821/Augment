package net.kinglybugle.augment.blocks;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.blocks.custom.*;
import net.kinglybugle.augment.blocks.custom.advancedtier.AdvancedCoalFiredBoilerBlock;
import net.kinglybugle.augment.blocks.custom.advancedtier.AdvancedFusionForgeBlock;
import net.kinglybugle.augment.blocks.custom.advancedtier.AdvancedInjectionMoldingMachineBlock;
import net.kinglybugle.augment.blocks.custom.basictier.BasicCoalFiredBoilerBlock;
import net.kinglybugle.augment.blocks.custom.basictier.BasicFusionForgeBlock;
import net.kinglybugle.augment.blocks.custom.basictier.BasicInjectionMoldingMachineBlock;
import net.kinglybugle.augment.blocks.custom.beams.SecondaryAtmosphereLaserBeamBlock;
import net.kinglybugle.augment.blocks.custom.beams.SourceAtmosphereLaserBeamBlock;
import net.kinglybugle.augment.blocks.custom.highgradetier.HighGradeCoalFiredBoilerBlock;
import net.kinglybugle.augment.blocks.custom.highgradetier.HighGradeFusionForgeBlock;
import net.kinglybugle.augment.blocks.custom.highgradetier.HighGradeInjectionMoldingMachineBlock;
import net.kinglybugle.augment.blocks.custom.quantumtier.QuantumCoalFiredBoilerBlock;
import net.kinglybugle.augment.blocks.custom.quantumtier.QuantumFusionForgeBlock;
import net.kinglybugle.augment.blocks.custom.quantumtier.QuantumInjectionMoldingMachineBlock;
import net.kinglybugle.augment.blocks.custom.reinforcedtier.ReinforcedCoalFiredBoilerBlock;
import net.kinglybugle.augment.blocks.custom.reinforcedtier.ReinforcedFusionForgeBlock;
import net.kinglybugle.augment.blocks.custom.reinforcedtier.ReinforcedInjectionMoldingMachineBlock;
import net.kinglybugle.augment.blocks.custom.simpletIer.SimpleCoalFiredBoilerBlock;
import net.kinglybugle.augment.blocks.custom.simpletIer.SimpleFusionForgeBlock;
import net.kinglybugle.augment.blocks.custom.simpletIer.SimpleInjectionMoldingMachineBlock;
import net.kinglybugle.augment.blocks.custom.titaniumtier.TitaniumAstralLaserBlock;
import net.kinglybugle.augment.blocks.custom.titaniumtier.TitaniumCoalFiredBoilerBlock;
import net.kinglybugle.augment.blocks.custom.titaniumtier.TitaniumFusionForgeBlock;
import net.kinglybugle.augment.blocks.custom.titaniumtier.TitaniumInjectionMoldingMachineBlock;
import net.kinglybugle.augment.blocks.entity.quantumtier.QuantumInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.fluid.ModFluids;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    //Simple Tier Declaration
    static String SimpleTier = "§4Simple";
    static String BasicTier = "§7Basic";
    static String AdvancedTier = "§6Advanced";
    static String ReinforcedTier = "§8Reinforced";
    static String HighGradeTier = "§bHigh-Grade";
    static String TitaniumTier = "§8Titanium";
    static String QuantumTier = "§dQuantum";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Augment.MOD_ID);
    //Semi Stripped Oak Block
    public static final RegistryObject<Block> SEMI_STRIPPED_OAK_LOG = registerBlock("semi_stripped_oak_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_DARK_OAK_LOG = registerBlock("semi_stripped_dark_oak_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_ACACIA_LOG = registerBlock("semi_stripped_acacia_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_ACACIA_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_CHERRY_LOG = registerBlock("semi_stripped_cherry_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_CHERRY_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_BIRCH_LOG = registerBlock("semi_stripped_birch_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_BIRCH_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_JUNGlE_LOG = registerBlock("semi_stripped_jungle_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_JUNGLE_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_SPRUCE_LOG = registerBlock("semi_stripped_spruce_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_SPRUCE_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_MANGROVE_LOG = registerBlock("semi_stripped_mangrove_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_MANGROVE_LOG)));


    //Resin Extractor Block
    public static final RegistryObject<Block> RESIN_EXTRACTOR_EMPTY = registerBlock("resin_extractor_empty", () -> new ResinExtractorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final RegistryObject<Block> RESIN_EXTRACTOR_FULL = registerBlock("resin_extractor_full", () -> new ResinExtractorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    //Energy Block
    //Wood - Simple
    public static final RegistryObject<Block> SIMPLE_COAL_FIRED_BOILER = registerBlock("simple_coal_fired_boiler", () -> new SimpleCoalFiredBoilerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), SimpleTier));
    public static final RegistryObject<Block> SIMPLE_INJECTION_MOLDING_MACHINE = registerBlock("simple_injection_molding_machine", () -> new SimpleInjectionMoldingMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), SimpleTier));
    public static final RegistryObject<Block> SIMPLE_FUSION_FORGE = registerBlock("simple_fusion_forge", () -> new SimpleFusionForgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(), SimpleTier));
    //Plastic - Basic
    public static final RegistryObject<Block> BASIC_COAL_FIRED_BOILER = registerBlock("basic_coal_fired_boiler", () -> new BasicCoalFiredBoilerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), BasicTier));
    public static final RegistryObject<Block> BASIC_INJECTION_MOLDING_MACHINE = registerBlock("basic_injection_molding_machine", () -> new BasicInjectionMoldingMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), BasicTier));
    public static final RegistryObject<Block> BASIC_FUSION_FORGE = registerBlock("basic_fusion_forge", () -> new BasicFusionForgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(), BasicTier));
    //Gold - Advanced
    public static final RegistryObject<Block> ADVANCED_COAL_FIRED_BOILER = registerBlock("advanced_coal_fired_boiler", () -> new AdvancedCoalFiredBoilerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), AdvancedTier));
    public static final RegistryObject<Block> ADVANCED_INJECTION_MOLDING_MACHINE = registerBlock("advanced_injection_molding_machine", () -> new AdvancedInjectionMoldingMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), AdvancedTier));
    public static final RegistryObject<Block> ADVANCED_FUSION_FORGE = registerBlock("advanced_fusion_forge", () -> new AdvancedFusionForgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(), AdvancedTier));
    //Steel - Reinforced
    public static final RegistryObject<Block> REINFORCED_COAL_FIRED_BOILER = registerBlock("reinforced_coal_fired_boiler", () -> new ReinforcedCoalFiredBoilerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), ReinforcedTier));
    public static final RegistryObject<Block> REINFORCED_INJECTION_MOLDING_MACHINE = registerBlock("reinforced_injection_molding_machine", () -> new ReinforcedInjectionMoldingMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), ReinforcedTier));
    public static final RegistryObject<Block> REINFORCED_FUSION_FORGE = registerBlock("reinforced_fusion_forge", () -> new ReinforcedFusionForgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(), ReinforcedTier));
    //Platinum - High-Grade
    public static final RegistryObject<Block> HIGH_GRADE_COAL_FIRED_BOILER = registerBlock("high_grade_coal_fired_boiler", () -> new HighGradeCoalFiredBoilerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), HighGradeTier));
    public static final RegistryObject<Block> HIGH_GRADE_INJECTION_MOLDING_MACHINE = registerBlock("high_grade_injection_molding_machine", () -> new HighGradeInjectionMoldingMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), HighGradeTier));
    public static final RegistryObject<Block> HIGH_GRADE_FUSION_FORGE = registerBlock("high_grade_fusion_forge", () -> new HighGradeFusionForgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(), HighGradeTier));
    //Titanium - Titanium
    public static final RegistryObject<Block> TITANIUM_COAL_FIRED_BOILER = registerBlock("titanium_coal_fired_boiler", () -> new TitaniumCoalFiredBoilerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), TitaniumTier));
    public static final RegistryObject<Block> TITANIUM_INJECTION_MOLDING_MACHINE = registerBlock("titanium_injection_molding_machine", () -> new TitaniumInjectionMoldingMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), TitaniumTier));
    public static final RegistryObject<Block> TITANIUM_FUSION_FORGE = registerBlock("titanium_fusion_forge", () -> new TitaniumFusionForgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(), TitaniumTier));
    public static final RegistryObject<Block> TITANIUM_ASTRAL_LASER = registerBlock("titanium_astral_laser", () -> new TitaniumAstralLaserBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), TitaniumTier));
    public static final RegistryObject<Block> STABILIZATION_CHAMBER = registerBlock("stabilization_chamber", () -> new StabilizationChamberBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable()));
    //AntiMatter - Quantum
    public static final RegistryObject<Block> QUANTUM_COAL_FIRED_BOILER = registerBlock("quantum_coal_fired_boiler", () -> new QuantumCoalFiredBoilerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), QuantumTier));
    public static final RegistryObject<Block> QUANTUM_INJECTION_MOLDING_MACHINE = registerBlock("quantum_injection_molding_machine", () -> new QuantumInjectionMoldingMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable(), QuantumTier));
    public static final RegistryObject<Block> QUANTUM_FUSION_FORGE = registerBlock("quantum_fusion_forge", () -> new QuantumFusionForgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(), QuantumTier));
    //Laser Block
    public static final RegistryObject<Block> SOURCE_ATMOSPHERE_LASER_BEAM = registerBlock("source_atmosphere_laser_beam", () -> new SourceAtmosphereLaserBeamBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable().noCollission().lightLevel((state) -> 8)));
    public static final RegistryObject<Block> SECONDARY_ATMOSPHERE_LASER_BEAM = registerBlock("secondary_atmosphere_laser_beam", () -> new SecondaryAtmosphereLaserBeamBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().noLootTable().noCollission().lightLevel((state) -> 8)));
    // Fluid Block
    public static final RegistryObject<LiquidBlock> CRUDE_OIL_BLOCK = BLOCKS.register("crude_oil_block", () -> new LiquidBlock(ModFluids.SOURCE_CRUDE_OIL, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable().ignitedByLava()));
    //Regular Block
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6f)));
    public static final RegistryObject<Block> PLATINUM_BLOCK = registerBlock("platinum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(8f)));
    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(12f)));
    public static final RegistryObject<Block> LASER_RETRIEVER = registerBlock("laser_retriever", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    //Ore Block
    public static final RegistryObject<Block> PLATINUM_ORE = registerBlock("platinum_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_ORE).strength(7f)));
    public static final RegistryObject<Block> DEEPSLATE_PLATINUM_ORE = registerBlock("deepslate_platinum_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_ORE).strength(7.5f)));
    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_ORE).strength(9f)));
    public static final RegistryObject<Block> DEEPSLATE_TITANIUM_ORE = registerBlock("deepslate_titanium_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_ORE).strength(10f)));
    //Core Block
    public static final RegistryObject<Block> WOODEN_CORE = registerBlock("wooden_core", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> GOLDEN_CORE = registerBlock("golden_core", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> STEEL_CORE = registerBlock("steel_core", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.STEEL_BLOCK.get())));
    public static final RegistryObject<Block> PLATINUM_CORE = registerBlock("platinum_core", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.PLATINUM_BLOCK.get())));
    public static final RegistryObject<Block> TITANIUM_CORE = registerBlock("titanium_core", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.TITANIUM_BLOCK.get())));
    //Plastic
    public static final RegistryObject<Block> PLASTIC_CORE = registerBlock("plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> WHITE_PLASTIC_CORE = registerBlock("white_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> RED_PLASTIC_CORE = registerBlock("red_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> ORANGE_PLASTIC_CORE = registerBlock("orange_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> PINK_PLASTIC_CORE = registerBlock("pink_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> YELLOW_PLASTIC_CORE = registerBlock("yellow_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> LIME_PLASTIC_CORE = registerBlock("lime_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> GREEN_PLASTIC_CORE = registerBlock("green_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> LIGHT_BLUE_PLASTIC_CORE = registerBlock("light_blue_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> CYAN_PLASTIC_CORE = registerBlock("cyan_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> BLUE_PLASTIC_CORE = registerBlock("blue_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> MAGENTA_PLASTIC_CORE = registerBlock("magenta_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> PURPLE_PLASTIC_CORE = registerBlock("purple_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> BROWN_PLASTIC_CORE = registerBlock("brown_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> GRAY_PLASTIC_CORE = registerBlock("gray_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> LIGHT_GRAY_PLASTIC_CORE = registerBlock("light_gray_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> BLACK_PLASTIC_CORE = registerBlock("black_plastic_core", () -> new PlasticBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlocKItem(name, toReturn);
        return toReturn;

    }
    private static <T extends Block> RegistryObject <Item> registerBlocKItem (String name, RegistryObject <T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
