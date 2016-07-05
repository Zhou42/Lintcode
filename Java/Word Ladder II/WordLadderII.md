# 关于java中的String
```java
String c1 = "abc";
String c2 = new String("abc");
Set<String> set = new HashSet<>();
set.add(c1);
System.out.println(set.contains(c2));
```
输出是true

```java
String c1 = "abc";
String c2 = "abc";
Set<String> set = new HashSet<>();
set.add(c1);
System.out.println(c1 == c2);
```
输出也是true

所以java把所有的相同String整理到一起


# 解题思路
1. 用BFS 找到start到end最小距离，以及所有dict中String到start或者end的距离   --- 复杂度为 26*length*n 而不能是n^2
2. 用DFS 从start到end，或者从end到start找路径。要用到目标距离减小的constraint去prune节点
3. 注意. 这道题中可以只用一个BFS做完1的要求。。。 

这道题可以从start出发考虑DFS，也可以等价从end出发考虑DFS。

实际上，正向用_距离减小条件_prune的DFS，和反向找parent再用_距离减小条件_prune的DFS，效果是一样的。复杂度也是一样的。

所以这道题的重点，实际是从某一个target string到dict中所有的string的distance array的计算

# Method 1: 一种不太对的做法...由于计算到某target的距离时 复杂度是n^2，应优化成 26*length*n 如下一种做法

