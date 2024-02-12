package net.scar.rotvmod.utils;

import net.minecraft.world.item.ItemStack;

public class ChapterAlmanac {
    private final String name;
    private final ItemStack icon;

    public ChapterAlmanac(String name, ItemStack icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public ItemStack getIcon() {
        return icon;
    }
}
