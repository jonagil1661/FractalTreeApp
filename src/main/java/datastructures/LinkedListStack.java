package datastructures;

import java.util.EmptyStackException;


// This implementation of LinkedListStack<E> is a
// // progression from the LinkedListStack class we developed earlier (this
// // is the LinkedList version).
// Key differences to note:
// 1. Here we implemented the Stack<E> interface, ensuring our class
//          adheres to a defined contract for stack operations.
// 2. Instead of using 'int data', this class is generic ('E'),
//        allowing it to be more versatile and handle different data types.
// 3. Exception handling has been refined and improved in this implementation.
//      Pay attention to how we handle scenenrios
//       like popping from an empty stack.


public class LinkedListStack<E> implements Stack<E> {
    private Node head;  // sometimes called top
    private int size;

    private class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return head.data;
    }

    @Override
    public void push(E item) {
        Node newNode = new Node(item);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E data = head.data;
        head = head.next;
        size--;
        return data;
    }

    @Override
    public int size() {
        return size;
    }

    public void printStack() {
        Node current = head; // Assuming 'head' is also  'top' of your stack
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
