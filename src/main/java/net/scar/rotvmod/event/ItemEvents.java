package net.scar.rotvmod.event;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.registry.ModItems;

import java.awt.*;
import java.util.List;

@Mod.EventBusSubscriber(modid = RotvMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ItemEvents {

    @SubscribeEvent
    public static void addItemToolTip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        if (item == ModItems.VOID_GEM.get()) {
            if (event.getToolTip().size() == 5) {
                event.getToolTip().remove(2);
            }
        }

    }
}
