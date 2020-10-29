package com.xiaoliu.learn.collections.customize;

/**
 * @description: 队列接口
 * @author: liufb
 * @create: 2020/9/22 13:02
 **/
public interface MyQueue<E> {
    /**
     * 添加元素
     *
     * @param e 元素
     * @return 添加结果
     */
    boolean add(E e);

    /**
     * 入队
     *
     * @param e 元素
     * @return 入队结果
     */
    boolean offer(E e);

    /**
     * 出队
     *
     * @return 出队元素
     */
    E poll();

    /**
     * 瞄一眼第一个元素
     *
     * @return 第一个元素
     */
    E peek();

    /**
     * 删除元素
     *
     * @return 被删除的元素
     */
    E remove();
}
