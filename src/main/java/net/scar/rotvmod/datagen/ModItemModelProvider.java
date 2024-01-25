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
        simpleItem(ModItems.VOID_TREE_BARK);
        simpleItem(ModItems.OVERFLOWN_VOID_TREE_BARK);

        // Discs
        simpleItem(ModItems.AURORA_MUSIC_DISC);
        simpleItem(ModItems.CALM_MUSIC_DISC);
        simpleItem(ModItems.DEORIUM_MUSIC_DISC);
        simpleItem(ModItems.ELEVEN_MUSIC_DISC);
        simpleItem(ModItems.PURPLE_MUSIC_DISC);
        simpleItem(ModItems.REVELATIONS_MUSIC_DISC);
        simpleItem(ModItems.VOID_MUSIC_DISC);

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

        simpleBlockItem(ModBlocks.VOID_DOOR);

        fenceItem(ModBlocks.VOID_FENCE, ModBlocks.VOID_PLANKS);
        buttonItem(ModBlocks.VOID_BUTTON, ModBlocks.VOID_PLANKS);
        wallItem(ModBlocks.VOID_WALL, ModBlocks.VOID_PLANKS);

        evenSimplerBlockItem(ModBlocks.VOID_STAIRS);
        evenSimplerBlockItem(ModBlocks.VOID_SLAB);
        evenSimplerBlockItem(ModBlocks.VOID_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.VOID_FENCE_GATE);

        trapdoorItem(ModBlocks.VOID_TRAPDOOR);

        buttonItem(ModBlocks.IMBUED_STONE_BUTTON, ModBlocks.IMBUED_STONE);
        evenSimplerBlockItem(ModBlocks.IMBUED_STONE_STAIRS);
        evenSimplerBlockItem(ModBlocks.IMBUED_STONE_SLAB);
        wallItem(ModBlocks.IMBUED_STONE_WALL, ModBlocks.IMBUED_STONE);

        buttonItem(ModBlocks.POLISHED_IMBUED_STONE_BUTTON, ModBlocks.POLISHED_IMBUED_STONE);
        evenSimplerBlockItem(ModBlocks.POLISHED_IMBUED_STONE_STAIRS);
        evenSimplerBlockItem(ModBlocks.POLISHED_IMBUED_STONE_SLAB);
        wallItem(ModBlocks.POLISHED_IMBUED_STONE_WALL, ModBlocks.POLISHED_IMBUED_STONE);

        buttonItem(ModBlocks.IMBUED_STONE_BRICKS_BUTTON, ModBlocks.IMBUED_STONE_BRICKS);
        evenSimplerBlockItem(ModBlocks.IMBUED_STONE_BRICKS_STAIRS);
        evenSimplerBlockItem(ModBlocks.IMBUED_STONE_BRICKS_SLAB);
        wallItem(ModBlocks.IMBUED_STONE_BRICKS_WALL, ModBlocks.IMBUED_STONE_BRICKS);

        saplingItem(ModBlocks.VOID_SAPLING);

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

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(RotvMod.MOD_ID,"block/" + item.getId().getPath()));
    }
}