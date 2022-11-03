package com.alpherininus.basmod.common.blocks;

import com.alpherininus.basmod.Basmod;
import com.mojang.brigadier.StringReader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BombBox extends Block {

    public BombBox(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        float chance = 0.35f;
        if(chance < rand.nextFloat()) {
            worldIn.addParticle(ParticleTypes.PORTAL, pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
                    0d,0.05d,0d);

            worldIn.addParticle(new BlockParticleData(ParticleTypes.FALLING_DUST, stateIn), pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
                    0.0D, 0.05D, 0.0D);

            worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
                    0d,0.05d,0d);

        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (Screen.hasShiftDown()) {
            tooltip.add(new StringTextComponent("\u00A7fBreak the Block to Trigger a Event."));
        } else {
            tooltip.add(new StringTextComponent("Hold \u00A76SHIFT \u00A7ffor more Information!"));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Tier0 extends Block {

        public Tier0(Properties properties) {
            super(properties);
        }

        @Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
        public static class BombBoxEvent {

            @SubscribeEvent
            public void onBlockBreak(final BlockEvent.BreakEvent event) {
                PlayerEntity player = event.getPlayer();

                IWorld world = event.getWorld();
                BlockPos pos = event.getPos();

                //if (world instanceof World) {
                //    ArmorStandEntity armorStand = new ArmorStandEntity(EntityType.ARMOR_STAND, (World) world);
                //    armorStand.setPosition(pos.getX(), pos.getY(), pos.getZ());
                //    world.addEntity(armorStand);
                //}

                if (player.experienceLevel <= 10 && !player.isCreative()) {
                    world.playSound(player, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }

                if (!player.isCreative())
                    player.giveExperiencePoints(-5);

                world.playSound(player, pos, SoundEvents.AMBIENT_UNDERWATER_LOOP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                for (int index = 0; index < world.getRandom().nextInt(10) + 7; index++) {
                    PigEntity pig = new PigEntity(EntityType.PIG, (World) world);
                    pig.setPosition(pos.getX() + world.getRandom().nextInt(10) - 5,
                            pos.getY(), pos.getZ() + world.getRandom().nextInt(10) - 5);
                    pig.addTag("bomb_tier_0");
                    world.addEntity(pig);
                }
            }
        }

        @Override
        public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
            return true;
        }
    }

    public static class Tier1 extends Block {

        public Tier1(Properties properties) {
            super(properties);
        }

        @Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
        public static class BombBoxEvent {

            @SubscribeEvent
            public void onBlockBreak(final BlockEvent.BreakEvent event) {
                PlayerEntity player = event.getPlayer();

                IWorld world = event.getWorld();
                BlockPos pos = event.getPos();

                //if (world instanceof World) {
                //    ArmorStandEntity armorStand = new ArmorStandEntity(EntityType.ARMOR_STAND, (World) world);
                //    armorStand.setPosition(pos.getX(), pos.getY(), pos.getZ());
                //    world.addEntity(armorStand);
                //}

                if (player.experienceLevel <= 10 && !player.isCreative()) {
                    world.playSound(player, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }

                if (!player.isCreative())
                    player.giveExperiencePoints(-5);

                world.playSound(player, pos, SoundEvents.AMBIENT_UNDERWATER_LOOP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                for (int index = 0; index < world.getRandom().nextInt(10) + 7; index++) {
                    ArmorStandEntity pig = new ArmorStandEntity(EntityType.ARMOR_STAND, (World) world);
                    pig.setPosition(pos.getX() + world.getRandom().nextInt(10) - 5,
                            pos.getY(), pos.getZ() + world.getRandom().nextInt(10) - 5);
                    pig.addTag("bomb_tier_1");
                    world.addEntity(pig);
                }
            }
        }

        @Override
        public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
            return true;
        }
    }

    public static class Tier2 extends Block {

        public Tier2(Properties properties) {
            super(properties);
        }

        @Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
        public static class BombBoxEvent {

            @SubscribeEvent
            public void onBlockBreak(final BlockEvent.BreakEvent event) {
                PlayerEntity player = event.getPlayer();

                IWorld world = event.getWorld();
                BlockPos pos = event.getPos();

                //if (world instanceof World) {
                //    ArmorStandEntity armorStand = new ArmorStandEntity(EntityType.ARMOR_STAND, (World) world);
                //    armorStand.setPosition(pos.getX(), pos.getY(), pos.getZ());
                //    world.addEntity(armorStand);
                //}

                if (player.experienceLevel <= 10 && !player.isCreative()) {
                    world.playSound(player, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }

                if (!player.isCreative())
                    player.giveExperiencePoints(-5);

                world.playSound(player, pos, SoundEvents.AMBIENT_UNDERWATER_LOOP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                for (int index = 0; index < world.getRandom().nextInt(10) + 7; index++) {
                    LightningBoltEntity pig = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, (World) world);
                    pig.setPosition(pos.getX() + world.getRandom().nextInt(10) - 5,
                            pos.getY(), pos.getZ() + world.getRandom().nextInt(10) - 5);
                    pig.addTag("bomb_tier_2");
                    world.addEntity(pig);
                }
            }
        }

        @Override
        public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
            return true;
        }
    }
}
