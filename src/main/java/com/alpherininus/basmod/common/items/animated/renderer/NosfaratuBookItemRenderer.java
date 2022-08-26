package com.alpherininus.basmod.common.items.animated.renderer;

import com.alpherininus.basmod.common.items.animated.HealStaffItem;
import com.alpherininus.basmod.common.items.animated.NosfaratuBookItem;
import com.alpherininus.basmod.common.items.animated.models.HealStaffItemModel;
import com.alpherininus.basmod.common.items.animated.models.NosfaratuBooktemModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class NosfaratuBookItemRenderer extends GeoItemRenderer<NosfaratuBookItem> {

    public NosfaratuBookItemRenderer() {
        super(new NosfaratuBooktemModel());
    }
}
