package com.alpherininus.basmod.common.items.animated.renderer;

import com.alpherininus.basmod.common.items.animated.HealStaffItem;
import com.alpherininus.basmod.common.items.animated.models.HealStaffItemModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class HealStaffItemRenderer extends GeoItemRenderer<HealStaffItem> {

    public HealStaffItemRenderer() {
        super(new HealStaffItemModel());
    }
}
