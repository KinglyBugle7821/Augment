package net.kinglybugle.augment.networking.packet;

import net.kinglybugle.augment.blocks.entity.advancedtier.AdvancedCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.advancedtier.AdvancedInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.basictier.BasicCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.basictier.BasicInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.highgradetier.HighGradeCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.highgradetier.HighGradeInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.quantumtier.QuantumCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.quantumtier.QuantumInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.reinforcedtier.ReinforcedCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.reinforcedtier.ReinforcedInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.simpletier.SimpleInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.simpletier.SimpleCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.titaniumtier.TitaniumAstralLaserBlockEntity;
import net.kinglybugle.augment.blocks.entity.titaniumtier.TitaniumCoalFiredBoilerBlockEntity;
import net.kinglybugle.augment.blocks.entity.titaniumtier.TitaniumInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.screen.advancedtier.AdvancedCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.advancedtier.AdvancedInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.basictier.BasicCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.basictier.BasicInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.highgradetier.HighGradeCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.highgradetier.HighGradeInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.quantumtier.QuantumCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.quantumtier.QuantumInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.reinforcedtier.ReinforcedCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.reinforcedtier.ReinforcedInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.simpletier.SimpleCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.simpletier.SimpleInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumAstralLaserMenu;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumCoalFiredBoilingMenu;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumInjectionMoldingMachineMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergySyncS2CPacket {
    private final int energy;
    private final BlockPos pos;

    public EnergySyncS2CPacket(int energy, BlockPos pos) {
        this.energy = energy;
        this.pos = pos;
    }

    public EnergySyncS2CPacket(FriendlyByteBuf buf) {
        this.energy = buf.readInt();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(energy);
        buf.writeBlockPos(pos);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //Simple
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof SimpleCoalFiredBoilerBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof SimpleCoalFiredBoilingMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof SimpleInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof SimpleInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            //Basic
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof BasicCoalFiredBoilerBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof BasicCoalFiredBoilingMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof BasicInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof BasicInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            //Advanced
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof AdvancedCoalFiredBoilerBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof AdvancedCoalFiredBoilingMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof AdvancedInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof AdvancedInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            //Reinforced
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof ReinforcedCoalFiredBoilerBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof ReinforcedCoalFiredBoilingMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof ReinforcedInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof ReinforcedInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            //High-Grade
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof HighGradeCoalFiredBoilerBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof HighGradeCoalFiredBoilingMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof HighGradeInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof HighGradeInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            //Titanium
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof TitaniumCoalFiredBoilerBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof TitaniumCoalFiredBoilingMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof TitaniumInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof TitaniumInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof TitaniumAstralLaserBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof TitaniumAstralLaserMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            //Quantum
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof QuantumCoalFiredBoilerBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof QuantumCoalFiredBoilingMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof QuantumInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof QuantumInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
        });
        return true;
    }
}
