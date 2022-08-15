package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.containers.BaSInfoContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerInit {

    public static DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, Basmod.MOD_ID);

    public static final RegistryObject<ContainerType<BaSInfoContainer>> LIGHTNING_CHANNELER_CONTAINER
            = CONTAINERS.register("experimental_item_screen",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                World world = inv.player.getEntityWorld();
                return new BaSInfoContainer(windowId, world, inv, inv.player);
            })));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }

}
