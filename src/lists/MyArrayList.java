package lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class MyArrayList<T> {
    //Default initial capacity
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private int size;
    private Object[] elementData;

    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Wrong initial capacity: " + initialCapacity);
        }
    }

    public MyArrayList(Collection<? extends T> collection) {
        Object[] a = collection.toArray();
        if ((size = a.length) != 0) {
            if (collection.getClass() == ArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            elementData = new Object[DEFAULT_CAPACITY];
        }
    }

    public void trimToSize() {
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? new Object[]{}
                    : Arrays.copyOf(elementData, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if ((minCapacity > elementData.length) &&
                !((elementData.length == 0) &&
                        (minCapacity <= DEFAULT_CAPACITY))) {
            grow(minCapacity);
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            if (elementData.length == 0)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0)
                throw new IllegalArgumentException();
            return minCapacity;
        }

        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0)
            throw new IllegalArgumentException();
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean contains(Object o) {
        return (indexOf(o) >= 0);
    }

    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    int indexOfRange(Object o, int start, int end) {
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (elementData[i] == null)
                    return i;
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(elementData[i]))
                    return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, size);
    }

    int lastIndexOfRange(Object o, int start, int end) {
        if (o == null) {
            for (int i = end - 1; i >= start; i--) {
                if (elementData[i] == null)
                    return i;
            }
        } else {
            for (int i = end - 1; i >= start ; i--) {
                if (o.equals(elementData[i]))
                    return i;
            }
        }
        return -1;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = (T) elementData[index];
        elementData[index] = newValue;
        return oldValue;
    }

    public boolean add(T element) {
        if (size == elementData.length)
            elementData = grow();
        elementData[size] = element;
        size++;
        return true;
    }

    public void add(int index, T e) {

        if ((index > size) || (index < 0))
            throw new IndexOutOfBoundsException();
        if (size == elementData.length)
            elementData = grow();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        size++;
    }

    public T remove(int index) {
        Objects.checkIndex(index, size);
        T oldValue = (T) elementData[index];
        fastRemove(elementData, index);
        return oldValue;
    }

    public boolean remove(Object o) {
        int i = 0;

        found: {
            if (o == null) {
                for (; i < size; i++) {
                    if (elementData[i] == null)
                        break found;
                }
            } else {
                for (; i < size; i++) {
                    if (elementData[i].equals(o))
                        break found;
                }
            }
            return false;
        }

        fastRemove(elementData, i);
        return true;
    }

    private void fastRemove(Object[] es, int index) {
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(es, index + 1, es, index, newSize - index);
        elementData[size = newSize] = null;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
    }

    public String toString() {
        return Arrays.toString(toArray());
    }
}
