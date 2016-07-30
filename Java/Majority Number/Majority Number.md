# Majority Number I, II, III
## Type I
是k = 2时的特例，写起来很简洁
```java 
public class Solution {
    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        int vote = 0;
        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (count == 0) {
                vote = nums.get(i);
                count++;
            } else {
                if (vote == nums.get(i) ) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        // 只需要思考，这样操作 剩下的只可能是众数
        // 可以这么做的原因也是 该数字是绝对多数
        return vote;
    }
}
```

## Type II
是 k = 3时的特例，代码见 type III

## Type III
```java
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: As described
     * @return: The majority number
     */
    // public int majorityNumber(ArrayList<Integer> nums, int k) {
    //     // write your code
    //     if (nums == null || nums.size() == 0) {
    //         return -1; //  should return an invalid number
    //     }
    //     HashMap<Integer, Integer> map = new HashMap<>();
    //     for (int i = 0; i < nums.size(); i++) {
    //         int x = nums.get(i);
    //         if (map.containsKey(x)) {
    //             map.put(x, map.get(x) + 1);
    //         } else {
    //             map.put(x, 1);
    //         }
    //         if (map.get(x) > nums.size() / k) {
    //             return x;
    //         }
    //     }
    //     return -1;
    // }
    
    // Majority Number I, II, III思路基本一致，都是投票问题
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        // write your code
        // 一下链接讲解非常到位 能找到的最好的了
        // http://www.cnblogs.com/yuzhangcmu/p/4175779.html
        if (nums == null || nums.size() == 0) {
            return Integer.MIN_VALUE;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
            if (map.size() >= k) {
                reduce(map);
            }
        }
        // 只是知道map中包含target value，但是不知道是哪一个；所以需要重新数
        for (Integer key : map.keySet()) {
            map.put(key, 0);
        }
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }    
        }
        // find the frequency that is larget than n/3
        for (Integer key : map.keySet()) {
            if (map.get(key) > nums.size() / k) {
                return key;
            }
        }
        return Integer.MIN_VALUE;
    }
    public void reduce(HashMap<Integer, Integer> map) {
        Set<Integer> keys = new HashSet<Integer>(map.keySet()); // 如果不新创建(deep copy)一个set，则后面删除，会对keySet本身产生影响; 则for循环iterator出错
        for (Integer key : keys) {
            if (map.get(key) == 1) {
                map.remove(key);
            } else {
                map.put(key, map.get(key) - 1);
            }
        }
    }
}
```


