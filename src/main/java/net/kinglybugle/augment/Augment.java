package net.kinglybugle.augment;

import com.mojang.logging.LogUtils;
import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.kinglybugle.augment.fluid.ModFluidTypes;
import net.kinglybugle.augment.fluid.ModFluids;
import net.kinglybugle.augment.misc.ModCreativeModeTabs;
import net.kinglybugle.augment.networking.ModMessages;
import net.kinglybugle.augment.particle.ModParticles;
import net.kinglybugle.augment.recipes.ModRecipes;
import net.kinglybugle.augment.screen.advancedtier.AdvancedCoalFiredBoilingScreen;
import net.kinglybugle.augment.screen.advancedtier.AdvancedInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.basictier.BasicCoalFiredBoilingScreen;
import net.kinglybugle.augment.screen.basictier.BasicInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.highgradetier.HighGradeCoalFiredBoilingScreen;
import net.kinglybugle.augment.screen.highgradetier.HighGradeInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.quantumtier.QuantumCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.quantumtier.QuantumCoalFiredBoilingScreen;
import net.kinglybugle.augment.screen.quantumtier.QuantumInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.reinforcedtier.ReinforcedCoalFiredBoilingScreen;
import net.kinglybugle.augment.screen.reinforcedtier.ReinforcedInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.simpletier.SimpleCoalFiredBoilingScreen;
import net.kinglybugle.augment.screen.simpletier.SimpleInjectionMoldingMachineScreen;
import net.kinglybugle.augment.screen.ModMenuTypes;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumAstralLaserScreen;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumCoalFiredBoilingScreen;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumInjectionMoldingMachineScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Augment.MOD_ID)
public class Augment {
    public static final String MOD_ID = "augment";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Augment() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModParticles.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        event.enqueueWork(() -> {
            ModMessages.register();
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            MenuScreens.register(ModMenuTypes.SIMPLE_COAL_GENERATOR_MENU.get(), SimpleCoalFiredBoilingScreen::new);
            MenuScreens.register(ModMenuTypes.SIMPLE_INJECTION_MOLDING_MACHINE_MENU.get(), SimpleInjectionMoldingMachineScreen::new);

            MenuScreens.register(ModMenuTypes.BASIC_COAL_GENERATOR_MENU.get(), BasicCoalFiredBoilingScreen::new);
            MenuScreens.register(ModMenuTypes.BASIC_INJECTION_MOLDING_MACHINE_MENU.get(), BasicInjectionMoldingMachineScreen::new);

            MenuScreens.register(ModMenuTypes.ADVANCED_COAL_GENERATOR_MENU.get(), AdvancedCoalFiredBoilingScreen::new);
            MenuScreens.register(ModMenuTypes.ADVANCED_INJECTION_MOLDING_MACHINE_MENU.get(), AdvancedInjectionMoldingMachineScreen::new);

            MenuScreens.register(ModMenuTypes.REINFORCED_COAL_GENERATOR_MENU.get(), ReinforcedCoalFiredBoilingScreen::new);
            MenuScreens.register(ModMenuTypes.REINFORCED_INJECTION_MOLDING_MACHINE_MENU.get(), ReinforcedInjectionMoldingMachineScreen::new);

            MenuScreens.register(ModMenuTypes.HIGH_GRADE_COAL_GENERATOR_MENU.get(), HighGradeCoalFiredBoilingScreen::new);
            MenuScreens.register(ModMenuTypes.HIGH_GRADE_INJECTION_MOLDING_MACHINE_MENU.get(), HighGradeInjectionMoldingMachineScreen::new);

            MenuScreens.register(ModMenuTypes.TITANIUM_COAL_GENERATOR_MENU.get(), TitaniumCoalFiredBoilingScreen::new);
            MenuScreens.register(ModMenuTypes.TITANIUM_INJECTION_MOLDING_MACHINE_MENU.get(), TitaniumInjectionMoldingMachineScreen::new);
            MenuScreens.register(ModMenuTypes.TITANIUM_ASTRAL_LASER_MENU.get(), TitaniumAstralLaserScreen::new);

            MenuScreens.register(ModMenuTypes.QUANTUM_COAL_GENERATOR_MENU.get(), QuantumCoalFiredBoilingScreen::new);
            MenuScreens.register(ModMenuTypes.QUANTUM_INJECTION_MOLDING_MACHINE_MENU.get(), QuantumInjectionMoldingMachineScreen::new);

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOURCE_ATMOSPHERE_LASER_BEAM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SECONDARY_ATMOSPHERE_LASER_BEAM.get(), RenderType.translucent());
        }
    }
}