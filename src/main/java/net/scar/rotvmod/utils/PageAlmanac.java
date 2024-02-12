package net.scar.rotvmod.utils;

import java.util.ArrayList;
import java.util.List;

public class PageAlmanac {
    public List<ChapterAlmanac> chapters = new ArrayList<>();
    public CategoryAlmanac category;

    public PageAlmanac(CategoryAlmanac category) {
        this.category = category;
    }

}
