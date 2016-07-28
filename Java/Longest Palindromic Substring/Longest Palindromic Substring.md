#  Longest Palindromic Substring
我只想出了O(n^2)的算法。  
O(n)的算法在这里有解释，感觉不具有普适性暂时不研究了  
http://blog.csdn.net/hopeztm/article/details/7932245  

另外关于KMP算法  
http://blog.csdn.net/v_july_v/article/details/7041827

```java
public class Solution {
    /**
     * @param s input string
     * @return the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        // Write your code here
        if(s == null || s.length() == 0) {
            return "";
        }
        // 先实现O(n^2)算法
        // a matrix containing whether a substring is palindromic
        int[][] isPalindrome = new int[s.length()][s.length()];
        // 考虑遍历substrng的长度 来初始化
        // 从长度是1和2的开始，以后3, 4...都可以从这两个得到
        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = 1;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                isPalindrome[i][i + 1] = 1;
            } else {
                isPalindrome[i][i + 1] = 0;
            }
        }
        for (int len = 3; len <= s.length(); len++) {
            for (int i = 0; i + len - 1 < s.length(); i++) {
                if (isPalindrome[i + 1][i + len - 2] == 1 && s.charAt(i) == s.charAt(i + len - 1)) {
                    isPalindrome[i][i + len - 1] = 1;
                } else {
                    isPalindrome[i][i + len - 1] = 0;
                }
            }
        }
        int max = -1;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (isPalindrome[j][i] == 1) {
                    if (i - j + 1 > max) {
                        max = i - j + 1;
                        res = s.substring(j, i + 1);
                    }
                }
            }
        }
        return res;
    }
}
```