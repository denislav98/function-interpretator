package function.interpretator.structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
            next = null;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int count;

    @Override
    public boolean add(E o) {
        Node<E> node = new Node<>(o);

        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }

        count++;
        return true;
    }

    @Override
    public E get(int index) {
        int n = indexOf(head); // count-1 actually
        Node<E> current = head;
        while (n > index) {
            --n;
            current = current.getNext();
        }
        return current.getValue();
    }

    private int indexOf(Node<E> link) {
        if (link == null) {
            return -1;
        }
        return 1 + indexOf(link.getNext());
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }



    @Override
    public E set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}