package net.scar.rotvmod.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.registry.ModBlocks;
import net.scar.rotvmod.datagen.tree.VoidTreeFoliagePlacer;
import net.scar.rotvmod.datagen.tree.VoidTreeTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> IMBUED_STONE_KEY = registerKey("imbued_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SLIME_FLOWER_KEY = registerKey("slime_flower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VOID_TREE_KEY = registerKey("void_tree");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldImbuedStone = List.of(OreConfiguration.target(deepslateReplaceables,
                ModBlocks.IMBUED_STONE.get().defaultBlockState()));

        register(context, IMBUED_STONE_KEY, Feature.ORE, new OreConfiguration(overworldImbuedStone, 14));

        //Flowers
        register(context, SLIME_FLOWER_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(10,3,2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SLIME_FLOWER.get())))));

        register(context, VOID_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.VOID_LOG.get()),
                new VoidTreeTrunkPlacer(5, 2, 2),

                BlockStateProvider.simple(ModBlocks.VOID_LEAVES_MONOCHROME.get()),
                new VoidTreeFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), 1),

                new TwoLayersFeatureSize(1, 0, 0)).build());
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(RotvMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}