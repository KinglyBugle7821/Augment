package net.kinglybugle.augment.networking.packet;

import net.kinglybugle.augment.blocks.entity.advancedtier.AdvancedInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.basictier.BasicInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.highgradetier.HighGradeInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.quantumtier.QuantumInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.reinforcedtier.ReinforcedInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.simpletier.SimpleInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.blocks.entity.titaniumtier.TitaniumInjectionMoldingMachineBlockEntity;
import net.kinglybugle.augment.screen.advancedtier.AdvancedInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.basictier.BasicInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.highgradetier.HighGradeInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.quantumtier.QuantumInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.reinforcedtier.ReinforcedInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.simpletier.SimpleInjectionMoldingMachineMenu;
import net.kinglybugle.augment.screen.titaniumtier.TitaniumInjectionMoldingMachineMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FluidSyncS2CPacket {
    private final FluidStack fluidStack;
    private final BlockPos pos;

    public FluidSyncS2CPacket(FluidStack fluidStack, BlockPos pos) {
        this.fluidStack = fluidStack;
        this.pos = pos;
    }

    public FluidSyncS2CPacket(FriendlyByteBuf buf) {
        this.fluidStack = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof SimpleInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof SimpleInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof BasicInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof BasicInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof AdvancedInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof AdvancedInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof ReinforcedInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof ReinforcedInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof HighGradeInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof HighGradeInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof TitaniumInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof TitaniumInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof QuantumInjectionMoldingMachineBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof QuantumInjectionMoldingMachineMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
        });
        return true;
    }
}
