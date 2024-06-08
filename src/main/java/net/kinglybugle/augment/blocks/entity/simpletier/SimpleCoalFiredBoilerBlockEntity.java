package net.kinglybugle.augment.blocks.entity.simpletier;

import net.kinglybugle.augment.blocks.custom.simpletIer.SimpleCoalFiredBoilerBlock;
import net.kinglybugle.augment.blocks.energy.ModEnergyStorage;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.kinglybugle.augment.blocks.entity.WrappedHandler;
import net.kinglybugle.augment.misc.EnergyDeclarations;
import net.kinglybugle.augment.networking.ModMessages;
import net.kinglybugle.augment.networking.packet.EnergySyncS2CPacket;
import net.kinglybugle.augment.recipes.CoalFiredBoilerRecipe;
import net.kinglybugle.augment.screen.simpletier.SimpleCoalFiredBoilingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
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
import java.util.Optional;

public class SimpleCoalFiredBoilerBlockEntity extends BlockEntity implements MenuProvider {


    public static final int CAPACITY = EnergyDeclarations.ENERGY_SIMPLE;
    public static final int MAX_TRANSFER = EnergyDeclarations.TRANSFER_SIMPLE;

    public static final int SLOT_SIZE = 2;

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    private int progress = 0;
    private int maxProgress = EnergyDeclarations.PROGRESS_SIMPLE;

    private LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> new ModEnergyStorage(CAPACITY, MAX_TRANSFER) {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            return 0;
        }

        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            setChanged();
            return ENERGY_STORAGE.extractEnergy(MAX_TRANSFER, false);
        }

        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return false;
        }
    });
    private final ItemStackHandler itemHandler = new ItemStackHandler(SLOT_SIZE){
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0 -> stack.getItem() == Items.COAL ||
                        stack.getItem() == Items.CHARCOAL ||
                        stack.getItem() == Items.COAL_BLOCK;
                case 1 -> false;
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
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == OUTPUT_SLOT, (i, s) -> false)),
                    Direction.UP, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == INPUT_SLOT,
                            (index, stack) -> itemHandler.isItemValid(INPUT_SLOT, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == INPUT_SLOT,
                            (index, stack) -> itemHandler.isItemValid(INPUT_SLOT, stack))),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == INPUT_SLOT,
                            (index, stack) -> itemHandler.isItemValid(INPUT_SLOT, stack))),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == INPUT_SLOT,
                            (index, stack) -> itemHandler.isItemValid(INPUT_SLOT, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == INPUT_SLOT,
                            (index, stack) -> itemHandler.isItemValid(INPUT_SLOT, stack))));

    protected final ContainerData data;


    public SimpleCoalFiredBoilerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SIMPLE_COAL_FIRED_BOILER_BE.get(), pPos, pBlockState);

        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> SimpleCoalFiredBoilerBlockEntity.this.progress;
                    case 1 -> SimpleCoalFiredBoilerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> SimpleCoalFiredBoilerBlockEntity.this.progress = pValue;
                    case 1 -> SimpleCoalFiredBoilerBlockEntity.this.maxProgress = pValue;
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
                Direction localDir = this.getBlockState().getValue(SimpleCoalFiredBoilerBlock.FACING);

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
        return Component.translatable("block.augment.simple_coal_fired_boiler");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        ModMessages.sendToClients(new EnergySyncS2CPacket(this.ENERGY_STORAGE.getEnergyStored(), getBlockPos()));
        return new SimpleCoalFiredBoilingMenu(pContainerId, pPlayerInventory, this, this.data);
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
        pTag.putInt("simple_coal_fired_boiler.progress", progress);
        pTag.putInt("simple_coal_fired_boiler.energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("simple_coal_fired_boiler.progress");
        ENERGY_STORAGE.setEnergy(pTag.getInt("simple_coal_fired_boiler.energy"));
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        recipeProvider(pLevel, pPos, pState);
        distributeEnergy();

    }

    private void distributeEnergy() {
        // Check all sides of the block and send energy if that block supports the energy capability
        for (Direction direction : Direction.values()) {
            if (ENERGY_STORAGE.getEnergyStored() <= 0) {
                return;
            }
            BlockEntity be = level.getBlockEntity(getBlockPos().relative(direction));
            if (be != null) {
                be.getCapability(ForgeCapabilities.ENERGY).map(e -> {
                    if (e.canReceive()) {
                        int received = e.receiveEnergy(Math.min(ENERGY_STORAGE.getEnergyStored(), MAX_TRANSFER), false);
                        ENERGY_STORAGE.extractEnergy(received, false);
                        setChanged();
                        return received;
                    }
                    return 0;
                });
            }
        }
    }

    private void recipeProvider(Level pLevel, BlockPos pPos, BlockState pState) {
        if (hasRecipe() && !isEnergyFull()){
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);
            if (hasProgressFinished()){
                if (isCoal()){
                    receiveNormalEnergy();
                    craftItem();
                    resetProgress();
                }
                else if (isCoalBlock()){
                    receiveHugeEnergy();
                    craftItem();
                    resetProgress();
                }
            }
        }
        else {
            resetProgress();
        }
    }

    private boolean isEnergyFull() {
        return ENERGY_STORAGE.getEnergyStored() == CAPACITY;
    }


    private void receiveHugeEnergy() {
        ENERGY_STORAGE.receiveEnergy(600, false);
    }

    private boolean isCoalBlock() {
        return itemHandler.getStackInSlot(0).getItem() == Items.COAL_BLOCK;
    }

    private void receiveNormalEnergy() {
        ENERGY_STORAGE.receiveEnergy(64, false);
    }

    private boolean isCoal() {
        return itemHandler.getStackInSlot(0).getItem() == Items.COAL ||
                itemHandler.getStackInSlot(0).getItem() == Items.CHARCOAL;
    }
    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<CoalFiredBoilerRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<CoalFiredBoilerRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());

    }

    private Optional<CoalFiredBoilerRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i< itemHandler.getSlots(); i++){
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(CoalFiredBoilerRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();

    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }
}