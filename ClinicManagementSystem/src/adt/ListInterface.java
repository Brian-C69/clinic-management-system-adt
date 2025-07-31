package adt;

public interface ListInterface<T> {
    void add(T item);
    boolean remove(int item);
    boolean contains(T item);
    T get(int index);
    int size();
    boolean isEmpty();
    void clear();
}

