package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Basmod.MOD_ID);
    
    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }

}
