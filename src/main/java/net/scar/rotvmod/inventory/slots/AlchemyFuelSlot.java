package net.scar.rotvmod.inventory.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.scar.rotvmod.inventory.screen.AlchemyMachineMenu;

public class AlchemyFuelSlot extends SlotItemHandler {
    private final AlchemyMachineMenu menu;

    public AlchemyFuelSlot(AlchemyMachineMenu pExtractorMenu, IItemHandler itemHandler, int pSlot, int pXPosition, int pYPosition) {
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