# K sum II
也可以借鉴九章的做法，都是dfs，写法不同而已。以下做法也很直观，也是普遍采用的方式。

```java
	public ArrayList<ArrayList<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        // 与K sum I不同，这里要求的是所有result，而不是result的数目。故要用搜索，而不是DP
        // 这道题里，变量需要保证序顺序，以防结果重复
        // 我这个思路也是网上很多人的思路，九章的也可以借鉴。
        // define the variables
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        int[] used = new int[A.length];
        int pos = 0; // 每一层recursion 只考虑pos开始到结尾的数字，防止重复的res
        kSumIIHelper(A, k, target, pos, path, used, res);
        return res;
    }
    
    private void kSumIIHelper(int[] A, int k, int target, int pos, ArrayList<Integer> path, int[] used, ArrayList<ArrayList<Integer>> res) {
        if (getSum(path) > target) {
            return;
        }
        // if sum of path is <= target
        if (path.size() == k) {
            if (getSum(path) == target) {
                res.add(new ArrayList<Integer>(path));
            }
            return;
        }
        // if sum of path is <= target && path.size() < k
        for (int i = pos; i < A.length; i++) {
            if (used[i] == 0) {
                path.add(A[i]);
                used[i] = 1;
                kSumIIHelper(A, k, target, i + 1, path, used, res);
                path.remove(path.size() - 1);
                used[i] = 0;
            }
        }
    }
    
    private int getSum(ArrayList<Integer> path) {
        int sum = 0;
        for (Integer i: path) {
            sum = sum + i;
        }
        return sum;
    }
```