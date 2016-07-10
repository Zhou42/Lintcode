# Heapify

```java
	// sift up�������ǣ�����Ľڵ������棬���¸��Ӷ���O(nlogn)
    // sift down �������ǣ�������������Ľڵ㣬�������ٵĴ��������Ӷ���O(n)
    // public void heapify(int[] A) {
    //     // write your code here
    //     if (A == null || A.length == 0) {
    //         return;
    //     }
    //     for (int i = 0; i < A.length; i++) {
    //         insert(A, i);
    //     }
    // }
    // // A[0 ... pos-1] �����еĶ�, A[pos] is the num to insert 
    // private void insert(int[] A, int pos) {
    //     // A[pos] �Ѿ�����ĩβ�ˣ�ֱ��sift up����
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
    
    // ʹ�� sift down ����
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