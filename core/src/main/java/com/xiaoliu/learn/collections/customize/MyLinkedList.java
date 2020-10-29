package com.xiaoliu.learn.collections.customize;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @description: LinkedList
 * @author: liufb
 * @create: 2020/9/22 13:09
 **/
public class MyLinkedList<E> implements MyList<E>, MyQueue<E> {
    private Node<E> first;
    private Node<E> last;
    private int size = 0;
    private int modCount = 0;

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<>(e, last, null);
        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E poll() {
        return first == null ? null : unlinkNode(first);
    }

    @Override
    public E peek() {
        return first == null ? null : first.item;
    }

    @Override
    public E remove() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return unlinkNode(first);
    }

    @Override
    public void add(int i, E e) {
        checkPositionIndex(i);
        Node<E> node = node(i);
        linkBefore(e, node);
    }

    @Override
    public E set(int i, E e) {
        checkElementIndex(i);
        Node<E> node = node(i);
        E oldElement = node.item;
        node.item = e;
        modCount++;
        return oldElement;
    }

    @Override
    public E get(int i) {
        checkElementIndex(i);
        return node(i).item;
    }

    @Override
    public E remove(int i) {
        checkElementIndex(i);
        return unlinkNode(node(i));
    }

    @Override
    public int indexOf(E e) {
        Node<E> node = first;
        if (e == null) {
            for (int i = 0; i < size; i++, node = node.next) {
                if (node.item == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++, node = node.next) {
                if (e.equals(node.item)) {
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
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator(0);
    }

    /**
     * 根据索引获取节点
     *
     * @param i 索引
     * @return 节点
     */
    private Node<E> node(int i) {
        Node<E> node;
        if (i < (size >> 1)) {
            node = first;
            for (int j = 0; j < i; j++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int j = size - 1; j > i; j--) {
                node = node.prev;
            }
            // 0 1 2 3 4 5 6 7 8 9
        }
        return node;
    }

    /**
     * 在node节点前插入一个元素
     *
     * @param e    待插入元素
     * @param node 节点
     */
    private void linkBefore(E e, Node<E> node) {
        Node<E> prev = node.prev;
        Node<E> newNode = new Node<>(e, prev, node);
        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
        node.prev = newNode;
        size++;
        modCount++;
    }

    /**
     * 删除节点
     *
     * @param node 节点
     * @return 节点
     */
    private E unlinkNode(Node<E> node) {
        if (node.prev == null) {
            first = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (node.next == null) {
            last = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        size--;
        modCount++;
        return node.item;
    }

    /**
     * 校验元素索引
     *
     * @param i 索引
     */
    private void checkElementIndex(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
    }

    /**
     * 校验位置索引
     *
     * @param i 索引
     */
    private void checkPositionIndex(int i) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
    }

    /**
     * 节点
     *
     * @param <E>
     */
    static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 迭代器
     */
    private class MyIterator implements Iterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        public MyIterator(int index) {
            next = (index == size) ? null : node(index);
            this.nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public void remove() {
            checkForComodification();
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            // TODO 源码中包含注释的这两段代码，未看懂作用
            /*Node<E> lastNext = lastReturned.next;*/
            unlinkNode(lastReturned);
            /*if (next == lastReturned)
                next = lastNext;
            else*/
            nextIndex--;
            lastReturned = null;
            expectedModCount++;
            // 当前节点为0
            // 0 1 2 3 4 5 6 7 8 9
            // next = 1,nextIndex = 1,lastReturned = 0
            // lastNext = 1
            // 0 2 3 4 5 6 7 8 9
            // nextIndex-- = 0

            // 当前节点为5
            // 0 1 2 3 4 5 6 7 8 9
            // next = 6,nextIndex = 6,lastReturned = 5
            // lastNext = 6
            // 0 1 2 3 4 6 7 8 9
            // nextIndex-- = 5

            // 当前节点为9
            // 0 1 2 3 4 5 6 7 8 9
            // next = null,nextIndex = 10,lastReturned = 9
            // lastNext = null
            // 0 1 2 3 4 5 6 7 8 9
            // nextIndex-- = 9

            // 当前节点为0
            // 0
            // next = null,nextIndex = 1,lastReturned = 0
            // lastNext = null
            // 0
            // nextIndex-- = 0
        }

        private void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
