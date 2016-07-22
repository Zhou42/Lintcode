# Spiral Matrix
方法略不同于九章，但是感觉更直观，也很简洁

```java
public class Solution {
    /**
     * @param matrix a matrix of m x n elements
     * @return an integer list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // Write your code here
        // 使用一个isUsed
        // 只有在不能继续走的时候，转向；
        // 貌似这个解法比九章的直观些；不需要繁琐的坐标控制
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<Integer>();
        }
        int rowNum = matrix.length, colNum = matrix[0].length;
        int[] di = {0, 0, -1, +1};
        int[] dj = {+1, -1, 0, 0};
        int i = 0, j = -1; // 当前的位置
        int idx = 0;
        int[][] used = new int[rowNum][colNum];
        List<Integer> res = new ArrayList<>();
        int i_next = i + di[idx], j_next = j + dj[idx];
        // 只要下一步位置，也就是(i_next, j_next) 是valid的，就可以继续走
        while (isValid(matrix, used, i_next, j_next)) {
            // 当下一个位置(i_next, j_next) valid的时候
            while (isValid(matrix, used, i_next, j_next)) {
                // 真正走到下一个位置(i_next, j_next)，也就是res加入这个数字
                res.add(matrix[i_next][j_next]);
                used[i_next][j_next] = 1;
                i = i_next; // 修改current位置
                j = j_next;
                i_next = i + di[idx]; // 修改下一步的位置
                j_next = j + dj[idx];
            }
            // find the valid direction
            for (int t = 0; t < 4; t++) {
                // 更改direction
                if (isValid(matrix, used, i + di[t], j + dj[t])) {
                    idx = t;
                }
            }
            i_next = i + di[idx];
            j_next = j + dj[idx];
        }
        return res;
    }
    public boolean isValid(int[][] matrix, int[][] used, int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length && used[i][j] == 0;
    }
    
}
```