#  Implement Trie
注意代码的简洁性
```java
/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {
    // Initialize your data structure here.
    // 注意trie的node中的内容，存在parent的hashmap中，所以每个node中只需要map和isword
    // map初始化为空，而不是null
    HashMap<Character, TrieNode> children;
    boolean isword;
    public TrieNode() {
        this.children = new HashMap<Character, TrieNode>();
        this.isword = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    // 这里可以使用九章的for循环，更容易控制
    // update后的curt的位置是word.charAt(pos)
    public void insert(String word) {
        if (search(word)) {
            return;
        }
        TrieNode curt = root;
        // int pos = 0;
        // while (pos < word.length() && curt.children.containsKey(word.charAt(pos))) {
        //     curt = curt.children.get(word.charAt(pos));
        //     pos++;
        // }
        // // insert
        // while (pos < word.length()) {
        //     curt.children.put(word.charAt(pos), new TrieNode());
        //     curt = curt.children.get(word.charAt(pos));
        //     pos++;
        // }
        for (int pos = 0; pos < word.length(); pos++) {
            char ch = word.charAt(pos);
            if (curt.children.containsKey(ch)) {
                curt = curt.children.get(ch);
            } else {
                curt.children.put(ch, new TrieNode());
                curt = curt.children.get(ch);
            }
        }
        // 此时有两种情况，一种是根本没有insert，只是原来word是一个prefix，现在只需要改curt的isword值即可
        // 或者此时的curt node是insert出来的，该node对应word的最后一个char
        curt.isword = true;
    }

    // Returns if the word is in the trie.
    // 可以简化search 和 startsWith
    // public boolean search(String word) {
    //     TrieNode curt = root;
    //     int pos = 0;
    //     while (pos < word.length() && curt.children.containsKey(word.charAt(pos))) {
    //         curt = curt.children.get(word.charAt(pos));
    //         pos++;
    //     }
    //     if (pos != word.length()) {
    //         return false;
    //     }
    //     if (curt.isword == false) {
    //         return false;
    //     }
    //     return true;
    // }

    // // Returns if there is any word in the trie
    // // that starts with the given prefix.
    // public boolean startsWith(String prefix) {
    //     TrieNode curt = root;
    //     int pos = 0;
    //     while (pos < prefix.length() && curt.children.containsKey(prefix.charAt(pos))) {
    //         curt = curt.children.get(prefix.charAt(pos));
    //         pos++;
    //     }
    //     if (pos != prefix.length()) {
    //         return false;
    //     }
    //     return true;
    // }
    public boolean search(String word) {
        TrieNode curt = findPos(word);
        if (curt == null || curt.isword == false) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean startsWith(String prefix) {
        TrieNode curt = findPos(prefix);
        if (curt == null) {
            return false;
        } else {
            return true;
        }
    }
    public TrieNode findPos(String word) {
        TrieNode curt = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curt.children.containsKey(ch)) {
                curt = curt.children.get(ch);
            } else {
                return null;
            }
        }
        return curt;
    }
    
}
```
