# Max Tree
整道题的思路就是找到每个node的parent即可。使用decreasing stack，每个元素操作为O(1), 故整体复杂度是O(n)。

# 初期的code，待改进简化
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
    
    
    
    // 使用decreasing stack，复杂度是O(n);
    // decreasing stack的一些性质：
    // 1. 某A[i]放入时，最后一个pop出来的number 是其左子树的root, 故A[i]的left指向这个number
    // 2. 连续被pop出来的number，是父子关系，后pop出来的number是父节点，由于其更大
    // 3. 当所有的number都考虑完了，假象放入一个无穷小，把所有的number 都按照以上规则pop出来 -> 整个树就建好了
    // 
    public TreeNode maxTree(int[] A) {
        // write your code here
        Stack<TreeNode> decStk = new Stack<>();
        TreeNode curt = new TreeNode(0);
        for (int i = 0; i < A.length; i++) {
            TreeNode newNode = new TreeNode(A[i]);
            if (i == 0) {
                decStk.add(newNode);
            } else {
                while (!decStk.isEmpty() && decStk.peek().val < A[i]) {
                    curt = decStk.pop();
                    if (decStk.isEmpty()) {
                        // decStk.push(new TreeNode(A[i]));
                        newNode.left = curt;
                        break;
                    } else {
                        if (decStk.peek().val < A[i]) {
                            decStk.peek().right = curt;
                        } else {
                            // decStk.push(new TreeNode(A[i]));
                            newNode.left = curt;
                            break;
                        }
                    }
                }
                decStk.push(newNode);
            }
        }
        while (!decStk.isEmpty()) {
            curt = decStk.pop();
            if (!decStk.isEmpty()) {
                decStk.peek().right = curt;
            }
        }
        return curt;
    }
```