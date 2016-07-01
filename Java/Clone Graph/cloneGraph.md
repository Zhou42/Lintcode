# 使用的是九章上第二个答案，其实第一个答案思路是一样的。第三个DFS思路并不好，不需要掌握

```java
public class Solution {
    /**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        // 使用hashmap：原来的node -> 新建的node
        // BFS search
        // 先clone node，再clone边
        if (node == null) {
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        // 注意这里可以自己用ArrayList模拟Queue，要掌握自己写stack和queue的能力
        Queue<UndirectedGraphNode> Q = new LinkedList<>();
        UndirectedGraphNode nodeClone = new UndirectedGraphNode(node.label);
        map.put(node, nodeClone);
        Q.offer(node);
        // BFS & clone nodes
        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode head = Q.poll();
                for (UndirectedGraphNode neighbor: head.neighbors) {
                    if (!map.containsKey(neighbor)) {
                        Q.offer(neighbor);
                        map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    }
                }
            }
        }
        // clone links
        for (UndirectedGraphNode originalNode: map.keySet()) {
            for (UndirectedGraphNode neighbor: originalNode.neighbors) {
                map.get(originalNode).neighbors.add(map.get(neighbor));
            }
        }
        
        return nodeClone;
    }
}
```