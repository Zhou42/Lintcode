# Add\ and\ Search\ Word
跟九章的思路一样，使用trie。

```java
public class WordDictionary {
    
    // Yang's solution
    
    class TrieNode {
        char c;
        boolean isWord;
        HashMap<Character, TrieNode> children;
        TrieNode(char c) {
            this.c = c;
            this.isWord = false;
            this.children = new HashMap<Character, TrieNode>();
        }
    }
    TrieNode root = new TrieNode('x'); // dummy
    // Adds a word into the data structure.
    public void addWord(String word) {
        // Write your code here
        TrieNode curt = root;
        for (int i = 0; i < word.length(); i++) {
            if (curt.children.containsKey(word.charAt(i))) {
                curt = curt.children.get(word.charAt(i));
            } else {
                // add new children to node curt
                curt.children.put(word.charAt(i), new TrieNode(word.charAt(i)));
                curt = curt.children.get(word.charAt(i));
            }
            if (i == word.length() - 1) {
                curt.isWord = true;
            }
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        // Write your code here
        return searchHelper(word, 0, root);
    }
    // head is the node that for index (pos - 1); 
    // 也就是说 pos针对的是head的children
    public boolean searchHelper(String word, int pos, TrieNode head) {
        // 注意这里head一定不为空 － 可以从调用情况得出
        char c = word.charAt(pos); // the char that is currently considered
        if (pos == word.length() - 1) {
            // when the last char is reached
            // 这个是递归出口 - 这样写感觉比九章答案更清晰
            if (head.children.containsKey(c)) {
                if (head.children.get(c).isWord) {
                    return true;
                } else {
                    return false;
                }
            } else if (c == '.') {
                for (TrieNode each : head.children.values()) {
                    if (each.isWord) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        }
        // pos不是word的最后一个char，pos后面还有char
        boolean res = false;
        // 只有当往下递归pos后面的word剩余部分被完全match，res才为true
        // 如果head的children为空，也就是head下面没结点了，res就返回false
        // 如果匹配不上，也返回false
        //           head
        //          /  |  \
        //         (children)
        
        if (c == '.') {
            for (TrieNode each : head.children.values()) {
                // 只要一个branch match上了就是true
                res = res || searchHelper(word, pos + 1, each);
            }
        } else if (head.children.containsKey(c)) { // 如果进入这个循环，head的children一定不空
            res = searchHelper(word, pos + 1, head.children.get(c));
        } else { // 如果word.charAt(pos) match 不上任何node
            return false;
        }
        return res;
    }
    
}




// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
```