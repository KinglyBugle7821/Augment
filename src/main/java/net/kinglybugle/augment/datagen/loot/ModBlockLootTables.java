package net.kinglybugle.augment.datagen.loot;

import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //Semi Stripped Log Block
        this.dropSelf(ModBlocks.SEMI_STRIPPED_OAK_LOG.get());
        this.dropSelf(ModBlocks.SEMI_STRIPPED_DARK_OAK_LOG.get());
        this.dropSelf(ModBlocks.SEMI_STRIPPED_ACACIA_LOG.get());
        this.dropSelf(ModBlocks.SEMI_STRIPPED_CHERRY_LOG.get());
        this.dropSelf(ModBlocks.SEMI_STRIPPED_BIRCH_LOG.get());
        this.dropSelf(ModBlocks.SEMI_STRIPPED_JUNGlE_LOG.get());
        this.dropSelf(ModBlocks.SEMI_STRIPPED_SPRUCE_LOG.get());
        this.dropSelf(ModBlocks.SEMI_STRIPPED_MANGROVE_LOG.get());
        //Custom Block Model
        this.dropSelf(ModBlocks.RESIN_EXTRACTOR_EMPTY.get());
        this.dropSelf(ModBlocks.RESIN_EXTRACTOR_FULL.get());
        //Energy Block
        this.dropSelf(ModBlocks.SIMPLE_FUSION_FORGE.get());
        this.dropSelf(ModBlocks.BASIC_FUSION_FORGE.get());
        this.dropSelf(ModBlocks.ADVANCED_FUSION_FORGE.get());
        this.dropSelf(ModBlocks.REINFORCED_FUSION_FORGE.get());
        this.dropSelf(ModBlocks.HIGH_GRADE_FUSION_FORGE.get());
        this.dropSelf(ModBlocks.TITANIUM_FUSION_FORGE.get());
        this.dropSelf(ModBlocks.QUANTUM_FUSION_FORGE.get());
        //Normal Block
        this.dropSelf(ModBlocks.STEEL_BLOCK.get());
        this.dropSelf(ModBlocks.PLATINUM_BLOCK.get());
        this.dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        this.dropSelf(ModBlocks.LASER_RETRIEVER.get());
        //Core Block
        this.dropSelf(ModBlocks.WOODEN_CORE.get());
        this.dropSelf(ModBlocks.GOLDEN_CORE.get());
        this.dropSelf(ModBlocks.STEEL_CORE.get());
        this.dropSelf(ModBlocks.PLATINUM_CORE.get());
        this.dropSelf(ModBlocks.TITANIUM_CORE.get());
        //Plastic Block
        this.dropSelf(ModBlocks.PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.WHITE_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.RED_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.ORANGE_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.PINK_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.YELLOW_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.LIME_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.GREEN_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.LIGHT_BLUE_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.CYAN_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.BLUE_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.MAGENTA_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.PURPLE_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.BROWN_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.GRAY_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.LIGHT_GRAY_PLASTIC_CORE.get());
        this.dropSelf(ModBlocks.BLACK_PLASTIC_CORE.get());
        //Ore Block
        this.add(ModBlocks.PLATINUM_ORE.get(),
                block -> createModOreDrop(ModBlocks.PLATINUM_ORE.get(), ModItems.RAW_PLATINUM.get()));
        this.add(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(),
                block -> createModOreDrop(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), ModItems.RAW_PLATINUM.get()));
        this.add(ModBlocks.TITANIUM_ORE.get(),
                block -> createModOreDrop(ModBlocks.TITANIUM_ORE.get(), ModItems.RAW_TITANIUM.get()));
        this.add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),
                block -> createModOreDrop(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), ModItems.RAW_TITANIUM.get()));
    }

    protected LootTable.Builder createModOreDrop(Block pBlock, Item pItem) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pItem).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
    protected LootTable.Builder createSingleItemTable(ItemLike pItem, NumberProvider pCount) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(pItem, LootItem.lootTableItem(pItem).apply(SetItemCountFunction.setCount(pCount)))));
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}