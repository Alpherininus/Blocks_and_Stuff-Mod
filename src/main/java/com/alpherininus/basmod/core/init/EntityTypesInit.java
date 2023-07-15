package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.*;
import com.alpherininus.basmod.common.entitys.animated.BasBossEntity;
import com.alpherininus.basmod.common.entitys.animated.BasWanderingTraderEntity;
import com.alpherininus.basmod.common.entitys.animated.BossOfDeadEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypesInit {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Basmod.MOD_ID);

    public static final RegistryObject<EntityType<CopperGolemEntity>> BASMOD_COPPER_GOLEM =
            ENTITY_TYPES.register("copper_golem",
                    () -> EntityType.Builder
                            .create(CopperGolemEntity::new, EntityClassification.CREATURE)
                            .size(1f,0.95f)
                            .build(new ResourceLocation(Basmod.MOD_ID, "copper_golem").toString()));

    public static final RegistryObject<EntityType<GodrickEntity>> GODRICK_BOSS =
            ENTITY_TYPES.register("boss_godrick",
                    () -> EntityType.Builder
                            .create(GodrickEntity::new, EntityClassification.MONSTER)
                            .size(1f,1f)
                            .build(new ResourceLocation(Basmod.MOD_ID, "godrick_txt").toString()));

    public static final RegistryObject<EntityType<SeiorShellArmorEntity>> BASMOD_SEIORSHELL =
            ENTITY_TYPES.register("seiorshell",
                    () -> EntityType.Builder.create(SeiorShellArmorEntity::new, EntityClassification.CREATURE)
                            .size(1.3964844F, 1.6F).trackingRange(10)
                            .build(new ResourceLocation(Basmod.MOD_ID, "seiorshell").toString())); // Horse

    public static final RegistryObject<EntityType<MagicalSpellArrowEntity>> MAGICAL_SPELL_ARROW =
            ENTITY_TYPES.register("magical_spell_arrow",
            () -> EntityType.Builder
                    .create((EntityType.IFactory<MagicalSpellArrowEntity>) MagicalSpellArrowEntity::new, EntityClassification.MISC)
                    .size(0.5F, 0.5F)
                    .build("magical_spell_arrow"));

    public static final RegistryObject<EntityType<BasBossEntity>> BASMOD_BOSS_ENTITY =
            ENTITY_TYPES.register("basmod_boss",
                    () -> EntityType.Builder
                            .create(BasBossEntity::new, EntityClassification.MONSTER)
                            .size(1.7F, 2.0F)
                            .build("basmod_boss"));

    public static final RegistryObject<EntityType<BossOfDeadEntity>> BOSS_OF_DEAD_ENTITY =
            ENTITY_TYPES.register("boss_of_dead",
                    () -> EntityType.Builder
                            .create(BossOfDeadEntity::new, EntityClassification.MONSTER)
                            .size(1.2F, 2.1F)
                            .build("boss_of_dead"));

    public static final RegistryObject<EntityType<BasWanderingTraderEntity>> BASMOD_WANDERINGTRADER =
            ENTITY_TYPES.register("bas_wandering_trader",
                    () -> EntityType.Builder
                            .create(BasWanderingTraderEntity::new, EntityClassification.CREATURE)
                            .size(1.0F, 2.0F)
                            .build("bas_wandering_trader"));

    public static final RegistryObject<EntityType<NPCEntity>> BASMOD_NPC_TYPE =
            ENTITY_TYPES.register("npc_type",
                    () -> EntityType.Builder
                           .create(NPCEntity::new, EntityClassification.CREATURE)
                            .size(1.0f,2.0f)
                           .build(new ResourceLocation(Basmod.MOD_ID, "npc_type").toString()));


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
