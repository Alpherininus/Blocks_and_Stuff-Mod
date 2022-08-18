package com.alpherininus.basmod.common.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;

public class BasmodStartGui extends Screen {

    public BasmodStartGui() {
        super(titleIn);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        // width ... -25 = Mittig. // heigth ... -100 = Oben.
        int drawcolor = 16777215;

        renderDirtBackground(0);

        font.drawString(new MatrixStack(), "Hier Könnte ihre Werbung Stehen!", width / 2 - 25, height / 2 - 100, drawcolor);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
