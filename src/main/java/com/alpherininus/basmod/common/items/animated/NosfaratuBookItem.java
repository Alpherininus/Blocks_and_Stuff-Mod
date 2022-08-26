package com.alpherininus.basmod.common.items.animated;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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

public class NosfaratuBookItem extends Item implements IAnimatable, ISyncable {

    private static final String CONTROLLER_NAME = "Controller";
    private static final int ANIM_OPEN = 0;
    public AnimationFactory factory = new AnimationFactory(this);

    public NosfaratuBookItem(Properties properties) {
        super(properties);
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
        AnimationController<MagicalStaffItem> controller = new AnimationController(this, CONTROLLER_NAME, 20, this::predicate);
        data.addAnimationController(controller);

        AnimationController<MagicalStaffItem> idlecontroller = new AnimationController(this, "idlecontroller", 0, this::predicateIDLE);
        data.addAnimationController(idlecontroller);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            final ItemStack stack = playerIn.getHeldItem(handIn);
            final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerWorld) worldIn);
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> playerIn);
            GeckoLibNetwork.syncAnimation(target, this, id, ANIM_OPEN);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
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
