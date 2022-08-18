package com.alpherininus.basmod.common.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;

public class BasmodStartGui extends Screen {

    public BasmodStartGui() {
        super(ITextComponent.getTextComponentOrEmpty("empty"));
    }

    @Override
    protected <T extends Widget> T addButton(T button) {
        
        buttons.add(1, new Button(width / 2 - 50, height / 2 - 50, 100, 20, new StringTextComponent("Start"), Button::onPress));
        buttons.add(new Slider(width / 2 - 50, height / 2 - 10, 100, 20, new StringTextComponent("Count: "), new StringTextComponent(" %"), 0, 100, 50, true, true, Button::onPress));
        return button;
    }

    @Override
    public void renderDirtBackground(int vOffset) {
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        // width ... -25 = Mittig. // heigth ... -100 = Oben.
        int drawcolor = 16777215;

        renderBackground(new MatrixStack());

        font.drawString(new MatrixStack(), "Hier Könnte ihre Werbung Stehen!", width / 2 - 25, height / 2 - 100, drawcolor);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

}
