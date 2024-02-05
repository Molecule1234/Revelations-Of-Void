package net.scar.rotvmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.registry.ModBlocks;
import net.scar.rotvmod.registry.ModItems;
import org.jetbrains.annotations.Nullable;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            Level level = context.getLevel();
            BlockPos pos = context.getClickedPos();

            if(state.is(ModBlocks.VOID_LOG.get())) {
                if (Math.random() < 0.15 && Math.random() > 0) {
                    popResource(level, pos, new ItemStack(ModItems.VOID_TREE_BARK.get(), 1));
                }

                return ModBlocks.STRIPPED_VOID_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.VOID_WOOD.get())) {
                if (Math.random() < 0.35 && Math.random() > 0) {
                    popResource(level, pos, new ItemStack(ModItems.VOID_TREE_BARK.get(), 1));
                }

                return ModBlocks.STRIPPED_VOID_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}