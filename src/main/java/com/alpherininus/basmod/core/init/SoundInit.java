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

    public static final RegistryObject<SoundEvent> RECORDS_BASMOD_THEME =
            registerSoundEvent("theme_music_disc");

    public static final RegistryObject<SoundEvent> RECORDS_GEARS =
            registerSoundEvent("gears_music_disc");

    public static final RegistryObject<SoundEvent> MUSIC_BASMOD_BOSS_BATTLEMUSIC =
            registerSoundEvent("basmod_boss_battlemusic");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return BAS_SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(Basmod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        BAS_SOUND_EVENTS.register(eventBus);
    }

}
