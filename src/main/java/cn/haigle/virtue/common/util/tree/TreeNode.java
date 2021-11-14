package cn.haigle.virtue.common.util.tree;

import java.util.List;

/**
 * 树节点
 *
 * @param <T> 主键类型
 * @param <R> 实现类型
 * @author 阮雪峰
 */
public interface TreeNode<T, R> {

    /**
     * 获取树节点ID
     * @return T
     */
    T getId();

    /**
     * 获取树父级ID
     * @return T
     */
    T getParentId();

    /**
     * 子集
     * @param children children
     */
    void setChildren(List<R> children);
}
