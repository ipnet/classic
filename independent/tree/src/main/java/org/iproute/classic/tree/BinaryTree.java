package org.iproute.classic.tree;

import java.util.function.BiConsumer;

/**
 * 二叉树的通用方法
 *
 * @author zhuzhenjie
 * @since 2022/8/20
 */
public interface BinaryTree<K extends Comparable<K>, V> {

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    boolean isEmpty();

    /**
     * Size int.
     *
     * @return the int
     */
    int size();

    /**
     * 新增数据
     *
     * @param k the k
     * @param v the v
     */
    void add(K k, V v);

    /**
     * 获取节点上的值
     *
     * @param k the k
     * @return the v
     */
    V get(K k);

    /**
     * 删除节点
     *
     * @param k the k
     */
    void delete(K k);


    /**
     * 深度遍历
     */
    void dfs(BiConsumer<K, V> action);

    /**
     * 广度遍历
     */
    void bfs(BiConsumer<K, V> action);

    /**
     * 前序遍历
     */
    void preOrder(BiConsumer<K, V> action);

    /**
     * 中序遍历
     */
    void inOrder(BiConsumer<K, V> action);

    /**
     * 后序遍历
     */
    void postOrder(BiConsumer<K, V> action);

}
