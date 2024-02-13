package net.scar.rotvmod.utils;

import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CategoryAlmanac {
    private final String name;
    private final List<ChapterAlmanac> chapters;
    private final ItemStack icon;

    public CategoryAlmanac(String name, ItemStack icon) {
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


    public List<ChapterAlmanac> getChaptersPage(int pageNumber) {
        List<ChapterAlmanac> result = new ArrayList<>();

        // Максимальное количество глав на странице (для левой и правой части)
        int maxChaptersPerPage = 12;

        // Количество глав на первой странице левой части
        int firstPageLeftChapters = 5;

        int totalLeftChapters = chapters.size() - firstPageLeftChapters;
        int totalPages = (int) Math.ceil((double) totalLeftChapters / 6) + 1;

        // Проверка на допустимость номера страницы
        if (pageNumber < 1 || pageNumber > totalPages) {
            return result;
        }

        // Если это первая страница и левая часть
        if (pageNumber == 1) {
            for (int i = 0; i < firstPageLeftChapters && i < chapters.size(); i++) {
                result.add(chapters.get(i));
            }
        } else {
            int startIndex = (pageNumber - 2) * 6 + firstPageLeftChapters;
            int endIndex = Math.min(startIndex + 6, chapters.size());
            for (int i = startIndex; i < endIndex; i++) {
                result.add(chapters.get(i));
            }
        }

        return result;
    }
}
