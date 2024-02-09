package net.scar.rotvmod.utils;

import net.minecraft.world.item.ItemStack;

public class CategoryAlmanac {
    public ItemStack icon;
    public String key;

    public CategoryAlmanac(String name, ItemStack icon) {
        this.icon = icon;
        this.key = name;
    }
}
