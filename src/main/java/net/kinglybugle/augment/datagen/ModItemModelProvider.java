package net.kinglybugle.augment.datagen;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.Items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Augment.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.RESIN_BALL);
        simpleItem(ModItems.ASH_DUST);
        simpleItem(ModItems.BARK_SPUD);
        simpleItem(ModItems.CRUDE_OIL_BUCKET);

        simpleItem(ModItems.STEEL_INGOT);
        simpleItem(ModItems.STEEL_CHUNK);
        simpleItem(ModItems.PLATINUM_INGOT);
        simpleItem(ModItems.PLATINUM_CHUNK);
        simpleItem(ModItems.RAW_PLATINUM);
        simpleItem(ModItems.TITANIUM_INGOT);
        simpleItem(ModItems.TITANIUM_CHUNK);
        simpleItem(ModItems.RAW_TITANIUM);
        simpleItem(ModItems.ANTI_MATTER);
        simpleItem(ModItems.STABILIZED_ANTI_MATTER);

        simpleItem(ModItems.ATMOSPHERE_LENS);

        simpleItem(ModItems.PLASTIC_SHEET);
        simpleItem(ModItems.WHITE_PLASTIC_SHEET);
        simpleItem(ModItems.RED_PLASTIC_SHEET);
        simpleItem(ModItems.ORANGE_PLASTIC_SHEET);
        simpleItem(ModItems.PINK_PLASTIC_SHEET);
        simpleItem(ModItems.YELLOW_PLASTIC_SHEET);
        simpleItem(ModItems.LIME_PLASTIC_SHEET);
        simpleItem(ModItems.GREEN_PLASTIC_SHEET);
        simpleItem(ModItems.LIGHT_BLUE_PLASTIC_SHEET);
        simpleItem(ModItems.CYAN_PLASTIC_SHEET);
        simpleItem(ModItems.BLUE_PLASTIC_SHEET);
        simpleItem(ModItems.MAGENTA_PLASTIC_SHEET);
        simpleItem(ModItems.PURPLE_PLASTIC_SHEET);
        simpleItem(ModItems.BROWN_PLASTIC_SHEET);
        simpleItem(ModItems.GRAY_PLASTIC_SHEET);
        simpleItem(ModItems.LIGHT_GRAY_PLASTIC_SHEET);
        simpleItem(ModItems.BLACK_PLASTIC_SHEET);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Augment.MOD_ID,"item/" + item.getId().getPath()));
    }
}