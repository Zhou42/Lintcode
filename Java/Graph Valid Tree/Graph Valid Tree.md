#  Graph Valid Tree
注意这里是无向图，所以只需要：1. 连通 2.无环
也可以像九章答案里那样，判断边数为n - 1，也就是只有root结点没有入度，其他入度为1，共n - 1条边。 再判断是否有环。

- 需要补充树和图的基础知识
- 以后学写Union find类的实现

```java
    public boolean validTree(int n, int[][] edges) {
        // Write your code here
        
        // Yang's own algorithm
        // 对于有向图：树上 每个node就一个parent 图不是
        // 注意这里是无向图，所以只需要：1. 连通 2.无环
        // 需要补充树和图的基础知识
        
        // 遍历每条边，如果某一个edge链接的两个component，已经是connected component，则加上这条边，会形成环。 
        // 由于不存在重复边，当考虑某一条边时，这条边不可能存在，则如果两个分量连通，必然存在其他路径连通，因而形成环。
        
        // 综上，先check每条边；再check 整个图是否connected
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; //  初始时，连通分量只是自己
        }
        
        for (int j = 0; j < edges.length; j++) {
            int topParent1 = findTopParent(parent, edges[j][0]);
            int topParent2 = findTopParent(parent, edges[j][1]);
            if (topParent1 == topParent2) { // 一旦发现 边的两个顶点已经连通
                return false;
            }
            parent[topParent1] = topParent2;
        }
        boolean res = true;
        for (int i = 1; i < n; i++) { // 证明连通
            res = res && (findTopParent(parent, i - 1) == findTopParent(parent, i)); 
        }
        return res;
    }
    
    public int findTopParent(int[] parent, int m) {
        int curt = m;
        while (parent[curt] != curt) {
            curt = parent[curt];
        }
        return curt; // curt 的parent就是自己 故是最大的大哥!
    }
    
    // 也可以像九章的做法，定义一个Union Find类
```
