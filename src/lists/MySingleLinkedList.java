package lists;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

public class MySingleLinkedList<T> {
    private int size;
    private Node<T> head;

    public MySingleLinkedList() {}

    public MySingleLinkedList(Collection<? extends T> c) {
        this();
        addAll(c);
    }

    public T getFirst() {
        final Node<T> f = head;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    public T removeFirst() {
        final Node<T> f = head;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkedFirst(f);
    }

    public void addFirst(T e) {
        linkFirst(e);
    }

    public boolean contain(Object o) {
        return indexOf(o) >= 0;
    }

    public int size() {
        return size;
    }

    public boolean add(T e) {
        final Node<T> newNode = new Node<T>(e, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        }
        size++;
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public T[] toArray() {
        if (size == 0) {
            return (T[]) (new Object[]{});
        } else {
            Object[] res = new Object[size];
            Node<T> x = head;
            for (int i = 0; i < size; i++) {
                res[i] = x.item;
                x = x.next;
            }

            return (T[]) res;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.item == null) return index;
                index++;
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.item.equals(o)) return index;
                index++;
            }
        }
        return -1;
    }

    public boolean addAll(Collection<? extends T> collection) {
        return addAll(size, collection);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) return false;
        Node<T> next, prev;
        if (index == size) {
            prev = node(index - 1);
            next = null;
        } else if (index == 0) {
            prev = null;
            next = head;
        } else {
            prev = node(index);
            next = prev.next;
        }

        final Node<T> firstNew = new Node<T>((T) a[0], null);
        Node<T> temp = firstNew;
        for (int i = 1; i < numNew; i++) {
            temp.next = new Node<T>((T) a[i], null);
            temp = temp.next;
        }
        if (prev == null) {
            temp.next = head;
            head = firstNew;
        } else {
            prev.next = firstNew;
            temp.next = next;
        }
        size += numNew;
        return true;
    }

    public T get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    public void clear() {
        for (Node<T> x = head; x != null; ) {
            Node<T> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }
        size = 0;
        head = null;
    }

    public T set(int index, T e) {
        checkElementIndex(index);
        Node<T> target = node(index);
        T oldElement = target.item;
        target.item = e;
        return oldElement;
    }

    public void add(int index, T e) {
        checkPositionIndex(index);

        Node<T> newNode = new Node<T>(e, null);

        if (index == size) {
            node(index - 1).next = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> temp = node(index - 1);
            newNode.next = temp.next;
            temp.next = newNode;
        }
        size++;
    }

    public T remove(int index) {
        checkElementIndex(index);

        Node<T> target = node(index);
        T e = target.item;
        node(index - 1).next = target.next;
        size--;
        return e;
    }

    private Node<T> node(int index) {
        Node<T> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isElementIndex(int index) {
        return ((index >= 0) && (index < size));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isPositionIndex(int index) {
        return ((index >= 0) && (index <= size));
    }

    private void unlink(Node<T> e) {
        if (e.equals(head)) removeFirst();
        else {
            for (Node<T> x = head; x.next != null; x = x.next) {
                if (e.equals(x.next)) {
                    final Node<T> next = x.next.next;
                    x.next.next = null;
                    x.next.item = null;
                    x.next = next;
                    size--;
                    break;
                }
            }
        }
    }

    private void linkFirst(T e) {
        final Node<T> f = head;
        head = new Node<T>(e, f);
        size++;
    }

    private T unlinkedFirst(Node<T> f) {
        final T element = f.item;
        final Node<T> next = f.next;
        f.next = null;
        f.item = null;
        head = next;
        size--;
        return element;
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}