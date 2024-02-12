package net.scar.rotvmod.utils;

import java.util.ArrayList;
import java.util.List;

public class ElementsController {
    private final List<AlmanacElement> elements;

    public ElementsController() {
        elements = new ArrayList<>();
    }

    public void addElement(AlmanacElement element) {
        elements.add(element);
    }

    public List<AlmanacElement> getElements() {
        return elements;
    }

    public AlmanacElement findElementWithinBounds(int clickX, int clickY) {
        for (AlmanacElement element : elements) {
            if (element.isWithinBounds(clickX, clickY)) {
                return element;
            }
        }
        return null; // Если ни один элемент не найден в пределах клика
    }
}