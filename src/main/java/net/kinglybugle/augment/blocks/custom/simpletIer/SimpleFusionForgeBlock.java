package net.kinglybugle.augment.blocks.custom.simpletIer;

import net.kinglybugle.augment.blocks.entity.basictier.BasicFusionForgeBlockEntity;
import net.kinglybugle.augment.blocks.entity.simpletier.SimpleFusionForgeBlockEntity;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.kinglybugle.augment.util.ModTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SimpleFusionForgeBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE = Block.box(0,0,0,16,16,16);

    private String pTier;
    public SimpleFusionForgeBlock(Properties pProperties, String pTier) {
        super(pProperties);
        this.pTier = pTier;
    }
    private String getTier(){
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
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SimpleFusionForgeBlockEntity) {
                ((SimpleFusionForgeBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack heldItem = pPlayer.getItemInHand(pHand);
        if (!heldItem.isEmpty()) {
            if (heldItem.is(ModTags.Items.INGOT_ITEM)) {
                SimpleFusionForgeBlockEntity blockEntity = (SimpleFusionForgeBlockEntity) pLevel.getBlockEntity(pPos);
                if (blockEntity != null) {
                    ItemStack inputSlotStack = blockEntity.itemHandler.getStackInSlot(SimpleFusionForgeBlockEntity.INPUT_SLOT);
                    ItemStack extraSlotStack = blockEntity.itemHandler.getStackInSlot(SimpleFusionForgeBlockEntity.EXTRA_SLOT);
                    if (inputSlotStack.isEmpty()) {
                        blockEntity.itemHandler.setStackInSlot(SimpleFusionForgeBlockEntity.INPUT_SLOT, heldItem.split(1));
                        pPlayer.setItemInHand(pHand, heldItem);
                        return InteractionResult.SUCCESS;
                    } else if (extraSlotStack.isEmpty() && !inputSlotStack.isEmpty()) {
                        blockEntity.itemHandler.setStackInSlot(SimpleFusionForgeBlockEntity.EXTRA_SLOT, heldItem.split(1));
                        pPlayer.setItemInHand(pHand, heldItem);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        else if (heldItem.isEmpty()) {
            SimpleFusionForgeBlockEntity blockEntity = (SimpleFusionForgeBlockEntity) pLevel.getBlockEntity(pPos);
            if (blockEntity != null) {
                ItemStack outputSlotStack = blockEntity.itemHandler.getStackInSlot(SimpleFusionForgeBlockEntity.OUTPUT_SLOT);
                ItemStack inputSlotStack = blockEntity.itemHandler.getStackInSlot(SimpleFusionForgeBlockEntity.INPUT_SLOT);
                ItemStack extraSlotStack = blockEntity.itemHandler.getStackInSlot(SimpleFusionForgeBlockEntity.EXTRA_SLOT);
                if (!outputSlotStack.isEmpty()) {
                    ItemStack extractedItem = blockEntity.itemHandler.extractItem(SimpleFusionForgeBlockEntity.OUTPUT_SLOT, 64, false);
                    insertItemIntoPlayerInventory(pPlayer, extractedItem);
                    return InteractionResult.SUCCESS;
                }
                else if (outputSlotStack.isEmpty() && !extraSlotStack.isEmpty()) {
                    ItemStack extractedItem = blockEntity.itemHandler.extractItem(SimpleFusionForgeBlockEntity.EXTRA_SLOT, 64, false);
                    insertItemIntoPlayerInventory(pPlayer, extractedItem);
                    return InteractionResult.SUCCESS;
                }
                else if (outputSlotStack.isEmpty() && extraSlotStack.isEmpty() && !inputSlotStack.isEmpty()) {
                    ItemStack extractedItem = blockEntity.itemHandler.extractItem(SimpleFusionForgeBlockEntity.INPUT_SLOT, 64, false);
                    insertItemIntoPlayerInventory(pPlayer, extractedItem);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
    private void insertItemIntoPlayerInventory(Player player, ItemStack stack) {
        if (!player.getInventory().add(stack)) {
            player.drop(stack, false);
        }
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SimpleFusionForgeBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()){
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.SIMPLE_FUSION_FORGE_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        Component shiftInfo = Component.translatable("gui.augment.shift_info");
        pTooltip.add(shiftInfo);
        if (Screen.hasShiftDown()){
            pTooltip.remove(shiftInfo);
            pTooltip.add(Component.literal("Creates Alloys By Mixing Ingots and Resources"));

            double energyCapacitykFE = (double) SimpleFusionForgeBlockEntity.ENERGY_CAPACITY / 1000;
            String formattedEnergyCapacity = String.format("%.2f", energyCapacitykFE);
            pTooltip.add(Component.translatable("gui.augment.energy_empty", formattedEnergyCapacity));

            pTooltip.add(Component.literal("Tier: " + getTier()));
        }
    }
}
