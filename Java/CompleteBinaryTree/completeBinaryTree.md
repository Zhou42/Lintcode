# Complete Binary Tree
```java
public boolean isComplete(TreeNode root) {
    // Write your code here
    if (root == null) {
        return true;
    } 
    // 主要的判断条件
    // 只有满足以下条件的二叉树，才是complete的；其余都是渣渣，return false
    if (depth(root.left) == depth(root.right)) {
        if (isFull(root.left) && isComplete(root.right)) {
            return true;
        } else {
            return false;
        }
    } else if(depth(root.left) == depth(root.right) + 1) {
        if (isComplete(root.left) && isFull(root.right)) {
            return true;
        } else {
            return false;
        } 
    }
    return false;
}



public boolean isFull(TreeNode root) {
    if (root == null) {
        return true;
    }
    if (isFull(root.left) && isFull(root.right) && depth(root.left) == depth(root.right)) {
        return true;
    }
    return false;
}
public int depth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    return Math.max(depth(root.left), depth(root.right)) + 1;
}
```