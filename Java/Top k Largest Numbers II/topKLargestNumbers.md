# Top k Largest Numbers II
## 关于堆的知识
https://www.youtube.com/watch?v=v1YUApMYXO4

http://www.cnblogs.com/vamei/archive/2013/03/20/2966612.html
## Code
这个复杂度是O(nlogk), n是加入的数字个数
不用堆的话，复杂度会是O(nk)...
```java
// Yang - 自己第一次写堆，有点乱，需要稍后改进完善
ArrayList<Integer> container;
int size;
public Solution(int k) {
    // initialize your data structure here.
    this.size = k;
    this.container = new ArrayList<>();
}

public void add(int num) {
    // Write your code here
    if (container.size() < size) {
        insert(container, num);
    } else {
        // 找最小 应该使用堆
        if (container.get(0) < num) {
            delete(container);
            insert(container, num);
        } 
    }
}

public List<Integer> topk() {
    // Write your code here
    // 使用堆
    ArrayList<Integer> temp = new ArrayList<Integer>(container);
    ArrayList<Integer> res = new ArrayList<Integer>();
    for (int i = 0; i < container.size(); i++) {
        res.add(temp.get(0));
        delete(temp);
    }
    Collections.reverse(res);
    return res;
}
// 维护一个最小堆, 这个堆必须是left full的，这样才能满足index的需要
private void insert(ArrayList<Integer> heap, int num) {
    heap.add(num);
    int curtIdx = heap.size() - 1;
    int parentIdx = (curtIdx - 1)/2;
    // sift up
    while (parentIdx >= 0 && heap.get(parentIdx) > heap.get(curtIdx)) {
        // swap 
        swap(heap, parentIdx, curtIdx);
        // update
        curtIdx = parentIdx;
        parentIdx = (curtIdx - 1)/2;
    }
}
private void delete(ArrayList<Integer> heap) {
    // swap with the last element
    swap(heap, 0, heap.size() - 1);
    heap.remove(heap.size() - 1);
    // sift down
    int curtIdx = 0;
    int lChildIdx = 2 * curtIdx + 1, rChildIdx = 2 * curtIdx + 2;
    int minChild = -1;
    while (true) {
        if (lChildIdx > heap.size() - 1 && rChildIdx > heap.size() - 1) {
            // 
            break;
        } else if (rChildIdx > heap.size() - 1) {
            minChild = lChildIdx;
        } else {
            minChild = heap.get(lChildIdx) < heap.get(rChildIdx) ? lChildIdx:rChildIdx;
        }
        
        if (heap.get(minChild) < heap.get(curtIdx)) {
            swap(heap, minChild, curtIdx);
            curtIdx = minChild;
            lChildIdx = 2 * curtIdx + 1;
            rChildIdx = 2 * curtIdx + 2;
        } else {
            break;
        }
    }
}
private void swap(ArrayList<Integer> heap, int posA, int posB) {
    int temp = heap.get(posA);
    heap.set(posA, heap.get(posB));
    heap.set(posB, temp);
}
```
