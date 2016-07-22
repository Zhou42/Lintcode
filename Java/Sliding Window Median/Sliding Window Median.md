# Sliding Window Median
这道题九章的算法并不是最优的，其复杂度是O(kn)和我写的程序一样  
更好的做法是最开始的想法，维护带删除的两个min和max heap可以达到O(nlog(k))  
比如这个  
http://www.jianshu.com/p/b96458efc850  
实际上不用自己实现，用PriorityQueue即可，带删除  
http://www.tutorialspoint.com/java/util/priorityqueue_remove.htm
具体解法见
http://www.cnblogs.com/fifi043/p/4979652.html

## 其实以下的二分是没有作用的，因为插入和删除都是O(k)的；可以简单的使用九章的做法，遍历找到要删除的元素，把要加入的元素放到该位置，再sift up或者sift down该元素即可(原数组sorted)
```java
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        // 一个估计会超时的解法
        // 维护一个k长的队列linkedlist，每次用binary search找去除的num和插入num的位置
        if (nums == null || nums.length == 0 || k == 0) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> window = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            window.add(nums[i]);
        }
        Collections.sort(window);
        res.add(window.get((k-1)/2));
        for (int i = k; i < nums.length; i++) {
            // 这里remove和addelement 应该都是O(k)的操作，不是很好的办法
            removeElement(window, nums[i - k]);
            addElement(window, nums[i]);
            res.add(window.get((k-1)/2));
        }
        return res;
    }
    public void removeElement(ArrayList<Integer> window, int target) {
        // binary search -> window is sorted
        int start = 0, end = window.size() - 1;
        while (start + 1 < end) {
            int mid = (end - start)/2 + start;
            if (window.get(mid) < target) {
                start = mid;
            } else if (window.get(mid) > target) {
                end = mid;
            } else {
                window.remove(mid);
                return;
            }
        }
        if (window.get(start) == target) {
            window.remove(start);
            return;
        }
        if (window.get(end) == target) {
            window.remove(end);
            return;
        }
        return;
    }
    public void addElement(ArrayList<Integer> window, int target) {
        // binary search -> window is sorted
        if (window.size() == 0) {
            window.add(target);
            return;
        }
        int start = 0, end = window.size() - 1;
        while (start + 1 < end) {
            int mid = (end - start)/2 + start;
            if (window.get(mid) <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (window.get(end) <= target) {
            window.add(target);
            return;
        }
        if (window.get(start) > target) {
            window.add(0, target);
            return;
        }
        window.add(start + 1, target);
        return;
    }
}

```

