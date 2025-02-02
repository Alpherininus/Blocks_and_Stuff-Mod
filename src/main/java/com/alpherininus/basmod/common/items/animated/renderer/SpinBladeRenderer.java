package com.alpherininus.basmod.common.items.animated.renderer;

import com.alpherininus.basmod.common.items.animated.SpinBladeItem;
import com.alpherininus.basmod.common.items.animated.models.SpinBladeModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class SpinBladeRenderer extends GeoItemRenderer<SpinBladeItem> {

    public SpinBladeRenderer() {
        super(new SpinBladeModel());
    }
}