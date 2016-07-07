# 具体讲解见
https://www.youtube.com/watch?v=gcULXE7ViZw

# 使用dummy node
```java
// With Dummy node 
public TreeNode removeNode(TreeNode root, int value) {
    // write your code here
    // 找到值是value的target，以及其parent
    TreeNode dummy = new TreeNode(0);
    dummy.left = root;
    // 找到target的parent; 如果root是null，则找到dummy; 如果value没找着，则返回target直到null之前的node
    TreeNode parent = getParent(dummy, root, value);
    // 看value是否被找到了，上面两种结果，都是value没被找到，直接返回root就成了
    TreeNode target;
    if (parent.left != null && parent.left.val == value) {
        // 找到了value，在parent的左边
        target = parent.left;
    } else if (parent.right != null && parent.right.val == value) {
        // 找到了value，在parent的右边
        target = parent.right;
    } else {
        // 没找到value
        return root;
    }
    
    // if target node has no children!! Easy!
    if (target.left == null && target.right == null) {
        // 一个sb的错误... 不能直接把target改动，要改parent的指针。因为这里是parent的指针和target的地址相等，改了target的地址，parent指针是不会变的
        // target = null; 错误❌
        if (parent.left == target) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    // 如果target的left child为null，而right child不为null，则target.right接到parent上
    } else if (target.left == null) {
        if (parent.left == target) {
            parent.left = target.right;
        } else {
            parent.right = target.right;
        }
    } else if (target.right == null) {
        if (parent.left == target) {
            parent.left = target.left;
        } else {
            parent.right = target.left;
        }
    // target有两个children ~_~
    } else {
        // 找到target右子树minimum
        TreeNode target_temp = target.right, parent_temp = target;
        while (target_temp.left != null) {
            parent_temp = target_temp;
            target_temp = target_temp.left;
        }
        // 复制给target node
        target.val = target_temp.val;
        // 删除target_temp节点
        // 如果target_temp 左右children都是null
        if (target_temp.right == null) {
            // 说明是left child
            if (parent_temp.left == target_temp) {
                parent_temp.left = null;
            // right child
            } else {
                parent_temp.right = null;
            }
        // 如果target有right child
        } else {
            if (parent_temp.left == target_temp) {
                parent_temp.left = target_temp.right;
            // right child
            } else {
                parent_temp.right = target_temp.right;
            }
        }
    }
    return dummy.left;
}

// 找到target的parent
private TreeNode getParent(TreeNode parent, TreeNode target, int value) {
    while (target != null) {
        if (value < target.val) {
            parent = target;
            target = target.left;
        } else if (value > target.val) {
            parent = target;
            target = target.right;
        } else {
            return parent;
        }
    }
    return parent;
}

```