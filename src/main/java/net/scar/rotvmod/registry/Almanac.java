package net.scar.rotvmod.registry;

import net.minecraft.world.item.Item;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.utils.ButtonAlmanac;
import net.scar.rotvmod.utils.CategoryAlmanac;
import net.scar.rotvmod.utils.ChapterAlmanac;

import java.util.ArrayList;
import java.util.List;

public class Almanac {

    public static List<CategoryAlmanac> CATEGORIES = new ArrayList<>();

    // Categories
    public static final CategoryAlmanac CATEGORY_1 = registerCategory("Category Name 1", ModItems.VOID_GEM.get());
    public static final CategoryAlmanac CATEGORY_2 = registerCategory("Category Name 2", ModItems.RAW_DEORIUM.get());
    public static final CategoryAlmanac CATEGORY_3 = registerCategory("Category Name 3", ModItems.SCARLET_FLUX.get());


    // Chapters
    public static final ChapterAlmanac VOID_LOTUS = addChapter(CATEGORY_1,"Void Lotus", ModBlocks.VOID_FLOWER.get().asItem());
    public static final ChapterAlmanac VOID_LOG = addChapter(CATEGORY_1,"Void Log", ModBlocks.VOID_LOG.get().asItem());
    public static final ChapterAlmanac TEST = addChapter(CATEGORY_1,"Polished Imbued Sto", ModBlocks.POLISHED_IMBUED_STONE_SLAB.get().asItem());
    public static final ChapterAlmanac TEST_1 = addChapter(CATEGORY_1,"Void Extractor", ModBlocks.VOID_EXTRACTOR.get().asItem());
    public static final ChapterAlmanac TEST_2 = addChapter(CATEGORY_1,"Scarlet Flux", ModItems.SCARLET_FLUX.get());
    public static final ChapterAlmanac TEST_3 = addChapter(CATEGORY_1, "Runes 3", ModItems.CLEAR_RUNE.get());
    public static final ChapterAlmanac TEST_4 = addChapter(CATEGORY_1, "Runes 4", ModItems.AURORA_MUSIC_DISC.get());
    public static final ChapterAlmanac TEST_5 = addChapter(CATEGORY_1, "Runes 5", ModItems.IMBUED_BRICK.get());
    public static final ChapterAlmanac TEST_6 = addChapter(CATEGORY_1, "Runes 6", ModItems.NUGGET_OVERFLOWN_DEORIUM.get());
    public static final ChapterAlmanac TEST_7 = addChapter(CATEGORY_1, "Runes 7", ModItems.NUGGET_DEORIUM.get());
    public static final ChapterAlmanac TEST_8 = addChapter(CATEGORY_1, "Runes 8", ModItems.NUGGET_ALTURIUM.get());
    public static final ChapterAlmanac TEST_9 = addChapter(CATEGORY_1, "Runes 9", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_10 = addChapter(CATEGORY_1, "Runes 10", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_11 = addChapter(CATEGORY_1, "Runes 11", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_12 = addChapter(CATEGORY_1, "Runes 12", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_13 = addChapter(CATEGORY_1, "Runes 13", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_14 = addChapter(CATEGORY_1, "Runes 14", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_15 = addChapter(CATEGORY_1, "Runes 15", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_16 = addChapter(CATEGORY_1, "Runes 16", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_17 = addChapter(CATEGORY_1, "Runes 17", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_18 = addChapter(CATEGORY_1, "Runes 18", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_19 = addChapter(CATEGORY_1, "Runes 19", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_20 = addChapter(CATEGORY_1, "Runes 20", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_21 = addChapter(CATEGORY_1, "Runes 21", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_22 = addChapter(CATEGORY_1, "Runes 22", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_23 = addChapter(CATEGORY_1, "Runes 23", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_24 = addChapter(CATEGORY_1, "Runes 24", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_25 = addChapter(CATEGORY_1, "Runes 25", ModItems.ALCHEMIST_ALMANAC.get());


    public static final ChapterAlmanac TEST_26 = addChapter(CATEGORY_2, "Test 20", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_27 = addChapter(CATEGORY_2, "Test 21", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_29 = addChapter(CATEGORY_2, "Test 22", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_33 = addChapter(CATEGORY_2, "Test 23", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_234 = addChapter(CATEGORY_2, "Test 24", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_235 = addChapter(CATEGORY_2, "TEst 25", ModItems.ALCHEMIST_ALMANAC.get());

    public static final ChapterAlmanac TEST_23426 = addChapter(CATEGORY_3, "Test 3434220", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_42427 = addChapter(CATEGORY_3, "Test 244221", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_2423 = addChapter(CATEGORY_3, "Test 22424", ModItems.ALCHEMIST_ALMANAC.get());
    public static final ChapterAlmanac TEST_23425 = addChapter(CATEGORY_3, "TEst 424225", ModItems.ALCHEMIST_ALMANAC.get());

    public static final ButtonAlmanac BUTTON_NEXT = new ButtonAlmanac("next");
    public static final ButtonAlmanac BUTTON_PREV = new ButtonAlmanac("prev");


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

        ChapterAlmanac chapter = new ChapterAlmanac(name, item.getDefaultInstance());
        category.addChapter(chapter);
        return chapter;

    }

}
