关于
```java
List<List<String>> myList = new ArrayList<ArrayList<String>>();
```
出错问题，而
```java
List<List<String>> myList = new ArrayList<List<String>>();
```
可以work


http://stackoverflow.com/questions/12861726/why-cant-you-have-a-list-of-lists-in-java

Note：这里其实是由于无论是List<> 还是 ArrayList<> 中的type不能改变
好比List<String>中，就必须是String
所以List<List<String>> 中元素，必须是List<String>，而不能是ArrayList<String>；否则同理还可以加入LinkedList<String>，就乱了
而List<String>可以实例化为ArrayList<List>
List<List<String>>实例化为ArrayList<List<String>> 都是没问题的，因为元素类型相同


##考虑以下程序
```java
List<List<Integer>> res = new ArrayList<List<Integer>>();
List<Integer> test1 = new ArrayList<Integer>();
List<Integer> test2 = new LinkedList<Integer>();
test1.add(1);
test2.add(2);
res.add(test1);
res.add(test2);

System.out.println(res);
```
输出
[[1], [2]]

故在实例化的时候，（除了最外层的类型外的）内部的元素类型必须都相同。比如res变量。
但是在add元素的时候，只要是实现了List接口的变量，都可以加入到res中。
