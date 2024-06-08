package net.kinglybugle.augment.blocks.custom.quantumtier;

import net.kinglybugle.augment.Items.ModItems;
import net.kinglybugle.augment.blocks.ModBlocks;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.kinglybugle.augment.blocks.entity.quantumtier.QuantumInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.fluid.ModFluids;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class QuantumInjectionMoldingMachineBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE = Block.box(0,0,0,16,16,16);

    private String pTier;
    public QuantumInjectionMoldingMachineBlock(Properties pProperties, String pTier) {
        super(pProperties);
        this.pTier = pTier;
    }

    private String getTier() {
        return pTier;
    }

    //FACING
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
    //SHAPE
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof QuantumInjectionMoldingMachineBlockEntity) {
//                ((QuantumInjectionMoldingMachineBlockEntity) blockEntity).drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }
    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        BlockEntity be = pLevel.getBlockEntity(pPos);
        if (be instanceof QuantumInjectionMoldingMachineBlockEntity injectionMolding && pLevel instanceof ServerLevel && !pPlayer.isCreative()){
            if (injectionMolding.ENERGY_STORAGE.getEnergyStored() != 0 || injectionMolding.FLUID_TANK.getFluidAmount() != 0){
                ItemStack itemStack = new ItemStack(ModBlocks.QUANTUM_INJECTION_MOLDING_MACHINE.get());
                injectionMolding.saveToItem(itemStack);
                ItemEntity entity = new ItemEntity(pLevel, pPos.getX() + 0.5D, pPos.getY() + 0.5D, pPos.getZ() + 0.5D, itemStack);
                pLevel.addFreshEntity(entity);
            }
            else {
                ItemStack itemStack = new ItemStack(ModBlocks.QUANTUM_INJECTION_MOLDING_MACHINE.get());
                ItemEntity entity = new ItemEntity(pLevel, pPos.getX() + 0.5D, pPos.getY() + 0.5D, pPos.getZ() + 0.5D, itemStack);
                pLevel.addFreshEntity(entity);
            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack heldItem = pPlayer.getItemInHand(pHand);

        if (!heldItem.isEmpty() && heldItem.getItem() instanceof BucketItem) {
            BucketItem bucketItem = (BucketItem) heldItem.getItem();
            Fluid fluid = bucketItem.getFluid();
           if (fluid == ModFluids.SOURCE_CRUDE_OIL.get()){
               BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
               if (blockEntity instanceof QuantumInjectionMoldingMachineBlockEntity) {
                   QuantumInjectionMoldingMachineBlockEntity injectionMoldingMachine = (QuantumInjectionMoldingMachineBlockEntity) blockEntity;
                   FluidStack oilStack = new FluidStack(ModFluids.SOURCE_CRUDE_OIL.get(), 1000);
                   int filledAmount = injectionMoldingMachine.FLUID_TANK.fill(oilStack, IFluidHandler.FluidAction.EXECUTE);
                   if (filledAmount > 0) {
                       if (!pPlayer.isCreative()) {
                           heldItem.shrink(1);
                           if (heldItem.isEmpty()) {
                               pPlayer.setItemInHand(pHand, new ItemStack(Items.BUCKET)); // Replace with empty bucket
                           } else if (!pPlayer.getInventory().add(new ItemStack(Items.BUCKET))) {
                               pPlayer.drop(heldItem, false);
                           }
                       }
                       return InteractionResult.SUCCESS;
                   }
               }
           }
           else if (fluid == Fluids.EMPTY){
               BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
               if (blockEntity instanceof QuantumInjectionMoldingMachineBlockEntity) {
                   QuantumInjectionMoldingMachineBlockEntity injectionMoldingMachine = (QuantumInjectionMoldingMachineBlockEntity) blockEntity;
                   FluidStack oilStack = new FluidStack(ModFluids.SOURCE_CRUDE_OIL.get(), 1000);
                   FluidStack filledAmount = injectionMoldingMachine.FLUID_TANK.drain(oilStack, IFluidHandler.FluidAction.EXECUTE);
                   if (filledAmount.getAmount() > 0) {
                       if (!pPlayer.isCreative()) {
                           heldItem.shrink(1);
                           if (heldItem.isEmpty()) {
                               pPlayer.setItemInHand(pHand, new ItemStack(ModItems.CRUDE_OIL_BUCKET.get())); // Replace with empty bucket
                           } else if (!pPlayer.getInventory().add(new ItemStack(ModItems.CRUDE_OIL_BUCKET.get()))) {
                               pPlayer.drop(heldItem, false);
                           }
                       }
                       return InteractionResult.SUCCESS;
                   }
               }
           }

            return InteractionResult.SUCCESS;
        }

        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof QuantumInjectionMoldingMachineBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer)pPlayer), (QuantumInjectionMoldingMachineBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }

        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide){
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.QUANTUM_INJECTION_MOLDING_MACHINE_BE.get(), (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new QuantumInjectionMoldingMachineBlockEntity(pPos, pState);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        Component shiftInfo = Component.translatable("gui.augment.shift_info");
        pTooltip.add(shiftInfo);
        if (Screen.hasShiftDown()){
            pTooltip.remove(shiftInfo);

            double energyCapacity = (double) QuantumInjectionMoldingMachineBlockEntity.ENERGY_CAPACITY / 1000;
            String formattedEnergyCapacity = String.format("%.2f", energyCapacity);
            double fluidCapacity = (double) QuantumInjectionMoldingMachineBlockEntity.FLUID_CAPACITY / 1000;
            String formattedFluidCapacity = String.format("%.2f", fluidCapacity);

            pTooltip.add(Component.literal("Creates Plastic-Based Material"));
            Component energyInfo = Component.translatable("gui.augment.energy_empty", formattedEnergyCapacity);
            Component fluidInfo = Component.translatable("gui.augment.fluid_empty", formattedFluidCapacity);
            pTooltip.add(energyInfo);
            pTooltip.add(fluidInfo);
            pTooltip.add(Component.literal("Tier: " + getTier()));

            if (pStack.hasTag() && pStack.getTag().contains("BlockEntityTag")) {
                CompoundTag blockEntityTag = pStack.getTag().getCompound("BlockEntityTag");
                if (blockEntityTag.contains("quantum_injection_molding_machine.energy")) {
                    int energyStored = blockEntityTag.getInt("quantum_injection_molding_machine.energy");
                    double energyStoredkFE = (double) energyStored / 1000;
                    String formattedEnergyStored = String.format("%.2f", energyStoredkFE);
                    pTooltip.remove(energyInfo);
                    pTooltip.add(Component.translatable("gui.augment.energy_stored", formattedEnergyStored, formattedEnergyCapacity));
                }
                if (blockEntityTag.contains("FluidTank")) {
                    CompoundTag fluidTag = blockEntityTag.getCompound("FluidTank");
                    FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(fluidTag);

                    double fluidStoredkFE = (double) fluidStack.getAmount() / 1000;
                    String formattedFluidStored = String.format("%.2f", fluidStoredkFE);
                    pTooltip.remove(fluidInfo);

                    pTooltip.add(Component.translatable("gui.augment.fluid_stored", formattedFluidStored, formattedFluidCapacity));
                }
            }
        }
    }
}
