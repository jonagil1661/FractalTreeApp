package datastructures;

public interface Queue<E> {
    void add(E item); // Enqueues an item
    E remove(); // Dequeues and returns the head of the queue
    E peek(); // Returns the head without removing it
    boolean isEmpty(); // Checks if the queue is empty
    int size(); // Returns the number of items in the queue
}
