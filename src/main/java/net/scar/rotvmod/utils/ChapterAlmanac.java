package net.scar.rotvmod.utils;

import net.minecraft.world.item.ItemStack;

public class ChapterAlmanac {
    public ItemStack icon;
    public String key;

    public ChapterAlmanac(String name, ItemStack icon) {
        this.icon = icon;
        this.key = name;
    }

}
