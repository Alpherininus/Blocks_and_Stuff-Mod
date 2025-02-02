package com.alpherininus.basmod.common.items.animated;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;

import java.util.Random;


public class SpinBladeItem extends Item implements IAnimatable, ISyncable {

    private final Random rand;
    public AnimationFactory factory = new AnimationFactory(this);

    public SpinBladeItem(Properties properties, Random rand) {
        super(properties);
        this.rand = rand;
        GeckoLibNetwork.registerSyncable(this);
    }

    private <E extends IAnimatable> PlayState predicateSpin(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.spining", true));

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<SpinBladeItem> idlecontroller = new AnimationController(this, "spincontroller", 0, this::predicateSpin);
        data.addAnimationController(idlecontroller);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        final ItemStack stack = playerIn.getHeldItem(handIn);

        stack.damageItem(3, playerIn, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onAnimationSync(int id, int state) {
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}

