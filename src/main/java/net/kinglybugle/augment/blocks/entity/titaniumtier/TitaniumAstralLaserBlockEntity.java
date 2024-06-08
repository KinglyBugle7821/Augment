package net.kinglybugle.augment.blocks.entity.titaniumtier;

import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.kinglybugle.augment.blocks.custom.titaniumtier.TitaniumAstralLaserBlock;
import net.kinglybugle.augment.blocks.energy.ModEnergyStorage;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.kinglybugle.augment.blocks.entity.WrappedHandler;
import net.kinglybugle.augment.misc.EnergyDeclarations;
import net.kinglybugle.augment.networking.ModMessages;
import net.kinglybugle.augment.networking.packet.EnergySyncS2CPacket;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumAstralLaserMenu;
import net.kinglybugle.augment.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Map;

public class TitaniumAstralLaserBlockEntity extends BlockEntity implements MenuProvider {


    public static final int CAPACITY = EnergyDeclarations.ENERGY_TITANIUM;
    public static final int MAX_TRANSFER = EnergyDeclarations.TRANSFER_TITANIUM;

    public static final int SLOT_SIZE = 7;

    public static final int LENS_SLOT = 0;
    public static final int OUTPUT_SLOT_1 = 1;
    public static final int OUTPUT_SLOT_2 = 2;
    public static final int OUTPUT_SLOT_3 = 3;
    public static final int OUTPUT_SLOT_4 = 4;
    public static final int OUTPUT_SLOT_5 = 5;
    public static final int OUTPUT_SLOT_6 = 6;

    private final int ENERGY_REQUIREMENT = 500;

    int lootTime = EnergyDeclarations.ATMOSPHERE_LOOT_TIME;
    int lensTime = EnergyDeclarations.LENS_DAMAGE_TIME;
    private int progress = 0;
    private int maxProgress = 1;

