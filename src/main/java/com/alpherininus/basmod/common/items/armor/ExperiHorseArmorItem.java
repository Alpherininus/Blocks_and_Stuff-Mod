package com.alpherininus.basmod.common.items.armor;

import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ExperiHorseArmorItem extends HorseArmorItem {

    public ExperiHorseArmorItem(int armorValue, String tierArmor, Properties builder) {
        super(armorValue, tierArmor, builder);
    }

    @Override
    public void onHorseArmorTick(ItemStack stack, World world, MobEntity horse) {
        {
            Minecraft mc = Minecraft.getInstance();
            ItemStack horsechest = horse.getItemStackFromSlot(EquipmentSlotType.CHEST);

            if (horsechest.getItem() == ItemInit.EXPERIMENTAL_HORSE_ARMOR.get()) {
                if (horse.isPotionActive(Effects.WITHER)) {
                    horse.removePotionEffect(Effects.WITHER);
                }
            }

        }
        super.onHorseArmorTick(stack, world, horse);
    }
}





