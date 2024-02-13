package net.scar.rotvmod.utils;

import java.util.List;

// Класс для хранения и поиска элементов с использованием квадродерева
public class SpatialIndex {
    private QuadTreeNode root;

    public SpatialIndex(int width, int height) {
        root = new QuadTreeNode(0, 0, width, height);
    }

    // Метод для добавления элемента в индекс
    public void add(Object item, int x, int y, int width, int height) {
        root.add(item, x, y, width, height);
    }

    // Метод для поиска элементов в определенной области
    public List<Object> search(int targetX, int targetY) {
        return root.search(targetX, targetY);
    }

    // Метод для удаления всех элементов класса ChapterAlmanac
    public void clearChapterAlmanacs() {
        root.clearChapterAlmanacs();
    }
}