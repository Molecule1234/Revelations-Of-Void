package net.scar.rotvmod.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.scar.rotvmod.screen.VoidExtractorMenu;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ExtractorFuelSlot extends SlotItemHandler {
    private final VoidExtractorMenu menu;

    public ExtractorFuelSlot(VoidExtractorMenu pExtractorMenu, IItemHandler itemHandler, int pSlot, int pXPosition, int pYPosition) {
        super(itemHandler, pSlot, pXPosition, pYPosition);
        this.menu = pExtractorMenu;
    }

    public boolean mayPlace(ItemStack pStack) {
        return this.menu.isFuel(pStack) || isBucket(pStack);
    }

    public int getMaxStackSize(ItemStack pStack) {
        return isBucket(pStack) ? 1 : super.getMaxStackSize(pStack);
    }

    public static boolean isBucket(ItemStack pStack) {
        return pStack.is(Items.BUCKET);
    }
}