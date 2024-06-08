package net.kinglybugle.augment.blocks.entity.basictier;

import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.blocks.custom.basictier.BasicInjectionMoldingMachineBlock;
import net.kinglybugle.augment.blocks.energy.ModEnergyStorage;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.kinglybugle.augment.blocks.entity.WrappedHandler;
import net.kinglybugle.augment.fluid.ModFluids;
import net.kinglybugle.augment.misc.EnergyDeclarations;
import net.kinglybugle.augment.networking.ModMessages;
import net.kinglybugle.augment.networking.packet.EnergySyncS2CPacket;
import net.kinglybugle.augment.networking.packet.FluidSyncS2CPacket;
import net.kinglybugle.augment.recipes.InjectionMoldingMachineRecipe;
import net.kinglybugle.augment.screen.basictier.BasicInjectionMoldingMachineMenu;
import net.kinglybugle.augment.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class BasicInjectionMoldingMachineBlockEntity extends BlockEntity implements MenuProvider {
    //Declarations
    public static final int DYE_SLOT = 0;
    public static final int RESIN_SLOT = 1;
    public static final int FLUID_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;

    public static final int SLOT_SIZE = 4;

    public static final int ENERGY_CAPACITY = EnergyDeclarations.ENERGY_BASIC;
    public static final int FLUID_CAPACITY = EnergyDeclarations.FLUID_BASIC;
    public static final int MAX_ENERGY_TRANSFER = EnergyDeclarations.TRANSFER_BASIC;
    public static final int MAX_FLUID_TRANSFER = EnergyDeclarations.TRANSFER_BASIC;

    public static final int ENERGY_REQUIREMENT = 250;

    private int progress = 0;
    private int maxProgress = EnergyDeclarations.PROGRESS_BASIC;

    //Content
    private final ItemStackHandler itemHandler = new ItemStackHandler(SLOT_SIZE){
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case DYE_SLOT -> stack.is(ModTags.Items.DYE_ITEM);
                case RESIN_SLOT -> stack.getItem() == ModItems.RESIN_BALL.get() ||
                                    stack.getItem() == Items.SLIME_BALL;
                case FLUID_SLOT -> stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
                case OUTPUT_SLOT -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == OUTPUT_SLOT, (i, s) -> false)),
                    Direction.UP, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == RESIN_SLOT,
                            (index, stack) -> itemHandler.isItemValid(RESIN_SLOT, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == DYE_SLOT,
                            (index, stack) -> itemHandler.isItemValid(DYE_SLOT, stack))),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == DYE_SLOT,
                            (index, stack) -> itemHandler.isItemValid(DYE_SLOT, stack))),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == DYE_SLOT,
                            (index, stack) -> itemHandler.isItemValid(DYE_SLOT, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == DYE_SLOT,
                            (index, stack) -> itemHandler.isItemValid(DYE_SLOT, stack))));
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();
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
    public BasicInjectionMoldingMachineBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BASIC_INJECTION_MOLDING_MACHINE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> BasicInjectionMoldingMachineBlockEntity.this.progress;
                    case 1 -> BasicInjectionMoldingMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> BasicInjectionMoldingMachineBlockEntity.this.progress = pValue;
                    case 1 -> BasicInjectionMoldingMachineBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }
    public final FluidTank FLUID_TANK = new FluidTank(FLUID_CAPACITY){
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide){
                ModMessages.sendToClients(new FluidSyncS2CPacket(this.fluid, worldPosition));
            }
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == ModFluids.SOURCE_CRUDE_OIL.get();
        }
    };
    public void setFluid(FluidStack stack){
        this.FLUID_TANK.setFluid(stack);
    }
    public FluidStack getFluidStack(){
        return this.FLUID_TANK.getFluid();
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
                Direction localDir = this.getBlockState().getValue(BasicInjectionMoldingMachineBlock.FACING);

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
        if (cap == ForgeCapabilities.FLUID_HANDLER){
            return lazyFluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
        lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
        lazyFluidHandler.invalidate();
    }
    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.augment.injection_molding_machine_display");
    }
    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergy(energy);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        ModMessages.sendToClients(new EnergySyncS2CPacket(this.ENERGY_STORAGE.getEnergyStored(), getBlockPos()));
        ModMessages.sendToClients(new FluidSyncS2CPacket(this.getFluidStack(), worldPosition));
        return new BasicInjectionMoldingMachineMenu(pContainerId, pPlayerInventory, this, this.data);
    }
    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("basic_injection_molding_machine.progress", progress);
        pTag.putInt("basic_injection_molding_machine.energy", ENERGY_STORAGE.getEnergyStored());
        pTag.put("FluidTank", FLUID_TANK.writeToNBT(new CompoundTag()));

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("basic_injection_molding_machine.progress");
        ENERGY_STORAGE.setEnergy(pTag.getInt("basic_injection_molding_machine.energy"));
        FLUID_TANK.readFromNBT(pTag);
        if (pTag.contains("FluidTank", Tag.TAG_COMPOUND)) {
            CompoundTag fluidTag = pTag.getCompound("FluidTank");
            FLUID_TANK.readFromNBT(fluidTag);
        }
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        recipeProvider(pLevel, pPos, pState);
        fluidProvider();
    }

    private void fluidProvider() {
        if(hasFluidItemInSourceSlot()){
            transferItemFluidToFluidTank();
        }
    }

    private void transferItemFluidToFluidTank() {
        itemHandler.getStackInSlot(FLUID_SLOT).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(handler -> {
            int drainAmount = Math.min(FLUID_TANK.getSpace(), MAX_FLUID_TRANSFER);
            FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if (FLUID_TANK.isFluidValid(stack)) {
                stack = handler.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                fillTankWithFluid(stack, handler.getContainer());
            }
        });
    }

    private void fillTankWithFluid(FluidStack stack, ItemStack container) {
        FLUID_TANK.fill(stack, IFluidHandler.FluidAction.EXECUTE);
        itemHandler.extractItem(FLUID_SLOT, 1, false);
        itemHandler.insertItem(FLUID_SLOT, container, false);
    }

    private boolean hasFluidItemInSourceSlot() {
        return itemHandler.getStackInSlot(FLUID_SLOT).getCount() > 0;
    }

    private void recipeProvider(Level pLevel, BlockPos pPos, BlockState pState) {
        if (hasRecipe() && hasEnoughEnergy()){
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if (hasProgressFinished()){
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

    private boolean hasEnoughEnergy() {
        return ENERGY_STORAGE.getEnergyStored() >= ENERGY_REQUIREMENT;
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<InjectionMoldingMachineRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.FLUID_TANK.drain(recipe.get().getFluid().getAmount(), IFluidHandler.FluidAction.EXECUTE);
        this.itemHandler.extractItem(DYE_SLOT, 1, false);
        this.itemHandler.extractItem(RESIN_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }
    private boolean hasRecipe() {
        Optional<InjectionMoldingMachineRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem())
                && hasCorrectFluidInTank(recipe) && hasCorrectFluidAmountInTank(recipe);
    }

    private boolean hasCorrectFluidAmountInTank(Optional<InjectionMoldingMachineRecipe> recipe) {
        return FLUID_TANK.getFluidAmount() >= recipe.get().getFluid().getAmount();
    }

    private boolean hasCorrectFluidInTank(Optional<InjectionMoldingMachineRecipe> recipe) {
        return recipe.get().getFluid().equals(FLUID_TANK.getFluid());
    }

    private Optional<InjectionMoldingMachineRecipe> getCurrentRecipe() {
        SimpleContainer inv = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++){
            inv.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(InjectionMoldingMachineRecipe.Type.INSTANCE, inv, level);
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
}
