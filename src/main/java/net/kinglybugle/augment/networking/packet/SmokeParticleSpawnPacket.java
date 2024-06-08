package net.kinglybugle.augment.networking.packet;

import net.kinglybugle.augment.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SmokeParticleSpawnPacket {
    private final BlockPos pos;

    public SmokeParticleSpawnPacket(BlockPos pos) {
        this.pos = pos;
    }

    public static void encode(SmokeParticleSpawnPacket packet, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(packet.pos);
    }

    public static SmokeParticleSpawnPacket decode(FriendlyByteBuf buffer) {
        return new SmokeParticleSpawnPacket(buffer.readBlockPos());
    }

    public static void handle(SmokeParticleSpawnPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            // Spawn smoke particles on the client side
            Minecraft.getInstance().level.addParticle(ModParticles.SMOKE_PARTICLES.get(), packet.pos.getX() + 0.5, packet.pos.getY() + 1, packet.pos.getZ() + 0.5, 0.0D, 1.0D, 0.0D);
        });
        context.get().setPacketHandled(true);
    }
}
