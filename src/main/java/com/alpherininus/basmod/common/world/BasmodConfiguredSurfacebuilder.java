package com.alpherininus.basmod.common.world;

import com.alpherininus.basmod.Basmod;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BasmodConfiguredSurfacebuilder {

    public static ConfiguredSurfaceBuilder<?> MAGICAL_FOREST = register("magical_forest",
            SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(
                    Blocks.GRASS_BLOCK.getDefaultState(),
                    Blocks.COARSE_DIRT.getDefaultState(),
                    Blocks.COARSE_DIRT.getDefaultState()
            )));

    private static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> csb) {

        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,
                new ResourceLocation(Basmod.MOD_ID, name), csb);
    }
}
