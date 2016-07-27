# Minimum Window Substring
仍然是前进行指针，可以考虑这类题弄一个模版
```java
public class Solution {
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
     
    // 这个方法与九章的第一个方式是相同的，只不过我使用HashMap记录 char -> 出现次数
    // 而九章答案使用array记录 char -> 出现次数
    public String minWindow(String source, String target) {
        // write your code
        if (source == null || source.length() == 0 || target == null || target.length() == 0) {
            return new String();
        }
        
        int minLen = Integer.MAX_VALUE, startMinW = 0, endMinW = 0;
        int start = 0, end = 0;
        // contains the char in target; the initial state is when subarray is empty 一旦subarray中出现target中的char，则从map中减去
        // 故当map中一旦出现正值，说明target中有char不在subarray中
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        
        for (start = 0; start < source.length(); start++) {
            // System.out.println("start is" + start);
            // System.out.println("end is" + end);
            while (end < source.length() && !isValid(map)) {
                // some char is not contained in the substring, need to expand the substring
                if (map.containsKey(source.charAt(end))) {
                    map.put(source.charAt(end), map.get(source.charAt(end)) - 1);
                }
                end++;
            }
            // now substring full cover the target
            if (minLen > end - start && isValid(map)) {
                minLen = end - start;
                startMinW = start;
                endMinW = end;
            }
            // remove char at the start from the substring
            if (map.containsKey(source.charAt(start))) {
                map.put(source.charAt(start), map.get(source.charAt(start)) + 1);
            }
        }
        return source.substring(startMinW, endMinW);
    }
    public boolean isValid(HashMap<Character, Integer> map) {
        // whether substring contains the target
        // 注意这个操作是常数O(1)的，因为char一共就26(x2)个字母 
        boolean res = true;
        for (Character key : map.keySet()) {
            res &= map.get(key) <= 0;
        }
        return res;
    }
}
```