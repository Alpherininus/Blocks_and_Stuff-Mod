package com.alpherininus.basmod.common.items.animated.renderer;

import com.alpherininus.basmod.common.items.animated.MagicalStaffItem;
import com.alpherininus.basmod.common.items.animated.models.MagicalStaffItemModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class MagicalStaffItemRenderer extends GeoItemRenderer<MagicalStaffItem> {

    public MagicalStaffItemRenderer() {
        super(new MagicalStaffItemModel());
    }
}
