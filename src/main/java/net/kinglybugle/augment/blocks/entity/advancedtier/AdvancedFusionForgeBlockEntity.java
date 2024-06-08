package net.kinglybugle.augment.blocks.entity.advancedtier;

import net.kinglybugle.augment.blocks.custom.advancedtier.AdvancedFusionForgeBlock;
import net.kinglybugle.augment.blocks.energy.ModEnergyStorage;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.kinglybugle.augment.blocks.entity.WrappedHandler;
import net.kinglybugle.augment.misc.EnergyDeclarations;
import net.kinglybugle.augment.networking.ModMessages;
import net.kinglybugle.augment.networking.packet.EnergySyncS2CPacket;
import net.kinglybugle.augment.networking.packet.SmokeParticleSpawnPacket;
import net.kinglybugle.augment.recipes.FusionForgeRecipe;
import net.kinglybugle.augment.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

import java.util.Map;
import java.util.Optional;

public class AdvancedFusionForgeBlockEntity extends BlockEntity {
    //Declarations
    public static final int INPUT_SLOT = 0;
    public static final int EXTRA_SLOT = 1;
    public static final int OUTPUT_SLOT = 3;

    public static final int SLOT_SIZE = 4;

    public static int ENERGY_CAPACITY = EnergyDeclarations.ENERGY_ADVANCED;
    public static final int MAX_ENERGY_TRANSFER = EnergyDeclarations.TRANSFER_ADVANCED;

    public static final int ENERGY_REQUIREMENT = 500;

    private int progress = 0;
    private int maxProgress = EnergyDeclarations.PROGRESS_ADVANCED;
    public boolean spawnSmoke = false;

    //Content
    public final ItemStackHandler itemHandler = new ItemStackHandler(SLOT_SIZE){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case INPUT_SLOT -> stack.is(ModTags.Items.INGOT_ITEM);
                case EXTRA_SLOT -> stack.is(ModTags.Items.INGOT_ITEM);
                case OUTPUT_SLOT -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == OUTPUT_SLOT, (i, s) -> false)),
                    Direction.UP, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == EXTRA_SLOT,
                            (index, stack) -> itemHandler.isItemValid(EXTRA_SLOT, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == INPUT_SLOT,
                            (index, stack) -> itemHandler.isItemValid(INPUT_SLOT, stack))),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == INPUT_SLOT,
                            (index, stack) -> itemHandler.isItemValid(INPUT_SLOT, stack))),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == INPUT_SLOT,
                            (index, stack) -> itemHandler.isItemValid(INPUT_SLOT, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == INPUT_SLOT,
                            (index, stack) -> itemHandler.isItemValid(INPUT_SLOT, stack))));
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.of(() -> new ModEnergyStorage(ENERGY_CAPACITY, MAX_ENERGY_TRANSFER) {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            setChanged();
            return ENERGY_STORAGE.receiveEnergy(maxReceive, false);
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
    protected final ContainerData data;
    public AdvancedFusionForgeBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ADVANCED_FUSION_FORGE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> AdvancedFusionForgeBlockEntity.this.progress;
                    case 1 -> AdvancedFusionForgeBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> AdvancedFusionForgeBlockEntity.this.progress = pValue;
                    case 1 -> AdvancedFusionForgeBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }
    public final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(ENERGY_CAPACITY, MAX_ENERGY_TRANSFER) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
        }
    };

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(AdvancedFusionForgeBlock.FACING);

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
        if (cap == ForgeCapabilities.ENERGY){
            return lazyEnergyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }
    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergy(energy);
    }
    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("advanced_fusion_forge.progress", progress);
        pTag.putInt("advanced_fusion_forge.energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("advanced_fusion_forge.progress");
        ENERGY_STORAGE.setEnergy(pTag.getInt("advanced_fusion_forge.energy"));
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        recipeProvider(pLevel, pPos, pState);


    }
    private void spawnSmokeParticlesRequest(BlockPos pos) {
        if (!level.isClientSide()) {
            ModMessages.sendToClients(new SmokeParticleSpawnPacket(pos));
        }
    }
    public void recipeProvider(Level pLevel, BlockPos pPos, BlockState pState) {
        if (hasRecipe() && hasEnoughEnergy()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);
            spawnSmokeParticlesRequest(pPos);

            if (hasProgressFinished()) {
                extractEnergy();
                craftItem();
                resetProgress();
            }
        }
        else {
            resetProgress();
        }
    }

    private void extractEnergy() {
        ENERGY_STORAGE.extractEnergy(ENERGY_REQUIREMENT, false);
    }

    public boolean hasEnoughEnergy() {
        return ENERGY_STORAGE.getEnergyStored() >= ENERGY_REQUIREMENT;
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<FusionForgeRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_SLOT, 1, false);
        this.itemHandler.extractItem(EXTRA_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }
    public boolean hasRecipe() {
        Optional<FusionForgeRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }
    public ItemStack getRenderStack(){
        ItemStack inputSlotStack = itemHandler.getStackInSlot(AdvancedFusionForgeBlockEntity.INPUT_SLOT);
        ItemStack extraSlotStack = itemHandler.getStackInSlot(AdvancedFusionForgeBlockEntity.EXTRA_SLOT);
        ItemStack outputSlotStack = itemHandler.getStackInSlot(AdvancedFusionForgeBlockEntity.OUTPUT_SLOT);
        if (itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()){
            return inputSlotStack.isEmpty() ? extraSlotStack : inputSlotStack;
        }
        else {
            return outputSlotStack;
        }
    }

    private Optional<FusionForgeRecipe> getCurrentRecipe() {
        SimpleContainer inv = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++){
            inv.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(FusionForgeRecipe.Type.INSTANCE, inv, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithFullMetadata();
    }

}
