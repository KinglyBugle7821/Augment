package net.kinglybugle.augment.util;

import net.kinglybugle.augment.Augment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {


        public static final TagKey<Block> ENERGY_BLOCK = tag("energy_block");
        public static final TagKey<Block> CORE_BLOCK = tag("core_block");
        public static final TagKey<Block> PLASTIC_BLOCK = tag("plastic_block");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Augment.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> PLASTIC_ITEM = tag("plastic_item");
        public static final TagKey<Item> DYE_ITEM = tag("dye_item");
        public static final TagKey<Item> INGOT_ITEM = tag("ingot_item");
        public static final TagKey<Item> LENS_ITEM = tag("lens_item");
        public static final TagKey<Item> UNSTABLE_ITEM = tag("unstable_item");


        private static TagKey<Item> tag(String name) {

            return ItemTags.create(new ResourceLocation(Augment.MOD_ID, name));
        }
    }
}