package net.scar.rotvmod.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.block.ModBlocks;

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
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
