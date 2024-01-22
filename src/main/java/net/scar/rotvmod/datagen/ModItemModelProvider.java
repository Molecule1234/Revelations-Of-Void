package net.scar.rotvmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;
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
}