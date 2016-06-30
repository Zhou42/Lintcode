```java
/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { 
 *         label = x;
 *         neighbors = new ArrayList<UndirectedGraphNode>(); 
 *     }
 * };
 */
public class Solution {
    /**
     * @param graph a list of Undirected graph node
     * @param s, t two Undirected graph nodes
     * @return an integer
     */
    public int sixDegrees(List<UndirectedGraphNode> graph,
                          UndirectedGraphNode s,
                          UndirectedGraphNode t) {
        // Write your code here
        // 分层遍历，用hashset记录是否包含
        if (graph == null || s == null || t == null) {
            return -1;
        }
        Set<UndirectedGraphNode> set = new HashSet<>();
        Queue<UndirectedGraphNode> Q = new LinkedList<>();
        int levelCounter = 0;
        Q.offer(s);
        set.add(s);
        while (!Q.isEmpty()) {
            int size = Q.size();
            // 每一层的遍历
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode head = Q.poll();
                // check if t is found
                if (head.label == t.label) {
                    return levelCounter;
                }
                // 把head 的 neighbour放进去,同时check是否找到了t
                for (UndirectedGraphNode item: head.neighbors) {
                    if (!set.contains(item)) {
                        Q.offer(item);
                        set.add(item);
                    }
                }
            }
            levelCounter++;
        }
        return -1;
        
    }
}
```