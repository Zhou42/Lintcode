# String to Integer II
这道题描述的并不清楚，所以跟面试官沟通很重要
```java
public class Solution {
    /**
     * @param str: A string
     * @return An integer
     */
     
    // 题目描述不是很清晰 
     
    // public int atoi(String str) {
    //     // write your code here
    //     if (str == null || str.length() == 0) {
    //         return 0;
    //     }
    //     int sum = 0;
    //     int pos = 0;
    //     int flag = 1;
    //     // check teh first character
    //     if (str.charAt(pos) == ' ') {
    //         while (str.charAt(pos) == ' ') {
    //             pos++;
    //         }
    //     }
    //     if (str.charAt(pos) == '-') {
    //         pos++;
    //         flag = -1;
    //     } else if (str.charAt(pos) == '+') {
    //         pos++;
    //     } else if (str.charAt(pos) >= '0' && str.charAt(pos) <= '9') {
            
    //     } else {
    //         return 0;
    //     }
    //     // '10' 的 pos = 0 位置是'1' 说明左侧是pos = 0
    //     // System.out.println(str.charAt(0));
    //     int count = 0;
    //     while (pos < str.length()) {
    //         char ch = str.charAt(pos);
    //         // System.out.println(ch - '0');
    //         if (ch >= '0' && ch <= '9') {
    //             if (sum > Integer.MAX_VALUE/10) {
    //                 while (pos < str.length()) {
    //                     if (str.charAt(pos) >= '0' && str.charAt(pos) <= '9') {
    //                         pos++;
    //                     } else if (str.charAt(pos) == '.' && count == 0) {
    //                         count++;
    //                         pos++;
    //                     } else {
    //                         return 0;
    //                     }
    //                 }
    //                 return Integer.MAX_VALUE;
    //             }
    //             if (sum < Integer.MIN_VALUE/10) {
    //                 while (pos < str.length()) {
    //                     if (str.charAt(pos) >= '0' && str.charAt(pos) <= '9') {
    //                         pos++;
    //                     } else if (str.charAt(pos) == '.' && count == 0) {
    //                         count++;
    //                         pos++;
    //                     } else {
    //                         return 0;
    //                     }
    //                 }
    //                 return Integer.MIN_VALUE;
    //             }
    //             sum = sum * 10 + ch - '0';
    //             pos++;
    //         } else if (ch == '.') {
    //             pos++;
    //             while (pos < str.length() && str.charAt(pos) == '0') {
    //                 pos++;
    //             }
    //             if (pos != str.length()) {
    //                 return 0;
    //             }
    //         } else {
    //             return 0;
    //         }
    //     }
    //     return sum * flag;
    // }
    
    // 按照九章的答案，需要实现功能：
    // 1. 前后空格trim掉
    // 2. 分辨第一个位置的 + - 
    // 3. 是否超过Integer的范围
    // 4. 需要考虑的数字为，从左面起，到第一个不是数字的位置为止
    
    public int atoi(String str) {
        // write your code here
        if (str == null) {
            return 0;
        }
        long sum = 0;
        int pos = 0;
        int flag = 1;
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        // check the first character
        if (str.charAt(pos) == '-') {
            pos++;
            flag = -1;
        } else if (str.charAt(pos) == '+') {
            pos++;
        } else if (str.charAt(pos) >= '0' && str.charAt(pos) <= '9') {
            
        } else {
            return 0;
        }        
        while (pos < str.length()) {
            char ch = str.charAt(pos);
            if (ch >= '0' && ch <= '9') {
                sum = sum * 10 + ch - '0';
                pos++;
            } else {
                break;
            }
            if (sum > Integer.MAX_VALUE) {
                break;
            }
        }
        sum = sum * flag;
        if (sum > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (sum < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int)sum;
    }
}

```