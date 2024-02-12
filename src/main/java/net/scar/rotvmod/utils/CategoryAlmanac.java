package net.scar.rotvmod.utils;

import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CategoryAlmanac extends AlmanacElement {
    private final String name;
    private final List<ChapterAlmanac> chapters;
    private final ItemStack icon;

    public CategoryAlmanac(String name, ItemStack icon, int x, int y, int height, int width) {
        super(x, y, height, width);
        this.name = name;
        this.icon = icon;
        this.chapters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void addChapter(ChapterAlmanac chapter) {
        chapters.add(chapter);
    }

    public List<ChapterAlmanac> getChapters() {
        return chapters;
    }
}
