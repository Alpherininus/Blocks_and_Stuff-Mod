package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.items.animated.HealStaffItem;
import com.alpherininus.basmod.common.items.animated.MagicalStaffItem;
import com.alpherininus.basmod.common.items.animated.TeleportStaffItem;
import com.alpherininus.basmod.common.items.animated.renderer.HealStaffItemRenderer;
import com.alpherininus.basmod.common.items.animated.renderer.MagicalStaffItemRenderer;
import com.alpherininus.basmod.common.items.animated.renderer.TeleportStaffItemRenderer;
import com.alpherininus.basmod.common.items.armor.*;
import com.alpherininus.basmod.common.items.LightsaberExampleItem;
import com.alpherininus.basmod.common.items.*;
import com.alpherininus.basmod.common.items.models.BasmodSpawnEggItem;
import com.alpherininus.basmod.common.materials.BasmodArmorMaterial;
import com.alpherininus.basmod.common.materials.BasmodToolMaterial;
import com.alpherininus.basmod.core.itemgroup.ModItemGroupItems;
import com.alpherininus.basmod.core.itemgroup.ModItemGroupWapons;
import com.alpherininus.basmod.core.util.BasmodConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Basmod.MOD_ID);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO FIRE EMBLEM THEME
    public static final RegistryObject<Item> SUBLIME_CREATOR_SWORD = ITEMS.register("sublime_creator_sword",
            () -> new SublimeCreatorSword(BasmodToolMaterial.SUBLIME_SWORD_MATERIAL, 2, -2f,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

    public static final RegistryObject<Item> CREATOR_SWORD = ITEMS.register("creator_sword",
            () -> new CreatorSword(BasmodToolMaterial.CREATOR_SWORD_MATERIAL, 2, -2f,
                    new Item.Properties().group(ModItemGroupWapons.BAS_MOD_WAPONS)));

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
            () -> new BasmodSpawnEggItem(EntityTypesInit.BASMOD_BOSS_ENTITY, 13671252, 7969893,
                    new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Item> EXPERIMENTAL_ITEM = ITEMS.register("experimental_item",
            () -> new ExperimentalItem(new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

    public static final RegistryObject<Item> RING_OFLOLITEM = ITEMS.register("ring_of_lol",
            () -> new BaSRingItem(new Item.Properties().group(ModItemGroupItems.BAS_MOD_ITEMS)));

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Item> THEME_MUSICDISC = ITEMS.register("theme_music_disc",
            () -> new MusicDiscItem(50, SoundInit.RECORDS_BASMOD_THEME, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)));

    public static final RegistryObject<Item> GEARS_MUSICDISC = ITEMS.register("gears_music_disc",
            () -> new MusicDiscItem(50, SoundInit.RECORDS_GEARS, new Item.Properties().maxStackSize(16).group(ItemGroup.MISC)));

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

    public static final RegistryObject<Item> A_HELMET = ITEMS.register("material_a_helmet", () -> new MaterialAArmorItem(BasmodArmorMaterial.A_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> B_HELMET = ITEMS.register("material_b_helmet", () -> new MaterialBArmorItem(BasmodArmorMaterial.B_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> C_HELMET = ITEMS.register("material_c_helmet", () -> new MaterialCArmorItem(BasmodArmorMaterial.C_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> D_HELMET = ITEMS.register("material_d_helmet", () -> new MaterialDArmorItem(BasmodArmorMaterial.D_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> E_HELMET = ITEMS.register("material_e_helmet", () -> new MaterialEArmorItem(BasmodArmorMaterial.E_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> F_HELMET = ITEMS.register("material_f_helmet", () -> new MaterialFArmorItem(BasmodArmorMaterial.F_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> G_HELMET = ITEMS.register("material_g_helmet", () -> new MaterialGArmorItem(BasmodArmorMaterial.G_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> H_HELMET = ITEMS.register("material_h_helmet", () -> new MaterialHArmorItem(BasmodArmorMaterial.H_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> I_HELMET = ITEMS.register("material_i_helmet", () -> new MaterialIArmorItem(BasmodArmorMaterial.I_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> J_HELMET = ITEMS.register("material_j_helmet", () -> new MaterialJArmorItem(BasmodArmorMaterial.J_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> K_HELMET = ITEMS.register("material_k_helmet", () -> new MaterialKArmorItem(BasmodArmorMaterial.K_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> L_HELMET = ITEMS.register("material_l_helmet", () -> new MaterialLArmorItem(BasmodArmorMaterial.L_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> M_HELMET = ITEMS.register("material_m_helmet", () -> new MaterialMArmorItem(BasmodArmorMaterial.M_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> N_HELMET = ITEMS.register("material_n_helmet", () -> new MaterialNArmorItem(BasmodArmorMaterial.N_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> O_HELMET = ITEMS.register("material_o_helmet", () -> new MaterialOArmorItem(BasmodArmorMaterial.O_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> P_HELMET = ITEMS.register("material_p_helmet", () -> new MaterialPArmorItem(BasmodArmorMaterial.P_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Q_HELMET = ITEMS.register("material_q_helmet", () -> new MaterialQArmorItem(BasmodArmorMaterial.Q_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> R_HELMET = ITEMS.register("material_r_helmet", () -> new MaterialRArmorItem(BasmodArmorMaterial.R_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> S_HELMET = ITEMS.register("material_s_helmet", () -> new MaterialSArmorItem(BasmodArmorMaterial.S_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> T_HELMET = ITEMS.register("material_t_helmet", () -> new MaterialTArmorItem(BasmodArmorMaterial.T_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> U_HELMET = ITEMS.register("material_u_helmet", () -> new MaterialUArmorItem(BasmodArmorMaterial.U_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> V_HELMET = ITEMS.register("material_v_helmet", () -> new MaterialVArmorItem(BasmodArmorMaterial.V_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> W_HELMET = ITEMS.register("material_w_helmet", () -> new MaterialWArmorItem(BasmodArmorMaterial.W_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> X_HELMET = ITEMS.register("material_x_helmet", () -> new MaterialXArmorItem(BasmodArmorMaterial.X_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Y_HELMET = ITEMS.register("material_y_helmet", () -> new MaterialYArmorItem(BasmodArmorMaterial.Y_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Z_HELMET = ITEMS.register("material_z_helmet", () -> new MaterialZArmorItem(BasmodArmorMaterial.Z_ARMOR_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> A_CHEST = ITEMS.register("material_a_chestplate", () -> new MaterialAArmorItem(BasmodArmorMaterial.A_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> B_CHEST = ITEMS.register("material_b_chestplate", () -> new MaterialBArmorItem(BasmodArmorMaterial.B_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> C_CHEST = ITEMS.register("material_c_chestplate", () -> new MaterialCArmorItem(BasmodArmorMaterial.C_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> D_CHEST = ITEMS.register("material_d_chestplate", () -> new MaterialDArmorItem(BasmodArmorMaterial.D_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> E_CHEST = ITEMS.register("material_e_chestplate", () -> new MaterialEArmorItem(BasmodArmorMaterial.E_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> F_CHEST = ITEMS.register("material_f_chestplate", () -> new MaterialFArmorItem(BasmodArmorMaterial.F_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> G_CHEST = ITEMS.register("material_g_chestplate", () -> new MaterialGArmorItem(BasmodArmorMaterial.G_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> H_CHEST = ITEMS.register("material_h_chestplate", () -> new MaterialHArmorItem(BasmodArmorMaterial.H_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> I_CHEST = ITEMS.register("material_i_chestplate", () -> new MaterialIArmorItem(BasmodArmorMaterial.I_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> J_CHEST = ITEMS.register("material_j_chestplate", () -> new MaterialJArmorItem(BasmodArmorMaterial.J_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> K_CHEST = ITEMS.register("material_k_chestplate", () -> new MaterialKArmorItem(BasmodArmorMaterial.K_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> L_CHEST = ITEMS.register("material_l_chestplate", () -> new MaterialLArmorItem(BasmodArmorMaterial.L_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> M_CHEST = ITEMS.register("material_m_chestplate", () -> new MaterialMArmorItem(BasmodArmorMaterial.M_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> N_CHEST = ITEMS.register("material_n_chestplate", () -> new MaterialNArmorItem(BasmodArmorMaterial.N_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> O_CHEST = ITEMS.register("material_o_chestplate", () -> new MaterialOArmorItem(BasmodArmorMaterial.O_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> P_CHEST = ITEMS.register("material_p_chestplate", () -> new MaterialPArmorItem(BasmodArmorMaterial.P_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Q_CHEST = ITEMS.register("material_q_chestplate", () -> new MaterialQArmorItem(BasmodArmorMaterial.Q_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> R_CHEST = ITEMS.register("material_r_chestplate", () -> new MaterialRArmorItem(BasmodArmorMaterial.R_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> S_CHEST = ITEMS.register("material_s_chestplate", () -> new MaterialSArmorItem(BasmodArmorMaterial.S_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> T_CHEST = ITEMS.register("material_t_chestplate", () -> new MaterialTArmorItem(BasmodArmorMaterial.T_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> U_CHEST = ITEMS.register("material_u_chestplate", () -> new MaterialUArmorItem(BasmodArmorMaterial.U_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> V_CHEST = ITEMS.register("material_v_chestplate", () -> new MaterialVArmorItem(BasmodArmorMaterial.V_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> W_CHEST = ITEMS.register("material_w_chestplate", () -> new MaterialWArmorItem(BasmodArmorMaterial.W_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> X_CHEST = ITEMS.register("material_x_chestplate", () -> new MaterialXArmorItem(BasmodArmorMaterial.X_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Y_CHEST = ITEMS.register("material_y_chestplate", () -> new MaterialYArmorItem(BasmodArmorMaterial.Y_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Z_CHEST = ITEMS.register("material_z_chestplate", () -> new MaterialZArmorItem(BasmodArmorMaterial.Z_ARMOR_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));

    public static final RegistryObject<Item> A_LEG = ITEMS.register("material_a_leggings", () -> new MaterialAArmorItem(BasmodArmorMaterial.A_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> B_LEG = ITEMS.register("material_b_leggings", () -> new MaterialBArmorItem(BasmodArmorMaterial.B_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> C_LEG = ITEMS.register("material_c_leggings", () -> new MaterialCArmorItem(BasmodArmorMaterial.C_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> D_LEG = ITEMS.register("material_d_leggings", () -> new MaterialDArmorItem(BasmodArmorMaterial.D_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> E_LEG = ITEMS.register("material_e_leggings", () -> new MaterialEArmorItem(BasmodArmorMaterial.E_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> F_LEG = ITEMS.register("material_f_leggings", () -> new MaterialFArmorItem(BasmodArmorMaterial.F_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> G_LEG = ITEMS.register("material_g_leggings", () -> new MaterialGArmorItem(BasmodArmorMaterial.G_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> H_LEG = ITEMS.register("material_h_leggings", () -> new MaterialHArmorItem(BasmodArmorMaterial.H_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> I_LEG = ITEMS.register("material_i_leggings", () -> new MaterialIArmorItem(BasmodArmorMaterial.I_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> J_LEG = ITEMS.register("material_j_leggings", () -> new MaterialJArmorItem(BasmodArmorMaterial.J_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> K_LEG = ITEMS.register("material_k_leggings", () -> new MaterialKArmorItem(BasmodArmorMaterial.K_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> L_LEG = ITEMS.register("material_l_leggings", () -> new MaterialLArmorItem(BasmodArmorMaterial.L_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> M_LEG = ITEMS.register("material_m_leggings", () -> new MaterialMArmorItem(BasmodArmorMaterial.M_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> N_LEG = ITEMS.register("material_n_leggings", () -> new MaterialNArmorItem(BasmodArmorMaterial.N_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> O_LEG = ITEMS.register("material_o_leggings", () -> new MaterialOArmorItem(BasmodArmorMaterial.O_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> P_LEG = ITEMS.register("material_p_leggings", () -> new MaterialPArmorItem(BasmodArmorMaterial.P_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Q_LEG = ITEMS.register("material_q_leggings", () -> new MaterialQArmorItem(BasmodArmorMaterial.Q_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> R_LEG = ITEMS.register("material_r_leggings", () -> new MaterialRArmorItem(BasmodArmorMaterial.R_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> S_LEG = ITEMS.register("material_s_leggings", () -> new MaterialSArmorItem(BasmodArmorMaterial.S_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> T_LEG = ITEMS.register("material_t_leggings", () -> new MaterialTArmorItem(BasmodArmorMaterial.T_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> U_LEG = ITEMS.register("material_u_leggings", () -> new MaterialUArmorItem(BasmodArmorMaterial.U_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> V_LEG = ITEMS.register("material_v_leggings", () -> new MaterialVArmorItem(BasmodArmorMaterial.V_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> W_LEG = ITEMS.register("material_w_leggings", () -> new MaterialWArmorItem(BasmodArmorMaterial.W_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> X_LEG = ITEMS.register("material_x_leggings", () -> new MaterialXArmorItem(BasmodArmorMaterial.X_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Y_LEG = ITEMS.register("material_y_leggings", () -> new MaterialYArmorItem(BasmodArmorMaterial.Y_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Z_LEG = ITEMS.register("material_z_leggings", () -> new MaterialZArmorItem(BasmodArmorMaterial.Z_ARMOR_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));

    public static final RegistryObject<Item> A_BOOTS = ITEMS.register("material_a_boots", () -> new MaterialAArmorItem(BasmodArmorMaterial.A_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> B_BOOTS = ITEMS.register("material_b_boots", () -> new MaterialBArmorItem(BasmodArmorMaterial.B_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> C_BOOTS = ITEMS.register("material_c_boots", () -> new MaterialCArmorItem(BasmodArmorMaterial.C_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> D_BOOTS = ITEMS.register("material_d_boots", () -> new MaterialDArmorItem(BasmodArmorMaterial.D_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> E_BOOTS = ITEMS.register("material_e_boots", () -> new MaterialEArmorItem(BasmodArmorMaterial.E_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> F_BOOTS = ITEMS.register("material_f_boots", () -> new MaterialFArmorItem(BasmodArmorMaterial.F_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> G_BOOTS = ITEMS.register("material_g_boots", () -> new MaterialGArmorItem(BasmodArmorMaterial.G_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> H_BOOTS = ITEMS.register("material_h_boots", () -> new MaterialHArmorItem(BasmodArmorMaterial.H_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> I_BOOTS = ITEMS.register("material_i_boots", () -> new MaterialIArmorItem(BasmodArmorMaterial.I_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> J_BOOTS = ITEMS.register("material_j_boots", () -> new MaterialJArmorItem(BasmodArmorMaterial.J_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> K_BOOTS = ITEMS.register("material_k_boots", () -> new MaterialKArmorItem(BasmodArmorMaterial.K_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> L_BOOTS = ITEMS.register("material_l_boots", () -> new MaterialLArmorItem(BasmodArmorMaterial.L_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> M_BOOTS = ITEMS.register("material_m_boots", () -> new MaterialMArmorItem(BasmodArmorMaterial.M_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> N_BOOTS = ITEMS.register("material_n_boots", () -> new MaterialNArmorItem(BasmodArmorMaterial.N_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> O_BOOTS = ITEMS.register("material_o_boots", () -> new MaterialOArmorItem(BasmodArmorMaterial.O_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> P_BOOTS = ITEMS.register("material_p_boots", () -> new MaterialPArmorItem(BasmodArmorMaterial.P_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Q_BOOTS = ITEMS.register("material_q_boots", () -> new MaterialQArmorItem(BasmodArmorMaterial.Q_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> R_BOOTS = ITEMS.register("material_r_boots", () -> new MaterialRArmorItem(BasmodArmorMaterial.R_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> S_BOOTS = ITEMS.register("material_s_boots", () -> new MaterialSArmorItem(BasmodArmorMaterial.S_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> T_BOOTS = ITEMS.register("material_t_boots", () -> new MaterialTArmorItem(BasmodArmorMaterial.T_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> U_BOOTS = ITEMS.register("material_u_boots", () -> new MaterialUArmorItem(BasmodArmorMaterial.U_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> V_BOOTS = ITEMS.register("material_v_boots", () -> new MaterialVArmorItem(BasmodArmorMaterial.V_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> W_BOOTS = ITEMS.register("material_w_boots", () -> new MaterialWArmorItem(BasmodArmorMaterial.W_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> X_BOOTS = ITEMS.register("material_x_boots", () -> new MaterialXArmorItem(BasmodArmorMaterial.X_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Y_BOOTS = ITEMS.register("material_y_boots", () -> new MaterialYArmorItem(BasmodArmorMaterial.Y_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<Item> Z_BOOTS = ITEMS.register("material_z_boots", () -> new MaterialZArmorItem(BasmodArmorMaterial.Z_ARMOR_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));

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
