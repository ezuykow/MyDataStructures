package lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

public class MyDoubleLinkedList<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    public MyDoubleLinkedList() {
    }

    public MyDoubleLinkedList(Collection<? extends T> c) {
        this();
        addAll(c);
    }

    public T getFirst() {
        final Node<T> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    public T getLast() {
        final Node<T> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
    }

    public void addFirst(T e) {
        Node<T> newNode = new Node<T>(e, null, first);
        first.prev = newNode;
        first = newNode;
        size++;
    }

    public T removeFirst() {
        Node<T> second = first.next;
        T item = first.item;
        first.next = null;
        first.item = null;
        second.prev = null;
        first = second;
        size--;
        return item;
    }

    public T removeLast() {
        Node<T> previous = last.prev;
        T item = last.item;
        previous.next = null;
        last.prev = null;
        last.item = null;
        last = previous;
        size--;
        return item;
    }

    public boolean contain(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<T> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<T> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    public void remove(Object o){
        Node<T> p, n;

        for (Node<T> x = first; x != null; x = x.next) {
            p = x.prev;
            if (o.equals(x.item)) {
                n = x.next;
                x.item = null;
                x.prev = null;
                x.next = null;
                p.next = n;
                n.prev = p;
                size--;
                break;
            }
        }
    }

    public T get(int index) {
        checkElementIndex(index);

        if (index < (size / 2)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x.item;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x.item;
        }
    }

    public void clear() {
        for (Node<T> x = first; x != null; ) {
            Node<T> next = x.next;
            x.item = null;
            x.prev = null;
            x.next = null;
            x = next;
        }
        size = 0;
        first = null;
        last = null;
    }

    public T set(int index, T e) {
        checkElementIndex(index);

        Node<T> x;
        if (index < (size / 2)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = (size - 1); i > index; i--) {
                x = x.prev;
            }
        }
        T item = x.item;
        x.item = e;
        return item;
    }

    public boolean add(T e) {
        final Node<T> newNode = new Node<T>(e, null, null);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
        return true;
    }

    public void add(int index, T e) {
        checkPositionIndex(index);

        Node<T> n = node(index);
        Node<T> p = n.prev;
        Node<T> newNode = new Node<T>(e, p, n);
        p.next = newNode;
        n.prev = newNode;
        size++;
    }

    public boolean addAll(Collection<? extends T> c) {
        return addAll(size, c);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        Node<T> nextOfIdx, prevOfIdx;
        if (index == size) {
            prevOfIdx = last;
            nextOfIdx = null;
        } else {
            nextOfIdx = node(index);
            prevOfIdx = nextOfIdx.prev;
        }

        for (Object o : a) {
            T e = (T) o;
            Node<T> newNode = new Node<T>(e, prevOfIdx, null);
            if (prevOfIdx == null) {
                first = newNode;
            } else {
                prevOfIdx.next = newNode;
            }
            prevOfIdx = newNode;
        }

        if (nextOfIdx == null) {
            last = prevOfIdx;
        } else {
            prevOfIdx.next = nextOfIdx;
            nextOfIdx.prev = prevOfIdx;
        }

        size += numNew;
        return true;
    }

    public int size() {
        return size;
    }

    public T[] toArray() {
        if (size == 0) {
            return ((T[]) new Object[]{});
        } else {
            Object[] res = new Object[size];
            Node<T> x = first;
            for (int i = 0; i < size; i++) {
                res[i] = x.item;
                x = x.next;
            }
            return ((T[]) res);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    private void checkElementIndex(int index) {
        if ((index < 0) || (index > (size - 1))) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkPositionIndex(int index) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<T> node(int index) {
        Node<T> x;
        if (index < (size / 2)) {
            x = first;
            for (int i = 1; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
