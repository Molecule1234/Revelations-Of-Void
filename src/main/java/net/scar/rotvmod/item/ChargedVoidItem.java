package net.scar.rotvmod.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public interface ChargedVoidItem {

    String VOID_FLUID_COUNT_NBT_KEY = "voidFluid";
    int MAX_VOID_FLUID_COUNT = 1000;

    static int getFluidCount(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getInt(VOID_FLUID_COUNT_NBT_KEY);
    }

    static @NotNull ItemStack setFluidCount(ItemStack stack, int fluidCount) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(VOID_FLUID_COUNT_NBT_KEY, fluidCount);
        stack.setTag(tag);
        return stack;
    }

}
