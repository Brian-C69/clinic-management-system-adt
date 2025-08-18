package adt;

import java.util.Iterator;

public class SortedArrayList<T extends Comparable<? super T>> implements ListInterface<T>{
    private T[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 20;

    public SortedArrayList() {
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        if (size >= array.length) {
            T[] newArray = (T[]) new Comparable[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public void add(T newEntry) {
        ensureCapacity();
        int i = 0;
        while (i < size && array[i].compareTo(newEntry) < 0) {
            i++;
        }
        for (int j = size; j > i; j--) {
            array[j] = array[j - 1];
        }
        array[i] = newEntry;
        size++;
    }

    @Override
    public boolean remove(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[size - 1] = null;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        return null;
    }

    @Override
    public boolean contains(T anEntry) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(anEntry)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            public boolean hasNext() {
                return currentIndex < size;
            }
            public T next() {
                return array[currentIndex++];
            }
        };
    }

    @Override
    public boolean remove(T item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean replace(int index, T newItem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
