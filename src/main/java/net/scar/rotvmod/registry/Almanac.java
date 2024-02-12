package net.scar.rotvmod.registry;

import net.minecraft.world.item.Item;
import net.scar.rotvmod.utils.CategoryAlmanac;
import net.scar.rotvmod.utils.ChapterAlmanac;

import java.util.ArrayList;
import java.util.List;

public class Almanac {

    public static List<CategoryAlmanac> CATEGORIES = new ArrayList<>();
    public static List<ChapterAlmanac> CHAPTERS = new ArrayList<>();


    // Categories
    public static final CategoryAlmanac CATEGORY_1 = registerCategory("category_1", ModItems.VOID_GEM.get());
    public static final CategoryAlmanac CATEGORY_2 = registerCategory("category_2", ModItems.RAW_DEORIUM.get());
    public static final CategoryAlmanac CATEGORY_3 = registerCategory("category_3", ModItems.SCARLET_FLUX.get());


    // Chapters
    public static final ChapterAlmanac CHAPTER_1 = registerChapter("chapter_1", ModItems.VOID_GEM.get());




    private static CategoryAlmanac registerCategory(String name, Item item) {
        CategoryAlmanac category = new CategoryAlmanac(name, item.getDefaultInstance());
        CATEGORIES.add(category);
        return category;
    }

    private static ChapterAlmanac registerChapter(String name, Item item) {
        ChapterAlmanac chapter = new ChapterAlmanac(name, item.getDefaultInstance());
        CHAPTERS.add(chapter);
        return chapter;
    }

}
