# Subsets II 解题思路

```java
// 这道题的重点是去重：如何保证没有重复的subsets
// 关键是递归的某branch的每层，不能出现重复的元素 -> 否则会出现重复subsets
// For example: {1(1), 1(2), 2}
//                 []            ............... layer 1
//               /    \
//             1(1)    2         ............... layer 2 -> 这个layer不能有1(2)
//            /    \   
//           1(2)   2            ............... layer 3
// 比如layer 2 不能有1(2),否则跟1(1)的branch重复
// 每层只考虑从 pos到S数组结尾的数字



public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
    ArrayList<Integer> path = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    Collections.sort(S);
    subsetsWithDupHelper(S, 0, path, res);
    return res;
}

private void subsetsWithDupHelper(ArrayList<Integer> S, int pos, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res) {
    res.add(new ArrayList<Integer>(path));
    // 每个recursion考虑的是某个branch的一层
    // the numbers that I consider in one layer in one recursion
    for (int i = pos; i < S.size(); i++) {
        // 这句话重要；如果是该layer的第一个(pos处的数字)，则一定没有重复，可以继续；如果是第二个或者以后的数字，要看一下是否与前一个数字相同，如果相同，则要在本layer跳过
        if (i != pos && S.get(i) == S.get(i - 1)) {
            continue;
        }
        // 除了以上被跳过的number，其余的是该branch本层可以使用的number
        path.add(S.get(i));
        // 向下一层递归，在path下面继续考虑从i + 1开始的number
        subsetsWithDupHelper(S, i + 1, path, res);
        path.remove(path.size() - 1);
    }
}
```