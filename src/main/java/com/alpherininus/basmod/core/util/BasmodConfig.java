package com.alpherininus.basmod.core.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class BasmodConfig {
    public static final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec COMMON_SPEC_GENERAL;


    // for Basmod Alpha version 5.2
    public static final ForgeConfigSpec.ConfigValue<Integer> config_integer_manabar;
    public static final ForgeConfigSpec.ConfigValue<Integer> config_integer_magicalstaff;
    public static final ForgeConfigSpec.ConfigValue<Integer> config_integer_healstaff;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final ForgeConfigSpec.ConfigValue<Integer> config_integer_ad_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Float> config_float_as_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_thunder_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_tnt_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_fireball_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_firework_magical_staff;

    public static final ForgeConfigSpec.ConfigValue<String> config_notices_magical_staff;

    static {
        CONFIG_BUILDER.comment("general configs").push("Basmod Configs");

        // RenderGameOverlayEvent
        config_integer_manabar = CONFIG_BUILDER.comment("Default value is Bar Maximum: 88.").worldRestart().define("Config Manabar", 88);
        config_integer_magicalstaff = CONFIG_BUILDER.comment("Default value is Bar Maximum: 88.").worldRestart().define("Config Magical Staff bar", 88);
        config_integer_healstaff = CONFIG_BUILDER.comment("Default value is Bar Maximum: 88.").worldRestart().define("Config Heal Staff bar", 88);

        //

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        CONFIG_BUILDER.comment("config your item").push("Magical Staff Configs");

        // Magical Staff
        config_integer_ad_magical_staff = CONFIG_BUILDER.comment("Default value is 2.").worldRestart().define("Magical Staff Attackdamadge", 2);
        config_float_as_magical_staff = CONFIG_BUILDER.comment("Default value is -2.0F.").define("Magical Staff Attackspeed", 0.20F);
        config_thunder_magical_staff = CONFIG_BUILDER.comment("Default value is true.").define("Spawn Thunder", true);
        config_tnt_magical_staff = CONFIG_BUILDER.comment("Default value is false.").define("Spawn TNT", false);
        config_fireball_magical_staff = CONFIG_BUILDER.comment("Default value is false.").define("Spawn Fireball", false);
        config_firework_magical_staff = CONFIG_BUILDER.comment("Default value is false.").define("Spawn Firework", false);
        config_notices_magical_staff = CONFIG_BUILDER.comment("Default value is \"Basmod Item\".").define("Notices", "\u00A76Basmod Item");

        //

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        CONFIG_BUILDER.pop();
        COMMON_SPEC_GENERAL = CONFIG_BUILDER.build();
    }
}
