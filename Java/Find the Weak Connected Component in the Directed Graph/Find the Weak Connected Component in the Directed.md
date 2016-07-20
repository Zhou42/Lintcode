# Find the Weak Connected Component in the Directed Graph
这道题九章的答案没有考虑duplicated label的问题，这里选择直接用DirectedGraphNode构建UnionFind

```java
    class UnionFind {
        HashMap<DirectedGraphNode, DirectedGraphNode> parent = new HashMap<>();
        // duplicate element? 如果有重复数字，应该直接使用DirectedGraphNode标记？
        // compressed find也没法保证，parent构建结束时，每个node的parent就是oldest parent；将来分组的时候，还是得逐个点，找oldest parent
        public DirectedGraphNode findOldestParent(DirectedGraphNode node) {
            while (node != this.parent.get(node)) {
                node = this.parent.get(node);
            }
            return node;
        }
        
        public void union(DirectedGraphNode node1, DirectedGraphNode node2) {
            DirectedGraphNode pa1 = findOldestParent(node1);
            DirectedGraphNode pa2 = findOldestParent(node2);
            this.parent.put(pa1, pa2); // if pa1 == pa2, this change nothing
                                  // if pa1 != pa2, this union the union find
        }
    }
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        // Write your code here
        UnionFind uf = new UnionFind();
        // initialize
        for (DirectedGraphNode node : nodes) {
            uf.parent.put(node, node);
        }
        // iterate through the links
        for (DirectedGraphNode node : nodes) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                uf.union(node, neighbor);
            }
        }
        // update the union find to their oldest parent
        for (DirectedGraphNode node : nodes) {
            uf.parent.put(node, uf.findOldestParent(node));
        }
        
        // 以下可以写成一个单独的函数 最好做到function分离
        // 以下考虑的是得到UnionFind后，如何提取出各个分组
        // 想法很简单，使用一个HashMap，key是每个DirectedGraphNode的oldest parent，value是这个component中的所有node；遍历每个DirectedGraphNode，如果该node的oldest parent在HashMap的key中，则把这个node加入到对应value；如果不在key中，则加入新的(key, value) pair
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        HashMap<DirectedGraphNode, List<DirectedGraphNode>> group = new HashMap<>();
        for (DirectedGraphNode node : nodes) {
            if (!group.containsKey(uf.parent.get(node))) {
                List<DirectedGraphNode> temp = new ArrayList<DirectedGraphNode>();
                temp.add(node);
                group.put(uf.parent.get(node), temp);
            } else {
                group.get(uf.parent.get(node)).add(node);
            }
        }
        // 遍历新建的HashMap，得到最终结果
        for (List<DirectedGraphNode> each : group.values()) {
            List<Integer> temp = new ArrayList<Integer>();
            for (DirectedGraphNode node : each) {
                temp.add(node.label);
            }
            Collections.sort(temp);
            res.add(temp);
        }
        return res;
    }	
```