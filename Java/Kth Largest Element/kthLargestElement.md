# Kth Largest Element

```java
    // 以下虽然通过测试，但是逻辑很容易出错；要像九章那样拆分！！！见后面那种实现方式：拆分出partition function
    // public int kthLargestElement(int k, int[] nums) {
    //     // write your code here
    //     return helper(k, nums, 0, nums.length - 1);
    // }
    
    // public int helper(int k, int[] nums, int start, int end) {
    //     // divide the nums[start...end] by nums[end] using two pointers
    //     int key = nums[end];
    //     int i = start, j = end - 1;
    //     while (i < j) {
    //         // 一步步走 容易控制i和j的位置; 每次扫描两端
    //         if (nums[i] > key) {
    //             i++;
    //         }
    //         if (nums[j] <= key) {
    //             j--;
    //         }
    //         if (i < j && nums[i] <= key && nums[j] > key) {
    //             swap(nums, i, j);
    //             i++;
    //             j--;
    //         }
    //     }
    //     // 此时i之前 和j之后都确定了与key相对大小; 要么i = j 要么j + 1 = i
    //     if (i == j) {
    //         if (nums[i] > key) {
    //             if (i - start + 1 > k - 1) {
    //                 return helper(k, nums, start, i);
    //             } else if (i - start + 1 < k - 1) {
    //                 return helper(k - (i - start + 1) - 1, nums, i + 1, end - 1);
    //             } else {
    //                 return key;
    //             }
    //         } else {
    //             if (i - start > k - 1) {
    //                 return helper(k, nums, start, i - 1);
    //             } else if (i - start < k - 1) {
    //                 return helper(k - (i - start) - 1, nums, i, end - 1);
    //             } else {
    //                 return key;
    //             }
    //         }
    //     } else {
    //         if (j - start + 1 > k - 1) {
    //             return helper(k, nums, start, j);
    //         } else if (j - start + 1 < k - 1) {
    //             return helper(k - (j - start + 1) - 1, nums, i, end - 1);
    //         } else {
    //             return key;
    //         }
    //     }
    // }
    
    // public void swap(int[] nums, int i, int j) {
    //     int temp = nums[j];
    //     nums[j] = nums[i];
    //     nums[i] = temp;
    // }
    public int kthLargestElement(int k, int[] nums) {
        return helper(k, nums, 0, nums.length - 1);
    }
    
    public int helper(int k, int[] nums, int start, int end) {
        int pos = partition(k, nums, start, end);
        if (pos - start + 1 == k) {
            return nums[pos];
        } else if (pos - start >= k) {
            return helper(k, nums, start, pos - 1);
        } else {
            return helper(k - (pos - start + 1), nums, pos + 1, end);
        }
    }
    
    public int partition(int k, int[] nums, int start, int end) {
        // return the idx of the key value; choose the last num as the key value of nums[start...end] 
        int key = nums[end];
        int i = start, j = end;
        while (i < j) {
            // find the first value on the left that is <= key, save it to nums[j]
            // even if i == j after while loop, this is correct
            while (i < j && nums[i] > key) {
                i++;
            }
            // now it is either 1. i == j or 2. nums[i] <= key and i < j
            // 无论哪种，以下操作都是合法的
            nums[j] = nums[i];
            // now if i < j, nums[i] can be overwritten in the future
            while (i < j && nums[j] <= key) {
                j--;
            }
            // now it is either 1. i == j or 2. nums[j] > key and i < j
            nums[i] = nums[j];
        }
        // 以上loop之后，一定有 i == j
        nums[i] = key;
        return i;
    }
```