/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author Bernard
 */
public interface ListInterface<T> {
    void add(T item);
    boolean remove(T item);
    boolean contains(T item);
    T get(int index);
    int size();
    boolean isEmpty();
    void clear();
}

