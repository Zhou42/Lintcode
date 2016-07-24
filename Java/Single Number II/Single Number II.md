# Single Number II
这个讲解很好 http://www.acmerblog.com/leetcode-single-number-ii-5394.html
具体过程见下面代码注释

```java
    public int singleNumberII(int[] A) {
        // write your code here
        // i can do it with hashmap - do not know how to do with constant space
        // if (A == null || A.length == 0) {
        //     return -1;
        // }
        // HashMap<Integer, Integer> map = new HashMap<>();
        // for (int i = 0; i < A.length; i++) {
        //     if (!map.containsKey(A[i])) {
        //         map.put(A[i], 1);
        //     } else {
        //         map.put(A[i], map.get(A[i]) + 1);
        //     }
        // }
        // for (Integer key : map.keySet()) {
        //     if (map.get(key) == 1) {
        //         return key;
        //     }
        // }
        // return -1;
        
        // 九章有更好的bit operation
        // 参考这个 容易懂 http://www.acmerblog.com/leetcode-single-number-ii-5394.html
        // 使用九章那个就成了，掩码变量的那个不好想出来
        // 只需要每个bit位分别独立求和，mod 3即可
        // 对于三个相同的数，该位求和mod 3结果为零；剩余的余数(0 or 1) 就是该位单独的数字
        if (A == null || A.length == 0) {
            return -1;
        }
        int[] bits = new int[32];
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < 32; j++) {
                bits[j] += (A[i] >> j) & 1;
            }
        }
        for (int j = 0; j < 32; j++) {
            bits[j] %= 3;
            res |= bits[j] << j;
        }
        return res;
    }
```