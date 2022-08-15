package com.alpherininus.basmod.common.items.armor;

import com.alpherininus.basmod.common.materials.BasmodArmorMaterial;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.FlowerBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BusterArmorItem extends ArmorItem
{
    private static final Logger LOGGER = LogManager.getLogger();

    public BusterArmorItem(BasmodArmorMaterial materialIn, EquipmentSlotType slots, Properties builder)
    {
        super(materialIn, slots, builder);
    }

    @Override
    public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player)
    {
        Minecraft mc = Minecraft.getInstance();

        ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
        ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
        ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);
        ItemStack leftArm = player.getItemStackFromSlot(EquipmentSlotType.OFFHAND);
        ItemStack rightArm = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);

        //Full Set
        if (
                head.getItem() == ItemInit.EXPERIMENTAL_HELMET.get() &&
                chest.getItem() == ItemInit.EXPERIMENTAL_CHESTPLATTE.get() &&
                legs.getItem() == ItemInit.EXPERIMENTAL_LEGGING.get() &&
                feet.getItem() == ItemInit.EXPERIMENTAL_BOOTS.get() &&
                        leftArm.getItem() == ItemInit.EXPERIMENTAL_BOOTS.get() &&
                        rightArm.getItem() == ItemInit.EXPERIMENTAL_BOOTS.get())
        {
            if (player.isPotionActive(Effects.WITHER)) {
                player.removePotionEffect(Effects.WITHER);
            }
        }

        //Check ArmorUtil for additional perks applied to armor
        //Helmet
        if (head.getItem() == ItemInit.EXPERIMENTAL_HELMET.get()) {

            if (player.isInWater()) {
                player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 2));
            } else {
                player.removePotionEffect(Effects.WATER_BREATHING);
            }
        }

        //Chestplate
        if (chest.getItem() == ItemInit.EXPERIMENTAL_CHESTPLATTE.get()) {
            if (player.isPotionActive(Effects.WITHER))
            {
                player.removePotionEffect(Effects.WITHER);
            }
        }

        //Leggings
        if (legs.getItem() == ItemInit.EXPERIMENTAL_LEGGING.get()) {} else {}

        //Boots
        if (feet.getItem() == ItemInit.EXPERIMENTAL_BOOTS.get()) {

            if (player.isInLava()) {
                player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 20, 2));
            }

        } else {
            player.removePotionEffect(Effects.FIRE_RESISTANCE);
        }

    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)    {
        return repair.getItem() == ItemInit.EXPERIMENTAL_ITEM.get();
    }


}
