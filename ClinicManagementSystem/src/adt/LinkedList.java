// File: adt/LinkedList.java
package adt;

public class LinkedList<T> implements ListInterface<T> {
    private Node<T> firstNode;
    private int numberOfEntries;

    private static class Node<T> {
        private T data;
        private Node<T> next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public void add(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        } else {
            Node<T> lastNode = getNodeAt(numberOfEntries - 1);
            lastNode.next = newNode;
        }
        numberOfEntries++;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        if ((newPosition >= 0) && (newPosition <= numberOfEntries)) {
            Node<T> newNode = new Node<>(newEntry);
            if (newPosition == 0) {
                newNode.next = firstNode;
                firstNode = newNode;
            } else {
                Node<T> nodeBefore = getNodeAt(newPosition - 1);
                newNode.next = nodeBefore.next;
                nodeBefore.next = newNode;
            }
            numberOfEntries++;
            return true;
        }
        return false;
    }

    @Override
    public T remove(int givenPosition) {
        if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {
            T result = null;
            if (givenPosition == 0) {
                result = firstNode.data;
                firstNode = firstNode.next;
            } else {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeToRemove = nodeBefore.next;
                result = nodeToRemove.data;
                nodeBefore.next = nodeToRemove.next;
            }
            numberOfEntries--;
            return result;
        }
        return null;
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {
            Node<T> desiredNode = getNodeAt(givenPosition);
            desiredNode.data = newEntry;
            return true;
        }
        return false;
    }

    @Override
    public T get(int givenPosition) {
        if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {
            return getNodeAt(givenPosition).data;
        }
        return null;
    }

    @Override
    public boolean contains(T anEntry) {
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.data.equals(anEntry)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    private Node<T> getNodeAt(int givenPosition) {
        Node<T> currentNode = firstNode;
        for (int i = 0; i < givenPosition; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }
}
