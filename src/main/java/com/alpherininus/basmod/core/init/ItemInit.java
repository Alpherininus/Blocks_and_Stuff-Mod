package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.items.*;
import com.alpherininus.basmod.common.items.animated.HealStaffItem;
import com.alpherininus.basmod.common.items.animated.MagicalStaffItem;
import com.alpherininus.basmod.common.items.animated.NosfaratuBookItem;
import com.alpherininus.basmod.common.items.animated.TeleportStaffItem;
import com.alpherininus.basmod.common.items.animated.renderer.HealStaffItemRenderer;
import com.alpherininus.basmod.common.items.animated.renderer.MagicalStaffItemRenderer;
import com.alpherininus.basmod.common.items.animated.renderer.NosfaratuBookItemRenderer;
import com.alpherininus.basmod.common.items.animated.renderer.TeleportStaffItemRenderer;
import com.alpherininus.basmod.common.items.armor.DivingHelmetItem;
import com.alpherininus.basmod.common.items.armor.ExperiArmorItem;
import com.alpherininus.basmod.common.items.armor.ExperiHorseArmorItem;
import com.alpherininus.basmod.common.items.armor.JetPackArmorItem;
import com.alpherininus.basmod.common.items.models.BasmodSpawnEggItem;
import com.alpherininus.basmod.common.items.theme.*;
import com.alpherininus.basmod.common.materials.BasmodArmorMaterial;
import com.alpherininus.basmod.common.materials.BasmodToolMaterial;
import com.alpherininus.basmod.core.itemgroup.ModItemGroupItems;
import com.alpherininus.basmod.core.itemgroup.ModItemGroupWapons;
import com.alpherininus.basmod.core.util.BasmodConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

