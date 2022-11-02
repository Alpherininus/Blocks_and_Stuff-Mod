package com.alpherininus.basmod.common.items;

import com.alpherininus.basmod.common.containers.BaSInfoContainer;
import com.alpherininus.basmod.core.util.BasmodTags;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class ExperimentalItem extends Item implements ICurioItem{
    public ExperimentalItem(Properties propertiesIn) {
        super(propertiesIn);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();

        if (!world.isRemote) {
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getPos());

            if (!playerEntity.isSneaking()) {
                // throw new IllegalStateException("INFO || to perform the action, you have to sneak!");
            } else {
                rightClickOnCertainBlockState(clickedBlock, context, playerEntity);
                stack.damageItem(1, playerEntity, player -> player.sendBreakAnimation(context.getHand()));
            }
        }

        return super.onItemUseFirst(stack, context);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        PlayerEntity player = (PlayerEntity) livingEntity;

        if (!player.world.isRemote()) {
            boolean hasPlayerFireResistance =
                    !Objects.equals(player.getActivePotionEffect(Effects.FIRE_RESISTANCE), null);

            if (!hasPlayerFireResistance) {
                player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));

                if (random.nextFloat() > 0.8f) {
                    stack.damageItem(1, player, p -> CuriosApi.getCuriosHelper().onBrokenCurio(
                            SlotTypePreset.CHARM.getIdentifier(), index, p));
                }
            }
        }

        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.basmod.default_items_shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.basmod.default_items"));

        }

        if (Screen.hasControlDown()) {
            tooltip.add(new StringTextComponent("\u00A77Hello my Padawan :)\n\n\u00A77-> \u00A76Sneak \u00A77and \u00A76Rightclick \u00A77to Block with: \u00A7cclickable_blocks_for_fire -> Blocktag\u00A77\n or Obsidian, Netherrack and\nbecome random fireresistence effekt.\n\n\u00A77-> \u00A76Rightclick \u00A77to show a \u00A7dFancy GUI\u00A7f(Under Dev).\n\n\u00A77-> \u00A76When Thunder \u00A77is showing a \u00A7bYellow Symbol \u00A77on the GUI.\n\n"));
        } else {
            tooltip.add(new StringTextComponent("Hold \u00A76CONTROL \u00A7ffor more Information"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    private void rightClickOnCertainBlockState(BlockState clickedBlock, ItemUseContext context,
                                               PlayerEntity playerEntity) {

        boolean playerIsNotOnFire = !playerEntity.isBurning();

        if (random.nextFloat() > 0.5f) {
            lightEntityOnFire(playerEntity, 6);

        } else if (playerIsNotOnFire && blockIsValidForResistance(clickedBlock)) {
            gainFireResistanceAndDestroyBlock(playerEntity, context.getWorld(), context.getPos());
        } else {
            lightGroundOnFire(context);
        }

    }

    private boolean blockIsValidForResistance(BlockState clickedBlock) {
        return clickedBlock.isIn(BasmodTags.Blocks.CLICKABLE_BLOCKS_FFIRE);
    }

    public static void lightEntityOnFire(Entity entity, int second) {
        entity.setFire(second);
    }

    private void gainFireResistanceAndDestroyBlock(PlayerEntity playerEntity, World world, BlockPos pos) {
        gainFireResistance(playerEntity);
        world.destroyBlock(pos, false);
    }

    public static void gainFireResistance(PlayerEntity playerEntity) {
        playerEntity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
    }

    public static void lightGroundOnFire(ItemUseContext context) {

        PlayerEntity playerentity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();

        if (AbstractFireBlock.canLightBlock(world, blockpos, context.getPlacementHorizontalFacing())) {
            world.playSound(playerentity, blockpos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F,
                    random.nextFloat() * 0.4F + 0.8F);

            BlockState blockstate = AbstractFireBlock.getFireForPlacement(world, blockpos);
            world.setBlockState(blockpos, blockstate, 11);
        }
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemstack) {
        ItemStack container = itemstack.copy();
        if (container.attemptDamageItem(3, random, null)) {
            return ItemStack.EMPTY;
        } else {
            return container;
        }
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (!worldIn.isRemote()) {
            ItemStack stack;

            if (!playerIn.isCrouching()) {
                INamedContainerProvider containerProvider = createContainerProvider(worldIn);
                NetworkHooks.openGui(((ServerPlayerEntity)playerIn), containerProvider);

            } else {
                // throw new IllegalStateException("INFO || Our Container provider is missing!");
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private INamedContainerProvider createContainerProvider(World worldIn) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.basmod.experimental_item_screen");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new BaSInfoContainer(i, worldIn, playerInventory, playerEntity);
            }
        };
    }

}
