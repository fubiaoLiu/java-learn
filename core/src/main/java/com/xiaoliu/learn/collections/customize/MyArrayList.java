package com.xiaoliu.learn.collections.customize;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @description: ArrayList
 * @author: liufb
 * @create: 2020/9/21 10:54
 **/
public class MyArrayList<E> implements MyList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private E[] elementArray;
    private int size = 0;
    private int modCount = 0;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        elementArray = (E[]) new Object[Math.max(DEFAULT_CAPACITY, capacity)];
    }

    @Override
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementArray[size++] = e;
        modCount++;
        return true;
    }

    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds.");
        }
        ensureCapacityInternal(size + 1);
        if (i != size - 1) {
            System.arraycopy(elementArray, i, elementArray, i + 1, size - i);
        }
        elementArray[i] = e;
        size++;
        modCount++;
    }

    @Override
    public E set(int i, E e) {
        checkIndex(i);
        E oldElement = elementArray[i];
        elementArray[i] = e;
        modCount++;
        return oldElement;
    }

    @Override
    public E get(int i) {
        checkIndex(i);
        return elementArray[i];
    }

    @Override
    public E remove(int i) {
        checkIndex(i);
        E element = elementArray[i];
        if (i != size - 1) {
            System.arraycopy(elementArray, i + 1, elementArray, i, size - i - 1);
        }
        elementArray[--size] = null;
        modCount++;
        return element;
    }

    @Override
    public int indexOf(E e) {
        if (e == null) {
            for (int i = 0; i < elementArray.length; i++) {
                if (elementArray[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < elementArray.length; i++) {
                if (e.equals(elementArray[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(elementArray, null);
        size = 0;
        modCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    /**
     * 确保内部数组容量
     *
     * @param minCapacity 需要的容量
     */
    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity > elementArray.length) {
            modCount++;
            grow(calculateCapacity(minCapacity));
        }
    }

    /**
     * 计算扩容后容量大小
     *
     * @param minCapacity 所需最小容量
     * @return 扩容后容量
     */
    private int calculateCapacity(int minCapacity) {
        int oldCapacity = elementArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        if (newCapacity > MAX_ARRAY_SIZE) {
            newCapacity = minCapacity > MAX_ARRAY_SIZE ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
        }
        return newCapacity;
    }

    /**
     * 扩容
     *
     * @param newCapacity 新容量
     */
    private void grow(int newCapacity) {
        elementArray = Arrays.copyOf(elementArray, newCapacity);
    }

    /**
     * 校验数组下标
     *
     * @param i 下标
     */
    private void checkIndex(int i) {
        if (i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds.");
        }
    }

    class MyIterator implements Iterator<E> {
        int cursor;
        int expectModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            checkForComodification();
            return elementArray[cursor++];
        }

        @Override
        public void remove() {
            checkForComodification();
            MyArrayList.this.remove(cursor);
            expectModCount = modCount;
        }

        private void checkForComodification() {
            if (expectModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
