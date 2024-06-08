package net.kinglybugle.augment.datagen;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.kinglybugle.augment.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Augment.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.SEMI_STRIPPED_OAK_LOG.get().asItem())
                .add(ModBlocks.SEMI_STRIPPED_DARK_OAK_LOG.get().asItem())
                .add(ModBlocks.SEMI_STRIPPED_ACACIA_LOG.get().asItem())
                .add(ModBlocks.SEMI_STRIPPED_CHERRY_LOG.get().asItem())
                .add(ModBlocks.SEMI_STRIPPED_BIRCH_LOG.get().asItem())
                .add(ModBlocks.SEMI_STRIPPED_JUNGlE_LOG.get().asItem())
                .add(ModBlocks.SEMI_STRIPPED_SPRUCE_LOG.get().asItem())
                .add(ModBlocks.SEMI_STRIPPED_MANGROVE_LOG.get().asItem());
        this.tag(ModTags.Items.PLASTIC_ITEM)
                .add(ModItems.PLASTIC_SHEET.get())
                .add(ModItems.WHITE_PLASTIC_SHEET.get())
                .add(ModItems.RED_PLASTIC_SHEET.get())
                .add(ModItems.ORANGE_PLASTIC_SHEET.get())
                .add(ModItems.PINK_PLASTIC_SHEET.get())
                .add(ModItems.YELLOW_PLASTIC_SHEET.get())
                .add(ModItems.LIME_PLASTIC_SHEET.get())
                .add(ModItems.GREEN_PLASTIC_SHEET.get())
                .add(ModItems.LIGHT_BLUE_PLASTIC_SHEET.get())
                .add(ModItems.CYAN_PLASTIC_SHEET.get())
                .add(ModItems.BLUE_PLASTIC_SHEET.get())
                .add(ModItems.MAGENTA_PLASTIC_SHEET.get())
                .add(ModItems.PURPLE_PLASTIC_SHEET.get())
                .add(ModItems.BROWN_PLASTIC_SHEET.get())
                .add(ModItems.GRAY_PLASTIC_SHEET.get())
                .add(ModItems.LIGHT_GRAY_PLASTIC_SHEET.get())
                .add(ModItems.BLACK_PLASTIC_SHEET.get());
        this.tag(ModTags.Items.DYE_ITEM)
                .add(Items.COAL)
                .add(Items.WHITE_DYE)
                .add(Items.RED_DYE)
                .add(Items.ORANGE_DYE)
                .add(Items.PINK_DYE)
                .add(Items.YELLOW_DYE)
                .add(Items.LIME_DYE)
                .add(Items.GREEN_DYE)
                .add(Items.LIGHT_BLUE_DYE)
                .add(Items.CYAN_DYE)
                .add(Items.BLUE_DYE)
                .add(Items.MAGENTA_DYE)
                .add(Items.PURPLE_DYE)
                .add(Items.BROWN_DYE)
                .add(Items.GRAY_DYE)
                .add(Items.LIGHT_GRAY_DYE)
                .add(Items.BLACK_DYE);
        this.tag(ModTags.Items.INGOT_ITEM)
                .add(Items.COAL)
                .add(Items.IRON_INGOT)
                .add(Items.COPPER_INGOT)
                .add(Items.GOLD_INGOT)
                .add(ModItems.STEEL_INGOT.get())
                .add(ModItems.PLATINUM_INGOT.get())
                .add(ModItems.TITANIUM_INGOT.get());
        this.tag(ModTags.Items.LENS_ITEM)
                .add(ModItems.ATMOSPHERE_LENS.get());
        this.tag(ModTags.Items.UNSTABLE_ITEM)
                .add(ModItems.ANTI_MATTER.get());
    }
}