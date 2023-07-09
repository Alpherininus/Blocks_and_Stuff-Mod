package com.alpherininus.basmod.core.util.materials.itemtier;

public class ItemAttackSpeed {

    public static float getSword() {
        return -2.4F; // = Attackspeed 1.6
    }
    public static float getTrident() {
        return -2.9f; // = Attackspeed 1.1
    }
    public static float getShovel() {
        return -3.0f; // = Attackspeed 1.0
    }
    public static float getPickaxe() {
        return -2.8f; // = Attackspeed 1.2
    }

    public static class Axe {
        public float getWoodStone() { return -3.2f;
        }
        public float getGoldDiamondNetherite() { return -3.0f;
        }
        public float getIron() { return -3.1f;
        }
    }

    public static class Hoe {
        public float getWoodGold() { return -3.0f;
        }
        public float getStone() { return -2.0f;
        }
        public float getIron() { return -1.0f;
        }
        public float getDiamondNetherite() { return -0.0f;
        }
    }
    public static float setAttackSpeed(float speed) {
        return speed;
    }
}
