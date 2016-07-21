# Word Search II
基本与九章的思路相同，写起来的复杂程度也差不多。  
九章在trie node中存string的方式 能够简化一点点程序，其实差不多。  

```java
public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
     
    int[] dx = {0, 0, -1, +1};
    int[] dy = {-1, +1, 0, 0};
    class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean isWord;
        // 每个node的内容包含在了hashmap中
        TrieNode() {
            this.isWord = false;
            this.children = new HashMap<Character, TrieNode>();
        }
    }
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        // write your code here
        TrieNode root = buildTrie(words);
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] isUsed = new boolean[board.length][board[0].length];
                StringBuilder path = new StringBuilder();
                dfs(board, i, j, isUsed, root, path, res);
            }
        }
        return res;
    }
    
    public TrieNode buildTrie(ArrayList<String> words) {
        TrieNode root = new TrieNode();
        TrieNode curt;
        for (String word : words) {
            curt = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curt.children.containsKey(c)) {
                    curt = curt.children.get(c);
                } else {
                    // is c does not exist in the children
                    curt.children.put(c, new TrieNode());
                    curt = curt.children.get(c);
                }
            }
            curt.isWord = true;
        }
        return root;
    }
    
    public void dfs(char[][] board, int i, int j, boolean[][] isUsed, TrieNode root, StringBuilder path, ArrayList<String> res) {
        if (root.isWord && !res.contains(path.toString())) { 
            // root 是已经match过的
            res.add(path.toString());
        }
        if (!isInBoard(board, i, j) || isUsed[i][j] || root.children == null) {
        	return;
        }
        // 这一层考虑的是
        //          root
        //        /   |   \
        // curt ->        <- board[i][j] 这一层是正在思考的
        if (!root.children.containsKey(board[i][j])) {
            return;
        }
        path.append(board[i][j]);
        isUsed[i][j] = true;
        // board[i][j]已经match上 next 
        // next是本层考虑的TrieNode
        TrieNode next = root.children.get(board[i][j]); 
        // 继续
        for (int t = 0; t < 4; t++) {
            int x = i + dx[t];
            int y = j + dy[t];
            // 
            dfs(board, x, y, isUsed, next, path, res);
        }
        isUsed[i][j] = false;
        path.delete(path.length() - 1, path.length());
    }
    
    public boolean isInBoard(char[][] board, int x, int y) {
        return x >= 0 && y >= 0 && x <= board.length - 1 && y <= board[0].length - 1;
    }
}
```