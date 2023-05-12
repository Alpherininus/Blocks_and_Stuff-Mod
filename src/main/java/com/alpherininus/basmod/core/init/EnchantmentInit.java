package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.items.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {

    public static final DeferredRegister<Enchantment> ENCHANTMENT_DEFERRED_REGISTER
            = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Basmod.MOD_ID);

    public static RegistryObject<Enchantment> LIGHTNING_STRIKER =
            ENCHANTMENT_DEFERRED_REGISTER.register("lightning_striker", () -> new LightningStriker(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND));

    public static RegistryObject<Enchantment> FIREBALL =
            ENCHANTMENT_DEFERRED_REGISTER.register("fireball", () -> new Fireball(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND));

    public static RegistryObject<Enchantment> HEAVENSFALL =
            ENCHANTMENT_DEFERRED_REGISTER.register("heavens_fall", () -> new HeavensFall(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND));

    public static RegistryObject<Enchantment> TNT_HIT =
            ENCHANTMENT_DEFERRED_REGISTER.register("tnt_attack", () -> new TNTAttack(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND));

    public static RegistryObject<Enchantment> MJOENIR_ATT =
            ENCHANTMENT_DEFERRED_REGISTER.register("mjoenirs_attack", () -> new MjoenirAttack(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND));

    public static RegistryObject<Enchantment> HEAL_P =
            ENCHANTMENT_DEFERRED_REGISTER.register("heal_prayer", () -> new MjoenirAttack(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND));

    public static void register(IEventBus eventBus) {
        ENCHANTMENT_DEFERRED_REGISTER.register(eventBus);
    }
}
