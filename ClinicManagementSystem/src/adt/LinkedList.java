package adt;

public class LinkedList<T> implements ListInterface<T> {
    private Node<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) head = newNode;
        else {
            Node<T> cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = newNode;
        }
        size++;
    }

    @Override
    public boolean remove(T item) {
        if (head == null) return false;
        if (head.data.equals(item)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> cur = head;
        while (cur.next != null && !cur.next.data.equals(item)) cur = cur.next;
        if (cur.next != null) {
            cur.next = cur.next.next;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size) return false;
        if (index == 0) head = head.next;
        else {
            Node<T> cur = head;
            for (int i = 0; i < index - 1; i++) cur = cur.next;
            cur.next = cur.next.next;
        }
        size--;
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        Node<T> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.data;
    }
    
    @Override
    public boolean contains(T item) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }




    @Override
    public boolean replace(int index, T newItem) {
        if (index < 0 || index >= size) return false;
        Node<T> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        cur.data = newItem;
        return true;
    }

    @Override public boolean isEmpty() { return size == 0; }
    @Override public int size() { return size; }
    @Override public void clear() { head = null; size = 0; }
}
