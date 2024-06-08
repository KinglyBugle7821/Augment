package net.kinglybugle.augment.blocks.entity.beams;

import net.kinglybugle.augment.blocks.ModBlocks;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SecondaryAtmosphereLaserBeamBlockEntity extends BlockEntity {
    public SecondaryAtmosphereLaserBeamBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SECONDARY_ATMOSPHERE_LASER_BEAM_BE.get(), pPos, pBlockState);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        BlockPos belowPos = pPos.below();
        BlockState belowBlockState = pLevel.getBlockState(belowPos);
        if (belowBlockState.getBlock() != ModBlocks.SECONDARY_ATMOSPHERE_LASER_BEAM.get() &&
                belowBlockState.getBlock() != ModBlocks.SOURCE_ATMOSPHERE_LASER_BEAM.get()) {
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        }
        if (pPos.getY() < 320) {
            for (int y = pPos.getY() + 1; y <= 320; y++) {
                BlockPos abovePos = new BlockPos(pPos.getX(), y, pPos.getZ());
                pLevel.setBlock(abovePos, ModBlocks.SECONDARY_ATMOSPHERE_LASER_BEAM.get().defaultBlockState(), 3);
            }
        }
    }
}
