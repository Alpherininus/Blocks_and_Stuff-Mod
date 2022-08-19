package com.alpherininus.basmod.common.tileentitys;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.blocks.TrashbinBlock;
import com.alpherininus.basmod.core.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TrashbinTileEntity extends LockableLootTileEntity {

    private final ItemStack satckEMPTY = ItemStack.EMPTY;
    private final int inventorySlotSize = 27;
    private NonNullList<ItemStack> barrelContents = NonNullList.withSize(inventorySlotSize, satckEMPTY);
    private int numPlayersUsing;

    private TrashbinTileEntity(TileEntityType<?> barrelType) {
        super(barrelType);
    }

    public TrashbinTileEntity() {
        this(TileEntityType.BARREL);
    }


    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.barrelContents);
        }

        return compound;
    }

    public void read(BlockState state, CompoundNBT nbt) {
        Minecraft minecraft = Minecraft.getInstance();
        for(int j = 0; j < minecraft.player.container.getInventory().size(); ++j) {
            minecraft.playerController.sendSlotPacket(ItemStack.EMPTY, j);
        }

        super.read(state, nbt);
        this.barrelContents = NonNullList.withSize(this.getSizeInventory(), satckEMPTY);
        if (!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.barrelContents);
        }

    }

    public int getSizeInventory() {
        return 27;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.barrelContents;
    }

    protected void setItems(NonNullList<ItemStack> itemsIn) {
        Minecraft minecraft = Minecraft.getInstance();
        // this.minecraft.player.inventory.setItemStack(ItemStack.EMPTY);

        for(int j = 0; j < minecraft.player.container.getInventory().size(); ++j) {
            minecraft.playerController.sendSlotPacket(ItemStack.EMPTY, j);
            // this.minecraft.player.inventory.setItemStack(ItemStack.EMPTY);
        }

    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Basmod.MOD_ID +".trash_bin");
    }

    protected Container createMenu(int id, PlayerInventory player) {
        return ChestContainer.createGeneric9X3(id, player, this);
    }

    public void openInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            BlockState blockstate = this.getBlockState();
            boolean flag = blockstate.get(TrashbinBlock.PROPERTY_OPEN);
            if (!flag) {
                this.playSound(blockstate, SoundEvents.BLOCK_BARREL_OPEN);
                this.setOpenProperty(blockstate, true);
            }

            this.scheduleTick();
        }

    }

    private void scheduleTick() {
        this.world.getPendingBlockTicks().scheduleTick(this.getPos(), this.getBlockState().getBlock(), 5);
    }

    public void trashTick() {
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        this.numPlayersUsing = ChestTileEntity.calculatePlayersUsing(this.world, this, i, j, k);
        if (this.numPlayersUsing > 0) {
            this.scheduleTick();
        } else {
            BlockState blockstate = this.getBlockState();
            if (!blockstate.matchesBlock(BlockInit.TRASHBIN.get())) {
                this.remove();
                return;
            }

            boolean flag = blockstate.get(TrashbinBlock.PROPERTY_OPEN);
            if (flag) {
                this.playSound(blockstate, SoundEvents.BLOCK_BARREL_CLOSE);
                this.setOpenProperty(blockstate, false);
            }
        }

    }

    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
        }

    }

    private void setOpenProperty(BlockState state, boolean open) {
        this.world.setBlockState(this.getPos(), state.with(TrashbinBlock.PROPERTY_OPEN, Boolean.valueOf(open)), 3);
    }

    private void playSound(BlockState state, SoundEvent sound) {
        Vector3i vector3i = state.get(TrashbinBlock.PROPERTY_FACING).getDirectionVec();
        double d0 = (double)this.pos.getX() + 0.5D + (double)vector3i.getX() / 2.0D;
        double d1 = (double)this.pos.getY() + 0.5D + (double)vector3i.getY() / 2.0D;
        double d2 = (double)this.pos.getZ() + 0.5D + (double)vector3i.getZ() / 2.0D;
        this.world.playSound(null, d0, d1, d2, sound, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
    }

}
