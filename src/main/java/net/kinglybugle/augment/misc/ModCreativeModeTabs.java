package net.kinglybugle.augment.misc;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Augment.MOD_ID);

    public static final RegistryObject<CreativeModeTab> AUGMENT_TAB = CREATIVE_MODE_TABS.register("augment_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BARK_SPUD.get())) // adds the icon for tutorial tab
                    .title(Component.translatable("creativetab.augment_tab")) // adds the identifier name for tutorial tab
                    .displayItems((pParameters, pOutput) -> {
                        //Semi-Stripped Log
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_OAK_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_DARK_OAK_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_ACACIA_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_CHERRY_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_BIRCH_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_JUNGlE_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_SPRUCE_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_MANGROVE_LOG.get());
                        //Energy Block
                        //Simple
                        pOutput.accept(ModBlocks.SIMPLE_COAL_FIRED_BOILER.get());
                        pOutput.accept(ModBlocks.SIMPLE_INJECTION_MOLDING_MACHINE.get());
                        pOutput.accept(ModBlocks.SIMPLE_FUSION_FORGE.get());
                        //Basic
                        pOutput.accept(ModBlocks.BASIC_COAL_FIRED_BOILER.get());
                        pOutput.accept(ModBlocks.BASIC_INJECTION_MOLDING_MACHINE.get());
                        pOutput.accept(ModBlocks.BASIC_FUSION_FORGE.get());
                        //Advanced
                        pOutput.accept(ModBlocks.ADVANCED_COAL_FIRED_BOILER.get());
                        pOutput.accept(ModBlocks.ADVANCED_INJECTION_MOLDING_MACHINE.get());
                        pOutput.accept(ModBlocks.ADVANCED_FUSION_FORGE.get());
                        //Reinforced
                        pOutput.accept(ModBlocks.REINFORCED_COAL_FIRED_BOILER.get());
                        pOutput.accept(ModBlocks.REINFORCED_INJECTION_MOLDING_MACHINE.get());
                        pOutput.accept(ModBlocks.REINFORCED_FUSION_FORGE.get());
                        //High-Grade
                        pOutput.accept(ModBlocks.HIGH_GRADE_COAL_FIRED_BOILER.get());
                        pOutput.accept(ModBlocks.HIGH_GRADE_INJECTION_MOLDING_MACHINE.get());
                        pOutput.accept(ModBlocks.HIGH_GRADE_FUSION_FORGE.get());
                        //Titanium
                        pOutput.accept(ModBlocks.TITANIUM_COAL_FIRED_BOILER.get());
                        pOutput.accept(ModBlocks.TITANIUM_INJECTION_MOLDING_MACHINE.get());
                        pOutput.accept(ModBlocks.TITANIUM_FUSION_FORGE.get());
                        pOutput.accept(ModBlocks.TITANIUM_ASTRAL_LASER.get());
                        //Quantum
                        pOutput.accept(ModBlocks.QUANTUM_COAL_FIRED_BOILER.get());
                        pOutput.accept(ModBlocks.QUANTUM_INJECTION_MOLDING_MACHINE.get());
                        pOutput.accept(ModBlocks.QUANTUM_FUSION_FORGE.get());
                        //Core Block
                        pOutput.accept(ModBlocks.WOODEN_CORE.get());
                        pOutput.accept(ModBlocks.GOLDEN_CORE.get());
                        pOutput.accept(ModBlocks.STEEL_CORE.get());
                        pOutput.accept(ModBlocks.PLATINUM_CORE.get());
                        pOutput.accept(ModBlocks.TITANIUM_CORE.get());
                        //Lens Item
                        pOutput.accept(ModItems.ATMOSPHERE_LENS.get());
                        //Plastic Block
                        pOutput.accept(ModBlocks.PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.WHITE_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.RED_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.ORANGE_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.PINK_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.YELLOW_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.LIME_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.GREEN_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.LIGHT_BLUE_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.CYAN_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.BLUE_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.MAGENTA_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.PURPLE_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.BROWN_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.GRAY_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.LIGHT_GRAY_PLASTIC_CORE.get());
                        pOutput.accept(ModBlocks.BLACK_PLASTIC_CORE.get());
                        //Resin Extractor Block
                        pOutput.accept(ModBlocks.RESIN_EXTRACTOR_EMPTY.get());
                        pOutput.accept(ModBlocks.RESIN_EXTRACTOR_FULL.get());
                        //Regular Block
                        pOutput.accept(ModBlocks.STEEL_BLOCK.get());
                        pOutput.accept(ModBlocks.PLATINUM_BLOCK.get());
                        pOutput.accept(ModBlocks.TITANIUM_BLOCK.get());
                        pOutput.accept(ModBlocks.LASER_RETRIEVER.get());
                        //Ore Block
                        pOutput.accept(ModBlocks.PLATINUM_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_PLATINUM_ORE.get());
                        pOutput.accept(ModBlocks.TITANIUM_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_TITANIUM_ORE.get());
                        //Plastic Sheet
                        pOutput.accept(ModItems.PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.WHITE_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.RED_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.ORANGE_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.PINK_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.YELLOW_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.LIME_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.GREEN_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.LIGHT_BLUE_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.CYAN_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.BLUE_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.MAGENTA_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.PURPLE_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.BROWN_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.GRAY_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.LIGHT_GRAY_PLASTIC_SHEET.get());
                        pOutput.accept(ModItems.BLACK_PLASTIC_SHEET.get());
                        //Regular Item
                        pOutput.accept(ModItems.RESIN_BALL.get());
                        pOutput.accept(ModItems.ASH_DUST.get());
                        pOutput.accept(ModItems.BARK_SPUD.get());
                        pOutput.accept(ModItems.CRUDE_OIL_BUCKET.get());
                        //Metal Item
                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModItems.STEEL_CHUNK.get());
                        pOutput.accept(ModItems.PLATINUM_INGOT.get());
                        pOutput.accept(ModItems.PLATINUM_CHUNK.get());
                        pOutput.accept(ModItems.RAW_PLATINUM.get());
                        pOutput.accept(ModItems.TITANIUM_INGOT.get());
                        pOutput.accept(ModItems.TITANIUM_CHUNK.get());
                        pOutput.accept(ModItems.RAW_TITANIUM.get());
                        pOutput.accept(ModItems.ANTI_MATTER.get());
                        pOutput.accept(ModItems.STABILIZED_ANTI_MATTER.get());

                    })
                    .build());

    public  static  void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}