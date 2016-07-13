# Kth Largest in N Arrays
1. 关于java中的comparator的用法
http://www.blogjava.net/yesjoy/articles/126046.html
可以注意一下，java中默认的自然顺序

2. 重新写Comparator的时候, 为什么mycomparator不用声明<Node>，即所操作元素类型？
```java
class mycomparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        return node2.val - node1.val;
    }
}
```

## Code
```java
    // 如果使用最大堆，每个最大元素删除后，需要知道其所在行和列
    class Node {
        int val, arrID, elemID;
        Node(int val, int arrID, int elemID) {
            this.val = val;
            this.arrID = arrID;
            this.elemID = elemID;
        }
        
    }
    // 因为PriorityQueue默认最小堆，而需要最大堆，需要重写comparator
    class mycomparator implements Comparator<Node> {
        @Override
        public int compare(Node node1, Node node2) {
            return node2.val - node1.val;
        }
    }
    
    public int KthInArrays(int[][] arrays, int k) {
        Queue<Node> Q = new PriorityQueue<Node>(k, new mycomparator());
        for (int i = 0; i < arrays.length; i++) {
            Arrays.sort(arrays[i]);
            // put the last element in heap
            if (arrays[i] != null && arrays[i].length != 0) {
                Q.add(new Node(arrays[i][arrays[i].length - 1], i, arrays[i].length - 1));
            }
        }
        // 取前K大的数
        for (int i = 0; i < k; i++) {
            Node temp = Q.poll();
            if (i == k - 1) {
                return temp.val;
            }
            // 只要被取出的数所在array不空，就向heap中放入该array的下一个数
            // heap中数字的个数，等于不空的array的数目；或者说 heap包含所有非空array的head
            if (temp.elemID != 0) {
                Q.add(new Node(arrays[temp.arrID][temp.elemID - 1], temp.arrID, temp.elemID - 1));
            }
        }
        return 0;
    }
```


