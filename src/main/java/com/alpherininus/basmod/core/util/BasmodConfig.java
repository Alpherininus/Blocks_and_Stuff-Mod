package com.alpherininus.basmod.core.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class BasmodConfig {
    public static final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec COMMON_SPEC_GENERAL;
    public static ForgeConfigSpec COMMON_SPEC_STAFFITEM;
    public static ForgeConfigSpec COMMON_SPEC_NPC_NAMEN;


    public static ForgeConfigSpec CLIENT_SPEC_GENERAL;
    public static ForgeConfigSpec SERVER_SPEC_GENERAL;

    // for Basmod Alpha version 5.2
    public static final ForgeConfigSpec.ConfigValue<Integer> config_integer_manabar;
    public static final ForgeConfigSpec.ConfigValue<Integer> config_integer_magicalstaff;
    public static final ForgeConfigSpec.ConfigValue<Integer> config_integer_healstaff;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final ForgeConfigSpec.ConfigValue<Integer> config_integer_ad_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_thunder_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_tnt_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_fireball_magical_staff;
    public static final ForgeConfigSpec.ConfigValue<Boolean> config_firework_magical_staff;

    public static final ForgeConfigSpec.ConfigValue<String> config_notices_magical_staff;

    static {
        CONFIG_BUILDER.comment("general configs").push("Basmod COMMON Configs");

        // RenderGameOverlayEvent
        config_integer_manabar = CONFIG_BUILDER.comment("Default value is Bar Maximum: 88.").worldRestart().define("Config Manabar", 88);
        config_integer_magicalstaff = CONFIG_BUILDER.comment("Default value is Bar Maximum: 88.").worldRestart().define("Config Magical Staff bar", 88);
        config_integer_healstaff = CONFIG_BUILDER.comment("Default value is Bar Maximum: 88.").worldRestart().define("Config Heal Staff bar", 88);

        //

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        CONFIG_BUILDER.pop();
        COMMON_SPEC_GENERAL = CONFIG_BUILDER.build();
    }
    static {
        CONFIG_BUILDER.comment("config your item").push("Magical Staff Configs");

        // Magical Staff
        config_integer_ad_magical_staff = CONFIG_BUILDER.comment("Default value is 2.").worldRestart().define("Magical Staff Attackdamadge", 2);
        config_thunder_magical_staff = CONFIG_BUILDER.comment("Default value is true.").define("Spawn Thunder", true);
        config_tnt_magical_staff = CONFIG_BUILDER.comment("Default value is false.").define("Spawn TNT", false);
        config_fireball_magical_staff = CONFIG_BUILDER.comment("Default value is false.").define("Spawn Fireball", false);
        config_firework_magical_staff = CONFIG_BUILDER.comment("Default value is false.").define("Spawn Firework", false);
        config_notices_magical_staff = CONFIG_BUILDER.comment("Default value is \"Basmod Item\".").define("Notices", "\u00A76Basmod Item");

        //

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        CONFIG_BUILDER.pop();
        COMMON_SPEC_STAFFITEM = CONFIG_BUILDER.build();
    }

    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID1;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID2;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID3;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID4;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID5;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID6;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID7;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID8;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID9;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID10;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID11;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID12;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID13;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID14;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID15;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID16;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID17;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID18;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID19;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID20;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID21;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID22;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID23;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID24;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID25;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID26;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID27;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID28;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID29;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID30;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID31;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID32;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID33;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID34;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID35;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID36;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID37;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID38;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID39;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID40;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID41;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID42;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID43;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID44;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID45;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID46;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID47;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID48;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID49;
    public static final ForgeConfigSpec.ConfigValue<String> config_npc_name_ID50;

    static {

        CONFIG_BUILDER.comment("NPC Default textures is locatet in: 'assets/basmod/textures/entity/npc_types/' .").push("NPC Names Configs");

        config_npc_name_ID1 = CONFIG_BUILDER.comment("gunter.png").define("id - NPCType:1", "Gunter");
        config_npc_name_ID2 = CONFIG_BUILDER.comment("lu.png").define("id - NPCType:2", "Lu");
        config_npc_name_ID3 = CONFIG_BUILDER.comment("sophi.png").define("id - NPCType:3", "Sophi");
        config_npc_name_ID4 = CONFIG_BUILDER.comment("fladimir.png").define("id - NPCType:4", "Fladimir");
        config_npc_name_ID5 = CONFIG_BUILDER.comment("frank.png").define("id - NPCType:5", "Frank");
        config_npc_name_ID6 = CONFIG_BUILDER.comment("olaf.png").define("id - NPCType:6", "Olaf");
        config_npc_name_ID7 = CONFIG_BUILDER.comment("kurata.png").define("id - NPCType:7", "Kurata");
        config_npc_name_ID8 = CONFIG_BUILDER.comment("jonny.png").define("id - NPCType:8", "Jonny");
        config_npc_name_ID9 = CONFIG_BUILDER.comment("anne.png").define("id - NPCType:9", "ANNE");
        config_npc_name_ID10 = CONFIG_BUILDER.comment("aimi.png").define("id - NPCType:10", "AIMI");
        config_npc_name_ID11 = CONFIG_BUILDER.comment("erika.png").define("id - NPCType:11", "Erika");
        config_npc_name_ID12 = CONFIG_BUILDER.comment("max.png").define("id - NPCType:12", "Max");
        config_npc_name_ID13 = CONFIG_BUILDER.comment("mei.png").define("id - NPCType:13", "Mei");
        config_npc_name_ID14 = CONFIG_BUILDER.comment("strafnur.png").define("id - NPCType:14", "Strafnur");
        config_npc_name_ID15 = CONFIG_BUILDER.comment("timmy.png").define("id - NPCType:15", "Timmy");
        config_npc_name_ID16 = CONFIG_BUILDER.comment("titania.png").define("id - NPCType:16", "Titania");
        config_npc_name_ID17 = CONFIG_BUILDER.comment("xander.png").define("id - NPCType:17", "Xander");
        config_npc_name_ID18 = CONFIG_BUILDER.comment("mist.png").define("id - NPCType:18", "Mist");
        config_npc_name_ID19 = CONFIG_BUILDER.comment("soleil.png").define("id - NPCType:19", "Soleil");
        config_npc_name_ID20 = CONFIG_BUILDER.comment("hisoka.png").define("id - NPCType:20", "Hisoka");
        config_npc_name_ID21 = CONFIG_BUILDER.comment("rudolf.png").define("id - NPCType:21", "Rudolf");
        config_npc_name_ID22 = CONFIG_BUILDER.comment("anna.png").define("id - NPCType:22", "Anna");
        config_npc_name_ID23 = CONFIG_BUILDER.comment("ryoko.png").define("id - NPCType:23", "Ryoko");
        config_npc_name_ID24 = CONFIG_BUILDER.comment("romey.png").define("id - NPCType:24", "Romey");
        config_npc_name_ID25 = CONFIG_BUILDER.comment("scarlett.png").define("id - NPCType:25", "Scarlett");
        config_npc_name_ID26 = CONFIG_BUILDER.comment("sev.png").define("id - NPCType:26", "Sev");
        config_npc_name_ID27 = CONFIG_BUILDER.comment("theodor.png").define("id - NPCType:27", "Theodor");
        config_npc_name_ID28 = CONFIG_BUILDER.comment("leo.png").define("id - NPCType:28", "Leo");
        config_npc_name_ID29 = CONFIG_BUILDER.comment("luca.png").define("id - NPCType:29", "Luca");
        config_npc_name_ID30 = CONFIG_BUILDER.comment("lucy.png").define("id - NPCType:30", "Lucy");
        config_npc_name_ID31 = CONFIG_BUILDER.comment("lukas.png").define("id - NPCType:31", "Lukas");
        config_npc_name_ID32 = CONFIG_BUILDER.comment("stefan.png").define("id - NPCType:32", "Stefan");
        config_npc_name_ID33 = CONFIG_BUILDER.comment("phillip.png").define("id - NPCType:33", "Phillip");
        config_npc_name_ID34 = CONFIG_BUILDER.comment("oli.png").define("id - NPCType:34", "Oli");
        config_npc_name_ID35 = CONFIG_BUILDER.comment("amy.png").define("id - NPCType:35", "Amy");
        config_npc_name_ID36 = CONFIG_BUILDER.comment("nico.png").define("id - NPCType:36", "Nico");
        config_npc_name_ID37 = CONFIG_BUILDER.comment("nicolle.png").define("id - NPCType:37", "Nicolle");
        config_npc_name_ID38 = CONFIG_BUILDER.comment("nora.png").define("id - NPCType:38", "Nora");
        config_npc_name_ID39 = CONFIG_BUILDER.comment("tim.png").define("id - NPCType:39", "Tim");
        config_npc_name_ID40 = CONFIG_BUILDER.comment("jakob.png").define("id - NPCType:40", "Jakob");
        config_npc_name_ID41 = CONFIG_BUILDER.comment("jack.png").define("id - NPCType:41", "Jack");
        config_npc_name_ID42 = CONFIG_BUILDER.comment("asuka.png").define("id - NPCType:42", "is not editadble");
        config_npc_name_ID43 = CONFIG_BUILDER.comment("isabella.png").define("id - NPCType:43", "Isabella");
        config_npc_name_ID44 = CONFIG_BUILDER.comment("ingrid.png").define("id - NPCType:44", "Ingrid");
        config_npc_name_ID45 = CONFIG_BUILDER.comment("hana.png").define("id - NPCType:45", "Hana");
        config_npc_name_ID46 = CONFIG_BUILDER.comment("franz.png").define("id - NPCType:46", "franz");
        config_npc_name_ID47 = CONFIG_BUILDER.comment("mi.png").define("id - NPCType:47", "mi");
        config_npc_name_ID48 = CONFIG_BUILDER.comment("ella.png").define("id - NPCType:48", "ella");
        config_npc_name_ID49 = CONFIG_BUILDER.comment("finn.png").define("id - NPCType:49", "finn");
        config_npc_name_ID50 = CONFIG_BUILDER.comment("kirstan.png").define("id - NPCType:50", "kirstan");

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        CONFIG_BUILDER.pop();
        COMMON_SPEC_NPC_NAMEN = CONFIG_BUILDER.build();

    }

    static {
        CONFIG_BUILDER.comment(" ").push("Basmod CLIENT Configs");

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        CONFIG_BUILDER.pop();
        CLIENT_SPEC_GENERAL = CONFIG_BUILDER.build();
    }

}
