# Convert Binary Search Tree to Doubly Linked List
## 对于Doubly Linked List 定义一个新的数据结构，可以简化code
- Singular Linked List只需要一个head，Doubly Linked List 需要head和tail

## Code
```java
class ResultType {
    DoublyListNode head, tail;
    public ResultType(DoublyListNode head, DoublyListNode tail) {
        this.head = head;
        this.tail = tail;
    }
}

public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
     
    // Yang's solution
    // public DoublyListNode bstToDoublyList(TreeNode root) {  
    //     // Write your code here
    //     if (root == null) {
    //         return null;
    //     }
    //     DoublyListNode lefthead = bstToDoublyList(root.left);
    //     DoublyListNode righthead = bstToDoublyList(root.right);
    //     // 这两个情况不需要，也不能向下走
    //     while (lefthead != null && lefthead.next != null) {
    //         lefthead = lefthead.next;
    //     }
    //     DoublyListNode middle = new DoublyListNode(root.val);
    //     middle.prev = lefthead;
    //     middle.next = righthead;
    //     // 在DoublyList边界，只需要指向null 就完成了。所以要查middle的左右到底是不是null
    //     if (lefthead != null) {
    //         lefthead.next = middle;
    //     }
    //     if (righthead != null) {
    //         righthead.prev = middle;
    //     }
    //     // 这里假设，最左面是head
    //     while (middle.prev != null) {
    //         middle = middle.prev;
    //     }
    //     return middle;
    // }
    
    // 九章仿照
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        // 单向列表，只需要head；双向列表，可以考虑定义一个数据结构，包含head和tail
        if (root == null) {
            return null;
        }
        ResultType res = bstToDoublyListHelper(root);
        return res.head;
    }
    public ResultType bstToDoublyListHelper(TreeNode root) {
        if (root == null) {
            return new ResultType(null, null);
        }
        DoublyListNode middle = new DoublyListNode(root.val);
        ResultType left = bstToDoublyListHelper(root.left);
        ResultType right = bstToDoublyListHelper(root.right);
        ResultType res = new ResultType(null, null);
        if (left.head == null) {
            res.head = middle;
        } else {
            left.tail.next = middle;
            middle.prev = left.tail;
            res.head = left.head;
        }
        if (right.head == null) {
            res.tail = middle;
        } else {
            middle.next = right.head;
            right.head.prev = middle;
            res.tail = right.tail;
        }
        return res;
    }
}
```
