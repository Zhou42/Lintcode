```java
public TreeNode buildTree(int[] inorder, int[] postorder) {
    // write your code here
    if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) {
        return null;
    }
    TreeNode root = buildTreeHelper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    return root;
}
// 需要这个helper函数，因为递归的每一层要控制考虑数组的哪一段。使用起始和终止坐标控制
public TreeNode buildTreeHelper(int[] inorder, int[] postorder, int s_i, int e_i, int s_p, int e_p) {
    // 递归的每一层输入为：inorder和postorder两个数组，分别的起始坐标和终止坐标
    // 递归出口为，起始坐标大于终止坐标
    if (s_i > e_i) {
        return null;
    }
    int num = e_p - s_p + 1;
    int pos = -1;
    TreeNode root = new TreeNode(postorder[e_p]);
    for (int i = 0; i < num; i++) {
        if (root.val == inorder[i + s_i]) {
            pos = i;
            break;
        }
    }
    root.left = buildTreeHelper(inorder, postorder, s_i, s_i + pos - 1, s_p, s_p + pos - 1);
    root.right = buildTreeHelper(inorder, postorder, s_i + pos + 1, e_i, s_p + pos, e_p - 1);
    return root;
}
```