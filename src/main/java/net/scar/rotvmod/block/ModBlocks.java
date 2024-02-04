package net.scar.rotvmod.block;

import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.block.custom.*;
import net.scar.rotvmod.block.custom.alchemy.AlchemyFurnaceBlock;
import net.scar.rotvmod.block.custom.extractor.VoidExtractorBlock;
import net.scar.rotvmod.datagen.tree.VoidTreeGrower;
import net.scar.rotvmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.scar.rotvmod.util.ModWoodTypes;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.sounds.SoundEvents;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RotvMod.MOD_ID);

    public static final RegistryObject<Block> IMBUED_STONE = registerBlock("imbued_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> IMBUED_STONE_BRICKS = registerBlock("imbued_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).sound(SoundType.DEEPSLATE_BRICKS)));
    public static final RegistryObject<Block> POLISHED_IMBUED_STONE = registerBlock("polished_imbued_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> CARVED_IMBUED_STONE = registerBlock("carved_imbued_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> CRACKED_IMBUED_STONE_BRICKS = registerBlock("cracked_imbued_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).sound(SoundType.DEEPSLATE_BRICKS)));
    public static final RegistryObject<Block> IMBUED_STONE_TILE = registerBlock("imbued_stone_tile",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> VOID_LOG = registerBlock("void_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> VOID_WOOD = registerBlock("void_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_VOID_LOG = registerBlock("stripped_void_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_VOID_WOOD = registerBlock("stripped_void_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> VOID_SIGN = BLOCKS.register("void_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.VOID));
    public static final RegistryObject<Block> VOID_WALL_SIGN = BLOCKS.register("void_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.VOID));

    public static final RegistryObject<Block> VOID_HANGING_SIGN = BLOCKS.register("void_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.VOID));
    public static final RegistryObject<Block> VOID_WALL_HANGING_SIGN = BLOCKS.register("void_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.VOID));

    public static final RegistryObject<Block> VOID_FLOWER = registerBlock("void_flower",
            () -> new FlowerBlock(() -> MobEffects.ABSORPTION, 0, BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_VOID_FLOWER = BLOCKS.register("potted_void_flower",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.VOID_FLOWER,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final RegistryObject<Block> SLIME_FLOWER = registerBlock("slime_flower",
            () -> new FlowerBlock(() -> MobEffects.ABSORPTION, 0, BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_SLIME_FLOWER = BLOCKS.register("potted_slime_flower",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.SLIME_FLOWER,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final RegistryObject<Block> VOID_PLANKS = registerBlock("void_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> VOID_LEAVES = registerBlock("void_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });
    public static final RegistryObject<Block> VOID_LEAVES_MONOCHROME = registerBlock("void_leaves_monochrome",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });
    public static final RegistryObject<Block> FLOWERING_VOID_LEAVES = registerBlock("flowering_void_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> VOID_STAIRS = registerBlock("void_stairs",
            () -> new StairBlock(() -> ModBlocks.VOID_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> VOID_SLAB = registerBlock("void_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> VOID_BUTTON = registerBlock("void_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.WOOD),
                    BlockSetType.OAK, 10, true));
    public static final RegistryObject<Block> VOID_PRESSURE_PLATE = registerBlock("void_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).sound(SoundType.WOOD),
                    BlockSetType.OAK));

    public static final RegistryObject<Block> VOID_FENCE = registerBlock("void_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> VOID_FENCE_GATE = registerBlock("void_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_OPEN));

    public static final RegistryObject<Block> VOID_DOOR = registerBlock("void_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).sound(SoundType.WOOD).noOcclusion(), BlockSetType.ACACIA));
    public static final RegistryObject<Block> VOID_TRAPDOOR = registerBlock("void_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).sound(SoundType.WOOD).noOcclusion(), BlockSetType.ACACIA));
    public static final RegistryObject<Block> VOID_WALL = registerBlock("void_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));



    public static final RegistryObject<Block> IMBUED_STONE_SLAB = registerBlock("imbued_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> IMBUED_STONE_STAIRS = registerBlock("imbued_stone_stairs",
            () -> new StairBlock(() -> ModBlocks.IMBUED_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> IMBUED_STONE_BUTTON = registerBlock("imbued_stone_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.DEEPSLATE),
                    BlockSetType.STONE, 10, true));
    public static final RegistryObject<Block> IMBUED_STONE_WALL = registerBlock("imbued_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> POLISHED_IMBUED_STONE_SLAB = registerBlock("polished_imbued_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> POLISHED_IMBUED_STONE_STAIRS = registerBlock("polished_imbued_stone_stairs",
            () -> new StairBlock(() -> ModBlocks.POLISHED_IMBUED_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> POLISHED_IMBUED_STONE_BUTTON = registerBlock("polished_imbued_stone_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.DEEPSLATE),
                    BlockSetType.STONE, 10, true));
    public static final RegistryObject<Block> POLISHED_IMBUED_STONE_WALL = registerBlock("polished_imbued_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> IMBUED_STONE_BRICKS_SLAB = registerBlock("imbued_stone_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> IMBUED_STONE_BRICKS_STAIRS = registerBlock("imbued_stone_bricks_stairs",
            () -> new StairBlock(() -> ModBlocks.IMBUED_STONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> IMBUED_STONE_BRICKS_BUTTON = registerBlock("imbued_stone_bricks_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.DEEPSLATE),
                    BlockSetType.STONE, 10, true));
    public static final RegistryObject<Block> IMBUED_STONE_BRICKS_WALL = registerBlock("imbued_stone_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.DEEPSLATE)));



    public static final RegistryObject<Block> ALCHEMY_FURNACE = registerBlock("alchemy_furnace",
            () -> new AlchemyFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> VOID_EXTRACTOR = registerBlock("void_extractor",
            () -> new VoidExtractorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().lightLevel(litBlockEmission(13))));


    public static final RegistryObject<Block> VOID_SAPLING = registerBlock("void_sapling",
            () -> new SaplingBlock(new VoidTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> {
            return p_50763_.getValue(VoidExtractorBlock.LIT) ? pLightValue : 0;
        };
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}