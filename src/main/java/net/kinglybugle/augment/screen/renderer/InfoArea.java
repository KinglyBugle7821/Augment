package net.kinglybugle.augment.screen.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Rect2i;

/*
 *  BluSunrize
 *  Copyright (c) 2021
 *
 *  This code is licensed under "Blu's License of Common Sense"
 *  Details can be found in the license file in the root folder of this project
 */
public abstract class InfoArea extends GuiGraphics {
    protected final Rect2i area;

    protected InfoArea(Rect2i area, Minecraft minecraft, MultiBufferSource.BufferSource pBufferSource) {
        super(minecraft, pBufferSource);
        this.area = area;
    }

    public abstract void draw(PoseStack transform);
}
