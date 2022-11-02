package com.alpherininus.basmod.core.init.villager;

import com.alpherininus.basmod.Basmod;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ProfessionsInit {

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister
            .create(ForgeRegistries.PROFESSIONS, Basmod.MOD_ID);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<VillagerProfession> JUNKY_VILLAGER_PROF = VILLAGER_PROFESSIONS.register("junky",
            () -> new VillagerProfession("junky", POITypesInit.TRASH_VILLAGER_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_FARMER));

    public static final RegistryObject<VillagerProfession> BONER_VILLAGER_PROF = VILLAGER_PROFESSIONS.register("boner",
            () -> new VillagerProfession("boner", POITypesInit.BONE_VILLAGER_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_SKELETON_AMBIENT));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void register(IEventBus eventBus) {
        VILLAGER_PROFESSIONS.register(eventBus);
    }

}
