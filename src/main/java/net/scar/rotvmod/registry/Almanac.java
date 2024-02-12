package net.scar.rotvmod.registry;

import net.minecraft.world.item.Item;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.utils.CategoryAlmanac;
import net.scar.rotvmod.utils.ChapterAlmanac;
import net.scar.rotvmod.utils.PageAlmanac;

import java.util.ArrayList;
import java.util.List;

public class Almanac {

    public static List<CategoryAlmanac> CATEGORIES = new ArrayList<>();

    // Categories
    public static final CategoryAlmanac CATEGORY_1 = registerCategory("Chapter Name", ModItems.VOID_GEM.get());
    public static final CategoryAlmanac CATEGORY_2 = registerCategory("category_2", ModItems.RAW_DEORIUM.get());
    public static final CategoryAlmanac CATEGORY_3 = registerCategory("category_3", ModItems.SCARLET_FLUX.get());


    // Chapters
    public static final ChapterAlmanac VOID_LOTUS = addChapter(CATEGORY_1,"Void Lotus", ModBlocks.VOID_FLOWER.get().asItem());
    public static final ChapterAlmanac VOID_LOG = addChapter(CATEGORY_1,"Void Log", ModBlocks.VOID_LOG.get().asItem());
    public static final ChapterAlmanac TEST = addChapter(CATEGORY_1,"Polished Imbued Sto", ModBlocks.POLISHED_IMBUED_STONE_SLAB.get().asItem());
    public static final ChapterAlmanac TEST_1 = addChapter(CATEGORY_1,"Void Extractor", ModBlocks.VOID_EXTRACTOR.get().asItem());
    public static final ChapterAlmanac TEST_2 = addChapter(CATEGORY_1,"Scarlet Flux", ModItems.SCARLET_FLUX.get());
    public static final ChapterAlmanac TEST_3 = addChapter(CATEGORY_1, "Runes", ModItems.CLEAR_RUNE.get());
    public static final ChapterAlmanac TEST_4 = addChapter(CATEGORY_1, "Runes", ModItems.AURORA_MUSIC_DISC.get());
    public static final ChapterAlmanac TEST_5 = addChapter(CATEGORY_1, "Runes", ModItems.IMBUED_BRICK.get());
    public static final ChapterAlmanac TEST_6 = addChapter(CATEGORY_1, "Runes", ModItems.NUGGET_OVERFLOWN_DEORIUM.get());
    public static final ChapterAlmanac TEST_7 = addChapter(CATEGORY_1, "Runes", ModItems.NUGGET_DEORIUM.get());
    public static final ChapterAlmanac TEST_8 = addChapter(CATEGORY_1, "Runes", ModItems.NUGGET_ALTURIUM.get());
    public static final ChapterAlmanac TEST_9 = addChapter(CATEGORY_1, "Runes", ModItems.ALCHEMIST_ALMANAC.get());


    private static CategoryAlmanac registerCategory(String name, Item item) {
        CategoryAlmanac category = new CategoryAlmanac(name, item.getDefaultInstance());
        CATEGORIES.add(category);
        return category;
    }

    private static ChapterAlmanac addChapter(CategoryAlmanac category, String name, Item item) {
        if (name.length() > 19) {
            RotvMod.LOGGER.error("Not registry chapter name " + name + " Reason: maximum name length 19 characters.");
            return null;
        }

        if (category.getChapters().size() < 11) {
            ChapterAlmanac chapter = new ChapterAlmanac(name, item.getDefaultInstance());
            category.addChapter(chapter);
            return chapter;
        } else {
            RotvMod.LOGGER.error("Not registry chapter name " + name + " Reason: max category chapter 11 count.");
            return null;
        }
    }

}
