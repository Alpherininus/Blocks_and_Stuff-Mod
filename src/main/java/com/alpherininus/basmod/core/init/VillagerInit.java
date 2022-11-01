package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;

public class VillagerInit {
    public static final DeferredRegister<PointOfInterestType> POINT_OF_INTEREST_TYPES = DeferredRegister
            .create(ForgeRegistries.POI_TYPES, Basmod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister
            .create(ForgeRegistries.PROFESSIONS, Basmod.MOD_ID);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<PointOfInterestType> TEST_VILLAGER_POI = POINT_OF_INTEREST_TYPES.register("test",
            () -> new PointOfInterestType("test", PointOfInterestType.getAllStates(BlockInit.TRASHBIN.get()), 1, 1));

    public static final RegistryObject<VillagerProfession> TEST_VILLAGER_PROF = VILLAGER_PROFESSIONS.register("test",
            () -> new VillagerProfession("test", TEST_VILLAGER_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.UI_BUTTON_CLICK));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void registerPOI() {
        try {
            ObfuscationReflectionHelper
                    .findMethod(PointOfInterestType.class, "registerBlockStates", PointOfInterestType.class)
                    .invoke(null, TEST_VILLAGER_POI.get());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
