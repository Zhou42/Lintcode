# Single Number III
这道题与I有关 主要还是考察bit operation。有很多位运算的典型结论需要知道
```java
public class Solution {
    /**
     * @param A : An integer array
     * @return : Two integers
     */
    public List<Integer> singleNumberIII(int[] A) {
        // write your code here
        // 非常好的讲解，尤其是关于bit operations
        // http://www.cnblogs.com/maples7/p/4483196.html
        // 补充(其实这些bit operation都是很典型的)
        // http://blog.csdn.net/zheng0518/article/details/8882394
        
        // 参考九章的答案
        // 有一个操作是需要知道的: n & (n - 1) 是将最右面的'1'置零; 这里n应该是无符号的
        if (A == null || A.length == 0) {
            return new ArrayList<Integer>();
        }
        int xor = 0;
        int res1 = 0, res2 = 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }
        // find the '1' on the right; 这里可以直接替换为 lowbit = xor ^ (-xor)
        int lowbit = xor - (xor & (xor - 1));
        for (int i = 0; i < A.length; i++) {
            if ((lowbit & A[i]) == 0) { // 这里c++ python，大于零的数可以作为boolean中的true使用，但是java中不是，int不能当boolean使用
                // if this digit is 1
                res1 ^= A[i];
            } else {
                // if this digit is 0
                res2 ^= A[i];
            }
        }
        res.add(res1);
        res.add(res2);
        return res;
    }
}
```