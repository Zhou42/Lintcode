# Evaluate Reverse Polish Notation
```java
public class Solution {
    /**
     * @param tokens The Reverse Polish Notation
     * @return the value
     */
    public int evalRPN(String[] tokens) {
        // Write your code here
        // so easy... 看来很多东西只是积累的不够，继续强化抽空看算法的视频
        // 注意细节，好比getNum函数注意负数
        // 九章的答案更简洁，实际是一样的
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (isNum(s)) {
                // into stack
                stk.push(getNum(s));
            } else {
                int temp1 = stk.pop(), temp2 = stk.pop();
                int res = 0;
                // 这里String还是使用if else 和 .equals()判断吧
                switch (s) {
                    case "+": 
                        res = temp1 + temp2;
                        break;
                    case "-":
                        res = temp2 - temp1;
                        break;
                    case "*":
                        res = temp1 * temp2;
                        break;
                    case "/":
                        res = temp2 / temp1;
                        break;
                    default:
                        break;
                }
                stk.push(res);
            }
        }
        return stk.pop();
    }
    public boolean isNum(String s) {
        return !s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/");
    }
    // 这个函数可以直接使用Integer.valueOf(token)
    public int getNum(String s) {
        int value = 0;
        int flag = 1;
        int i = 0;
        if (s.charAt(0) == '-') {
            flag = -1;
            i = 1;
        }
        while (i < s.length()) {
            char c = s.charAt(i);
            value = value * 10 + c - '0';
            i++;
        }
        return value * flag;
    }
}
```