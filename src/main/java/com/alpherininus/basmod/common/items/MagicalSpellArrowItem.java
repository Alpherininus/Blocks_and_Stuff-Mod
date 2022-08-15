package com.alpherininus.basmod.common.items;

import com.alpherininus.basmod.common.entitys.MagicalSpellArrowEntity;
import com.alpherininus.basmod.core.init.EntityTypesInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MagicalSpellArrowItem extends ArrowItem {
    public MagicalSpellArrowItem(Properties builder) {
        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new MagicalSpellArrowEntity(EntityTypesInit.MAGICAL_SPELL_ARROW.get(), shooter, worldIn);
    }
}
