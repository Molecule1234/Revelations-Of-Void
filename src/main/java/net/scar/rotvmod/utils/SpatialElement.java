package net.scar.rotvmod.utils;

// Класс для хранения координат элемента вместе с его размерами
class SpatialElement {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Object item;

    public SpatialElement(int x, int y, int width, int height, Object item) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.item = item;
    }

    public boolean contains(int targetX, int targetY) {
        return targetX >= x && targetX <= x + width &&
                targetY >= y && targetY <= y + height;
    }

    public Object getItem() {
        return item;
    }
}