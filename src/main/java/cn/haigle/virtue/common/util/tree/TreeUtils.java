package cn.haigle.virtue.common.util.tree;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 阮雪峰
 */
public class TreeUtils {
    private TreeUtils() {
    }

    /**
     * 平铺转树形
     *
     * @param list 平铺list
     * @param <T>  树结构主键泛型
     * @param <R>  树结构泛型
     * @return List<T>
     */
    public static <T, R extends TreeNode<T, R>> List<R> build(List<R> list) {
        List<R> roots = list.stream().filter(item -> item.getParentId() == null || item.getParentId().equals(0L)).collect(Collectors.toList());
        Map<T, List<R>> groupByParentMap = list.stream().filter(item -> item.getParentId() != null).collect(Collectors.groupingBy(TreeNode::getParentId));
        findChildren(roots, groupByParentMap);
        return roots;
    }

    /**
     * 查找子集
     *
     * @param treeList         treeList
     * @param groupByParentMap 父节点分组
     * @param <T>              树结构主键泛型
     * @param <R>              树结构泛型
     */
    private static <T, R extends TreeNode<T, R>> void findChildren(List<R> treeList, Map<T, List<R>> groupByParentMap) {
        for (R root : treeList) {
            if (groupByParentMap.containsKey(root.getId())) {
                List<R> children = groupByParentMap.get(root.getId());
                root.setChildren(children);
                findChildren(children, groupByParentMap);
            }
        }
    }
}
