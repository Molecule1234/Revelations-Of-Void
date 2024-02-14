package net.scar.rotvmod.utils;

import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ChapterAlmanac {
    private final String name;
    private final ItemStack icon;
    private final List<PageAlmanac> pages;

    public ChapterAlmanac(String name, ItemStack icon) {
        this.name = name;
        this.icon = icon;
        this.pages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public List<PageAlmanac> getPages() {
        return pages;
    }

    public PageAlmanac addPage(PageAlmanac page) {
        pages.add(page);
        return page;
    }

}
