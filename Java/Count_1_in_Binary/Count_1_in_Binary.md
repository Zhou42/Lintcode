# Count 1 in Binary
# 关于有符号/无符号 二进制左移/右移
- http://blog.sina.com.cn/s/blog_99201d890101hd6s.html
- 按二进制形式把所有的数字向右移动对应的位数，低位移出(舍弃)，高位的空位补符号位，即正数补零，负数补1; 当右移的运算数是byte 和short类型时，将自动把这些类型扩大为 int 型。
- 注意对于有符号数，右移和除以2 是有区别的，例如: -1 >> 1，得到的还是 -1， 因为-1二进制右移前面补1; 而－1除以2 得 0
- 也就是说，左右移动，只有在_无符号整数_时，等价于乘除2

# 这道题的关键是注意 num是一个有符号的数；当是负数时, >>等会改变1的个数；除以2也不行 好比 -1 (二进制下是32个1)
```java
    // 九章答案
    // x & (x - 1) 的含义为去掉二进制数中1的最后一位，无论 x 是正数还是负数都成立。
    // 所以假设有M个1， 九章的结果是O(M)的
    
    // 九章等给的答案 难记住的结论。。。 没必要记忆
    public int countOnes(int num) {
        int mask = 0x00000001;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (mask == (mask & num)) {
                count++;
            }
            mask = mask << 1;
        }
        return count;
    }
```