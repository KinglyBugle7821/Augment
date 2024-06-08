package net.kinglybugle.augment.datagen;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.kinglybugle.augment.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Augment.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SIMPLE_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.SIMPLE_COAL_FIRED_BOILER.get(),
                        ModBlocks.SIMPLE_FUSION_FORGE.get(),
                        ModBlocks.BASIC_COAL_FIRED_BOILER.get(),
                        ModBlocks.BASIC_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.BASIC_FUSION_FORGE.get(),
                        ModBlocks.ADVANCED_COAL_FIRED_BOILER.get(),
                        ModBlocks.ADVANCED_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.ADVANCED_FUSION_FORGE.get(),
                        ModBlocks.REINFORCED_COAL_FIRED_BOILER.get(),
                        ModBlocks.REINFORCED_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.REINFORCED_FUSION_FORGE.get(),
                        ModBlocks.HIGH_GRADE_COAL_FIRED_BOILER.get(),
                        ModBlocks.HIGH_GRADE_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.HIGH_GRADE_FUSION_FORGE.get(),
                        ModBlocks.TITANIUM_COAL_FIRED_BOILER.get(),
                        ModBlocks.TITANIUM_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.TITANIUM_FUSION_FORGE.get(),
                        ModBlocks.TITANIUM_ASTRAL_LASER.get(),
                        ModBlocks.STEEL_BLOCK.get(),
                        ModBlocks.PLATINUM_BLOCK.get(),
                        ModBlocks.PLATINUM_ORE.get(),
                        ModBlocks.DEEPSLATE_PLATINUM_ORE.get(),
                        ModBlocks.TITANIUM_BLOCK.get(),
                        ModBlocks.TITANIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),
                        ModBlocks.PLASTIC_CORE.get(),
                        ModBlocks.WHITE_PLASTIC_CORE.get(),
                        ModBlocks.RED_PLASTIC_CORE.get(),
                        ModBlocks.ORANGE_PLASTIC_CORE.get(),
                        ModBlocks.PINK_PLASTIC_CORE.get(),
                        ModBlocks.YELLOW_PLASTIC_CORE.get(),
                        ModBlocks.LIME_PLASTIC_CORE.get(),
                        ModBlocks.GREEN_PLASTIC_CORE.get(),
                        ModBlocks.LIGHT_BLUE_PLASTIC_CORE.get(),
                        ModBlocks.CYAN_PLASTIC_CORE.get(),
                        ModBlocks.BLUE_PLASTIC_CORE.get(),
                        ModBlocks.MAGENTA_PLASTIC_CORE.get(),
                        ModBlocks.PURPLE_PLASTIC_CORE.get(),
                        ModBlocks.BROWN_PLASTIC_CORE.get(),
                        ModBlocks.GRAY_PLASTIC_CORE.get(),
                        ModBlocks.LIGHT_GRAY_PLASTIC_CORE.get(),
                        ModBlocks.BLACK_PLASTIC_CORE.get(),
                        ModBlocks.GOLDEN_CORE.get(),
                        ModBlocks.STEEL_CORE.get(),
                        ModBlocks.PLATINUM_CORE.get(),
                        ModBlocks.TITANIUM_CORE.get(),
                        ModBlocks.LASER_RETRIEVER.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.SEMI_STRIPPED_OAK_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_SPRUCE_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_MANGROVE_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_JUNGlE_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_ACACIA_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_DARK_OAK_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_CHERRY_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_BIRCH_LOG.get(),
                        ModBlocks.RESIN_EXTRACTOR_EMPTY.get(),
                        ModBlocks.RESIN_EXTRACTOR_FULL.get());


        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SIMPLE_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.SIMPLE_COAL_FIRED_BOILER.get(),
                        ModBlocks.SIMPLE_FUSION_FORGE.get(),
                        ModBlocks.BASIC_COAL_FIRED_BOILER.get(),
                        ModBlocks.BASIC_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.BASIC_FUSION_FORGE.get(),
                        ModBlocks.STEEL_BLOCK.get(),
                        ModBlocks.PLATINUM_BLOCK.get(),
                        ModBlocks.PLATINUM_ORE.get(),
                        ModBlocks.DEEPSLATE_PLATINUM_ORE.get(),
                        ModBlocks.GOLDEN_CORE.get(),
                        ModBlocks.STEEL_CORE.get(),
                        ModBlocks.LASER_RETRIEVER.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.TITANIUM_BLOCK.get(),
                        ModBlocks.TITANIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),
                        ModBlocks.PLATINUM_CORE.get(),
                        ModBlocks.TITANIUM_CORE.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL);

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.SEMI_STRIPPED_OAK_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_DARK_OAK_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_ACACIA_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_CHERRY_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_BIRCH_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_JUNGlE_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_SPRUCE_LOG.get(),
                        ModBlocks.SEMI_STRIPPED_MANGROVE_LOG.get());
        this.tag(ModTags.Blocks.ENERGY_BLOCK)
                .add(ModBlocks.SIMPLE_COAL_FIRED_BOILER.get(),
                        ModBlocks.SIMPLE_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.SIMPLE_FUSION_FORGE.get(),
                        ModBlocks.BASIC_COAL_FIRED_BOILER.get(),
                        ModBlocks.BASIC_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.BASIC_FUSION_FORGE.get(),
                        ModBlocks.ADVANCED_COAL_FIRED_BOILER.get(),
                        ModBlocks.ADVANCED_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.ADVANCED_FUSION_FORGE.get(),
                        ModBlocks.REINFORCED_COAL_FIRED_BOILER.get(),
                        ModBlocks.REINFORCED_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.REINFORCED_FUSION_FORGE.get(),
                        ModBlocks.HIGH_GRADE_COAL_FIRED_BOILER.get(),
                        ModBlocks.HIGH_GRADE_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.HIGH_GRADE_FUSION_FORGE.get(),
                        ModBlocks.TITANIUM_ASTRAL_LASER.get(),
                        ModBlocks.TITANIUM_COAL_FIRED_BOILER.get(),
                        ModBlocks.TITANIUM_INJECTION_MOLDING_MACHINE.get(),
                        ModBlocks.TITANIUM_FUSION_FORGE.get());
        this.tag(ModTags.Blocks.CORE_BLOCK)
                .add(ModBlocks.WOODEN_CORE.get(),
                        ModBlocks.GOLDEN_CORE.get(),
                        ModBlocks.STEEL_CORE.get(),
                        ModBlocks.PLATINUM_CORE.get(),
                        ModBlocks.TITANIUM_CORE.get());
        this.tag(ModTags.Blocks.PLASTIC_BLOCK)
                .add(ModBlocks.PLASTIC_CORE.get(),
                        ModBlocks.WHITE_PLASTIC_CORE.get(),
                        ModBlocks.RED_PLASTIC_CORE.get(),
                        ModBlocks.ORANGE_PLASTIC_CORE.get(),
                        ModBlocks.PINK_PLASTIC_CORE.get(),
                        ModBlocks.YELLOW_PLASTIC_CORE.get(),
                        ModBlocks.LIME_PLASTIC_CORE.get(),
                        ModBlocks.GREEN_PLASTIC_CORE.get(),
                        ModBlocks.LIGHT_BLUE_PLASTIC_CORE.get(),
                        ModBlocks.CYAN_PLASTIC_CORE.get(),
                        ModBlocks.BLUE_PLASTIC_CORE.get(),
                        ModBlocks.MAGENTA_PLASTIC_CORE.get(),
                        ModBlocks.PURPLE_PLASTIC_CORE.get(),
                        ModBlocks.BROWN_PLASTIC_CORE.get(),
                        ModBlocks.GRAY_PLASTIC_CORE.get(),
                        ModBlocks.LIGHT_GRAY_PLASTIC_CORE.get(),
                        ModBlocks.BLACK_PLASTIC_CORE.get());

    }
}