import static com.alpherininus.basmod.core.init.EntityTypesInit.BASMOD_BOSS_ENTITY;
import static com.alpherininus.basmod.core.init.EntityTypesInit.BOSS_OF_DEAD_ENTITY;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Basmod.MOD_ID);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO FIRE EMBLEM THEME
    public static final RegistryObject<Item> SUBLIME_CREATOR_SWORD = ITEMS.register("sublime_creator_sword",
            () -> new SublimeCreatorSword(BasmodToolMaterial.SUBLIME_SWORD_MATERIAL, 4, -2f,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> CREATOR_SWORD = ITEMS.register("creator_sword",
            () -> new CreatorSword(BasmodToolMaterial.CREATOR_SWORD_MATERIAL, 3, -2f,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> FAILNAUGHT_BOW = ITEMS.register("failnaught_bow",
            () -> new FailnaughtBowItem(new Item.Properties().maxStackSize(1).group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> THUNDER_SWORD = ITEMS.register("thundersword",
            () -> new ThunderSword(BasmodToolMaterial.B_TOOL_MATERIAL, 1, -3f,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> SOLEILS_SHINE = ITEMS.register("soleils_shine",
            () -> new SoleilsShineSword(BasmodToolMaterial.G_TOOL_MATERIAL, 2, -2f,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> FIRESWEEP = ITEMS.register("firesweep_sword",
            () -> new FireSweepSword(BasmodToolMaterial.G_TOOL_MATERIAL, 2, -2f,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> NOHR_SHIELD_RED = ITEMS.register("nohr_shield_red",
            () -> new NohrShieldItem(new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> NOHR_SHIELD_WHITE = ITEMS.register("nohr_shield_white",
            () -> new NohrShieldItem(new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<SeioerShellSpawnEggItem> SEIEORSHELL_SPAWN_EGG = ITEMS.register("seieorshell_spawn_egg",
            () -> new SeioerShellSpawnEggItem(EntityTypesInit.BASMOD_SEIORSHELL, 13661252, 7969893,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO Blocks and Stuff
    public static final RegistryObject<Item> NOHR_SHIELD_RED_BLOCK = ITEMS.register("nohr_shield_red_blocking",
            () -> new NohrShieldItem(new Item.Properties()));
    public static final RegistryObject<Item> NOHR_SHIELD_WHITE_BLOCK = ITEMS.register("nohr_shield_white_blocking",
            () -> new NohrShieldItem(new Item.Properties()));
    public static final RegistryObject<Item> BASMOD_BOOK_ITEMS = ITEMS.register("basmod_itembook",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BASMOD_BOOK_BLOCKS = ITEMS.register("basmod_blockbook",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BASMOD_BOOK_WAPONS = ITEMS.register("basmod_waponbook",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASUKA_AXT = ITEMS.register("asuka_axt",
            () -> new AsukaAxt(BasmodToolMaterial.A_TOOL_MATERIAL, 3, -2f,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<SpawnEggItem> WITHER_SPAWN_EGG = ITEMS.register("wither_spawn_egg",
            () -> new SpawnEggItem(EntityType.WITHER, 13661252, 7969893,
                    new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<SpawnEggItem> ENDERDRAGON_SPAWN_EGG = ITEMS.register("ender_dragon_spawn_egg",
            () -> new SpawnEggItem(EntityType.ENDER_DRAGON, 13661252, 7969893,
                    new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<SpawnEggItem> ILLUSIONER_SPAWN_EGG = ITEMS.register("illusioner_spawn_egg",
            () -> new SpawnEggItem(EntityType.ILLUSIONER, 13661252, 7969893,
                    new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<SpawnEggItem> GIANT_SPAWN_EGG = ITEMS.register("giant_spawn_egg",
            () -> new SpawnEggItem(EntityType.GIANT, 13661252, 7969893,
                    new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<SpawnEggItem> IRONGOLEM_SPAWN_EGG = ITEMS.register("iron_golem_spawn_egg",
            () -> new SpawnEggItem(EntityType.IRON_GOLEM, 13661252, 7969893,
                    new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<BasmodSpawnEggItem> COPPER_GOLEM_SPAWN_EGG = ITEMS.register("copper_golem_spawn_egg",
            () -> new BasmodSpawnEggItem(EntityTypesInit.BASMOD_COPPER_GOLEM, 13671252, 7969893,
                    new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<BasmodSpawnEggItem> BASBOSS_SPAWN_EGG = ITEMS.register("bas_boss_spawn_egg",
            () -> new BasmodSpawnEggItem(BASMOD_BOSS_ENTITY, 13671252, 7969893,
                    new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<BasmodSpawnEggItem> BOSS_OF_DEAD_SPAWN_EGG = ITEMS.register("boss_of_dead_spawn_egg",
            () -> new BasmodSpawnEggItem(BOSS_OF_DEAD_ENTITY, 13671252, 7969893,
                    new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Item> EXPERIMENTAL_ITEM = ITEMS.register("experimental_item",
            () -> new ExperimentalItem(new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> RING_OFLOLITEM = ITEMS.register("ring_of_lol",
            () -> new BaSRingItem(new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> TOTEM_OFF_DESPAWN = ITEMS.register("totem_of_despawn",
            () -> new Item(new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> DOOR_KEY_ITEM = ITEMS.register("door_key",
            () -> new Item(new Item.Properties().maxStackSize(1).group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> DOOR_REDKEY_ITEM = ITEMS.register("red_door_key",
            () -> new Item(new Item.Properties().maxStackSize(1).group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> DOOR_BLUEKEY_ITEM = ITEMS.register("blue_door_key",
            () -> new Item(new Item.Properties().maxStackSize(1).group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> DOOR_GREENKEY_ITEM = ITEMS.register("green_door_key",
            () -> new Item(new Item.Properties().maxStackSize(1).group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> MAGICAL_SPELL_ARROWITEM = ITEMS.register("magical_spell_arrow",
            () -> new MagicalSpellArrowItem(new Item.Properties().maxStackSize(16).group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> MAGICAL_COAL = ITEMS.register("magical_coal",
            () -> new MagicalCoalItem(new Item.Properties().maxStackSize(8).group(ModItemGroupItems.BAS_MOD_ITEMS)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Item> THEME_MUSICDISC = ITEMS.register("theme_music_disc",
            () -> new MusicDiscItem(50, SoundInit.RECORDS_BASMOD_THEME, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)));

    public static final RegistryObject<Item> GEARS_MUSICDISC = ITEMS.register("gears_music_disc",
            () -> new MusicDiscItem(50, SoundInit.RECORDS_BASMOD_GEARS, new Item.Properties().maxStackSize(16).group(ItemGroup.MISC)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO Animated Items

    public static final RegistryObject<Item> ANIMATED_MAGICAL_STAFF = ITEMS.register("magical_staff",
            () -> new MagicalStaffItem(BasmodToolMaterial.A_TOOL_MATERIAL, BasmodConfig.config_integer_ad_magical_staff.get(), BasmodConfig.config_float_as_magical_staff.get(),
                    new Item.Properties().setISTER(() -> MagicalStaffItemRenderer::new).maxStackSize(1).group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> ANIMATED_HEAL_STAFF = ITEMS.register("heal_staff",
            () -> new HealStaffItem(BasmodToolMaterial.A_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().setISTER(() -> HealStaffItemRenderer::new).maxStackSize(1).group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> ANIMATED_TELEPORTER_STAFF = ITEMS.register("teleport_staff",
            () -> new TeleportStaffItem(BasmodToolMaterial.A_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().setISTER(() -> TeleportStaffItemRenderer::new).maxStackSize(1).group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> ANIMATED_NOSFARATU_BOOK = ITEMS.register("nosfaratu_book",
            () -> new NosfaratuBookItem(new Item.Properties().setISTER(() -> NosfaratuBookItemRenderer::new).maxStackSize(1).group(ModItemGroupWapons.BAS_MOD_WAPONS), new Random(3)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO SWORDS
    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword",
            () -> new AxeItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> LIGHT_SWORD = ITEMS.register("light_sword",
            () -> new LightsaberExampleItem(BlockInit.LIGHTSOURCE_BLOCKS.get(),
                    new Item.Properties()
                            .group(ModItemGroupWapons.BAS_MOD_WAPONS)
                            .maxStackSize(1),
                    BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO ARMOR
    public static final RegistryObject<Item> EXPERIMENTAL_HELMET = ITEMS.register("experi_helmet",
            () -> new ExperiArmorItem(BasmodArmorMaterial.EXPERIMENTALITEM, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> EXPERIMENTAL_CHESTPLATTE = ITEMS.register("experi_chestplatte",
            () -> new ExperiArmorItem(BasmodArmorMaterial.EXPERIMENTALITEM, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> EXPERIMENTAL_LEGGING = ITEMS.register("experi_leggings",
            () -> new ExperiArmorItem(BasmodArmorMaterial.EXPERIMENTALITEM, EquipmentSlotType.LEGS,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> EXPERIMENTAL_BOOTS = ITEMS.register("experi_boots",
            () -> new ExperiArmorItem(BasmodArmorMaterial.EXPERIMENTALITEM, EquipmentSlotType.FEET,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> EXPERIMENTAL_HORSE_ARMOR = ITEMS.register("experi_horse_armor",
            () -> new ExperiHorseArmorItem(9, "experi",
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> JETPACK_CHESTPLATTE = ITEMS.register("jetpack",
            () -> new JetPackArmorItem(BasmodArmorMaterial.EXPERIMENTALITEM, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Item> DIVING_HELMET_HELMET = ITEMS.register("diving_helmet",
            () -> new DivingHelmetItem(BasmodArmorMaterial.EXPERIMENTALITEM, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO TOOLS
    public static final RegistryObject<Item> RUBY_AXT = ITEMS.register("ruby_axt",
            () -> new AxeItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> RUBY_PICKAXT = ITEMS.register("ruby_pickaxt",
            () -> new PickaxeItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel",
            () -> new ShovelItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> UMBRAL_AXT = ITEMS.register("umbral_axt",
            () -> new AxeItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> UMBRAL_PICKAXT = ITEMS.register("umbral_pickaxt",
            () -> new PickaxeItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> UMBRAL_SHOVEL = ITEMS.register("umbral_shovel",
            () -> new ShovelItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> DARK_STEEL_AXT = ITEMS.register("dark_steel_axt",
            () -> new AxeItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> DARK_STEEL_PICKAXT = ITEMS.register("dark_steel_pickaxt",
            () -> new PickaxeItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> DARK_STEEL_SHOVEL = ITEMS.register("dark_steel_shovel",
            () -> new ShovelItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> STEEL_AXT = ITEMS.register("steel_axt",
            () -> new AxeItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> STEEL_PICKAXT = ITEMS.register("steel_pickaxt",
            () -> new PickaxeItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(BasmodToolMaterial.B_TOOL_MATERIAL, 1, 1,
                    new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO ORES
    public static final RegistryObject<Item> RUBY_ITEM = ITEMS.register("ruby",
            () -> new Item(new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> UMBRAL_STEEL_ITEM = ITEMS.register("umbral_steel",
            () -> new Item(new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> DARK_STEEL_ITEM = ITEMS.register("dark_steel",
            () -> new Item(new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> STEEL_ITEM = ITEMS.register("steel",
            () -> new Item(new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> RELICS_ITEM = ITEMS.register("relics",
            () -> new Item(new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Item> MANA_BUCKET = ITEMS.register("mana_bucket",
            () -> new BucketItem(FluidInit.MANA_FLUID,
                    new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
