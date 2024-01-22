package net.scar.rotvmod.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.block.ModBlocks;
import net.scar.rotvmod.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider  {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.VOID_LOG.get());
        this.dropSelf(ModBlocks.VOID_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_VOID_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_VOID_WOOD.get());
        this.dropSelf(ModBlocks.VOID_PLANKS.get());

        this.add(ModBlocks.VOID_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.VOID_LEAVES.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.VOID_SIGN.get(), block ->
                createSingleItemTable(ModItems.VOID_SIGN.get()));
        this.add(ModBlocks.VOID_WALL_SIGN.get(), block ->
                createSingleItemTable(ModItems.VOID_SIGN.get()));
        this.add(ModBlocks.VOID_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.VOID_HANGING_SIGN.get()));
        this.add(ModBlocks.VOID_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.VOID_HANGING_SIGN.get()));

        this.dropSelf(ModBlocks.VOID_FLOWER.get());
        this.add(ModBlocks.POTTED_VOID_FLOWER.get(), createPotFlowerItemTable(ModBlocks.VOID_FLOWER.get()));

        this.dropSelf(ModBlocks.SLIME_FLOWER.get());
        this.add(ModBlocks.POTTED_SLIME_FLOWER.get(), createPotFlowerItemTable(ModBlocks.SLIME_FLOWER.get()));

        this.dropSelf(ModBlocks.VOID_STAIRS.get());
        this.dropSelf(ModBlocks.VOID_BUTTON.get());
        this.dropSelf(ModBlocks.VOID_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.VOID_TRAPDOOR.get());
        this.dropSelf(ModBlocks.VOID_FENCE.get());
        this.dropSelf(ModBlocks.VOID_FENCE_GATE.get());

        this.add(ModBlocks.VOID_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.VOID_SLAB.get()));
        this.add(ModBlocks.VOID_DOOR.get(),
                block -> createDoorTable(ModBlocks.VOID_DOOR.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
