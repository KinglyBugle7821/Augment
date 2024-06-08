package net.kinglybugle.augment.networking.packet;

import net.kinglybugle.augment.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GreenParticleSpawnPacket {
    private final BlockPos pos;

    public GreenParticleSpawnPacket(BlockPos pos) {
        this.pos = pos;
    }

    public static void encode(GreenParticleSpawnPacket packet, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(packet.pos);
    }

    public static GreenParticleSpawnPacket decode(FriendlyByteBuf buffer) {
        return new GreenParticleSpawnPacket(buffer.readBlockPos());
    }

    public static void handle(GreenParticleSpawnPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Minecraft.getInstance().level.addParticle(ParticleTypes.SCRAPE, packet.pos.getX() + 0.5, packet.pos.getY() + 0.5, packet.pos.getZ() + 0.5, 0.0D, 1.0D, 0.0D);
        });
        context.get().setPacketHandled(true);
    }
}
