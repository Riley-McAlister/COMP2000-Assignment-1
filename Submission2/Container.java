package Submission2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Container<T> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public T get(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }

    public Stream<T> stream(){
        return items.stream();
    }

}
