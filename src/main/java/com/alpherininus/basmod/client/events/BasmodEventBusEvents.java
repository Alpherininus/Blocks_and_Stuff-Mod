package com.alpherininus.basmod.client.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.client.events.loots.BasmodAdditionModifier;
import com.alpherininus.basmod.client.events.loots.BasmodStructureAdditionModifier;
import com.alpherininus.basmod.common.entitys.CopperGolemEntity;
import com.alpherininus.basmod.common.entitys.SeiorShellArmorEntity;
import com.alpherininus.basmod.common.entitys.animated.BasBossEntity;
import com.alpherininus.basmod.common.entitys.animated.BossOfDeadEntity;
import com.alpherininus.basmod.common.items.SeioerShellSpawnEggItem;
import com.alpherininus.basmod.common.items.models.BasmodSpawnEggItem;
import com.alpherininus.basmod.core.init.EntityTypesInit;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

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

}
