# Heapify

```java
	// sift up的问题是，更多的节点在下面，导致复杂度是O(nlogn)
    // sift down 的优势是，让最多的在下面的节点，操作最少的次数。复杂度是O(n)
    // public void heapify(int[] A) {
    //     // write your code here
    //     if (A == null || A.length == 0) {
    //         return;
    //     }
    //     for (int i = 0; i < A.length; i++) {
    //         insert(A, i);
    //     }
    // }
    // // A[0 ... pos-1] 是现有的堆, A[pos] is the num to insert 
    // private void insert(int[] A, int pos) {
    //     // A[pos] 已经在最末尾了，直接sift up即可
    //     while (pos > 0 && A[pos] < A[(pos - 1)/2]) {
    //         swap(A, pos, (pos - 1)/2);
    //         pos = (pos - 1)/2;
    //     }
    // }
    // private void swap(int[] A, int n, int m) {
    //     int temp = A[n];
    //     A[n] = A[m];
    //     A[m] = temp;
    // }
    
    // 使用 sift down 更优
    public void heapify(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        for (int i = A.length/2 - 1; i >= 0; i--) {
            siftDown(A, i);
        }
    }
    private void siftDown(int[] A, int pos) {
        // A[pos] is the number I consider currently
        while (pos < A.length) {
            int smallest = pos;
            if (2 * pos + 1 < A.length && A[2 * pos + 1] < A[smallest]) {
                smallest = 2 * pos + 1;
            }
            if (2 * pos + 2 < A.length && A[2 * pos + 2] < A[smallest]) {
                smallest = 2 * pos + 2;
            }
            if (smallest == pos) {
                return;
            }
            swap(A, pos, smallest);
            pos = smallest;
        }
    }
    private void swap(int[] A, int n, int m) {
        int temp = A[n];
        A[n] = A[m];
        A[m] = temp;
    }
```