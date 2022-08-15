package com.alpherininus.basmod.common.items.animated.renderer;

import com.alpherininus.basmod.common.items.animated.TeleportStaffItem;
import com.alpherininus.basmod.common.items.animated.models.TeleportStaffItemModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class TeleportStaffItemRenderer extends GeoItemRenderer<TeleportStaffItem> {

    public TeleportStaffItemRenderer() {
        super(new TeleportStaffItemModel());
    }
}
