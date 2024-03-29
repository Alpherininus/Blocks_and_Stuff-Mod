package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.blocks.*;
import com.alpherininus.basmod.common.blocks.portals.DarkPortalBlock;
import com.alpherininus.basmod.common.blocks.doorblock.*;
import com.alpherininus.basmod.common.blocks.portals.EndPortalBlock;
import com.alpherininus.basmod.common.blocks.portals.NetherPortalBlock;
import com.alpherininus.basmod.common.blocks.trees.MagicalOakTree;
import com.alpherininus.basmod.core.util.itemgroup.ModItemGroupBlocks;
import com.alpherininus.basmod.core.util.materials.blocktier.BlockHardness;
import com.alpherininus.basmod.core.util.materials.blocktier.BlockResistance;
import com.alpherininus.basmod.core.util.materials.blocktier.BlockToolLevel;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Basmod.MOD_ID);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO ORES

    public static final RegistryObject<Block> UMBRAL_STEEL_ORE = registryBlock("umbral_steel_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(BlockHardness.getIron(), BlockResistance.getIron())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(BlockToolLevel.getDiamond()).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_STEEL_ORE = registryBlock("dark_steel_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(BlockHardness.getIron(), BlockResistance.getIron())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(BlockToolLevel.getIron()).sound(SoundType.STONE)));

    public static final RegistryObject<Block> STEEL_ORE = registryBlock("steel_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(BlockHardness.getIron(), BlockResistance.getIron())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(BlockToolLevel.getIron()).sound(SoundType.STONE)));

    public static final RegistryObject<Block> RUBY_ORE = registryBlock("ruby_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(BlockHardness.getIron(), BlockResistance.getIron())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(BlockToolLevel.getIron()).sound(SoundType.STONE)));

    public static final RegistryObject<Block> RELICS_ORE = registryBlock("relics_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(8f, 7f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(BlockToolLevel.getDiamond()).sound(SoundType.STONE)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO BUILDINGBLOCKS magical_oak_stairs

    public static final RegistryObject<Block> MAGICAL_OAK_LEAVES = registryBlock("magical_oak_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES, MaterialColor.OBSIDIAN)
                    .hardnessAndResistance(BlockHardness.getLeaves(), BlockResistance.getLeaves()).tickRandomly().notSolid().sound(SoundType.PLANT)));

    public static final RegistryObject<Block> MAGICAL_OAK_LOG = registryBlock("magical_oak_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties
                    .create(Material.WOOD, (state) -> state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.OBSIDIAN)
                    .hardnessAndResistance(BlockHardness.getWood(), BlockResistance.getWood())
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MAGICAL_OAK_PLANKS = registryBlock("magical_oak_planks",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(BlockHardness.getWood(), BlockResistance.getWood())
                    .harvestTool(ToolType.AXE).harvestLevel(BlockToolLevel.getWoodAndGold()).sound(SoundType.METAL)));

    public static final RegistryObject<Block> MAGICAL_OAK_DOOR = registryBlock("magical_oak_door",
            () -> new MagicalOakDoorItem(AbstractBlock.Properties.create(Material.IRON, MaterialColor.OBSIDIAN).hardnessAndResistance(BlockHardness.getBedrock(), BlockResistance.getBedrock())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(10).sound(SoundType.METAL).notSolid()));

    public static final RegistryObject<Block> MAGICAL_OAK_STAIRS = registryBlock("magical_oak_stairs",
            () -> new StairsBlock(() -> BlockInit.MAGICAL_OAK_PLANKS.get().getDefaultState(),
                    AbstractBlock.Properties.from(BlockInit.MAGICAL_OAK_PLANKS.get())));

    public static final RegistryObject<Block> MAGICAL_OAK_SLAB = registryBlock("magical_oak_slab",
            () -> new SlabBlock(AbstractBlock.Properties.from(BlockInit.MAGICAL_OAK_PLANKS.get())));

    public static final RegistryObject<Block> MAGICAL_OAK_TRAPDOOR = registryBlock("magical_oak_trapdoor",
            () -> new TrapDoorBlock(AbstractBlock.Properties.from(BlockInit.MAGICAL_OAK_PLANKS.get()).notSolid()));

    public static final RegistryObject<Block> UMBRAL_STEEL_BLOCK = registryBlock("umbral_steel_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(6f, 7f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(BlockToolLevel.getDiamond()).sound(SoundType.STONE)));

    public static final RegistryObject<Block> STEEL_BLOCK = registryBlock("steel_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(BlockHardness.getIron(), BlockResistance.getIron())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(BlockToolLevel.getIron()).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_STEEL_BLOCK = registryBlock("dark_steel_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(BlockHardness.getIron(), BlockResistance.getIron())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(BlockToolLevel.getIron()).sound(SoundType.STONE)));

    public static final RegistryObject<Block> RUBY_BLOCK = registryBlock("ruby_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.EMERALD)
                    .setRequiresTool()
                    .hardnessAndResistance(BlockHardness.getStone(), BlockResistance.getObsidian()).sound(SoundType.METAL)));

    public static final RegistryObject<Block> NOCOLLI_BLOCK = registryBlock("no_collision", ()-> new NoCollisionBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.EMERALD)
            .setRequiresTool()
            .hardnessAndResistance(BlockHardness.getIron(), BlockResistance.getIron()).sound(SoundType.METAL)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO DECORATION AND FLOWERS :>

    public static final RegistryObject<Block> MAGICAL_FLOWER = registryBlock("magical_flower",
            () -> new MagicalFlower(Effects.INSTANT_HEALTH, 10,
                    AbstractBlock.Properties.from(Blocks.DANDELION)
                            .sound(SoundType.BONE)
                            .setLightLevel(BlockState -> 3)));

    public static final RegistryObject<Block> MAGICAL_OAK_SAPLING = registryBlock("magical_oak_sapling",
            () -> new SaplingBlock(new MagicalOakTree(), AbstractBlock.Properties
                    .from(Blocks.OAK_SAPLING)
                    .sound(SoundType.CROP)
                    .setLightLevel(BlockState -> 4)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Block> KEY_DOOR = registryBlock("key_door",
            () -> new KeyDoorBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(BlockHardness.getBedrock(), BlockResistance.getBedrock())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(10).sound(SoundType.STONE).notSolid()));

    public static final RegistryObject<Block> BLUEKEY_DOOR = registryBlock("blue_key_door",
            () -> new BlueKeyDoorBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(BlockHardness.getBedrock(), BlockResistance.getBedrock())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(10).sound(SoundType.STONE).notSolid()));

    public static final RegistryObject<Block> REDKEY_DOOR = registryBlock("red_key_door",
            () -> new RedKeyDoorBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(BlockHardness.getBedrock(), BlockResistance.getBedrock())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(10).sound(SoundType.STONE).notSolid()));

    public static final RegistryObject<Block> GREENKEY_DOOR = registryBlock("green_key_door",
            () -> new GreenKeyDoorBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(BlockHardness.getBedrock(), BlockResistance.getBedrock())
                    .harvestTool(ToolType.PICKAXE).harvestLevel(10).sound(SoundType.STONE).notSolid()));


    public static final RegistryObject<Block> TRASHBIN = registryBlock("trash",
            () -> new TrashbinBlock(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.GRAY)
                    .hardnessAndResistance(BlockHardness.getIron(), BlockResistance.getIron())
                    .harvestLevel(BlockToolLevel.getIron()).sound(SoundType.STONE)));

    public static final RegistryObject<Block> LIGHT_BLOCK = registryBlock("light_block",
            () -> new BasmodLightBlock(AbstractBlock.Properties.create(Material.REDSTONE_LIGHT, MaterialColor.YELLOW)
                    .hardnessAndResistance(BlockHardness.getLeaves(), BlockResistance.getLeaves()).sound(SoundType.GLASS)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Block> DARK_PORTAL_BLOCK = registryBlock("dark_portal_block",
            () -> new DarkPortalBlock(AbstractBlock.Properties.create(Material.PORTAL, MaterialColor.OBSIDIAN)
                    .hardnessAndResistance(BlockHardness.getObsidian(), BlockResistance.getObsidian()).sound(SoundType.NETHERRACK).setLightLevel(BlockState -> 3)));

    public static final RegistryObject<Block> END_PORTAL_BLOCK = registryBlock("end_portal_block",
            () -> new EndPortalBlock(AbstractBlock.Properties.create(Material.PORTAL, MaterialColor.OBSIDIAN)
                    .hardnessAndResistance(BlockHardness.getObsidian(), BlockResistance.getObsidian()).sound(SoundType.NETHERRACK).setLightLevel(BlockState -> 3)));

    public static final RegistryObject<Block> NETHER_PORTAL_BLOCK = registryBlock("nether_portal_block",
            () -> new NetherPortalBlock(AbstractBlock.Properties.create(Material.PORTAL, MaterialColor.OBSIDIAN)
                    .hardnessAndResistance(BlockHardness.getObsidian(), BlockResistance.getObsidian()).sound(SoundType.NETHERRACK).setLightLevel(BlockState -> 3)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Block> BOMB_BOX = registryBlock("bomb_box",
            () -> new BombBox(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.WOOD)
                    .hardnessAndResistance(0.0f, 0.0f).sound(SoundType.WOOD)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO BLOCKS

    public static final RegistryObject<Block> TELEPORTERBLOCK = registryBlock("teleporter_block", TeleporterBlock::new);
    public static final RegistryObject<Block> BOUNCEBLOCK_BLUE = registryBlock("jump_block", BouncSourceBlockBlue::new);
    public static final RegistryObject<Block> BOUNCEBLOCK_RED = registryBlock("jump_block_red", BouncSourceBlockRed::new);

    public static final RegistryObject<Block> BASMOD_BLOCK = registryBlock("bas_block", BasmodBlock::new);

    public static final RegistryObject<Block> LIGHTSOURCE_BLOCKS = BLOCKS.register("light_blocks", LightSourceBlocks::new);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //TODO Registry -> registryBlock oder BLOCKS.register

    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlock(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registryBlock(String name, RegistryObject<T> block) {
        ItemInit.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties().group(ModItemGroupBlocks.BAS_MOD_BLOCKS)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
