package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.world.gen.structures.MagicalWitchHouse;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;


public class StructureInit {

    public static final DeferredRegister<Structure<?>> STRUCTURES =
            DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Basmod.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> MAGICAL_WITCH_HOUSE =
            STRUCTURES.register("magicalhouse", MagicalWitchHouse::new);

    public static final RegistryObject<Structure<NoFeatureConfig>> GRATERLOL =
            STRUCTURES.register("grater", MagicalWitchHouse::new);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void setupStructures() {
        setupMapSpacingAndLand(MAGICAL_WITCH_HOUSE.get(),
                new StructureSeparationSettings(100,50, 1234567890),
                true);

        setupMapSpacingAndLand(GRATERLOL.get(), new StructureSeparationSettings(100, 50, 1234567890), true);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, StructureSeparationSettings structureSeparationSettings,
                                                                       boolean transformSurroundingLand) {

        Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);

        if (transformSurroundingLand) {
            ImmutableList<Structure<?>> field_236384_t_ =
                    ImmutableList.<Structure<?>>builder()
                            .addAll(Structure.field_236384_t_)
                            .add(structure).build();
        }

        ImmutableMap<Structure<?>, StructureSeparationSettings> field_236191_b_ =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.field_236191_b_)
                        .put(structure, structureSeparationSettings)
                        .build();

        WorldGenRegistries.NOISE_SETTINGS.getEntries().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap =
                    settings.getValue().getStructures().func_236195_a_();

            if (structureMap instanceof ImmutableMap) {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().getStructures().func_236195_a_();

            } else {
                structureMap.put(structure, structureSeparationSettings);
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void register(IEventBus eventBus) {
        STRUCTURES.register(eventBus);
    }

}
