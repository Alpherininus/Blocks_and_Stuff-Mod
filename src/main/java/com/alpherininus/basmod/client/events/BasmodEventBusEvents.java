package com.alpherininus.basmod.client.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.client.entity.BasmodPlayerEntity;
import com.alpherininus.basmod.common.entitys.CopperGolemEntity;
import com.alpherininus.basmod.common.entitys.SeiorShellArmorEntity;
import com.alpherininus.basmod.common.entitys.animated.BasBossEntity;
import com.alpherininus.basmod.common.entitys.animated.BossOfDeadEntity;
import com.alpherininus.basmod.client.events.loots.BasmodAdditionModifier;
import com.alpherininus.basmod.client.events.loots.BasmodStructureAdditionModifier;
import com.alpherininus.basmod.common.items.SeioerShellSpawnEggItem;
import com.alpherininus.basmod.common.items.models.BasmodSpawnEggItem;
import com.alpherininus.basmod.common.particles.ManaParticle;
import com.alpherininus.basmod.core.init.EntityTypesInit;
import com.alpherininus.basmod.core.init.ParticleInit;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BasmodEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityTypesInit.BASMOD_COPPER_GOLEM.get(), CopperGolemEntity.setCustomCopperGolemAttributes().create());
        event.put(EntityTypesInit.BASMOD_SEIORSHELL.get(), SeiorShellArmorEntity.setCustomAttributes().create());

        event.put(EntityTypesInit.BASMOD_BOSS_ENTITY.get(), BasBossEntity.setCustomBasbossAttributes());
        event.put(EntityTypesInit.BOSS_OF_DEAD_ENTITY.get(), BossOfDeadEntity.setCustomBossOfDeadAttributes());

    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        BasmodSpawnEggItem.initSpawnEggs();
        SeioerShellSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new BasmodAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Basmod.MOD_ID,"greenkey_from_blocks")),

                new BasmodStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Basmod.MOD_ID,"greenkey_in_chests")),
                new BasmodStructureAdditionModifier.Serializer().setRegistryName
                    (new ResourceLocation(Basmod.MOD_ID,"redkey_in_chests")),
                new BasmodStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Basmod.MOD_ID,"bluekey_in_chests")),
                new BasmodStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Basmod.MOD_ID,"normalkey_in_chests"))
        );
    }

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.addParticle(ParticleInit.MANA_PARTICLE.get(), 0, 0, 0, 1, 1, 1);
    }

}
