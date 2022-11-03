package com.alpherininus.basmod.common.blocks;

import com.alpherininus.basmod.Basmod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

public class BombBox extends Block {

    public BombBox(Properties properties) {
        super(properties);
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

                if (world instanceof World) {
                    ArmorStandEntity armorStand = new ArmorStandEntity(EntityType.ARMOR_STAND, (World) world);
                    armorStand.setPosition(pos.getX(), pos.getY(), pos.getZ());
                    armorStand.addTag("armor_stand_0");
                    world.addEntity(armorStand);
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

                if (world instanceof World) {
                    ArmorStandEntity armorStand = new ArmorStandEntity(EntityType.ARMOR_STAND, (World) world);
                    armorStand.setPosition(pos.getX(), pos.getY(), pos.getZ());
                    world.addEntity(armorStand);
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

                if (world instanceof World) {
                    ArmorStandEntity armorStand = new ArmorStandEntity(EntityType.ARMOR_STAND, (World) world);
                    armorStand.setPosition(pos.getX(), pos.getY(), pos.getZ());
                    world.addEntity(armorStand);
                }
            }
        }

        @Override
        public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
            return true;
        }
    }
}
