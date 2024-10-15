package datastructures;

import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E> {
    private Node head;
    private Node tail;
    private int size; // Variable to keep track of the queue's size

    private class Node {
        E data;
        Node next;
        int size = 0; // Initialize size to 0


        Node(E data) {
            this.data = data;
        }
    }

    public LinkedListQueue() {
        // constructor is optional all of these are
        //  initialized automatically w/out it.
        //  AN Explicit constructor clearly indicates that
        //  head and tail are initialized to null and
        //  size to 0 when an instance of
        //  LinkedListQueue is created

        head = null;
        tail = null;
        size = 0; // Initialize size to 0
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    @Override
    public void add(E item) {
        Node newNode = new Node(item);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            head = tail;
        }
        size++;  // Increment the size
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;  // Decrement the size
        return data;
    }
    @Override
    public int size() {
        return size;  // Return the current size of the queue
    }


    public void printQueue() {
        Node current = head; // Assuming 'heads' is 'front' of your queue
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

}
