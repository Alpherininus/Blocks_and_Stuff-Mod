package com.alpherininus.basmod.core.init.villager;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;

public class POITypesInit {
    public static final DeferredRegister<PointOfInterestType> POINT_OF_INTEREST_TYPES = DeferredRegister
            .create(ForgeRegistries.POI_TYPES, Basmod.MOD_ID);

    public static final RegistryObject<PointOfInterestType> TRASH_VILLAGER_POI = POINT_OF_INTEREST_TYPES.register("junky",
            () -> new PointOfInterestType("junky", PointOfInterestType.getAllStates(BlockInit.TRASHBIN.get()), 1, 1));

    public static final RegistryObject<PointOfInterestType> BONE_VILLAGER_POI = POINT_OF_INTEREST_TYPES.register("boner",
            () -> new PointOfInterestType("boner", PointOfInterestType.getAllStates(Blocks.BONE_BLOCK), 1, 1));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // common
    public static void registerPOItypes() {
        try {
            ObfuscationReflectionHelper
                    .findMethod(PointOfInterestType.class, "registerBlockStates", PointOfInterestType.class)
                    .invoke(null, TRASH_VILLAGER_POI.get());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            ObfuscationReflectionHelper
                    .findMethod(PointOfInterestType.class, "registerBlockStates", PointOfInterestType.class)
                    .invoke(null, BONE_VILLAGER_POI.get());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void register(IEventBus eventBus) {
        POINT_OF_INTEREST_TYPES.register(eventBus);
    }
}
