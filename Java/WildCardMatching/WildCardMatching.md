# Wildcard Matching - Dynamic Programming

```java
// 使用DP, 代表前多少个字符
// row是通配符 p
// column是需要匹配的字符串 s
public boolean isMatch(String s, String p) {
    boolean[][] flag = new boolean[p.length() + 1][s.length() + 1];
    // initialize
    flag[0][0] = true;
    // 初始化第一行
    for (int j = 1; j < s.length() + 1; j++) {
        flag[0][j] = false;
    }
    // 初始化第一列。此时s为空，只有在之前的匹配为true，并且此时p为'*'，才为true 
    for (int i = 1; i < p.length() + 1; i++) {
        if ((p.charAt(i - 1) == '*') && flag[i - 1][0]) {
            flag[i][0] = true;
        } else {
            flag[i][0] = false;
        }
    }
    // f的递归规则为:
    // 如果p[i - 1]为s[i - 1] or '?' -> flag[i][j] = flag[i - 1][j - 1];
    // 如果p[i - 1]为'*' -> flag[i][j] = flag[i - 1][0] || ... || flag[i - 1][j]; 原因是，除了'*'剩余的字符串，可以匹配s所有，直至空串
    //      以上可以化简 flag[i][j - 1] = flag[i - 1][0] || ... || flag[i - 1][j - 1]
    // 如果[i - 1]为其他，则无法匹配，flag[i][j] = false;
    for (int i = 1; i < p.length() + 1; i++) {
        for (int j = 1; j < s.length() + 1; j++) {
            // 分情况，看p[i - 1]，也就是当前考虑的p的结尾，是什么
            if ((p.charAt(i - 1) == s.charAt(j - 1)) || (p.charAt(i - 1) == '?')) {
                flag[i][j] = flag[i - 1][j - 1];
            } else if (p.charAt(i - 1) == '*') {
                flag[i][j] = flag[i][j - 1] || flag[i - 1][j];
            } else {
                flag[i][j] = false;
            }
        }
    }
    return flag[p.length()][s.length()];
}
```