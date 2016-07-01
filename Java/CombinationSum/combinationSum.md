# Combination Sum

```java
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
     
    // 基于DFS的搜索
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        if (candidates == null || candidates.length == 0) {
            return null;
        }
        // candidates可能不是排序数组，先从小到大排序
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        helper(candidates, 0, path, res, target);
        return res;
    }
    // path是从root到recursion所在node的路径上所有node集合；pos说的是，在排序了的candidates数组，开始考虑的数字的index，目的是result不重复和保持non-decreasing，如不会出现如[2,2,3]和[2,3,2]重复；
    // 例如是[2, 3, 6, 7], 为了顺序
    //                         root
    //                /       |       |      \
    //               2        3       6       7
    //          /  |  |  \   / | \    | \     | 
    //          2  3  6  7  3  6  7   6  7    7
    //          ...  / \ | /|\ |\ \   |\  |   |
    //               6 7 7 367 6 7 7  6 7 7   7
    private void helper(int[] candidates, int pos, List<Integer> path, List<List<Integer>> res, int target) {
        if (path != null && path.size() != 0) {
            int sum = 0;
            for (Integer each: path) {
                sum += each;
            }
            if (sum > target) {
                return;
            } 
            if (sum == target) {
                // 注意这里要deep copy，同时ArrayList可以直接这样deep copy，元素顺序不变 － 应该是除了地址外，复制前后都相同
                List<Integer> copyPath = new ArrayList<>(path);
                res.add(copyPath);
            }
        }
        // 以下其实是位于某个节点，向下选分支的过程，比如该节点是node label 3，则下面分支会有3，6，7，该层的pos说的是，以下分支节点，可以从3以及以后考虑。因此分别加入这三个节点（当然每个分支recursion后要移除该分支节点）
        // 注意path是non-decreasing的
        // prev是为了去重，防止dupicate elements in the candidates。如果candidates中有重复元素，会导致result中有重复集合。可以容易的从tree中看出来
        int prev = -1;
        for (int i = pos; i < candidates.length; i++) {
            if (prev == -1 || candidates[i] != prev) {
                path.add(candidates[i]);
                helper(candidates, i, path, res, target);
                path.remove(path.size() - 1);
            }
            prev = candidates[i];
        }
    }
```
