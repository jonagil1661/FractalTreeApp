package datastructures;

public interface Stack<E> {
    boolean isEmpty();
    E peek();
    void push(E item);
    E pop();
    int size();
}