    private LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> new ModEnergyStorage(CAPACITY, MAX_TRANSFER) {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            setChanged();
            return ENERGY_STORAGE.receiveEnergy(MAX_TRANSFER, false);
        }

        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            return 0;
        }

        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    });
    public final ItemStackHandler itemHandler = new ItemStackHandler(SLOT_SIZE){
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case LENS_SLOT -> stack.is(ModTags.Items.LENS_ITEM);
                case OUTPUT_SLOT_1, OUTPUT_SLOT_4, OUTPUT_SLOT_2, OUTPUT_SLOT_3, OUTPUT_SLOT_5, OUTPUT_SLOT_6 -> true;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    public final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(CAPACITY, MAX_TRANSFER) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
        }
    };
    @Nonnull
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(
                    Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == OUTPUT_SLOT_1 ||
                                                                                            i == OUTPUT_SLOT_2 ||
                                                                                            i == OUTPUT_SLOT_3 ||
                                                                                            i == OUTPUT_SLOT_4 ||
                                                                                            i == OUTPUT_SLOT_5 ||
                                                                                            i == OUTPUT_SLOT_6, (i, s) -> false)),
                    Direction.UP, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == LENS_SLOT,
                            (index, stack) -> itemHandler.isItemValid(LENS_SLOT, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == LENS_SLOT,
                            (index, stack) -> itemHandler.isItemValid(LENS_SLOT, stack))),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == LENS_SLOT,
                            (index, stack) -> itemHandler.isItemValid(LENS_SLOT, stack))),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == LENS_SLOT,
                            (index, stack) -> itemHandler.isItemValid(LENS_SLOT, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == LENS_SLOT,
                            (index, stack) -> itemHandler.isItemValid(LENS_SLOT, stack))));

    protected final ContainerData data;

    public TitaniumAstralLaserBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TITANIUM_ASTRAL_LASER_BE.get(), pPos, pBlockState);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> TitaniumAstralLaserBlockEntity.this.progress;
                    case 1 -> TitaniumAstralLaserBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> TitaniumAstralLaserBlockEntity.this.progress = pValue;
                    case 1 -> TitaniumAstralLaserBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(TitaniumAstralLaserBlock.FACING);

                if(side == Direction.UP || side == Direction.DOWN) {
                    return directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }
        else if (cap == ForgeCapabilities.ENERGY) {
            return energyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        energyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        energyHandler.invalidate();
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.augment.titanium_astral_laser");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        ModMessages.sendToClients(new EnergySyncS2CPacket(this.ENERGY_STORAGE.getEnergyStored(), getBlockPos()));
        return new TitaniumAstralLaserMenu(pContainerId, pPlayerInventory, this, this.data);
    }
    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergy(energy);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("titanium_astral_laser.progress", progress);
        pTag.putInt("titanium_astral_laser.energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("titanium_astral_laser.progress");
        ENERGY_STORAGE.setEnergy(pTag.getInt("titanium_astral_laser.energy"));
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        checkLens(pLevel, pPos, pState);
    }

    private void checkLens(Level pLevel, BlockPos pPos, BlockState pState) {
        if (isAtmosphereLens() && hasEnoughEnergy()){
            placeBeam();
            shouldPlaceAtmosphereLaser(pLevel, pPos, pState);
            extractEnergy();
            if (hasLaserRetrieverBelow(pLevel, pPos, pState) && !isStorageFull(pLevel, pPos, pState)){
                getAtmosphereLoot();
                damageLens(pLevel);
            }
        }
        else {
            resetProgress();
            breakBeam(pLevel, pPos, pState);
        }
    }

    private boolean isStorageFull(Level pLevel, BlockPos pPos, BlockState pState) {
        if (itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() == 64 &&
                itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() == 64 &&
                itemHandler.getStackInSlot(OUTPUT_SLOT_3).getCount() == 64 &&
                itemHandler.getStackInSlot(OUTPUT_SLOT_4).getCount() == 64 &&
                itemHandler.getStackInSlot(OUTPUT_SLOT_5).getCount() == 64 &&
                itemHandler.getStackInSlot(OUTPUT_SLOT_6).getCount() == 64){
            return true;
        }
        return false;
    }

    private void damageLens(Level pLevel) {
        lensTime--;
        if (lensTime <= 0) {
            ItemStack lensStack = itemHandler.getStackInSlot(LENS_SLOT);

            // Create a dummy entity or use an existing one from the level
            LivingEntity dummyEntity = new LivingEntity(EntityType.PLAYER, pLevel) {
                @Override
                public Iterable<ItemStack> getArmorSlots() {
                    return null;
                }

                @Override
                public ItemStack getItemBySlot(EquipmentSlot pSlot) {
                    return null;
                }

                @Override
                public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

                }

                @Override
                public HumanoidArm getMainArm() {
                    return null;
                }
            };

            lensStack.hurtAndBreak(1, dummyEntity, (e) -> {});
            lensTime = EnergyDeclarations.LENS_DAMAGE_TIME;
        }
    }

    private boolean hasLaserRetrieverBelow(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos belowPos = pPos.below();
        BlockPos northPos = pPos.north();
        BlockPos southPos = pPos.south();
        BlockPos eastPos = pPos.east();
        BlockPos westPos = pPos.west();

        BlockState belowBlockState = pLevel.getBlockState(belowPos);
        BlockState northBlockState = pLevel.getBlockState(northPos);
        BlockState southBlockState = pLevel.getBlockState(southPos);
        BlockState eastBlockState = pLevel.getBlockState(eastPos);
        BlockState westBlockState = pLevel.getBlockState(westPos);

        if (belowBlockState == ModBlocks.LASER_RETRIEVER.get().defaultBlockState() ||
                northBlockState == ModBlocks.LASER_RETRIEVER.get().defaultBlockState() ||
                southBlockState == ModBlocks.LASER_RETRIEVER.get().defaultBlockState() ||
                eastBlockState == ModBlocks.LASER_RETRIEVER.get().defaultBlockState() ||
                westBlockState == ModBlocks.LASER_RETRIEVER.get().defaultBlockState()){
            return true;
        }
        return false;
    }


    private void getAtmosphereLoot() {

        lootTime--;
        System.out.println(lootTime);
        if(lootTime == 0){
            if (itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() < 64){
                itemHandler.insertItem(OUTPUT_SLOT_1, ModItems.ANTI_MATTER.get().getDefaultInstance(), false);
            }
            else if (itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() < 64 && itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() == 64){
                itemHandler.insertItem(OUTPUT_SLOT_2, ModItems.ANTI_MATTER.get().getDefaultInstance(), false);
            }
            else if (itemHandler.getStackInSlot(OUTPUT_SLOT_3).getCount() < 64 && itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() == 64){
                itemHandler.insertItem(OUTPUT_SLOT_3, ModItems.ANTI_MATTER.get().getDefaultInstance(), false);
            }
            else if (itemHandler.getStackInSlot(OUTPUT_SLOT_4).getCount() < 64 && itemHandler.getStackInSlot(OUTPUT_SLOT_3).getCount() == 64){
                itemHandler.insertItem(OUTPUT_SLOT_4, ModItems.ANTI_MATTER.get().getDefaultInstance(), false);
            }
            else if (itemHandler.getStackInSlot(OUTPUT_SLOT_5).getCount() < 64 && itemHandler.getStackInSlot(OUTPUT_SLOT_4).getCount() == 64){
                itemHandler.insertItem(OUTPUT_SLOT_5, ModItems.ANTI_MATTER.get().getDefaultInstance(), false);
            }
            else if (itemHandler.getStackInSlot(OUTPUT_SLOT_6).getCount() < 64 && itemHandler.getStackInSlot(OUTPUT_SLOT_5).getCount() == 64){
                itemHandler.insertItem(OUTPUT_SLOT_6, ModItems.ANTI_MATTER.get().getDefaultInstance(), false);
            }
            lootTime = EnergyDeclarations.ATMOSPHERE_LOOT_TIME;
        }
    }


    private void breakBeam(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos abovePos = pPos.above();
        BlockState aboveBlockState = pLevel.getBlockState(abovePos);
        if (aboveBlockState.getBlock() == ModBlocks.SOURCE_ATMOSPHERE_LASER_BEAM.get()){
            pLevel.setBlock(pPos.above(), Blocks.AIR.defaultBlockState(), 3);
        }
    }

    private void shouldPlaceAtmosphereLaser(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos.above(), ModBlocks.SOURCE_ATMOSPHERE_LASER_BEAM.get().defaultBlockState(), 3);
    }

    private void extractEnergy() {
        ENERGY_STORAGE.extractEnergy(ENERGY_REQUIREMENT, false);
    }
    private boolean hasEnoughEnergy() {
        return ENERGY_STORAGE.getEnergyStored() >= ENERGY_REQUIREMENT;
    }

    private boolean isAtmosphereLens() {
        if (itemHandler.getStackInSlot(LENS_SLOT).is(ModItems.ATMOSPHERE_LENS.get())){
            return true;
        }
        return false;
    }

    private void resetProgress() {
        progress = 0;
    }

    private void placeBeam() {
        progress++;
    }

    private boolean hasRecipe() {
        if (itemHandler.getStackInSlot(LENS_SLOT).is(Items.GLASS_PANE)){
            return true;
        }
        return false;
    }
}