# Space Replacement
注意复杂度保持O(n)
```java
    // 可以用O(n)方法实现
    // 第一遍扫过n个数 得到新的长度 从而知道新的数组的结尾
    // 从新的数组结尾搬运数字
    public int replaceBlank(char[] string, int length) {
        if (string == null || string.length == 0) {
            return 0;
        }
        int newlength = length;
        for (int i = 0; i < length; i++) {
            if (string[i] == ' ') {
                newlength += 2;
            }
        }
        // p1 points at the tail of the original array
        // p2 points at the tail of the new array
        int p1 = length - 1, p2 = newlength - 1;
        while (p1 != p2) { // 当p2追上p1的时候，就不可能再有空格了
            if (string[p1] != ' ') {
                string[p2] = string[p1];
                p2--;
                p1--;
            } else {
                p1--;
                string[p2--] = '0';
                string[p2--] = '2';
                string[p2--] = '%';
            }
        }
        return newlength;
    }
```