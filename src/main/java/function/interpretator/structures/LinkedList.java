package function.interpretator.structures;

public class LinkedList<E> {

    Node<E> head;
    int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public LinkedList(E e) {
        head = new Node<>(e);
        size = 1;
    }

    public void add(E e) {
        if (head == null) {
            head = new Node<>(e);
        } else {
            Node<E> current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<E>(e);
        }
        size++;
    }

    public E get(int n) {
        if (n > size) {
            return null;
        }
        Node<E> current = head;
        for (int i = 1; i < n; i++) {
            current = current.next;
        }
        return current.e;
    }

    public void remove(E value) {
        Node<E> prev = null;
        Node<E> cur = head;
        while (cur != null) {
            if (cur.e.equals(value)) {
                // Found something to remove
                if (prev == null) {
                    head = cur.next;
                } else {
                    prev.next = cur.next;
                }
            }
            if (cur.next != head) {
                prev = cur;
            }
            cur = cur.next;
        }
    }

    public int getSize() {
        return size;
    }

    private static class Node<E> {
        private final E e;
        private Node<E> next;

        @SuppressWarnings("unused")
        Node(E e, Node<E> n) {
            this.e = e;
            this.next = n;
        }

        Node(E e) {
            this.e = e;
        }
    }
}