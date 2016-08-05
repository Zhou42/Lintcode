# Bomb Enemy
九章的算法不容易想，先用自己的方法做吧..
```java
public class Solution {
    /**
     * @param grid Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies(char[][] grid) {
        // Write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length, m = grid[0].length;
        int[][] left = new int[n][m];
        int[][] up = new int[n][m];
        int[][] down = new int[n][m];
        int[][] right = new int[n][m];
        // fill left
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (grid[i][j - 1] == '0') {
                    left[i][j] = left[i][j - 1];
                } else if (grid[i][j - 1] == 'E') {
                    left[i][j] = left[i][j - 1] + 1;
                } else {
                    left[i][j] = 0;
                }
            }
        }
        // fill right
        for (int j = m - 2; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                if (grid[i][j + 1] == '0') {
                    right[i][j] = right[i][j + 1];
                } else if (grid[i][j + 1] == 'E') {
                    right[i][j] = right[i][j + 1] + 1;
                } else {
                    right[i][j] = 0;
                }
            }
        }
        // fill up
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i - 1][j] == '0') {
                    up[i][j] = up[i - 1][j];
                } else if (grid[i - 1][j] == 'E') {
                    up[i][j] = up[i - 1][j] + 1;
                } else {
                    up[i][j] = 0;
                }
            }
        }
        // fill down
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i + 1][j] == '0') {
                    down[i][j] = down[i + 1][j];
                } else if (grid[i + 1][j] == 'E') {
                    down[i][j] = down[i + 1][j] + 1;
                } else {
                    down[i][j] = 0;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') {
                    max = Math.max(max, left[i][j] + right[i][j] + up[i][j] + down[i][j]);
                }
            }
        }
        return max;
    }
    
    // 可以简化，如九章中做法，思路见http://www.cnblogs.com/grandyang/p/5599289.html
    // 思路是一行行扫描，每一行和列，都可以视为，被W分割的空间，在某个空间中时，rowCount可以被一次计算出来，代表该行的E数目，ColCount(j)可以被计算作为每列的E数目。这两个量，只有在W出现的时候才更新
    // 每个元素被横向／纵向各计数一次，所以时间O(2 * n^2) 空间则优化的多
    
}
```