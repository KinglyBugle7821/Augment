package net.kinglybugle.augment.datagen;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Augment.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
//        blockWithItem(ModBlocks.RONALDO_BLOCK);
        axisBlock(((RotatedPillarBlock) ModBlocks.SEMI_STRIPPED_OAK_LOG.get()), blockTexture(ModBlocks.SEMI_STRIPPED_OAK_LOG.get()),
                new ResourceLocation(Augment.MOD_ID, "block/semi_stripped_oak_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.SEMI_STRIPPED_DARK_OAK_LOG.get()), blockTexture(ModBlocks.SEMI_STRIPPED_DARK_OAK_LOG.get()),
                new ResourceLocation(Augment.MOD_ID, "block/semi_stripped_dark_oak_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.SEMI_STRIPPED_ACACIA_LOG.get()), blockTexture(ModBlocks.SEMI_STRIPPED_ACACIA_LOG.get()),
                new ResourceLocation(Augment.MOD_ID, "block/semi_stripped_acacia_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.SEMI_STRIPPED_CHERRY_LOG.get()), blockTexture(ModBlocks.SEMI_STRIPPED_CHERRY_LOG.get()),
                new ResourceLocation(Augment.MOD_ID, "block/semi_stripped_cherry_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.SEMI_STRIPPED_BIRCH_LOG.get()), blockTexture(ModBlocks.SEMI_STRIPPED_BIRCH_LOG.get()),
                new ResourceLocation(Augment.MOD_ID, "block/semi_stripped_birch_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.SEMI_STRIPPED_JUNGlE_LOG.get()), blockTexture(ModBlocks.SEMI_STRIPPED_JUNGlE_LOG.get()),
                new ResourceLocation(Augment.MOD_ID, "block/semi_stripped_jungle_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.SEMI_STRIPPED_SPRUCE_LOG.get()), blockTexture(ModBlocks.SEMI_STRIPPED_SPRUCE_LOG.get()),
                new ResourceLocation(Augment.MOD_ID, "block/semi_stripped_spruce_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.SEMI_STRIPPED_MANGROVE_LOG.get()), blockTexture(ModBlocks.SEMI_STRIPPED_MANGROVE_LOG.get()),
                new ResourceLocation(Augment.MOD_ID, "block/semi_stripped_mangrove_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.LASER_RETRIEVER.get()), blockTexture(ModBlocks.LASER_RETRIEVER.get()),
                new ResourceLocation(Augment.MOD_ID, "block/laser_retriever_top"));

        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.PLATINUM_BLOCK);
        blockWithItem(ModBlocks.PLATINUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_PLATINUM_ORE);
        blockWithItem(ModBlocks.TITANIUM_BLOCK);
        blockWithItem(ModBlocks.TITANIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TITANIUM_ORE);

        blockWithItem(ModBlocks.WOODEN_CORE);
        blockWithItem(ModBlocks.GOLDEN_CORE);
        blockWithItem(ModBlocks.STEEL_CORE);
        blockWithItem(ModBlocks.PLATINUM_CORE);
        blockWithItem(ModBlocks.TITANIUM_CORE);

        blockWithItem(ModBlocks.PLASTIC_CORE);
        blockWithItem(ModBlocks.WHITE_PLASTIC_CORE);
        blockWithItem(ModBlocks.RED_PLASTIC_CORE);
        blockWithItem(ModBlocks.ORANGE_PLASTIC_CORE);
        blockWithItem(ModBlocks.PINK_PLASTIC_CORE);
        blockWithItem(ModBlocks.YELLOW_PLASTIC_CORE);
        blockWithItem(ModBlocks.LIME_PLASTIC_CORE);
        blockWithItem(ModBlocks.GREEN_PLASTIC_CORE);
        blockWithItem(ModBlocks.LIGHT_BLUE_PLASTIC_CORE);
        blockWithItem(ModBlocks.CYAN_PLASTIC_CORE);
        blockWithItem(ModBlocks.BLUE_PLASTIC_CORE);
        blockWithItem(ModBlocks.MAGENTA_PLASTIC_CORE);
        blockWithItem(ModBlocks.PURPLE_PLASTIC_CORE);
        blockWithItem(ModBlocks.BROWN_PLASTIC_CORE);
        blockWithItem(ModBlocks.GRAY_PLASTIC_CORE);
        blockWithItem(ModBlocks.LIGHT_GRAY_PLASTIC_CORE);
        blockWithItem(ModBlocks.BLACK_PLASTIC_CORE);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
