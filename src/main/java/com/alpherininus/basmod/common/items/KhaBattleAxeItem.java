package com.alpherininus.basmod.common.items;

import com.alpherininus.basmod.core.init.ItemInit;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KhaBattleAxeItem extends AxeItem {

    private final int range; // Erweiterte Reichweite
    private final ImmutableMultimap<Attribute, AttributeModifier> attributeModifiers;
    private final float attackDamage;

    public KhaBattleAxeItem(IItemTier tier, float attack, float speed, int range, Properties properties) {
        super(tier, attack, speed, properties);
        this.range = range;
        this.attackDamage = attack + tier.getAttackDamage();

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) speed, AttributeModifier.Operation.ADDITION));

        this.attributeModifiers = builder.build();
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        playerIn.getAttackingEntity();
        return ActionResult.resultConsume(itemstack);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getEntityWorld();
        BlockPos targetPos = target.getPosition();
        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    BlockPos hitPos = targetPos.add(x, y, z);
                    if (world.getBlockState(hitPos).isSolid()) {
                        targetPos = hitPos;
                        break;
                    }
                }
            }
        }

        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        LivingEntity target = context.getPlayer();
        LivingEntity attacker = context.getPlayer();

        World world = target.getEntityWorld();
        BlockPos targetPos = target.getPosition();
        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    BlockPos hitPos = targetPos.add(x, y, z);
                    if (world.getBlockState(hitPos).isSolid()) {
                        targetPos = hitPos;
                        break;
                    }
                }
            }
        }

        this.hitEntity(this.getDefaultInstance().getStack(), target, attacker);

        return super.onItemUse(context);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(equipmentSlot);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

}
