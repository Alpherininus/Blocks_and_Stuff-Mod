package com.alpherininus.basmod.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;

public class BaSRingItem extends Item implements ICurioItem {

    public BaSRingItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (!worldIn.isRemote()) {
            ItemStack stack;

            if (!playerIn.isCrouching()) {

            } else {
                // throw new IllegalStateException("INFO || Our Container provider is missing!");
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        PlayerEntity player = (PlayerEntity) livingEntity;

        if (!player.world.isRemote()) {
            boolean hasPlayerFireResistance =
                    !Objects.equals(player.getActivePotionEffect(Effects.FIRE_RESISTANCE), null);

            if (!hasPlayerFireResistance) {
                player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));

                if (random.nextFloat() > 0.6f) {
                    stack.damageItem(1, player, p -> CuriosApi.getCuriosHelper().onBrokenCurio(
                            SlotTypePreset.RING.getIdentifier(), index, p));
                }
            }
        }

        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }

}
