package com.alpherininus.basmod.core.util;

import com.alpherininus.basmod.Basmod;
import net.minecraft.client.gui.fonts.FontResourceManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;

public class BasmodFonts extends FontResourceManager {

    public static final ResourceLocation DEFAULT_FONT_RENDERER_NAME = new ResourceLocation(Basmod.MOD_ID,"npc_character");


    public BasmodFonts(TextureManager textureManager) {
        super(textureManager);
    }

    public static Style normal = Style.EMPTY;
    // public static Style npc_character_face = Style.EMPTY.setFontId(new ResourceLocation(Basmod.MOD_ID, "npc_character"));

}
