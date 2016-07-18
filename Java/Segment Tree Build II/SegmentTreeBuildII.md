# Segment Tree Build II
1. 这道题主要要考虑出 O(n) 的结果。利用从底向上的方法，获得max value!

## Code
```java
    public SegmentTreeNode build(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return null;
        }
        return buildHelper(A, 0, A.length - 1);
    }
    
    public SegmentTreeNode buildHelper(int[] A, int start, int end) {
        // 叶子节点；也就是递归出口
        if (start == end) {
            return new SegmentTreeNode(start, end, A[start]);
        }
        // find the max value of A[start...end]
        // 这里可以依靠子节点的结果！！可以将复杂度又O(nlogn)变为O(n)!!
        // int maxVal = Integer.MIN_VALUE;
        // for (int i = start; i <= end; i++) {
        //     if (maxVal < A[i]) {
        //         maxVal = A[i];
        //     }
        // }
        SegmentTreeNode root = new SegmentTreeNode(start, end, Integer.MIN_VALUE);
        root.left = buildHelper(A, start, (end - start)/2 + start);
        root.right = buildHelper(A, (end - start)/2 + start + 1, end);
        root.max = Math.max(root.left.max, root.right.max);
        return root;
    }
```