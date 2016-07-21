# Trapping Rain Water II
见注释
```java
public class Solution {
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
     
    class Node {
        int i, j;
        int height;
        public Node(int i, int j, int height) {
            this.i = i;
            this.j = j;
            this.height = height;
        }
    }
    class mycomparator implements Comparator<Node> {
        public int compare(Node node1, Node node2) {
            return node1.height - node2.height;
        }
    }
    public int trapRainWater(int[][] heights) {
        // write your code here
        // PriorityQueue中维护一个边界，由于需要找边界的最小值，所以使用最小堆
        // 以下基本就是，找到边界最小值，然后更新边界
        // 跟Trapping Rain Water I中 我的做法一样，每个循环走一步，只不过这里每一步，可能有四个方向
        // 对于每个方向，如果柱子的高度比边界的储水高度高，则将该柱子的高度，作为边界高度；如果柱子的高度不比边界的储水高度高，则一方面计算volume，一方面继续以当前储水高度，储存新的边界
        
        Queue<Node> PQ = new PriorityQueue<Node>(100, new mycomparator());
        int rowNum = heights.length;
        int colNum = heights[0].length;
        int[][] used = new int[rowNum][colNum];
        int[] di = {0, 0, -1, +1};
        int[] dj = {-1, +1, 0, 0};
        int volume = 0;
        // 入Queue的时候 考虑水量
        // border into PQ
        for (int i = 0; i < rowNum; i++) {
            PQ.offer(new Node(i, 0, heights[i][0]));
            PQ.offer(new Node(i, colNum - 1, heights[i][colNum - 1]));
            used[i][0] = 1;
            used[i][colNum - 1] = 1;
        }
        for (int j = 1; j < colNum - 1; j++) {
            PQ.offer(new Node(0, j, heights[0][j]));
            PQ.offer(new Node(rowNum - 1, j, heights[rowNum - 1][j]));
            used[0][j] = 1;
            used[rowNum - 1][j] = 1; 
        }
        while (!PQ.isEmpty()) {
            Node curt = PQ.poll();
            // for each direction
            for (int t = 0; t < 4; t++) {
                int i_new = curt.i + di[t];
                int j_new = curt.j + dj[t];
                if (isInBoard(heights, i_new, j_new) && used[i_new][j_new] == 0) {
                    // 比较这个height与curt的height
                    if (curt.height >= heights[i_new][j_new]) {
                        volume += curt.height - heights[i_new][j_new];
                        // 由于新的位置，柱子矮于当前储水高度；在新的位置放入当前可以灌水的高度
                        PQ.offer(new Node(i_new, j_new, curt.height));
                    } else {
                        // update height value for border
                        PQ.offer(new Node(i_new, j_new, heights[i_new][j_new]));
                    }
                    used[i_new][j_new] = 1; // this new point is considered
                }
            }
        }
        return volume;
    }
    
    public boolean isInBoard(int[][] heights, int i, int j) {
        return i >= 0 && i < heights.length && j >= 0 && j < heights[0].length;
    }
};
```