package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class PaintingsInit {
    public static final DeferredRegister<PaintingType> PAINTING_TYPES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Basmod.MOD_ID);

    public static final RegistryObject<PaintingType> ASUKA_PAINTING =
            PAINTING_TYPES.register("asuka", () -> new PaintingType(32, 16));

    public static final RegistryObject<PaintingType> DUCK_ATTACK =
            PAINTING_TYPES.register("duck_att", () -> new PaintingType(48, 48));

    public static final RegistryObject<PaintingType> BASMOD_PAINTING =
            PAINTING_TYPES.register("basmod", () -> new PaintingType(16, 16));

    public static final RegistryObject<PaintingType> RICK_PAINTING =
            PAINTING_TYPES.register("rick", () -> new PaintingType(32, 32));


    public static void register(IEventBus eventBus) {
        PAINTING_TYPES.register(eventBus);
    }
}
