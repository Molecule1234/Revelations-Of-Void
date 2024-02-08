package net.scar.rotvmod.inventory.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.registry.ModItems;

import javax.annotation.Nonnull;

public class ChargeSlot extends SlotItemHandler {
    public ChargeSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        return stack.getItem() == ModItems.VOID_GEM.get();
    }
}