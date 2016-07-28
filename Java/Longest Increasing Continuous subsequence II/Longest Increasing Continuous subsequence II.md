# Longest Increasing Continuous subsequence II
使用DP + DFS  
每个元素只需要遍历一遍 - 观察visited的变化  

```java
public class Solution {
    /**
     * @param A an integer matrix
     * @return  an integer
     */
     
    int[] di = {-1, +1, 0, 0};
    int[] dj = {0, 0, -1, +1};
    int[][] dp;
    int[][] visited;
    
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        // Write your code here
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int n = A.length, m = A[0].length;
        dp = new int[n][m];
        visited = new int[n][m];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = search(A, i, j);
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
    public int search(int[][] A, int i, int j) {
        // from (i, j), return the longest increasing continuous subsequence 
        if (visited[i][j] == 1) {
            return dp[i][j];
        }
        int ans = 1;
        for (int t = 0; t < 4; t++) {
            int i_new = i + di[t];
            int j_new = j + dj[t];
            if (isValid(A, i_new, j_new) && A[i][j] > A[i_new][j_new]) {
                if (visited[i_new][j_new] == 1) {
                    ans = Math.max(ans, dp[i_new][j_new] + 1);
                } else {
                    ans = Math.max(ans, search(A, i_new, j_new) + 1);
                }
            }
        }
        dp[i][j] = ans;
        visited[i][j] = 1;
        return ans;
    }
    public boolean isValid(int[][] A, int i, int j) {
        // return if i,j is in A
        int n = A.length, m = A[0].length;
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
```
