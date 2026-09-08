package Prototype;

import java.util.ArrayList;
import java.util.List;

public class Container<Animal> {
    private List<Animal> items = new ArrayList<>();

    public void add(Animal item) {
        items.add(item);
    }

    public void remove(Animal item) {
        items.remove(item);
    }

    public Animal get(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }

}
