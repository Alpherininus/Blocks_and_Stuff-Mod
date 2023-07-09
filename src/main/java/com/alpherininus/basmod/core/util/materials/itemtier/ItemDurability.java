package com.alpherininus.basmod.core.util.materials.itemtier;

import net.minecraft.entity.player.PlayerEntity;

public class ItemDurability {

    public static int getWood() {
        return 59;
    }

    public static int getGold() {
        return 32;
    }

    public static int getStone() {
        return 131;
    }

    public static int getIron() {
        return 250;
    }

    public static int getDiamond() {
        return 1561;
    }

    public static int getNetherite() {
        return 2031;
    }

    public static int setItemDurability(int durability) {
        return durability;
    }

    public static int setModifiItemDurability(PlayerEntity modifi, int modifiDur) {
        return modifiDur;
    }

}
