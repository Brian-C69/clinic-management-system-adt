// File: adt/ListInterface.java
package adt;

import java.util.Iterator;

public interface ListInterface<T> extends Iterable<T> {
    void add(T newEntry);
    boolean add(int newPosition, T newEntry);
    T remove(int givenPosition);
    void clear();
    boolean replace(int givenPosition, T newEntry);
    T get(int givenPosition);
    boolean contains(T anEntry);
    int size();
    boolean isEmpty();

    @Override
    Iterator<T> iterator();
}
