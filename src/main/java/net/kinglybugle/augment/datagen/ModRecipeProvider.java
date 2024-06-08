package net.kinglybugle.augment.datagen;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> PLATINUM_SMELTABLES = List.of(ModItems.RAW_PLATINUM.get(),
                                                                        ModBlocks.PLATINUM_ORE.get(),
                                                                        ModBlocks.DEEPSLATE_PLATINUM_ORE.get());
    private static final List<ItemLike> TITANIUM_SMELTABLES = List.of(ModItems.RAW_TITANIUM.get(),
                                                                        ModBlocks.TITANIUM_ORE.get(),
                                                                        ModBlocks.DEEPSLATE_TITANIUM_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, PLATINUM_SMELTABLES, RecipeCategory.MISC, ModItems.PLATINUM_INGOT.get(), 0.25f, 200, "platinum");
        oreBlasting(pWriter, PLATINUM_SMELTABLES, RecipeCategory.MISC, ModItems.PLATINUM_INGOT.get(), 0.25f, 100, "platinum");
        oreSmelting(pWriter, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 0.5f, 200, "titanium");
        oreBlasting(pWriter, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 0.5f, 100, "titanium");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BARK_SPUD.get())
                .pattern(" D ")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('D', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(pWriter);
        //Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLATINUM_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.PLATINUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.PLATINUM_INGOT.get()), has(ModItems.PLATINUM_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TITANIUM_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.TITANIUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.TITANIUM_INGOT.get()), has(ModItems.TITANIUM_INGOT.get()))
                .save(pWriter);
        //Ingot
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.STEEL_CHUNK.get())
                .unlockedBy(getHasName(ModItems.STEEL_CHUNK.get()), has(ModItems.STEEL_CHUNK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLATINUM_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.PLATINUM_CHUNK.get())
                .unlockedBy(getHasName(ModItems.PLATINUM_CHUNK.get()), has(ModItems.PLATINUM_CHUNK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.TITANIUM_CHUNK.get())
                .unlockedBy(getHasName(ModItems.TITANIUM_CHUNK.get()), has(ModItems.TITANIUM_CHUNK.get()))
                .save(pWriter);
        //Core Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WOODEN_CORE.get())
                .pattern(" # ")
                .pattern("#S#")
                .pattern(" # ")
                .define('#', Ingredient.of(
                        Items.ACACIA_PLANKS,
                        Items.BIRCH_PLANKS,
                        Items.DARK_OAK_PLANKS,
                        Items.JUNGLE_PLANKS,
                        Items.OAK_PLANKS,
                        Items.SPRUCE_PLANKS,
                        Items.CHERRY_PLANKS,
                        Items.CRIMSON_PLANKS,
                        Items.WARPED_PLANKS,
                        Items.MANGROVE_PLANKS,
                        Items.BAMBOO_PLANKS
                ))
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GOLDEN_CORE.get())
                .pattern("D#D")
                .pattern("#X#")
                .pattern("D#D")
                .define('#', Items.GOLD_BLOCK)
                .define('D', Items.DIAMOND)
                .define('X', Items.EMERALD)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_CORE.get())
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .define('#', ModBlocks.STEEL_BLOCK.get())
                .define('X', ModBlocks.GOLDEN_CORE.get())
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLATINUM_CORE.get())
                .pattern(" # ")
                .pattern("#D#")
                .pattern(" # ")
                .define('#', ModBlocks.PLATINUM_BLOCK.get())
                .define('D', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TITANIUM_CORE.get())
                .pattern("X#X")
                .pattern("#D#")
                .pattern("X#X")
                .define('#', ModBlocks.TITANIUM_BLOCK.get())
                .define('D', Items.GOLDEN_APPLE)
                .define('X', Items.ECHO_SHARD)
                .unlockedBy(getHasName(ModBlocks.TITANIUM_BLOCK.get()), has(ModBlocks.TITANIUM_BLOCK.get()))
                .unlockedBy(getHasName(Items.GOLDEN_APPLE), has(Items.GOLDEN_APPLE))
                .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.PLASTIC_SHEET.get()), has(ModItems.PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WHITE_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.WHITE_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.WHITE_PLASTIC_SHEET.get()), has(ModItems.WHITE_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RED_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.RED_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.RED_PLASTIC_SHEET.get()), has(ModItems.RED_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ORANGE_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.ORANGE_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.ORANGE_PLASTIC_SHEET.get()), has(ModItems.ORANGE_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.YELLOW_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.YELLOW_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.YELLOW_PLASTIC_SHEET.get()), has(ModItems.YELLOW_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GREEN_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.GREEN_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.GREEN_PLASTIC_SHEET.get()), has(ModItems.GREEN_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLUE_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.BLUE_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.BLUE_PLASTIC_SHEET.get()), has(ModItems.BLUE_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PURPLE_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.PURPLE_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.PURPLE_PLASTIC_SHEET.get()), has(ModItems.PURPLE_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PINK_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.PINK_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.PINK_PLASTIC_SHEET.get()), has(ModItems.PINK_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GRAY_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.GRAY_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.GRAY_PLASTIC_SHEET.get()), has(ModItems.GRAY_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LIGHT_GRAY_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.LIGHT_GRAY_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.LIGHT_GRAY_PLASTIC_SHEET.get()), has(ModItems.LIGHT_GRAY_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LIGHT_BLUE_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.LIGHT_BLUE_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.LIGHT_BLUE_PLASTIC_SHEET.get()), has(ModItems.LIGHT_BLUE_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CYAN_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.CYAN_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.CYAN_PLASTIC_SHEET.get()), has(ModItems.CYAN_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LIME_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.LIME_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.LIME_PLASTIC_SHEET.get()), has(ModItems.LIME_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAGENTA_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.MAGENTA_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.MAGENTA_PLASTIC_SHEET.get()), has(ModItems.MAGENTA_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BROWN_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.BROWN_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.BROWN_PLASTIC_SHEET.get()), has(ModItems.BROWN_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLACK_PLASTIC_CORE.get()).pattern("###").pattern("#X#").pattern("###").define('#', ModItems.BLACK_PLASTIC_SHEET.get()).define('X', Items.EMERALD).unlockedBy(getHasName(ModItems.BLACK_PLASTIC_SHEET.get()), has(ModItems.BLACK_PLASTIC_SHEET.get())).unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pWriter);

        //Other
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.RESIN_EXTRACTOR_EMPTY.get(), 1)
                .requires(Items.CHAIN)
                .requires(Items.CHAIN)
                .requires(Items.BOWL)
                .unlockedBy(getHasName(Items.BOWL), has(Items.BOWL))
                .save(pWriter);
        //Chunk
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_CHUNK.get(), 1)
                .requires(ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PLATINUM_CHUNK.get(), 1)
                .requires(ModItems.PLATINUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.PLATINUM_INGOT.get()), has(ModItems.PLATINUM_INGOT.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TITANIUM_CHUNK.get(), 1)
                .requires(ModItems.TITANIUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.TITANIUM_INGOT.get()), has(ModItems.TITANIUM_INGOT.get()))
                .save(pWriter);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  Augment.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
