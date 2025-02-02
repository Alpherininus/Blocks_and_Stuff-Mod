package com.alpherininus.basmod.common.items.animated;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.PacketDistributor;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Random;

public class NosfaratuBookItem extends Item implements IAnimatable, ISyncable {

    private static final String CONTROLLER_NAME = "Controller";
    private static final int ANIM_OPEN = 0;
    public AnimationFactory factory = new AnimationFactory(this);
    private final Random rand;

    public NosfaratuBookItem(Properties properties, Random rand) {
        super(properties);
        this.rand = rand;
        GeckoLibNetwork.registerSyncable(this);

    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState predicateIDLE(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController controller = new AnimationController(this, CONTROLLER_NAME, 20, this::predicate);
        data.addAnimationController(controller);

        AnimationController<MagicalStaffItem> idlecontroller = new AnimationController(this, "idlecontroller", 0, this::predicateIDLE);
        data.addAnimationController(idlecontroller);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        final ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.isSneaking()) {
            if (!worldIn.isRemote) {
                final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerWorld) worldIn);
                final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> playerIn);
                GeckoLibNetwork.syncAnimation(target, this, id, ANIM_OPEN);

                playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, 1, 5));
            }

        }

        if (!worldIn.isRemote()) {
            final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerWorld) worldIn);
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> playerIn);
            GeckoLibNetwork.syncAnimation(target, this, id, ANIM_OPEN);

        }
        stack.damageItem(3, playerIn, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (!attacker.world.isRemote()) {
            ServerWorld world = (ServerWorld) attacker.world;
            ServerPlayerEntity player = ((ServerPlayerEntity) attacker);
            BlockPos pos = target.getPosition();

            target.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 1));
        }

        stack.damageItem(3, attacker, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));

        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ANIM_OPEN) {
            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, CONTROLLER_NAME);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                final ClientPlayerEntity player = Minecraft.getInstance().player;
                if (player != null) {
                    player.sendStatusMessage(new StringTextComponent("Nosferatu"), true);
                }
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("rightclick", false));
            }
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

}
