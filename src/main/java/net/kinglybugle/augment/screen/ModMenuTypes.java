package net.kinglybugle.augment.screen;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.screen.advancedtier.AdvancedCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.advancedtier.AdvancedInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.basictier.BasicCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.basictier.BasicInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.highgradetier.HighGradeCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.highgradetier.HighGradeInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.quantumtier.QuantumCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.quantumtier.QuantumInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.reinforcedtier.ReinforcedCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.reinforcedtier.ReinforcedInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.simpletier.SimpleCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.simpletier.SimpleInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumAstralLaserMenu;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumInjectionMoldingMachineMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Augment.MOD_ID);

    public static final RegistryObject<MenuType<SimpleCoalFiredBoilingMenu>> SIMPLE_COAL_GENERATOR_MENU =
            registerMenuType("simple_coal_fired_boiler_menu", SimpleCoalFiredBoilingMenu::new);
    public static final RegistryObject<MenuType<SimpleInjectionMoldingMachineMenu>> SIMPLE_INJECTION_MOLDING_MACHINE_MENU =
            registerMenuType("simple_injection_molding_machine_menu", SimpleInjectionMoldingMachineMenu::new);

    public static final RegistryObject<MenuType<BasicCoalFiredBoilingMenu>> BASIC_COAL_GENERATOR_MENU =
            registerMenuType("basic_coal_fired_boiler_menu", BasicCoalFiredBoilingMenu::new);
    public static final RegistryObject<MenuType<BasicInjectionMoldingMachineMenu>> BASIC_INJECTION_MOLDING_MACHINE_MENU =
            registerMenuType("basic_injection_molding_machine_menu", BasicInjectionMoldingMachineMenu::new);

    public static final RegistryObject<MenuType<AdvancedCoalFiredBoilingMenu>> ADVANCED_COAL_GENERATOR_MENU =
            registerMenuType("advanced_coal_fired_boiler_menu", AdvancedCoalFiredBoilingMenu::new);
    public static final RegistryObject<MenuType<AdvancedInjectionMoldingMachineMenu>> ADVANCED_INJECTION_MOLDING_MACHINE_MENU =
            registerMenuType("advanced_injection_molding_machine_menu", AdvancedInjectionMoldingMachineMenu::new);

    public static final RegistryObject<MenuType<ReinforcedCoalFiredBoilingMenu>> REINFORCED_COAL_GENERATOR_MENU =
            registerMenuType("reinforced_coal_fired_boiler_menu", ReinforcedCoalFiredBoilingMenu::new);
    public static final RegistryObject<MenuType<ReinforcedInjectionMoldingMachineMenu>> REINFORCED_INJECTION_MOLDING_MACHINE_MENU =
            registerMenuType("reinforced_injection_molding_machine_menu", ReinforcedInjectionMoldingMachineMenu::new);

    public static final RegistryObject<MenuType<HighGradeCoalFiredBoilingMenu>> HIGH_GRADE_COAL_GENERATOR_MENU =
            registerMenuType("high_grade_coal_fired_boiler_menu", HighGradeCoalFiredBoilingMenu::new);
    public static final RegistryObject<MenuType<HighGradeInjectionMoldingMachineMenu>> HIGH_GRADE_INJECTION_MOLDING_MACHINE_MENU =
            registerMenuType("high_grade_injection_molding_machine_menu", HighGradeInjectionMoldingMachineMenu::new);

    public static final RegistryObject<MenuType<TitaniumCoalFiredBoilingMenu>> TITANIUM_COAL_GENERATOR_MENU =
            registerMenuType("titanium_coal_fired_boiler_menu", TitaniumCoalFiredBoilingMenu::new);
    public static final RegistryObject<MenuType<TitaniumAstralLaserMenu>> TITANIUM_ASTRAL_LASER_MENU =
            registerMenuType("titanium_astral_laser_menu", TitaniumAstralLaserMenu::new);
    public static final RegistryObject<MenuType<TitaniumInjectionMoldingMachineMenu>> TITANIUM_INJECTION_MOLDING_MACHINE_MENU =
            registerMenuType("titanium_injection_molding_machine_menu", TitaniumInjectionMoldingMachineMenu::new);

    public static final RegistryObject<MenuType<QuantumCoalFiredBoilingMenu>> QUANTUM_COAL_GENERATOR_MENU =
            registerMenuType("quantum_coal_fired_boiler_menu", QuantumCoalFiredBoilingMenu::new);
    public static final RegistryObject<MenuType<QuantumInjectionMoldingMachineMenu>> QUANTUM_INJECTION_MOLDING_MACHINE_MENU =
            registerMenuType("quantum_injection_molding_machine_menu", QuantumInjectionMoldingMachineMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
