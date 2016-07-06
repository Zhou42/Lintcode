# Binary Tree Zigzag Level Order Traversal
1. 关于Queue的实例化问题；java中LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用
- http://www.cnblogs.com/end/archive/2012/10/25/2738493.html
- http://www.runoob.com/java/data-queue.html
- http://www.infoq.com/cn/articles/java-blocking-queue/

2. Stack是一个类，可以实例化
比如，以下声明是合法的
```java
	Queue<Integer> Q = new LinkedList<Integer>();
	Stack<Integer> stk = new Stack<Integer>();
```

3. 原题程序
```java
public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
    // write your code here
    // BFS level order traversal
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if (root == null) {
        return res;
    }
    
    // Queue都可以用什么实例化？？
    Queue<TreeNode> Q = new LinkedList<>();
    // 图上需要标记是否遍历过，树则不用，下面的肯定没有遍历过
    Q.offer(root);
    int controlBit = 0;
    while(!Q.isEmpty()) {
        int size = Q.size();
        ArrayList<Integer> level = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            TreeNode head = Q.poll();
            level.add(head.val);
            if (head.left != null) {
                Q.offer(head.left);
            } 
            if (head.right != null) {
                Q.offer(head.right);
            }
        }
        if (controlBit == 1) {
            Collections.reverse(level);
            controlBit = 0;
        } else {
            controlBit = 1;
        }
        res.add(level);
    }
    return res;
}
```