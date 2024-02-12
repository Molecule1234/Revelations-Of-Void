package net.scar.rotvmod.utils;

public class AlmanacElement {
    private int x;
    private int y;
    private int height;
    private int width;

    public AlmanacElement(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isWithinBounds(int clickX, int clickY) {
        return clickX >= this.x && clickX <= this.x + this.width && clickY >= this.y && clickY <= this.y + height;
    }

}
