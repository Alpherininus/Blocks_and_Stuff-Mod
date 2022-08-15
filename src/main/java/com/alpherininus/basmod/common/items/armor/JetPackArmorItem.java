package com.alpherininus.basmod.common.items.armor;

import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

import javax.annotation.Nullable;
import java.util.List;

public class JetPackArmorItem extends GeoArmorItem implements IAnimatable {
    // Chestplatte
    private static final Logger LOGGER = LogManager.getLogger();
    private final AnimationFactory factory = new AnimationFactory(this);

    public JetPackArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }

    @Override
    public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player) {

        Minecraft mc = Minecraft.getInstance();
        ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);

        BlockPos posE = player.getPosition();

        if (chest.getItem() == ItemInit.JETPACK_CHESTPLATTE.get()) {
            player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 1, 5, false, false));
            player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 1, 10, false, false));

        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<JetPackArmorItem>(this, "controller", 20, this::armorPredicate));
    }

    private PlayState armorPredicate(AnimationEvent<JetPackArmorItem> jetPackArmorItemAnimationEvent) {
        jetPackArmorItemAnimationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


}