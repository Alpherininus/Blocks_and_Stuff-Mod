package com.alpherininus.basmod.core.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class BasmodConfig {
    public static final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec COMMON_SPEC;
    public static ForgeConfigSpec COMMON_SPEC_ITEM;
    public static ForgeConfigSpec CLIENT_SPEC;


    public static final ForgeConfigSpec.ConfigValue<Integer> config_integer;
    public static final ForgeConfigSpec.ConfigValue<String> config_string;

    public static final ForgeConfigSpec.ConfigValue<Integer> config_integer_ad_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Float> config_float_as_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_thunder_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_tnt_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_fireball_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_firework_magical_staff;

    public static final ForgeConfigSpec.ConfigValue<String> config_notices_magical_staff;


    static {
        CONFIG_BUILDER.comment("Basmod Configs").push("Basmod Configs");

        config_integer = CONFIG_BUILDER.comment("This is an integer. Default value is 3.").define("Config Integer", 3);
        config_string = CONFIG_BUILDER.comment("This is an integer. Default value is \"Alpherininus\".").define("Config String", "Alpherininus");

        CONFIG_BUILDER.pop();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        COMMON_SPEC = CONFIG_BUILDER.build();
    }

    static {
        CONFIG_BUILDER.comment("Config your Item").push("Item Configs");

        // Magical Staff
        config_integer_ad_magical_staff = CONFIG_BUILDER.comment("This is an integer. Default value is 2.").define("Magical Staff Attackdamadge", 2);
        config_float_as_magical_staff = CONFIG_BUILDER.comment("This is an float. Default value is -2.0F.").define("Magical Staff Attackspeed", -2.0F);

        config_thunder_magical_staff = CONFIG_BUILDER.comment("Default value is true.").define("Spawn Thunder", true);
        config_tnt_magical_staff = CONFIG_BUILDER.comment("Default value is false.").define("Spawn TNT", false);
        config_fireball_magical_staff = CONFIG_BUILDER.comment("Default value is false.").define("Spawn Fireball", false);
        config_firework_magical_staff = CONFIG_BUILDER.comment("Default value is false.").define("Spawn Firework", false);

        config_notices_magical_staff = CONFIG_BUILDER.comment("This is an integer. Default value is \"Basmod Item\".").define("Notices", "\u00A76Basmod Item");

        //
        CONFIG_BUILDER.pop();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        COMMON_SPEC_ITEM = CONFIG_BUILDER.build();
    }

}
