package net.kinglybugle.augment.event;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.blocks.entity.ModBlockEntities;
import net.kinglybugle.augment.blocks.entity.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Augment.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.SIMPLE_FUSION_FORGE_BE.get(), SimpleFusionForgeBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BASIC_FUSION_FORGE_BE.get(), BasicFusionForgeBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ADVANCED_FUSION_FORGE_BE.get(), AdvancedFusionForgeBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.REINFORCED_FUSION_FORGE_BE.get(), ReinforcedFusionForgeBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.HIGH_GRADE_FUSION_FORGE_BE.get(), HighGradeFusionForgeBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TITANIUM_FUSION_FORGE_BE.get(), TitaniumFusionForgeBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.QUANTUM_FUSION_FORGE_BE.get(), QuantumFusionForgeBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.STABILIZATION_CHAMBER_BE.get(), StabilizationChambereBlockEntityRenderer::new);
    }
}