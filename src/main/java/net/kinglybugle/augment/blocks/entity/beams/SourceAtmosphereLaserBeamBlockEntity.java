package net.kinglybugle.augment.blocks.entity.beams;

import net.kinglybugle.augment.blocks.ModBlocks;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SourceAtmosphereLaserBeamBlockEntity extends BlockEntity {
    public SourceAtmosphereLaserBeamBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SOURCE_ATMOSPHERE_LASER_BEAM_BE.get(), pPos, pBlockState);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos.above(), ModBlocks.SECONDARY_ATMOSPHERE_LASER_BEAM.get().defaultBlockState(), 3);
        BlockPos belowPos = pPos.below();
        BlockState belowBlockState = pLevel.getBlockState(belowPos);
        if (belowBlockState.getBlock() != ModBlocks.TITANIUM_ASTRAL_LASER.get()) {
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        }
    }
}
