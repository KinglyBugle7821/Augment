package net.kinglybugle.augment.blocks;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.blocks.custom.CoalFiredBoilerBlock;
import net.kinglybugle.augment.blocks.custom.ModFlammableRotatedPillarBlock;
import net.kinglybugle.augment.blocks.custom.ResinExtractorBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Augment.MOD_ID);
    //Semi Stripped Oak Block
    public static final RegistryObject<Block> SEMI_STRIPPED_OAK_LOG = registerBlock("semi_stripped_oak_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_DARK_OAK_LOG = registerBlock("semi_stripped_dark_oak_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_ACACIA_LOG = registerBlock("semi_stripped_acacia_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_ACACIA_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_CHERRY_LOG = registerBlock("semi_stripped_cherry_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_CHERRY_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_BIRCH_LOG = registerBlock("semi_stripped_birch_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_BIRCH_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_JUNGlE_LOG = registerBlock("semi_stripped_jungle_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_JUNGLE_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_SPRUCE_LOG = registerBlock("semi_stripped_spruce_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_SPRUCE_LOG)));
    public static final RegistryObject<Block> SEMI_STRIPPED_MANGROVE_LOG = registerBlock("semi_stripped_mangrove_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_MANGROVE_LOG)));
    //Resin Extractor Block
    public static final RegistryObject<Block> RESIN_EXTRACTOR_EMPTY = registerBlock("resin_extractor_empty", () -> new ResinExtractorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final RegistryObject<Block> RESIN_EXTRACTOR_FULL = registerBlock("resin_extractor_full", () -> new ResinExtractorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    //Energy Block
    public static final RegistryObject<Block> COAL_FIRED_BOILER = registerBlock("coal_fired_boiler", () -> new CoalFiredBoilerBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlocKItem(name, toReturn);
        return toReturn;

    }
    private static <T extends Block> RegistryObject <Item> registerBlocKItem (String name, RegistryObject <T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
