package net.kinglybugle.augment.blocks.entity;

import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.screen.InjectionMoldingMachineMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.antlr.v4.runtime.tree.xpath.XPathLexer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InjectionMoldingMachineBlockEntity extends BlockEntity implements MenuProvider {
    //Declarations
    public static final int DYE_SLOT = 0;
    public static final int RESIN_SLOT = 1;
    public static final int FLUID_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;

    public static final int SLOT_SIZE = 4;

    public static final int ENERGY_CAPACITY = 5000;
    public static final int FLUID_CAPACITY = 10000;
    public static final int MAX_ENERGY_TRANSFER = 250;
    public static final int MAX_FLUID_TRANSFER = 1000;

    private int progress = 0;
    private int maxProgress = 100;

    //Content
    private final ItemStackHandler itemHandler = new ItemStackHandler(SLOT_SIZE);
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    public InjectionMoldingMachineBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.INJECTION_MOLDING_MACHINE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> InjectionMoldingMachineBlockEntity.this.progress;
                    case 1 -> InjectionMoldingMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> InjectionMoldingMachineBlockEntity.this.progress = pValue;
                    case 1 -> InjectionMoldingMachineBlockEntity.this.maxProgress = pValue;
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
        if (cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.augment.injection_molding_machine");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new InjectionMoldingMachineMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("injection_molding_machine.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("injection_molding_machine.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        recipeProvider(pLevel, pPos, pState);
    }

    private void recipeProvider(Level pLevel, BlockPos pPos, BlockState pState) {
        if (hasRecipe()){
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if (hasProgressFinished()){
                craftItem();
                resetProgress();
            }
        }
        else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.PLASTIC_SHEET.get(), 1);
        this.itemHandler.extractItem(DYE_SLOT, 1, false);
        this.itemHandler.extractItem(RESIN_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }
    private boolean hasRecipe() {
        boolean hasCoalItem = this.itemHandler.getStackInSlot(DYE_SLOT).getItem() == Items.COAL;
        boolean hasResinItem = this.itemHandler.getStackInSlot(RESIN_SLOT).getItem() == ModItems.RESIN_BALL.get();

        ItemStack result = new ItemStack(ModItems.PLASTIC_SHEET.get());

        return hasCoalItem && hasResinItem && canInsertAmountIntoOutpurSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutpurSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }


}