```java
// 由于这里BFS遍历，假设了tree中元素不重复，故遍历的复杂度等于dict的size。这样的结果是对的，因为重复出现的元素，并不能减小dist的值。（同时也不会影响九章里需要的map和distance的结果）
// 以下这种做法没发通过大的数据。原因是calculateDist建立了所有dict到end的距离，复杂度是O(n^2), n是dict的size。
Map<String, Integer> distArray = new HashMap<>();
public List<List<String>> findLadders(String start, String end, Set<String> dict) {
    // write your code here  
    // use level order traversal to find the shortest distance
    // use recursion to find the paths
    List<List<String>> res = new ArrayList<List<String>>();
    ArrayList<String> path = new ArrayList<String>();
    int dist = findTheShortestDistance(start, end, dict);
    path.add(start);
    calculateDist(dict, end);
    findLaddersHelper(dict, path, dist, res, start, end);
    return res;
}

// 以下DFS搜索，复杂度很高
private void findLaddersHelper(Set<String> dict, ArrayList<String> path, int dist, List<List<String>> res, String start, String end) {
    if (path.size() == dist) {
        if (findDistance(path.get(path.size() - 1) ,end) == 1) {
            ArrayList<String> temp = new ArrayList<>(path);
            temp.add(end);
            res.add(temp);
        }
        return;
    }
    String tail = path.get(path.size() - 1);
    for (String next: getNextWords(tail)) {
        if (!path.contains(next) && dict.contains(next) && distArray.get(next) == distArray.get(tail) - 1) {
            path.add(next);
            findLaddersHelper(dict, path, dist, res, start, end);
            path.remove(path.size() - 1);
        }
    }
}
 // 以下BFS的复杂度是 26*length*n，其中length是单词的长度，n是dict的size；相比构建dict的distance matrix (复杂度O(n^2)), 这个复杂度是O(n)。
 // 由于遍历了整个dict，所以可以得到，每个dict的父节点集合
private int findTheShortestDistance(String start, String end, Set<String> dict){
    Queue<String> Q = new LinkedList<String>();
    // whether the string is considered already
    Set<String> set = new HashSet<>();
    Q.offer(start);
    set.add(start);
    int dist = 0;
    while (!Q.isEmpty()) {
        int size = Q.size();
        dist++;
        for (int i = 0; i < size; i++) {
            String head = Q.poll();
            if (findDistance(head, end) == 1) {
                return dist;
            }
            // 不能这样遍历dict，因为dict可能非常大
            // for (String word: dict) {
            //     if (!set.contains(word) && findDistance(head, word) == 1) {
            //         Q.offer(word);
            //         set.add(word);
            //     }
            // }
            
            //
            for (String word: getNextWords(head)) {
                if (!set.contains(word) && dict.contains(word)) {
                    Q.offer(word);
                    set.add(word);
                }
            }
            
        }
    }
    return dist;
}
private int findDistance(String A, String B) {
    if (A.length() != B.length()) {
        return -1;
    }
    int count = 0;
    for (int i = 0; i < A.length(); i++) {
        if (A.charAt(i) != B.charAt(i)) {
            count++;
        }
    }
    return count;
}

private ArrayList<String> getNextWords(String word) {
    ArrayList<String> res = new ArrayList<String>();
    int length = word.length();
    for (int i = 0; i < length; i++) {
        for (char c = 'a'; c <= 'z'; c++) {
            String temp = replace(word, i, c);
            if (temp != word) {
                res.add(temp);
            }
        }
    }
    return res;
}

private String replace(String word, int pos, char c) {
    StringBuilder sb = new StringBuilder(word);
    sb.setCharAt(pos, c);
    return sb.toString();
}
// 问题在这里，这个过程是n^2的，所以超时
private void calculateDist(Set<String> dict, String target) {
    for (String each: dict) {
        distArray.put(each, findTheShortestDistance(target, each, dict));
    }
}
```
# Method 2: 优化了计算到dict中string某target的距离
```java
Map<String, Integer> distArray = new HashMap<>();
public List<List<String>> findLadders(String start, String end, Set<String> dict) {
    // write your code here  
    // use level order traversal to find the shortest distance
    // use recursion to find the paths
    List<List<String>> res = new ArrayList<List<String>>();
    ArrayList<String> path = new ArrayList<String>();
    int dist = findTheShortestDistance(start, end, dict);
    path.add(start);
    // calculateDist(dict, end);
    // 计算到end的距离向量
    distArrayCalculate(end, dict);
    findLaddersHelper(dict, path, dist, res, start, end);
    return res;
}

private void findLaddersHelper(Set<String> dict, ArrayList<String> path, int dist, List<List<String>> res, String start, String end) {
    if (path.size() == dist) {
        if (findDistance(path.get(path.size() - 1) ,end) == 1) {
            ArrayList<String> temp = new ArrayList<>(path);
            temp.add(end);
            res.add(temp);
        }
        return;
    }
    String tail = path.get(path.size() - 1);
    for (String next: getNextWords(tail)) {
        if (!path.contains(next) && dict.contains(next) && distArray.get(next) == distArray.get(tail) - 1) {
            path.add(next);
            findLaddersHelper(dict, path, dist, res, start, end);
            path.remove(path.size() - 1);
        }
    }
}
// 用来 反向，找到end的距离；复杂度是26*length*n
private void distArrayCalculate(String target, Set<String> dict) {
    Queue<String> Q = new LinkedList<String>();
    // whether the string is considered already
    Set<String> set = new HashSet<>();
    Q.offer(target);
    set.add(target);
    int dist = -1;
    while (!Q.isEmpty()) {
        int size = Q.size();
        dist++;
        for (int i = 0; i < size; i++) {
            String head = Q.poll();
            distArray.put(head, dist);
            for (String word: getNextWords(head)) {
                if (!set.contains(word) && dict.contains(word)) {
                    Q.offer(word);
                    set.add(word);
                }
            }
            
        }
    }
}

private int findTheShortestDistance(String start, String end, Set<String> dict){
    Queue<String> Q = new LinkedList<String>();
    // whether the string is considered already
    Set<String> set = new HashSet<>();
    Q.offer(start);
    set.add(start);
    int dist = 0;
    while (!Q.isEmpty()) {
        int size = Q.size();
        dist++;
        for (int i = 0; i < size; i++) {
            String head = Q.poll();
            if (findDistance(head, end) == 1) {
                return dist;
            }
            // 不能这样遍历dict，否则这个BFS整体复杂度是O(n^2)， n是dict的size
            // for (String word: dict) {
            //     if (!set.contains(word) && findDistance(head, word) == 1) {
            //         Q.offer(word);
            //         set.add(word);
            //     }
            // }
            
            // BFS整体复杂度降为 26*length*n，n是dict的size
            for (String word: getNextWords(head)) {
                if (!set.contains(word) && dict.contains(word)) {
                    Q.offer(word);
                    set.add(word);
                }
            }
            
        }
    }
    return dist;
}
private int findDistance(String A, String B) {
    if (A.length() != B.length()) {
        return -1;
    }
    int count = 0;
    for (int i = 0; i < A.length(); i++) {
        if (A.charAt(i) != B.charAt(i)) {
            count++;
        }
    }
    return count;
}

private ArrayList<String> getNextWords(String word) {
    ArrayList<String> res = new ArrayList<String>();
    int length = word.length();
    for (int i = 0; i < length; i++) {
        for (char c = 'a'; c <= 'z'; c++) {
            String temp = replace(word, i, c);
            if (temp != word) {
                res.add(temp);
            }
        }
    }
    return res;
}

private String replace(String word, int pos, char c) {
    StringBuilder sb = new StringBuilder(word);
    sb.setCharAt(pos, c);
    return sb.toString();
}


```

