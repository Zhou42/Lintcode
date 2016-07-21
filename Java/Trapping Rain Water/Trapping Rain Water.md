# Trapping Rain Water
想到了两个做法，九章给的另外一种做法，与这里最后使用的方法思路是一样的。都是找到左右指针处为止最大值中较小的一个之后，从较小的一侧开始灌水   
区别是 每次找到左右最大值中较小的一个之后，是否连续灌水  

```java
public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
     
    // 这里自己的方法，是九章答案的一种；更优的方法，是直接考虑两边，同时记录两边的max height；则不用再记录两个向量left_highest和right_highest
    // 根本的想法是一样的，只不过做了优化
    
    // public int trapRainWater(int[] heights) {
    //     // write your code here
    //     if (heights == null || heights.length == 0) {
    //         return 0;
    //     }
    //     // 这道题首先要观察到，一个位置i的水的高度，取决于min(左面最高，右面最高); 如果这个值不大于heights[i]，则该处水为0；如果大于heights[i]，则该处水高度为 min(左面最高，右面最高) - heights[i]
    //     // 左右末端的水一定是0，因为左右端外面没有bar可以藏水;所以max的初始值可以用0或者-1，从而两端水高度为0
    //     int n = heights.length;
    //     int[] left_highest = new int[n];
    //     int[] right_highest = new int[n];
    //     int[] volume = new int[n];
    //     int max = -1;
    //     // left -> right
    //     for (int i = 0; i < n; i++) {
    //         left_highest[i] = max; //  does not include self
    //         if (heights[i] > max) {
    //             max = heights[i];
    //         }
    //     }
    //     // left <- right
    //     max = -1;
    //     for (int i = n - 1; i >= 0; i--) {
    //         right_highest[i] = max;
    //         if (heights[i] > max) {
    //             max = heights[i];
    //         }
    //     }
    //     int sum = 0;
    //     // volume
    //     for (int i = 0; i < n; i++) {
    //         int temp = Math.min(left_highest[i], right_highest[i]);
    //         if (temp > heights[i]) {
    //             sum += temp - heights[i];
    //         }
    //     }
        
    //     return sum;
    // }
    
    // 从两边两个指针start 和 end， 同时保存left_max和right_max；考虑left_max和right_max中的较小值，因为某一处的水高度，由左右最大高度的min决定，所以 比如left_max比较小，则左侧可以求灌水高度，因为其高度由left_max决定
    public int trapRainWater(int[] heights) {
        int start = 0, end = heights.length - 1;
        int left_max = 0, right_max = 0;
        int volume = 0;
        // 每次走一步，考虑左右已知的最大值，其中较小的一边可以灌水。
        // 每次结束的start和end停留的位置，是没有被考虑的位置
        while (start <= end) {
            if (left_max <= right_max) {
                if (heights[start] <= left_max) {
                    // 灌水
                    volume += left_max - heights[start];
                } else {
                    left_max = heights[start];
                }
                start++;
            } else {
                if (heights[end] <= right_max) {
                    // 灌水
                    volume += right_max - heights[end];
                } else {
                    right_max = heights[end];
                }
                end--;
            }
        }
        return volume;
    }
}
```
