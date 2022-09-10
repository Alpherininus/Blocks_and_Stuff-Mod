package com.alpherininus.basmod;

import com.alpherininus.basmod.common.containers.screen.BaSInfoScreen;
import com.alpherininus.basmod.common.entitys.animated.renerer.BasBossRenderer;
import com.alpherininus.basmod.common.entitys.animated.renerer.BossOfDeadRenderer;
import com.alpherininus.basmod.common.entitys.renderer.CopperGolemRenderer;
import com.alpherininus.basmod.common.entitys.renderer.MagicalSpellArrowRenderer;
import com.alpherininus.basmod.common.entitys.renderer.SeieorShellRenderer;
import com.alpherininus.basmod.common.handlers.BasmodAnivilHandler;
import com.alpherininus.basmod.common.items.armor.JetPackArmorItem;
import com.alpherininus.basmod.common.items.armor.models.renderer.JetPackArmorRenderer;
import com.alpherininus.basmod.common.items.models.BasmodItemModel;
import com.alpherininus.basmod.common.recipes.BasmodBrewing;
import com.alpherininus.basmod.common.recipes.BasmodBrewingPotion;
import com.alpherininus.basmod.common.world.gen.BiomeGeneration;
import com.alpherininus.basmod.common.world.gen.OreGeneration;
import com.alpherininus.basmod.core.init.*;
import com.alpherininus.basmod.core.util.BasmodConfig;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod("basmod")
@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Basmod {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "basmod";

    public Basmod() {
        IEventBus eventbus = FMLJavaModLoadingContext.get().getModEventBus();

        // TODO Registrys
        BlockInit.BLOCKS.register(eventbus);
        ItemInit.ITEMS.register(eventbus);

        EnchantmentInit.ENCHANTMENT_DEFERRED_REGISTER.register(eventbus);
        TileEntityInit.TILE_ENTITIES.register(eventbus);
        BiomeInit.BIOMES.register(eventbus);
        ContainerInit.CONTAINERS.register(eventbus);
        EntityTypesInit.ENTITY_TYPES.register(eventbus);
        PaintingsInit.PAINTING_TYPES.register(eventbus);
        FluidInit.FLUIDS.register(eventbus);
        SoundInit.BAS_SOUND_EVENTS.register(eventbus);
        StructureInit.STRUCTURES.register(eventbus);
        PotionInit.POTIONS.register(eventbus);
        EffectInit.POTIONS.register(eventbus);

        GeckoLib.initialize();

        // TODO EVENTS
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

        // TODO EVENTBUS
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::addOres);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BasmodConfig.COMMON_SPEC, "basmod-common.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.CHARM.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.RING.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.HEAD.getMessageBuilder().build());
    }

    private void setup(final FMLCommonSetupEvent event) {
        // BasmodNetwork.init();
        BasmodAnivilHandler.initAnvilRecipes();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        event.enqueueWork(() -> {
            BrewingRecipeRegistry.addRecipe(new BasmodBrewing(
                    Ingredient.fromStacks(new ItemStack(Items.STICK)),
                    Ingredient.fromStacks(new ItemStack(Items.DIAMOND)),
                    new ItemStack(Items.DIAMOND_SWORD)));

            BrewingRecipeRegistry.addRecipe(new BasmodBrewing(
                    Ingredient.fromStacks(new ItemStack(ItemInit.CREATOR_SWORD.get())),
                    Ingredient.fromStacks(new ItemStack(ItemInit.RELICS_ITEM.get())),
                    new ItemStack(ItemInit.SUBLIME_CREATOR_SWORD.get())));

            BrewingRecipeRegistry.addRecipe(new BasmodBrewing(
                    Ingredient.fromStacks(new ItemStack(ItemInit.SUBLIME_CREATOR_SWORD.get())),
                    Ingredient.fromStacks(new ItemStack(Items.PRISMARINE_SHARD)),
                    new ItemStack(ItemInit.LIGHT_SWORD.get())));

            BrewingRecipeRegistry.addRecipe(new BasmodBrewing(
                    Ingredient.fromStacks(new ItemStack(Items.BUCKET)),
                    Ingredient.fromStacks(new ItemStack(BlockInit.MAGICAL_FLOWER.get())),
                    new ItemStack(ItemInit.MANA_BUCKET.get())));

            BrewingRecipeRegistry.addRecipe(new BasmodBrewingPotion(
                    Ingredient.fromStacks(new ItemStack(Items.GLASS_BOTTLE)),
                    Ingredient.fromStacks(new ItemStack(Blocks.FROSTED_ICE)),
                    PotionInit.FREEZE_POTION.get()));

            BrewingRecipeRegistry.addRecipe(new BasmodBrewingPotion(
                    Ingredient.fromStacks(new ItemStack(Items.GLASS_BOTTLE)),
                    Ingredient.fromStacks(new ItemStack(BlockInit.MAGICAL_FLOWER.get())),
                    PotionInit.MANA.get()));
        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // // event.enqueueWork(() -> AxeItem = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.BLOCK_STRIPPING_MAP)
        // //         .put(BlockInit.MAGICAL_OAK_LOG.get(), BlockInit.MAGICAL_STRIPPED_OAK_LOG.get())
        // //         .put(BlockInit.MAGICAL_OAK_WOOD.get(), BlockInit.MAGICAL_STRIPPED_OAK_WOOD.get()).build()
        // // );

        event.enqueueWork(BiomeGeneration::generateBiome);
        event.enqueueWork(StructureInit::setupStructures);

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        Minecraft mc = Minecraft.getInstance();
        mc = event.getMinecraftSupplier().get();

        event.enqueueWork(() -> {

            BasmodItemModel.makeBow(ItemInit.FAILNAUGHT_BOW.get());

            RenderTypeLookup.setRenderLayer(BlockInit.BLUEKEY_DOOR.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockInit.GREENKEY_DOOR.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockInit.REDKEY_DOOR.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockInit.KEY_DOOR.get(), RenderType.getCutout());

            RenderTypeLookup.setRenderLayer(BlockInit.MAGICAL_OAK_DOOR.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockInit.MAGICAL_OAK_TRAPDOOR.get(), RenderType.getCutout());

            RenderTypeLookup.setRenderLayer(BlockInit.MAGICAL_FLOWER.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockInit.MAGICAL_OAK_SAPLING.get(), RenderType.getCutout());

            RenderTypeLookup.setRenderLayer(FluidInit.MANA_FLUID.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(FluidInit.MANA_BLOCK.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(FluidInit.MANA_FLOWING.get(), RenderType.getTranslucent());

            ScreenManager.registerFactory(ContainerInit.LIGHTNING_CHANNELER_CONTAINER.get(), BaSInfoScreen::new);

        });

        // BasmodItemModel.makeBow(ItemInit.FAILNAUGHT_BOW.get());

        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.BASMOD_COPPER_GOLEM.get(), CopperGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.BASMOD_SEIORSHELL.get(), SeieorShellRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MAGICAL_SPELL_ARROW.get(), MagicalSpellArrowRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.BASMOD_BOSS_ENTITY.get(), BasBossRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.BOSS_OF_DEAD_ENTITY.get(), BossOfDeadRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(JetPackArmorItem.class, JetPackArmorRenderer::new);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

}
