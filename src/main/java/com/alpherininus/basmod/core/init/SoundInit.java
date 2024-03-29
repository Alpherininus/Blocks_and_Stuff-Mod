package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
    public static DeferredRegister<SoundEvent> BAS_SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Basmod.MOD_ID);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO Basmod Records

    public static final RegistryObject<SoundEvent> RECORDS_BASMOD_THEME =
            registerSoundEvent("theme_music_disc");

    public static final RegistryObject<SoundEvent> RECORDS_BASMOD_GEARS =
            registerSoundEvent("gears_music_disc");

    public static final RegistryObject<SoundEvent> RECORDS_DER_ROBOTER =
            registerSoundEvent("random/the_roboter");


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO Basmod Music

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO Basmod Ambient

    public static final RegistryObject<SoundEvent> AMBIENT_MYSTERY =
            registerSoundEvent("ambient/mystery");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO Basmod Main Menu

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<SoundEvent> DEAT_GODRICK_CRAFT_VICTORY_FF7 =
            registerSoundEvent("entitys/boss/victory_ff7_gc");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return BAS_SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(Basmod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        BAS_SOUND_EVENTS.register(eventBus);
    }

}