# Method 3， 合并了2中两个BFS，比较简洁的最终算法
1. 可能的改进：直接用Map记录search中每个String的next Strings都是什么，这样就不用每次都重复计算了，也就是不用重复调用getNextWords()了

```java
Map<String, Integer> distArray = new HashMap<>();
public List<List<String>> findLadders(String start, String end, Set<String> dict) {
    // write your code here  
    // use level order traversal to find the shortest distance
    // use recursion to find the paths
    List<List<String>> res = new ArrayList<List<String>>();
    ArrayList<String> path = new ArrayList<String>();
    path.add(start);
    // 计算dict中各个string到end的距离向量,同时返回start到end最小距离
    int minDistStartToEnd = distArrayCalculate(start, end, dict);
    findLaddersHelper(dict, path, minDistStartToEnd, res, start, end);
    return res;
}
// DFS找start到end的路径
private void findLaddersHelper(Set<String> dict, ArrayList<String> path, int minDistStartToEnd, List<List<String>> res, String start, String end) {
    if (path.size() == minDistStartToEnd) {
        if (findDistance(path.get(path.size() - 1), end) == 1) {
            ArrayList<String> temp = new ArrayList<>(path);
            temp.add(end);
            res.add(temp);
        }
        return;
    }
    String tail = path.get(path.size() - 1);
    // 考虑该branch，下面的children nodes，做一些prune的简化
    for (String next: getNextWords(tail)) {
        if (!path.contains(next) && dict.contains(next) && distArray.get(next) == distArray.get(tail) - 1) {
            path.add(next);
            findLaddersHelper(dict, path, minDistStartToEnd, res, start, end);
            path.remove(path.size() - 1);
        }
    }
}
// BFS反向，找dict中各个String到end的距离；同时计算start到end最小距离；复杂度是26*length*n
private int distArrayCalculate(String start, String end, Set<String> dict) {
    Queue<String> Q = new LinkedList<String>();
    // whether the string is considered already
    Set<String> set = new HashSet<>();
    Q.offer(end);
    set.add(end);
    int distToEnd = -1;
    int flag = -1; // 用来控制记录，第一次找到start时的距离，也就是start与end的最小距离
    int minDistStartToEnd = -1;
    while (!Q.isEmpty()) {
        int size = Q.size();
        distToEnd++;
        for (int i = 0; i < size; i++) {
            String head = Q.poll();
            if (findDistance(head, start) == 0 && flag == -1) {
                flag = 0;
                // start与end的最小距离
                minDistStartToEnd = distToEnd;
            }
            distArray.put(head, distToEnd);
            for (String word: getNextWords(head)) {
                if (!set.contains(word) && dict.contains(word)) {
                    Q.offer(word);
                    set.add(word);
                }
            }
            
        }
    }
    return minDistStartToEnd;
}
// 计算String A与String B的距离
private int findDistance(String A, String B) {
    if (A.length() != B.length()) {
        return -1;
    }
    int count = 0;
    for (int i = 0; i < A.length(); i++) {
        if (A.charAt(i) != B.charAt(i)) {
            count++;
        }
    }
    return count;
}

private ArrayList<String> getNextWords(String word) {
    ArrayList<String> res = new ArrayList<String>();
    int length = word.length();
    for (int i = 0; i < length; i++) {
        for (char c = 'a'; c <= 'z'; c++) {
            String temp = replace(word, i, c);
            if (temp != word) {
                res.add(temp);
            }
        }
    }
    return res;
}

private String replace(String word, int pos, char c) {
    StringBuilder sb = new StringBuilder(word);
    sb.setCharAt(pos, c);
    return sb.toString();
}
```

