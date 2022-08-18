package com.alpherininus.basmod.common.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IBidiRenderer;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;

public class BasmodStartGui extends MainMenuScreen {

    private Slider mySlider;
    private final ITextComponent message;
    private IBidiRenderer field_243289_b = IBidiRenderer.field_243257_a;
    private final Screen nextScreen;
    private int textHeight;

    private int drawcolor = 16777215;

    public BasmodStartGui(ITextComponent message, Screen nextScreen) {
        this.message = message;
        this.nextScreen = nextScreen;
    }

    public boolean shouldCloseOnEsc() {
        return false;
    }

    protected void init() {

        this.addButton(new Button(width / 2 - 50, height / 2 - 50, 100, 20, new StringTextComponent("Start"), Button::onPress));
        assert this.minecraft != null;
        this.minecraft.displayGuiScreen(this.nextScreen);

        this.mySlider = new Slider(width / 2 - 50, height / 2 - 10, 100, 20, new StringTextComponent("Count: "), new StringTextComponent(" %"), 0, 100, 50, true, true, Button::onPress);

        this.field_243289_b = IBidiRenderer.func_243258_a(this.font, this.message, this.width - 50);
        this.textHeight = this.field_243289_b.func_241862_a() * 9;
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);

        this.field_243289_b.func_241863_a(matrixStack, this.width / 2, this.height / 2 - this.textHeight / 2);

        font.drawString(new MatrixStack(), "Hier Könnte ihre Werbung Stehen!", width / 2 - 25, height / 2 - 100, drawcolor);

        mySlider.getValue();

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

}
