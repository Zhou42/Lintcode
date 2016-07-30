# Add Binary
这种代码一定要写熟练。。。
```java
public class Solution {
    /**
     * @param a a number
     * @param b a number
     * @return the result
     */
    public String addBinary(String a, String b) {
        // Write your code here
        if (a == null || b == null) {
            return null;
        }
        // 如果不知道谁长谁短，则需要在后面分类讨论，写重复的代码。这里先确定 谁是短的那个
        if (a.length() > b.length()) {
            String temp = a;
            a = b;
            b = temp;
        } // now I know a is shorter
        String res = "";
        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;
        int temp = 0, digit = 0;
        while (i >= 0) {
            temp = a.charAt(i) - '0' + b.charAt(j) - '0' + carry;
            // System.out.println(temp);
            // if (temp >= 2) {
            //     carry = 1;
            //     temp = temp - 2;
            //     res = String.valueOf(temp) + res;
            // } else {
            //     res = String.valueOf(temp) + res;
            //     carry = 0;
            // }
            //  关于进位和余数的问题 统一这么写
            carry = temp / 2;
            digit = temp % 2;
            res = String.valueOf(digit) + res;
            i--;
            j--;
        }
        while (j >= 0) {
            temp = b.charAt(j) - '0' + carry;
            carry = temp / 2;
            digit = temp % 2;
            res = String.valueOf(digit) + res;
            j--;
        }        
        if (carry == 1) {
            res = String.valueOf(1) + res;
        }
        return res;
    }
}
```