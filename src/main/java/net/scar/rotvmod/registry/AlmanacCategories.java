package net.scar.rotvmod.registry;

import net.minecraft.world.item.ItemStack;
import net.scar.rotvmod.utils.CategoryAlmanac;

import java.util.ArrayList;
import java.util.List;

public class AlmanacCategories {
    public static List<CategoryAlmanac> CATEGORYIES = new ArrayList<>();

    public static final CategoryAlmanac CATEGORY_1 = registerCategory("category_1", ModItems.VOID_GEM.get().getDefaultInstance());
    public static final CategoryAlmanac CATEGORY_2 = registerCategory("category_2", ModItems.IMBUED_BRICK.get().getDefaultInstance());
    public static final CategoryAlmanac CATEGORY_3 = registerCategory("category_3", ModItems.SCARLET_FLUX.get().getDefaultInstance());

    private static CategoryAlmanac registerCategory(String name, ItemStack stack) {
        CategoryAlmanac category = new CategoryAlmanac(name, stack);
        CATEGORYIES.add(category);
        return category;
    }

}
