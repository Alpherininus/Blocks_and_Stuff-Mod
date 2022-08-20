package com.alpherininus.basmod.common.items.armor;

import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DivingHelmetItem extends ArmorItem {

    public DivingHelmetItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }

    @Override
    public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player) {

        Minecraft mc = Minecraft.getInstance();
        ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);

        BlockPos posE = player.getPosition();

        if (chest.getItem() == ItemInit.DIVING_HELMET_HELMET.get()) {
            player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 1, 10, false, false));

        }
    }
}
