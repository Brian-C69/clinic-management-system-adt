package adt;

public class LinkedQueue<T> implements QueueInterface<T> {
    private Node<T> front, rear;
    private int size;

    public LinkedQueue() {
        front = rear = null;
        size = 0;
    }

    @Override
    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) front = rear = newNode;
        else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) return null;
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return data;
    }
    
     @Override
    public T peek() {
        return isEmpty() ? null : front.data;
    }

    public T getFront() { return isEmpty() ? null : front.data; }
    @Override public boolean isEmpty() { return size == 0; }
    @Override public int size() { return size; }
    @Override public void clear() { front = rear = null; size = 0; }
}
