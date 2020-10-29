package com.xiaoliu.learn.collections.customize;

import java.util.Iterator;

/**
 * @description: List接口
 * @author: liufb
 * @create: 2020/9/21 10:54
 **/
public interface MyList<E> {
    /**
     * 添加元素
     *
     * @param e 元素
     * @return 添加结果
     */
    boolean add(E e);

    /**
     * 添加元素
     *
     * @param i 索引
     * @param e 元素
     */
    void add(int i, E e);

    /**
     * 设置指定索引位置的元素
     *
     * @param i 索引
     * @param e 元素
     * @return 返回旧元素
     */
    E set(int i, E e);

    /**
     * 获取指定索引位置的元素
     *
     * @param i 索引
     * @return 返回元素
     */
    E get(int i);

    /**
     * 删除指定索引位置的元素
     *
     * @param i 索引
     * @return 返回元素
     */
    E remove(int i);

    /**
     * 查找元素所在位置的索引
     *
     * @param e 元素
     * @return 元素所在位置的索引
     */
    int indexOf(E e);

    /**
     * 是否包含指定元素
     *
     * @param e 元素
     * @return 是否包含
     */
    boolean contains(E e);

    /**
     * 获取集合大小
     *
     * @return 集合元素个数
     */
    int size();

    /**
     * 集合是否为空
     *
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * 清空集合
     */
    void clear();

    /**
     * 获取迭代器
     *
     * @return 迭代器
     */
    Iterator<E> iterator();
}
