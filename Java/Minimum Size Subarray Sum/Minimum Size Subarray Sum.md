# Minimum Size Subarray Sum
使用binary search 可以得到 O(nlogn)的结果 
使用left right两个指针，可以得到O(n)的结果

```java
public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    // public int minimumSize(int[] nums, int s) {
    //     // write your code here
    //     if (nums == null || nums.length == 0) {
    //         return -1;
    //     }
    //     int len = nums.length;
    //     int[] culSum = new int[len + 1]; // the first element is zero
    //     culSum[0] = 0;
    //     for (int i = 1; i < len + 1; i++) {
    //         culSum[i] = culSum[i - 1] + nums[i - 1];
    //     }
    //     if (culSum[len] < s) { // sum of all nums is smaller than s
    //         return -1;
    //     }
    //     int minLen = Integer.MAX_VALUE;
    //     for (int i = 0; i < len; i++) {
    //         // i is the previous position of the start; pos is end of subarray in culSum
    //         int pos = findPos(culSum, i, s);
    //         if (pos != -1 && pos - i < minLen) {
    //             minLen = pos - i;
    //         }
    //     }
    //     return minLen;
    // }
    
    // public int findPos(int[] culSum, int i, int target) {
    //     // i is the previous place of the start of the subarray
    //     // find the first element that >= culSum[i] + target
    //     int start = i + 1, end = culSum.length - 1;
    //     while (start + 1 < end) {
    //         int mid = (end - start)/2 + start;
    //         if (culSum[mid] < culSum[i] + target) {
    //             start = mid;
    //         } else {
    //             end = mid;
    //         }
    //     }
    //     // start = end (没有进入while) or start + 1 = end (进入while)
    //     if (culSum[start] >= culSum[i] + target) {
    //         return start;
    //     }
    //     if (culSum[end] >= culSum[i] + target) {
    //         return end;
    //     }
    //     return -1;
    // }
    
    // 使用left right两个指针，可以达到O(n)
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }  
        int minLen = Integer.MAX_VALUE;
        int left = 0, right = 0; // -> [ , )
        int sum = 0;
        // left是数组的左边界 right是右边界 exclusive
        for (left = 0; left < nums.length; left++) {
            while (right < nums.length && sum < s) {// right = nums.length时已经考虑了所有的数字了
                sum += nums[right]; // right处的数字还没有加入
                right++;
            }
            if (sum >= s && minLen > right - left) {
                minLen = right - left;
            }
            sum -= nums[left];
        }
        if (minLen == Integer.MAX_VALUE) {
            return -1;
        }
        return minLen;
    }
}
```