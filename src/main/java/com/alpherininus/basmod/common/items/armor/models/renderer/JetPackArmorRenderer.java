package com.alpherininus.basmod.common.items.armor.models.renderer;

import com.alpherininus.basmod.common.items.armor.JetPackArmorItem;
import com.alpherininus.basmod.common.items.armor.models.JetPackArmorModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class JetPackArmorRenderer extends GeoArmorRenderer<JetPackArmorItem> {
    public JetPackArmorRenderer() {
        super(new JetPackArmorModel());

        this.bodyBone = "armorBody";
        this.rightArmBone = "armorLeftArm";
        this.leftArmBone = "armorRightArm";
    }

}
