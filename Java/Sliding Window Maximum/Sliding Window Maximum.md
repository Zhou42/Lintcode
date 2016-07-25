# Sliding Window Maximum
这道题使用deque
- 只保存sliding window中的数字
- 保持non-increasing
- 需要保存坐标和value，但是value可以从坐标中得到，所以只需要存坐标

https://segmentfault.com/a/1190000003903509

```java
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The maximum number inside the window at each moving.
     */
     
    // 最后的test case超时
    
    // class mycomparator implements Comparator<Integer> {
    //     public int compare(Integer i1, Integer i2) {
    //         return i2 - i1;
    //     }
    // }
    // public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
    //     // write your code here
    //     if (nums == null || nums.length == 0 || k > nums.length) {
    //         return new ArrayList<Integer>();
    //     }
    //     // use priority queue and the complexity is O(kn)
    //     Queue<Integer> maxHeap = new PriorityQueue<>(k, new mycomparator());
    //     ArrayList<Integer> res = new ArrayList<Integer>();
    //     // put the first k elements into the heap
    //     for (int i = 0; i < k; i++) {
    //         maxHeap.offer(nums[i]);
    //     }
    //     res.add(maxHeap.peek());
    //     for (int j = k; j < nums.length; j++) {
    //         // the tail of the window
    //         maxHeap.remove(nums[j - k]);
    //         maxHeap.offer(nums[j]);
    //         res.add(maxHeap.peek());
    //     }
    //     return res;
    // }
    
    // 使用deque -> 其中只保存sliding window中的element，但是保证non increasing
    // 由于要判断head是否是sliding window最左面需要remove的元素，所以需要both index和value，但是value可以从index得到，所以deque中只需要存index即可
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> res = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        // 这里k一定 <= nums.length
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
        }     
        res.add(nums[deque.peekFirst()]);
        // j is the new element 
        for (int j = k; j < nums.length; j++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[j]) {
                deque.pollLast();
            }
            deque.offer(j);
            if (deque.peekFirst() == j - k) {
                deque.pollFirst();
            }
            res.add(nums[deque.peekFirst()]);
        }
        return res;
    }
}

```