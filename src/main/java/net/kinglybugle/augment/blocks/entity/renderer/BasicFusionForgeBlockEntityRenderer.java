package net.kinglybugle.augment.blocks.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.kinglybugle.augment.blocks.entity.basictier.BasicFusionForgeBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BasicFusionForgeBlockEntityRenderer implements BlockEntityRenderer<BasicFusionForgeBlockEntity> {
    public BasicFusionForgeBlockEntityRenderer(BlockEntityRendererProvider.Context context){

    }

    @Override
    public void render(BasicFusionForgeBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack inputItemStack = pBlockEntity.itemHandler.getStackInSlot(BasicFusionForgeBlockEntity.INPUT_SLOT);
        renderSlot(pPoseStack, itemRenderer, inputItemStack, 0.5f, 0.7f, 0.5f, pPackedLight, pBuffer, pBlockEntity);

        ItemStack extraItemStack = pBlockEntity.itemHandler.getStackInSlot(BasicFusionForgeBlockEntity.EXTRA_SLOT);
        renderSlot(pPoseStack, itemRenderer, extraItemStack, 0.5f, 0.75f, 0.5f, pPackedLight, pBuffer, pBlockEntity);

        ItemStack outputItemStack = pBlockEntity.itemHandler.getStackInSlot(BasicFusionForgeBlockEntity.OUTPUT_SLOT);
        renderSlot(pPoseStack, itemRenderer, outputItemStack, 0.5f, 0.35f, 0.5f, pPackedLight, pBuffer, pBlockEntity);
    }
    private void renderSlot(PoseStack pPoseStack, ItemRenderer itemRenderer, ItemStack itemStack, float x, float y, float z, int packedLight, MultiBufferSource pBuffer, BlockEntity pBlockEntity) {
        if (!itemStack.isEmpty()) {
            pPoseStack.pushPose();
            pPoseStack.translate(x, y, z);
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(270));

            itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
            pPoseStack.popPose();
        }
    }

    private int getLightLevel(Level pLevel, BlockPos pPos){
        int bLight = pLevel.getBrightness(LightLayer.BLOCK, pPos);
        int sLight = pLevel.getBrightness(LightLayer.SKY, pPos);
        return LightTexture.pack(bLight, sLight);
    }
}
