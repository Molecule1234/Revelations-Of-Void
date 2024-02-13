package net.scar.rotvmod.utils;

import java.util.ArrayList;
import java.util.List;


// Класс, представляющий узел квадродерева
class QuadTreeNode {
    private int x, y, width, height;
    private List<SpatialElement> items;
    private QuadTreeNode[] children;

    public QuadTreeNode(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.items = new ArrayList<>();
        this.children = new QuadTreeNode[4];
    }

    // Метод для добавления элемента в узел
    public void add(Object item, int x, int y, int width, int height) {
        items.add(new SpatialElement(x, y, width, height, item));
    }

    // Метод для поиска элементов в узле и его потомках
    public List<Object> search(int targetX, int targetY) {
        List<Object> result = new ArrayList<>();

        for (SpatialElement element : items) {
            if (element.contains(targetX, targetY)) {
                result.add(element.getItem());
            }
        }

        for (QuadTreeNode child : children) {
            if (child != null) {
                result.addAll(child.search(targetX, targetY));
            }
        }

        return result;
    }


    // Метод для удаления всех элементов класса ChapterAlmanac
    public void clearChapterAlmanacs() {
        items.removeIf(element -> element.getItem() instanceof ChapterAlmanac);
        for (QuadTreeNode child : children) {
            if (child != null) {
                child.clearChapterAlmanacs();
            }
        }
    }

}