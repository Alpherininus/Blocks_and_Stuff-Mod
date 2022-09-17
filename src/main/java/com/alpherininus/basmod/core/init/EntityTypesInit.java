package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.CopperGolemEntity;
import com.alpherininus.basmod.common.entitys.MagicalSpellArrowEntity;
import com.alpherininus.basmod.common.entitys.SeiorShellArmorEntity;
import com.alpherininus.basmod.common.entitys.animated.BasBossEntity;
import com.alpherininus.basmod.common.entitys.animated.BossOfDeadEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class EntityTypesInit {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Basmod.MOD_ID);

    public static final RegistryObject<EntityType<CopperGolemEntity>> BASMOD_COPPER_GOLEM =
            ENTITY_TYPES.register("copper_golem",
                    () -> EntityType.Builder
                            .create(CopperGolemEntity::new, EntityClassification.CREATURE)
                            .size(1f,0.95f)
                            .build(new ResourceLocation(Basmod.MOD_ID, "copper_golem").toString()));

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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
