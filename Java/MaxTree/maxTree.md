# Max Tree
整道题的思路就是找到每个node的parent即可。使用decreasing stack，每个元素操作为O(1), 故整体复杂度是O(n)。

这个算法还需要具体的查资料，不容易想到。。。不常用到的思路

# Code
```java
	// 思路整理
    // 整道题的思路就是找到每个node的parent即可
    // 对于某数列，比如 [2, 5, 6, 0, 3, 1]，每个数(除了root最大的节点之外)都有parent节点
    // 定理1: 该parent结点(如果存在)，一定是左面第一个比自己大的数，或者是右面第一个比自己大的数
    // 证明1: 首先parent一定比自己大；其次，考虑左面如果有比自己大的数(x, y, z...)，parent只能是第一个(离自己最近的那个)，而不可能是第二个，三个。。。；否则比如考虑数字1，左面第一个比1大的数是3，如果假设1的parent为5(第三个比1大的数)，则意味着5的右子树的root是1，且1是右子树的最大值；显然(第一，二个比1大的数不服...); 同理证明右面的情况；
    // 定理2: parent是左右第一个比自己大的数中较小的那一个。
    // 证明2: 考虑数字0，左面第一个比自己大的数是6， 右面第一个比自己大的数是3；假设0选择6作为parent(较大的那个)，则0成为6右子树最大值，也就是root，显然3表示不服。故parent是min{左面第一个比自己大的， 右面第一个比自己大的}
    
    // 这也是为什么decreasing stack其作用，decreasing stack中，位于某个元素左面的是第一个比自己大的数，把自己pop出来的数是右面第一个比自己大的数
    
    // 所以这道题只需要：
    // 每个元素出栈的时候，判断自己的parent是谁，改指针即可；而stack中一定最后剩的是root，也就是最大值，返回即可
    
    public TreeNode maxTree(int[] A) {
        // define a decrasing stack
        Stack<TreeNode> stk = new Stack<>();
        // 对所有的TreeNode，都是出栈时指定parent结点
        for (int i = 0; i < A.length; i++) {
            // 当stk不空，且第一个num小于A[i]的时候，为保证递减栈，则pop出来第一个num
            TreeNode node = new TreeNode(A[i]);
            // 当1.stk不为空 2.stk的head比A[i]小(否则不会被pop出stk)，进行while
            while (!stk.isEmpty() && stk.peek().val < node.val) {
                // pop保证是递减栈，同时指定该元素的parent
                TreeNode curt = stk.pop();
                // 如果pop出来之后为空，则curt是A[i]左儿子(因为所有A[i]左侧的数都比A[i]小，组成A[i]的左子树，其中最大的curt，一定是root); 如果pop出来之后不空, 则需要比较stk.peek() 与 A[i]的大小关系
                if (stk.isEmpty()) {
                    node.left = curt; // 如果stk为空，则A[i]是curt的parent
                } else {
                    // 如果stk的head比A[i]小，则stk的head是curt的parent
                    if (stk.peek().val < node.val) {
                        stk.peek().right = curt;
                    } else { //如果stk的head比A[i]大，则A[i]是curt的parent
                        node.left = curt;
                    }
                }
            }
            stk.push(node);
        }
        while (stk.size() > 1) {
            TreeNode curt = stk.pop();
            stk.peek().right = curt;
        }
        return stk.pop();
    }
```