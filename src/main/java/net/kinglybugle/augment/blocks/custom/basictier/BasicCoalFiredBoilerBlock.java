package net.kinglybugle.augment.blocks.custom.basictier;

import net.kinglybugle.augment.blocks.ModBlocks;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.kinglybugle.augment.blocks.entity.basictier.BasicCoalFiredBoilerBlockEntity;
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
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BasicCoalFiredBoilerBlock extends BaseEntityBlock {

    public static final VoxelShape SHAPE = Block.box(0,0,0, 16, 16, 16);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    private String pTier;
    public BasicCoalFiredBoilerBlock(Properties properties, String pTier) {
        super(properties);
        this.pTier = pTier;
    }
    private String getTier() {
        return pTier;
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof BasicCoalFiredBoilerBlockEntity){

            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }
    //CREDIT TO symbols97 on discord
    //Thanks for sharing the code!
    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        BlockEntity be = pLevel.getBlockEntity(pPos);
        if (be instanceof BasicCoalFiredBoilerBlockEntity coalFiredBoiler && pLevel instanceof ServerLevel && !pPlayer.isCreative()){
            if (coalFiredBoiler.ENERGY_STORAGE.getEnergyStored() != 0){
                    ItemStack itemStack = new ItemStack(ModBlocks.BASIC_COAL_FIRED_BOILER.get());
                    coalFiredBoiler.saveToItem(itemStack);
                    ItemEntity entity = new ItemEntity(pLevel, pPos.getX() + 0.5D, pPos.getY() + 0.5D, pPos.getZ() + 0.5D, itemStack);
                    pLevel.addFreshEntity(entity);

            }
            else {
                    ItemStack itemStack = new ItemStack(ModBlocks.BASIC_COAL_FIRED_BOILER.get());
                    ItemEntity entity = new ItemEntity(pLevel, pPos.getX() + 0.5D, pPos.getY() + 0.5D, pPos.getZ() + 0.5D, itemStack);
                    pLevel.addFreshEntity(entity);

            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (!pLevel.isClientSide()){
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof BasicCoalFiredBoilerBlockEntity){
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (BasicCoalFiredBoilerBlockEntity)entity, pPos);
            }else {
                throw new IllegalStateException("Missing Container Provider");
            }
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BasicCoalFiredBoilerBlockEntity(pPos, pState);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {

        if (pLevel.isClientSide()){
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.BASIC_COAL_FIRED_BOILER_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        Component shiftInfo = Component.translatable("gui.augment.shift_info");
        pTooltip.add(shiftInfo);
        if (Screen.hasShiftDown()) {
            pTooltip.remove(shiftInfo);
            pTooltip.add(Component.literal("Creates Energy From Coal-Based Material"));

            double energyCapacitykFE = (double) BasicCoalFiredBoilerBlockEntity.CAPACITY / 1000;
            String formattedEnergyCapacity = String.format("%.2f", energyCapacitykFE);
            Component energyInfo = Component.translatable("gui.augment.energy_empty", formattedEnergyCapacity);
            pTooltip.add(energyInfo);

            pTooltip.add(Component.literal("Tier: " + getTier()));

            if (pStack.hasTag() && pStack.getTag().contains("BlockEntityTag")) {
                CompoundTag blockEntityTag = pStack.getTag().getCompound("BlockEntityTag");
                if (blockEntityTag.contains("basic_coal_fired_boiler.energy")) {
                    int energyStored = blockEntityTag.getInt("basic_coal_fired_boiler.energy");
                    double energyStoredkFE = (double) energyStored / 1000;
                    String formattedEnergyStored = String.format("%.2f", energyStoredkFE);
                    pTooltip.remove(energyInfo);
                    pTooltip.add(Component.translatable("gui.augment.energy_stored", formattedEnergyStored, formattedEnergyCapacity));
                }
            }
        }
    }
}
