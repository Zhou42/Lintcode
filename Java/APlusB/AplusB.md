# A + B Problem
思路见注释

```java
class Solution {
    /*
     * param a: The first integer
     * param b: The second integer
     * return: The sum of a and b
     */
     
    //该方法不能应对负数 失败！
    
    // public int aplusb(int a, int b) {
    //     // write your code here, try to do it without arithmetic operators.
    //     // 这里先假设 unsigned nums
    //     long res = 0;
    //     int mask = 1;
    //     int carry = 0;
    //     for (int i = 0; i < 32; i++) {
    //         int digita = mask & a;
    //         int digitb = mask & b;
    //         if (carry == 0) {
    //             carry = digita & digitb;
    //             res = res | ((digita ^ digitb) << i);
    //         } else {
    //             carry = digita | digitb;
    //             res = res | (~(digita ^ digitb) << i);
    //         }
    //         a = a >> 1;
    //         b = b >> 1;
    //     }
    //     if (carry == 1) {
    //         res = res | (1 << 32);
    //     }
    //     return (int)res;
    // }
    
    // 想法很巧妙；如果要求a + b, 等价于求a和b除去carry的结果，也就是temp；以及carry，也就是(a & b) << 1的和。这样就形成了迭代，新的加数分别是temp和(a & b) << 1。
    // 不太清楚为什么carry最后一定可以变成0
    public int aplusb(int a, int b) {
        while (b != 0) {
            // 这个是除了carry之外的digits结果
            int temp = a ^ b;
            // carry, 也是下一次的加数
            b = (a & b) << 1;
            // 新的另一个加数
            a = temp;
        }
        return a;
    }
};
```