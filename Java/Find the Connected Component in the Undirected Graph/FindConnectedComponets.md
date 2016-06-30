# 关于array排序，与ArrayList等collection的排序
1. 对于 array， 使用Arrays.sort()
2. 对于ArrayList，使用Collections.sort()

Collections.sort()的一个参考

http://blog.csdn.net/janronehoo/article/details/8746447

#Code

```java
/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here
        // 由一个node BFS遍历所有的连通的node，单独一个函数
        // Hashset记录是否已经遍历过
        // 有待尝试union check方法！！
        Set<UndirectedGraphNode> set = new HashSet<>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (UndirectedGraphNode item: nodes) {
            if (!set.contains(item)) {
                List<Integer> temp = findConnectedComponet(item, set);
                Collections.sort(temp);
                res.add(temp);
            }
        }
        return res;
    }
    
    // BFS 由一个node找到所有的连通分量；这里可以使用返回result的方式，也可当作参数传递result
    private List<Integer> findConnectedComponet(UndirectedGraphNode node, Set<UndirectedGraphNode> set) {
        Queue<UndirectedGraphNode> Q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        Q.offer(node);
        set.add(node);
        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode head = Q.poll();
                // 进入Queue的都是unique的
                // 把head的value加入res
                res.add(head.label);
                for (UndirectedGraphNode item: head.neighbors) {
                    if (!set.contains(item)) {
                        set.add(item);
                        Q.offer(item);
                    }
                }
            }
        }
        return res;
    }
}
```
