package com.alpherininus.basmod.client.containers;

import com.alpherininus.basmod.core.init.ContainerInit;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;


public class BaSInfoContainer extends Container {

    private final PlayerEntity playerEntity;
    // private final IItemHandler playerInventory;

    public BaSInfoContainer(int windowId, World world, PlayerInventory playerInventory, PlayerEntity player) {
        super(ContainerInit.LIGHTNING_CHANNELER_CONTAINER.get(), windowId);
        playerEntity = player;
        // this.playerInventory = new InvWrapper(playerInventory);
    }

    public boolean isLightningStorm() {
        return playerEntity.getEntityWorld().isThundering();
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.DUMMY, playerIn, Block.getBlockFromItem(ItemInit.EXPERIMENTAL_ITEM.get()));
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return super.transferStackInSlot(playerIn, index);
    }

}
