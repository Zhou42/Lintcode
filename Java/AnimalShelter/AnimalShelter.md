# Animal Shelter
1. 使用三个Queue的话，就是dog cat和总的，各一个queue，正常操作就OK
2. 一个Queue的话，每次取出cat和dog需要shift余下的Queue(如下)

```java
class resultType {
    String name;
    int type;
    resultType(String name, int type) {
        this.name = name;
        this.type = type;
    }
}

public class AnimalShelter {
    Queue<resultType> Q = new LinkedList<resultType>();
    public AnimalShelter() {
        // do initialize if necessary
    }

    /**
     * @param name a string
     * @param type an integer, 1 if Animal is dog or 0
     * @return void
     */
    void enqueue(String name, int type) {
        // Write your code here
        Q.offer(new resultType(name, type)); // 生成一个新的实例
    }

    public String dequeueAny() {
        // Write your code here
        if (Q.isEmpty()) {
            return null;
        }
        return Q.poll().name;
    }

    public String dequeueDog() {
        return dequeueType(1);
    }

    public String dequeueCat() {
        return dequeueType(0);
    }
    
    public String dequeueType(int type) {
        // Write your code here
        int size = Q.size();
        if (Q.isEmpty()) {
            return null;
        }
        resultType head = Q.poll();
        int count = 1; // how many elements have been polled out
        while (head.type != type) {
            enqueue(head.name, head.type);
            head = Q.poll();
            count++;
        }
        for (int i = 0; i < size - count; i++) {
            resultType temp = Q.poll();
            enqueue(temp.name, temp.type);
        }
        return head.name;
    }
}
```