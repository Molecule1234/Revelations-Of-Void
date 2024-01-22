package net.scar.rotvmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.block.ModBlocks;
import net.scar.rotvmod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RotvMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ANCIENT_TOME);

        // Ingots
        simpleItem(ModItems.INGOT_ALTURIUM);
        simpleItem(ModItems.INGOT_DEORIUM);
        simpleItem(ModItems.INGOT_OVERFLOWN_DEORIUM);

        // Nuggets
        simpleItem(ModItems.NUGGET_DEORIUM);
        simpleItem(ModItems.NUGGET_ALTURIUM);
        simpleItem(ModItems.NUGGET_OVERFLOWN_DEORIUM);

        // Raws
        simpleItem(ModItems.RAW_ALTURIUM);
        simpleItem(ModItems.RAW_DEORIUM);
        simpleItem(ModItems.RAW_OVERFLOWN_DEORIUM);

        simpleItem(ModItems.CLEAR_RUNE);
        simpleItem(ModItems.IMBUED_BRICK);

        simpleItem(ModItems.VOID_SIGN);
        simpleItem(ModItems.VOID_HANGING_SIGN);

        simpleItem(ModItems.VOID_BOAT);
        simpleItem(ModItems.VOID_CHEST_BOAT);

        simpleBlockItemBlockTexture(ModBlocks.VOID_FLOWER);
        simpleBlockItemBlockTexture(ModBlocks.SLIME_FLOWER);

        simpleBlockItem(ModBlocks.VOID_DOOR);

        fenceItem(ModBlocks.VOID_FENCE, ModBlocks.VOID_PLANKS);
        buttonItem(ModBlocks.VOID_BUTTON, ModBlocks.VOID_PLANKS);
        wallItem(ModBlocks.VOID_WALL, ModBlocks.VOID_PLANKS);

        evenSimplerBlockItem(ModBlocks.VOID_STAIRS);
        evenSimplerBlockItem(ModBlocks.VOID_SLAB);
        evenSimplerBlockItem(ModBlocks.VOID_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.VOID_FENCE_GATE);

        trapdoorItem(ModBlocks.VOID_TRAPDOOR);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(RotvMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(RotvMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(RotvMod.MOD_ID,"block/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(RotvMod.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(RotvMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(RotvMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(RotvMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(RotvMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}