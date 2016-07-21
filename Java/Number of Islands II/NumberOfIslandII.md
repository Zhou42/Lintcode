# Number of Islands II
UnionFind 思路上有些小技巧

```java
    class UnionFind {
        HashMap<Integer, Integer> parent = new HashMap<>();
        public int findParent(int x) {
            while (x != this.parent.get(x)) {
                // System.out.println(x);
                x = this.parent.get(x);
            }
            return x;
        }
        public void union(int x, int y) {
            // System.out.println(x);
            int x_pa = findParent(x);
            int y_pa = findParent(y);
            if (x_pa != y_pa) {
                this.parent.put(x_pa, y_pa);
            }
        }
    }
    // 跟九章标记island的label方式不一样，这样更简洁，只标记island
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // Write your code here
        if (operators == null) {
            return new ArrayList<Integer>();
        }
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};
        UnionFind uf = new UnionFind();
        int[][] map = new int[n][m];
        List<Integer> res = new ArrayList<Integer>();
        int label = 1;
        int islandNum = 0;
        for (Point p : operators) {
            // if (hasLand(map, p.x - 1, p.y, n, m) == 0 && hasLand(map, p.x + 1, p.y, n, m) == 0  && hasLand(map, p.x, p.y - 1, n, m) == 0 && hasLand(map, p.x, p.y + 1, n, m) == 0) {
            //     // no land around
            //     map[p.x][p.y] = label;
            //     uf.parent.put(label, label);
            //     label++;
            // } else {
            //     // acquire the list of neighbours
            //     List<Integer> neighbors = new ArrayList<>();
            //     if (hasLand(map, p.x - 1, p.y, n, m) != 0) {
            //         neighbors.add(map[p.x - 1][p.y]);
            //     }
            //     if (hasLand(map, p.x + 1, p.y, n, m) != 0) {
            //         neighbors.add(map[p.x + 1][p.y]);
            //     }
            //     if (hasLand(map, p.x, p.y - 1, n, m) != 0) {
            //         neighbors.add(map[p.x][p.y - 1]);
            //     }
            //     if (hasLand(map, p.x, p.y + 1, n, m) != 0) {
            //         neighbors.add(map[p.x][p.y + 1]);
            //     }
            //     if (neighbors.size() == 1) {
            //         map[p.x][p.y] = neighbors.get(0);
            //     } else {
            //         // merge 
            //         map[p.x][p.y] = neighbors.get(0);
            //         for (int i = 1; i < neighbors.size(); i++) {
            //             uf.union(neighbors.get(0), neighbors.get(i));
            //         }
            //     }
            // }
            
            // 以上的程序，没发做到实时计数islandNum的目的
            // 这样思考这个问题；无论哪个point，一开始都认为加入了一个new island；随后这个island跟周围的island *逐个*融合
            // 每加入一个island则islandNum++, 每融合一次，则islandNum--
            map[p.x][p.y] = label;
            uf.parent.put(label, label);
            label++;
            islandNum++;
            for (int t = 0; t < 4; t++) {
                if (hasLand(map, p.x + dx[t], p.y + dy[t], n, m) != 0) {
                    // 如果parent不同，说明还不是一个island，可以融合
                    if (uf.findParent(map[p.x + dx[t]][p.y + dy[t]]) != uf.findParent(map[p.x][p.y])) {
                        uf.union(map[p.x][p.y], map[p.x + dx[t]][p.y + dy[t]]);
                        // 一旦merge则islandNum--
                        islandNum--;
                    }
                }
            }
            res.add(islandNum);
        }
        return res;
    }
    // 只需关注周围是否有land 有island就可能可以merge
    public int hasLand(int[][] map, int i, int j, int n, int m) {
        // check whether a neighbor region has land and return the label
        if (i < 0 || i > n - 1 || j < 0 || j > m - 1 || map[i][j] == 0) {
            return 0; // no land
        }
        return map[i][j];
    }
    // 这样会导致超时 不能现数island的数目
    // public int islandNum(UnionFind uf) {
    //     // update the hashmap
    //     for (Integer key : uf.parent.keySet()) {
    //         uf.parent.put(key, uf.findParent(key));
    //     }
    //     Set<Integer> island = new HashSet();
    //     for (Integer value : uf.parent.values()) {
    //         if (!island.contains(value)) {
    //             island.add(value);
    //         }
    //     }
    //     return island.size();
    // }
```