package net.scar.rotvmod.datagen.tree;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class VoidTreeTrunkPlacer extends TrunkPlacer {
    public static final Codec<VoidTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(pineTrunkPlacerInstance ->
            trunkPlacerParts(pineTrunkPlacerInstance).apply(pineTrunkPlacerInstance, VoidTreeTrunkPlacer::new));

    public VoidTreeTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.VOID_TREE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
                                                            RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        // THIS WHERE BLOCK PLACING LOGIC GOES
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
         for(int i = 0; i < pFreeTreeHeight; i++) {
            placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);
        }

        pBlockSetter.accept(pPos.above(0).relative(Direction.NORTH, 1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
        pBlockSetter.accept(pPos.above(0).relative(Direction.NORTH, 2), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));

        pBlockSetter.accept(pPos.above(0).relative(Direction.EAST, 1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
        pBlockSetter.accept(pPos.above(0).relative(Direction.EAST, 2), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));

        pBlockSetter.accept(pPos.above(0).relative(Direction.WEST, 1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
        pBlockSetter.accept(pPos.above(0).relative(Direction.WEST, 2), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));

        pBlockSetter.accept(pPos.above(0).relative(Direction.SOUTH, 1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
        pBlockSetter.accept(pPos.above(0).relative(Direction.SOUTH, 2), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));





        pBlockSetter.accept(pPos.above(1).relative(Direction.SOUTH, 1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
        pBlockSetter.accept(pPos.above(1).relative(Direction.SOUTH, 2), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));


        pBlockSetter.accept(pPos.above(1).relative(Direction.EAST, 1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));
        pBlockSetter.accept(pPos.above(1).relative(Direction.WEST, 1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));


        pBlockSetter.accept(pPos.above(0).relative(Direction.EAST, 1).south(1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));
        pBlockSetter.accept(pPos.above(0).relative(Direction.EAST, 1).north(1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));
        pBlockSetter.accept(pPos.above(0).relative(Direction.WEST, 1).south(1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));
        pBlockSetter.accept(pPos.above(0).relative(Direction.WEST, 1).north(1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));

        pBlockSetter.accept(pPos.above(3).relative(Direction.NORTH, 1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y))));

        pBlockSetter.accept(pPos.above(5).relative(Direction.EAST, 1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
        pBlockSetter.accept(pPos.above(5).relative(Direction.EAST, 2), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));

        pBlockSetter.accept(pPos.above(5).relative(Direction.WEST, 1), ((BlockState)
                Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(pFreeTreeHeight - 4), 0, false));
    }
}