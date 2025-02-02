package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

public class FluidInit {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation(Basmod.MOD_ID,"block/mana_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation(Basmod.MOD_ID,"block/mana_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation(Basmod.MOD_ID,"block/mana_overlay");

    public static final DeferredRegister<Fluid>
            FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Basmod.MOD_ID);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<FlowingFluid> MANA_FLUID
            = FLUIDS.register("mana_still", () -> new ForgeFlowingFluid.Source(FluidInit.MANA_PROPERTIES));

    public static final RegistryObject<FlowingFluid> MANA_FLOWING
            = FLUIDS.register("mana_flow", () -> new ForgeFlowingFluid.Flowing(FluidInit.MANA_PROPERTIES));


    public static final ForgeFlowingFluid.Properties MANA_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MANA_FLUID.get(), () -> MANA_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.ITEM_HONEY_BOTTLE_DRINK).overlay(WATER_OVERLAY_RL)
            .color(13671252)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> FluidInit.MANA_BLOCK.get()).bucket(() -> ItemInit.MANA_BUCKET.get());
    // 0x66FF00
    // Block
    public static final RegistryObject<FlowingFluidBlock> MANA_BLOCK = BlockInit.BLOCKS.register("mana",
            () -> new FlowingFluidBlock(() -> FluidInit.MANA_FLUID.get(), AbstractBlock.Properties.create(Material.WATER)
                    .doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
