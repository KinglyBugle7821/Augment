package net.kinglybugle.augment.event;

import net.kinglybugle.augment.Augment;
import net.kinglybugle.augment.particle.ModParticles;
import net.kinglybugle.augment.particle.custom.SmokeParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Augment.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.SMOKE_PARTICLES.get(),
                SmokeParticles.Provider::new);
    }
}