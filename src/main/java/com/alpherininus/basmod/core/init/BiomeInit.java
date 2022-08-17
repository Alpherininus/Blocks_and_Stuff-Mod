package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.world.BasmodConfiguredSurfacebuilder;
import com.alpherininus.basmod.common.world.gen.StructureGeneration;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BiomeInit {
    public static final DeferredRegister<Biome> BIOMES
            = DeferredRegister.create(ForgeRegistries.BIOMES, Basmod.MOD_ID);

    public static final RegistryObject<Biome> DARK_OF_GODNESS_BIOME = BIOMES.register("dark_of_godness",
            () -> makeDarkOfGodnessBiome(() -> BasmodConfiguredSurfacebuilder.DARK_OF_GODNESS, 0.250f, 0.15f));

    private static Biome makeDarkOfGodnessBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float tiefe, float massstab) {

        int zombieWeight = 200;
        int zombieVillagerWeight = 50;
        int skeletonWeight = 150;

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(BasmodConfiguredSurfacebuilder.DARK_OF_GODNESS);

        MobSpawnInfo.Builder mobspawninfoExtraChickens$builder = new MobSpawnInfo.Builder();
        MobSpawnInfo.Builder mobspawninfoForMonster$builder = new MobSpawnInfo.Builder();
        MobSpawnInfo.Builder mobspawninfoForCreature$builder = new MobSpawnInfo.Builder();

        DefaultBiomeFeatures.withOverworldOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withDisks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withBadlandsOakTrees(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withLavaLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);

        biomegenerationsettings$builder.withStructure(StructureFeatures.RUINED_PORTAL);

        DefaultBiomeFeatures.withFossils(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withFossils(biomegenerationsettings$builder);

        DefaultBiomeFeatures.withIcebergs(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withDefaultFlowers(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withAllForestFlowerGeneration(biomegenerationsettings$builder);

        DefaultBiomeFeatures.withSpawnsWithExtraChickens(mobspawninfoExtraChickens$builder);

        DefaultBiomeFeatures.withHostileMobs(mobspawninfoForMonster$builder
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, zombieWeight, 4, 4))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, skeletonWeight, 4, 4))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityTypesInit.BASMOD_BOSS_ENTITY.get(), 1, 1, 1))
                .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ILLUSIONER, 3, 1, 1)),
                zombieWeight, zombieVillagerWeight, skeletonWeight);

        DefaultBiomeFeatures.withPassiveMobs(mobspawninfoForCreature$builder
                .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 10, 2, 4))
                .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 8, 2, 4))
                .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CHICKEN, 8, 2, 4))
                .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.COW, 6, 2, 4)));


        Features.ORE_ANDESITE.count(10);
        Features.ORE_DIORITE.count(15);
        Features.ORE_MAGMA.count(20);
        Features.ORE_REDSTONE.count(10);
        Features.ORE_SOUL_SAND.count(10);
        Features.ORE_LAPIS.count(10);
        Features.ORE_IRON.count(10);
        Features.PATCH_BERRY_BUSH.count(5);
        Features.PATCH_BERRY_DECORATED.count(15);

        Features.Placements.SPRING_PLACEMENT.count(10);
        Features.Placements.VEGETATION_PLACEMENT.count(5);

        OreFeature.TWISTING_VINES.withConfiguration(NoFeatureConfig.INSTANCE);
        OreFeature.WEEPING_VINES.withConfiguration(NoFeatureConfig.INSTANCE);

        return (new Biome.Builder())
                .precipitation(Biome.RainType.NONE)
                .category(Biome.Category.FOREST)
                .depth(tiefe)
                .scale(massstab)
                .temperature(0.6F)
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .downfall(0.6F)
                .setEffects((new BiomeAmbience.Builder())
                        .setWaterColor(563131)
                        .setWaterFogColor(563131)
                        .setFogColor(0)
                        .withSkyColor(0)
                        .withFoliageColor(272727)
                        .withGrassColor(231313)
                        .setParticle(new ParticleEffectAmbience(ParticleTypes.CLOUD, 0.003f))
                        .setAmbientSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                        .setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D))
                        .setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D))
                        .setMusic(BackgroundMusicTracks.getDefaultBackgroundMusicSelector(SoundEvents.MUSIC_DRAGON))
                        .build())
                .withMobSpawnSettings(mobspawninfoExtraChickens$builder.build())
                .withMobSpawnSettings(mobspawninfoForCreature$builder.build())
                .withMobSpawnSettings(mobspawninfoForMonster$builder.build())
                .withGenerationSettings(biomegenerationsettings$builder.build())
                .build();

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }

}