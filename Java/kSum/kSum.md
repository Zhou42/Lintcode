# k Sum
## 使用三维DP即可

```java
public int kSum(int A[], int k, int target) {
    // write your code here
    // Oh yeah! 使用三维DP即可
    // 定义solution数目tensor为C[i][j][h]: i = 0 ~ A.length, j = 0 ~ k, h = 0 ~ target
    // i: 考虑A[]中前i个数
    // j: 选j个数
    // h: sum是h
    int[][][] C = new int[A.length + 1][k + 1][target + 1];
    // initialization
    // 做题前，画一个三维坐标轴，即可明白
    // 只需要初始化两个平面的数值
    C[0][0][0] = 1; 
    for (int i = 1; i < target + 1; i++) {
        C[0][0][i] = 0;
    }
    for (int i = 1; i < A.length + 1; i++) {
        for (int h = 0; h < target + 1; h++) {
            if (h == 0) {
                C[i][0][h] = 1;
            } else {
                C[i][0][h] = 0;
            }
        }
    }
    for (int j = 1; j < k + 1; j++) {
        for (int h = 0; h < target + 1; h++) {
            C[0][j][h] = 0;
        }
    }
    // calculate other C[][][]
    for (int i = 1; i < A.length + 1; i++) {
        for (int j = 1; j < k + 1; j++) {
            for (int h = 0; h < target + 1; h++) {
                // 对于考虑A[]前i个数，实际只有两种选择:
                // 1. 一种是选第i个数
                // 2. 一种是不选
                if (h < A[i - 1]) {
                    C[i][j][h] = C[i - 1][j][h];
                } else {
                    C[i][j][h] = C[i - 1][j][h] + C[i - 1][j - 1][h - A[i - 1]];
                }
            }
        }
    }
    
    return C[A.length][k][target];
}
```