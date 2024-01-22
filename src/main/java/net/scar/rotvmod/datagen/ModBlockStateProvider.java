package net.scar.rotvmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RotvMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(((RotatedPillarBlock) ModBlocks.VOID_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.VOID_WOOD.get()), blockTexture(ModBlocks.VOID_LOG.get()), blockTexture(ModBlocks.VOID_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_VOID_LOG.get()), blockTexture(ModBlocks.STRIPPED_VOID_LOG.get()),
                new ResourceLocation(RotvMod.MOD_ID, "block/stripped_void_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_VOID_WOOD.get()), blockTexture(ModBlocks.STRIPPED_VOID_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_VOID_LOG.get()));

        blockItem(ModBlocks.VOID_LOG);
        blockItem(ModBlocks.VOID_WOOD);
        blockItem(ModBlocks.STRIPPED_VOID_LOG);
        blockItem(ModBlocks.STRIPPED_VOID_WOOD);

        blockWithItem(ModBlocks.VOID_PLANKS);

        leavesBlock(ModBlocks.VOID_LEAVES);

        signBlock(((StandingSignBlock) ModBlocks.VOID_SIGN.get()), ((WallSignBlock) ModBlocks.VOID_WALL_SIGN.get()),
                blockTexture(ModBlocks.VOID_PLANKS.get()));

        hangingSignBlock(ModBlocks.VOID_HANGING_SIGN.get(), ModBlocks.VOID_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.VOID_PLANKS.get()));

        simpleBlockWithItem(ModBlocks.VOID_FLOWER.get(), models().cross(blockTexture(ModBlocks.VOID_FLOWER.get()).getPath(),
                blockTexture(ModBlocks.VOID_FLOWER.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_VOID_FLOWER.get(), models().singleTexture("potted_void_flower", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.VOID_FLOWER.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.SLIME_FLOWER.get(), models().cross(blockTexture(ModBlocks.SLIME_FLOWER.get()).getPath(),
                blockTexture(ModBlocks.SLIME_FLOWER.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_SLIME_FLOWER.get(), models().singleTexture("potted_slime_flower", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.SLIME_FLOWER.get())).renderType("cutout"));

        stairsBlock(((StairBlock) ModBlocks.VOID_STAIRS.get()), blockTexture(ModBlocks.VOID_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.VOID_SLAB.get()), blockTexture(ModBlocks.VOID_PLANKS.get()), blockTexture(ModBlocks.VOID_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.VOID_BUTTON.get()), blockTexture(ModBlocks.VOID_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.VOID_PRESSURE_PLATE.get()), blockTexture(ModBlocks.VOID_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.VOID_FENCE.get()), blockTexture(ModBlocks.VOID_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.VOID_FENCE_GATE.get()), blockTexture(ModBlocks.VOID_PLANKS.get()));
        wallBlock(((WallBlock) ModBlocks.VOID_WALL.get()), blockTexture(ModBlocks.VOID_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.VOID_DOOR.get()), modLoc("block/void_door_bottom"), modLoc("block/void_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.VOID_TRAPDOOR.get()), modLoc("block/void_trapdoor"), true, "cutout");
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(RotvMod.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

//    public void makeStrawberryCrop(CropBlock block, String modelName, String textureName) {
//        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);
//
//        getVariantBuilder(block).forAllStates(function);
//    }

//    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
//        ConfiguredModel[] models = new ConfiguredModel[1];
//        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()),
//                new ResourceLocation(RotvMod.MOD_ID, "block/" + textureName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()))).renderType("cutout"));
//
//        return models;
//    }

//    public void makeCornCrop(CropBlock block, String modelName, String textureName) {
//        Function<BlockState, ConfiguredModel[]> function = state -> cornStates(state, block, modelName, textureName);
//
//        getVariantBuilder(block).forAllStates(function);
//    }

//    private ConfiguredModel[] cornStates(BlockState state, CropBlock block, String modelName, String textureName) {
//        ConfiguredModel[] models = new ConfiguredModel[1];
//        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CornCropBlock) block).getAgeProperty()),
//                new ResourceLocation(RotvMod.MOD_ID, "block/" + textureName + state.getValue(((CornCropBlock) block).getAgeProperty()))).renderType("cutout"));
//
//        return models;
//    }


    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}