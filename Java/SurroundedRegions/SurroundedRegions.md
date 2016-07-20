# Surrounded Regions

- 使用DFS 由于递归栈太深 导致 StackOverflowError
- sb... 很大的矩阵 得多少层递归啊！！简单的bfs即可解决！！
- 这里要使用BFS
- 九章想法很好 见结尾注释

# Code
- 我的Yang's version 2 的思路

```java
	for each node:
		if('X'):
			continue;
		if('O'):
			if in a valid region (BFS)
				'O' -> 'X' (BFS)
```
Code
```java
    // Yang's version 2
    // note：树上的bfs 只需要一个Queue，因为node不会被重复考虑
    //       图上的bfs需要一个isVisited，用HashMap 数组等，否则Node将被多次放入...
    //       [放入Queue之后]，则说明被考虑了，isVisited变true
    public boolean isValidRegion(char[][] board, int i, int j) {
        // only 'O' will enter this function
        if (isOnBorder(board, new Node(i, j))) {
            return false;
        }
        boolean[][] isTraversed = new boolean[board.length][board[0].length];
        Queue<Node> Q = new LinkedList<>();
        Q.offer(new Node(i, j));
        // once in the queue, this node should not be consider anymore
        isTraversed[i][j] = true;
        while(!Q.isEmpty()) {
            // add
            Node head = Q.poll();
            // every node in Q is not on border, so head is not on border
            for (Node node : expand(board, isTraversed, head)) {
                // the return of expand includes all 'O's around that are not traversed
                if (isOnBorder(board, node)) {
                    return false;
                }
                Q.offer(node);
                isTraversed[node.x][node.y] = true;
            }
        }
        return true;
    }
    public List<Node> expand(char[][] board, boolean[][] isTraversed, Node curt) {
        List<Node> res = new ArrayList<Node>();
        // four directions
        if (board[curt.x + 1][curt.y] == 'O' && isTraversed[curt.x + 1][curt.y] == false) {
            res.add(new Node(curt.x + 1, curt.y));
        }
        if (board[curt.x - 1][curt.y] == 'O' && isTraversed[curt.x - 1][curt.y] == false) {
            res.add(new Node(curt.x - 1, curt.y));
        }
        if (board[curt.x][curt.y - 1] == 'O' && isTraversed[curt.x][curt.y - 1] == false) {
            res.add(new Node(curt.x, curt.y - 1));
        }
        if (board[curt.x][curt.y + 1] == 'O' && isTraversed[curt.x][curt.y + 1] == false) {
            res.add(new Node(curt.x, curt.y + 1));
        }
        return res;
    }
    public void captureRegion(char[][] board, int i, int j) {
        boolean[][] isTraversed = new boolean[board.length][board[0].length];
        Queue<Node> Q = new LinkedList<>();
        Q.offer(new Node(i, j));
        isTraversed[i][j] = true;
        while(!Q.isEmpty()) {
            // add
            Node head = Q.poll();
            board[head.x][head.y] = 'X';
            // every node in Q is not on border, so head is not on border
            for (Node node : expand(board, isTraversed, head)) {
                // the return of expand includes all 'O's around that are not traversed
                Q.offer(node);
                isTraversed[node.x][node.y] = true;
            }
        }
    }
    
    public boolean isOnBorder(char[][] board, Node node) {
        // is on border
        if (node.x == 0 || node.x == board.length - 1 || node.y == 0 || node.y == board[0].length - 1) {
            return true;
        }
        return false;
    }
    class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // 九章的想法💡很好
    // 先在border上走一圈，遇到'O' 就bfs 并且标记为'F'，这样把不符合标准的'O'就都变成了'F'。
    // 然后遍历一遍所有，把剩下的符合标准的'O'变成'X'
    // 在遍历一遍，把所有的'F'变成'O'
    // 由于在bfs遍历的过程中，更改了char的值('O' -> 'F')，所以连isVisited也省了
```