package net.scar.rotvmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
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

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}