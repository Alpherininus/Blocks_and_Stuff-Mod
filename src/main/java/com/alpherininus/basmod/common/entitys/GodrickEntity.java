package com.alpherininus.basmod.common.entitys;

import com.alpherininus.basmod.client.controller.ai.AttackGoal;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

import java.util.function.Predicate;

public class GodrickEntity extends MonsterEntity {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final PlayerEntity playerIn = mc.player;

    private static final EntityPredicate PLAYER_INVADER_CONDITION = (new EntityPredicate()).setDistance(64.0D);
    private static final Predicate<LivingEntity> NOT_UNDEAD = (p_213797_0_) -> p_213797_0_.getCreatureAttribute() != CreatureAttribute.UNDEAD && p_213797_0_.attackable();

    private final ServerBossInfo bossInfo1Phase = (ServerBossInfo) new ServerBossInfo(new StringTextComponent("GODRICK CRAFT"), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)
            .setPlayEndBossMusic(true).setCreateFog(true).setDarkenSky(true);

    private static final Predicate<LivingEntity> field_213627_bA = (p_213626_0_) -> p_213626_0_ instanceof MobEntity;

    public GodrickEntity(EntityType<? extends MonsterEntity> entityType, World world) {

        super(entityType, world);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static AttributeModifierMap setCustomAttributes() {
         return MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 9999D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 20.5f)
                    .createMutableAttribute(Attributes.ATTACK_SPEED, 2.0f)
                    .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
                    .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0f).create();

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void registerGoals() {
        this.addSwimGoals();

        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 15.5F, 0.5F));
        this.goalSelector.addGoal(1, new AttackGoal(this, 0.5D, true));
        this.addEvilGoal();

        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, field_213627_bA));
    }

    protected void addEvilGoal() {
        this.goalSelector.addGoal(2, new GodrickEntity.EvilAttackGoal(this));
    }

    protected void addSwimGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 2.0D));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected ActionResultType getEntityInteractionResult(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        return super.getEntityInteractionResult(p_230254_1_, p_230254_2_);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void updateAITasks() {

        if (this.ticksExisted % 20 == 0) {
            this.heal(1.9F);
        }

        this.bossInfo1Phase.setPercent(this.getHealth() / this.getMaxHealth());
    }

    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo1Phase.addPlayer(player);
    }

    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo1Phase.removeAllPlayers();
    }

    static class EvilAttackGoal extends MeleeAttackGoal {
        public EvilAttackGoal(GodrickEntity ge) {
            super(ge, 1.4D, true);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return 6.3F + attackTarget.getWidth();
        }
    }

}
