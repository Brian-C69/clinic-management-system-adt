package adt;

public interface ListInterface<T> {
    void add(T item);
    boolean remove(T item);
    boolean remove(int index);     // NEW - remove by index
    boolean contains(T item);
    T get(int index);
    boolean replace(int index, T newItem); // NEW - replace at index
    boolean isEmpty();
    int size();
    void clear();
}
