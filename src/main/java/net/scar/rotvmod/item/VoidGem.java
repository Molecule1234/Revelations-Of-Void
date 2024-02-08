package net.scar.rotvmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class VoidGem extends Item implements ChargedVoidItem {

    public VoidGem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.rotv.void_gem.tooltip_fluid", ChargedVoidItem.getFluidCount(pStack), MAX_VOID_FLUID_COUNT));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, TooltipFlag.NORMAL);
    }

}

