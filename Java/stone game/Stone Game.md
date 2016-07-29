# Stone Game
- 九章使用的是记忆化搜索，貌似复杂度是O(n^2) 是每个cost的位置计算了一次。但是每个位置实际上check了很多次...
- 这里做法是O(n^3)

```java
public class Solution {
    /**
     * @param A an integer array
     * @return an integer
     */
    public int stoneGame(int[] A) {
        // Write your code here
        // a...b....c 考虑这样一个数组 其最小的cost是
        // 某个b使得[a...b) [b...c] 的cost和最小
        // 所以需要从底向上 建立合并每一段[a...b]的cost
        
        
        
        // 九章使用的是记忆化搜索，貌似复杂度是O(n^2) 是每个cost的位置计算了一次。但是每个位置实际上check了很多次...
        // 这里做法是O(n^3)
        
        
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        // cost[i][j] 代表合并i ~ j需要的cost
        int[][] cost = new int[n][n];
        // initialize
        for (int i = 0; i < n; i++) {
            cost[i][i] = 0;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) { //  start point of the subarray
                int min = Integer.MAX_VALUE;
                // cost[i][i + len - 1] = min(cost[i][j] + cost[j + 1][i + len - 1]) + sum(A, i, i + len - 1)
                // 
                for (int j = i; j < i + len - 1; j++) { // j是前半段的结尾
                    min = Math.min(min, cost[i][j] + cost[j + 1][i + len - 1]);
                }
                cost[i][i + len - 1] = min + sum(A, i, i + len - 1);
            }
        }
        return cost[0][n-1];
    }
    
    public int sum(int[] A, int i, int j) {
        int sum = 0;
        for (int pos = i; pos <= j; pos++) {
            sum += A[pos];
        }
        return sum;
    }
}

```