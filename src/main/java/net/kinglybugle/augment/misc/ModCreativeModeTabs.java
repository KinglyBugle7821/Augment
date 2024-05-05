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

                        pOutput.accept(ModBlocks.SEMI_STRIPPED_OAK_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_DARK_OAK_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_ACACIA_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_CHERRY_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_BIRCH_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_JUNGlE_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_SPRUCE_LOG.get());
                        pOutput.accept(ModBlocks.SEMI_STRIPPED_MANGROVE_LOG.get());

                        pOutput.accept(ModBlocks.COAL_FIRED_BOILER.get());

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

                        pOutput.accept(ModItems.RESIN_BALL.get());
                        pOutput.accept(ModItems.ASH_DUST.get());
                        pOutput.accept(ModItems.BARK_SPUD.get());

                    })
                    .build());

    public  static  void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}