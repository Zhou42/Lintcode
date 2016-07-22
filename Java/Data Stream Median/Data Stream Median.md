# Data Stream Median
使用两个heap，一个maxHeap 一个minHeap  
具体见程序，Leetcode的方法比较简便

```java
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    
    class mycomparator implements Comparator<Integer> {
        public int compare(Integer n1, Integer n2) {
            return n2 - n1;
        }
    }
    public int[] medianII(int[] nums) {
        // write your code here
        // 使用两个priorityqueue
        if (nums == null || nums.length == 0) {
            return null;
        }
        Queue<Integer> pq1 = new PriorityQueue<>(10, new mycomparator());
        Queue<Integer> pq2 = new PriorityQueue<>();
        int[] res = new int[nums.length];
        // for (int i = 0; i < nums.length; i++) {
        //     if (i % 2 == 0) {
        //         // even num of numbers before ith position
        //         if (pq1.isEmpty()) { // two pqs are empty
        //             pq2.offer(nums[i]);
        //             res[i] = pq2.peek();
        //         } else {
        //             // see whether I can put this element directly into heap b
        //             if (pq1.peek() <= nums[i]) {
        //                 pq2.offer(nums[i]);
        //                 res[i] = pq2.peek();
        //             } else {
        //                 int head1 = pq1.poll();
        //                 pq2.offer(head1);
        //                 pq1.offer(nums[i]);
        //                 res[i] = pq2.peek();
        //             }
        //         }
        //     } else {
        //         // odd num of nums before pos
        //         // because heap b is larger, we know b.head exists
        //         if (nums[i] <= pq2.peek()) {
        //             pq1.offer(nums[i]);
        //             res[i] = pq1.peek();
        //         } else {
        //             int head2 = pq2.poll();
        //             pq1.offer(head2);
        //             pq2.offer(nums[i]);
        //             res[i] = pq1.peek();
        //         }
        //     }
        // }
        
        // 实际可以写的简洁些
        // |==================| |====================|
        //       maxheap               minheap
        // 考虑到maxheap和minheap的构造过程，可以看到，minheap中的元素总是大于maxheap中的元素的
        // 每次只需要总是 1. 把新的nums[i]放入到右侧的minheap中 2. 再把右侧的head放入左侧maxheap中，就可以保证[minheap中的元素总是大于maxheap中的元素的性质]
        for (int i = 0; i < nums.length; i++) {
            pq2.offer(nums[i]);
            pq1.offer(pq2.poll());
            if (pq1.size() > pq2.size()) {
                pq2.offer(pq1.poll());
            }
            
            if (pq1.size() == pq2.size()) {
                res[i] = pq1.peek();
            } else {
                res[i] = pq2.peek();
            }
        }
        return res;
    }
}

```