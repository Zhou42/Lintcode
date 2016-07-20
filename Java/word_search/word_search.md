# Word Search
见注释，注意此类题要形成模版 (像binary search一样成熟的模版)
```java
    public boolean exist(char[][] board, String word) {
        // write your code here
        // for each char:
        //    DFS -> word
        for (int i = 0; i < board.length; i++) { // row
            for (int j = 0; j < board[0].length; j++) { // col
                char[][] used = new char[board.length][board[0].length];
                if(existDFS(board, used, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    // public boolean existDFS(char[][] board, char[][] used, String word, int i, int j, int pos) {
    //     // 注意这里找到一个path即可返回！！！ 不需要搜索所有路径！！
    //     // 虽然potentially 最差的还是要搜索所有路径，但是一般情况会少很多
    //     if (pos == word.length() - 1) {
    //         // whole word is matched
    //         return board[i][j] == word.charAt(pos);
    //     }
    //     boolean res = false;
    //     if (board[i][j] == word.charAt(pos)) {
    //         used[i][j] = 1; // 后面记得还原
    //         if (i > 0 && used[i - 1][j] == 0) {
    //             // there is char above and is not used
    //             boolean up = existDFS(board, used, word, i - 1, j, pos + 1);
    //             if (up) {
    //                 return true; //  
    //             }
    //         }
    //         if (i < board.length - 1 && used[i + 1][j] == 0) {
    //             // there is char below and is not used
    //             boolean down = existDFS(board, used, word, i + 1, j, pos + 1);
    //             if (down) {
    //                 return true;
    //             }
    //         }
    //         if (j > 0 && used[i][j - 1] == 0) {
    //             // there is char left and is not used
    //             boolean left = existDFS(board, used, word, i, j - 1, pos + 1);
    //             if (left) {
    //                 return true;
    //             }
    //         }
    //         if (j < board[0].length - 1 && used[i][j + 1] == 0) {
    //             // there is char right and is not used
    //             boolean right = existDFS(board, used, word, i, j + 1, pos + 1);
    //             if (right) {
    //                 return true;
    //             }
    //         }
    //         // 由于所有分支用一个used table，所以每个分支使用完，一定要还原used table
    //         // 基于上面一句假设，这里最开始使used[i][j] = 1，经历了所有分支后，每个分支均已️还原used，则现在used跟刚进入existDFS时候比，只是used[i][j]改变了，故使used[i][j] = 0即可还原
    //         used[i][j] = 0;
    //         return false;
    //     } else {
    //         return false;
    //     }
    // }
    public boolean existDFS(char[][] board, char[][] used, String word, int i, int j, int pos) {
        // 以后这类题，递归出口就这样写
        if (pos == word.length()) {
            // whole word is matched
            return true;
        }
        // 棋盘类的判断条件都这样写
        // 无论是走到board外面(此时word还没结束)，还是pos处没有match上，都false
        // 其实这里是在判断，该child分支，是否valid需要继续考虑
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || word.charAt(pos) != board[i][j] || used[i][j] == 1) {
            return false;
        }
        // pos这个char match上了，可以继续递归了
        // 这里标记是否使用过board[i][j]，也可以直接把board[i][j]改为'#'，结尾再改回来
        used[i][j] = 1;
        // 注意这里只要有一个返回真，有“短路”作用，直接后面的就不计算了，跟我上面的程序作用一样；但是这样更简洁
        boolean res = existDFS(board, used, word, i + 1, j, pos + 1) ||
                        existDFS(board, used, word, i - 1, j, pos + 1) ||
                        existDFS(board, used, word, i, j + 1, pos + 1) ||
                        existDFS(board, used, word, i, j - 1, pos + 1);
        // 保证返回上一层的used不变
        used[i][j] = 0;
        return res;
        
    }
    
 

```