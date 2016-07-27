# Nuts\&Bolts\ Problem/
虽然nuts和bolts本身没法排序，但是可以用互相当key 实现quicksort

```java
/**
 * public class NBCompare {
 *     public int cmp(String a, String b);
 * }
 * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
 * if "a" is bigger than "b", it will return 1, else if they are equal,
 * it will return 0, else if "a" is smaller than "b", it will return -1.
 * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
*/
public class Solution {
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    // public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
    //     // write your code here
    //     // 因为Nuts(或者Bolts)集合中的元素互相之间无法比较大小，所以没法直接排序。
    //     // 这个暴力算法 复杂度O(n^2) 竟然也通过了！！
    //     if (nuts == null || bolts == null || nuts.length == 0 || bolts.length == 0 || nuts.length != bolts.length) {
    //         return;
    //     }
    //     for (int i = 0; i < nuts.length; i++) {
    //         for (int j = i; j < bolts.length; j++) {
    //             if (compare.cmp(nuts[i], bolts[j]) == 0) {
    //                 swap(bolts, i, j);
    //             }
    //         }
    //     }
    // }
    
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        if (nuts == null || bolts == null || nuts.length == 0 || bolts.length == 0 || nuts.length != bolts.length) {
            return;
        }
        // 考虑用bolt给nut排序
        quicksort(nuts, bolts, compare, 0, nuts.length - 1);
    }
    
    public void quicksort(String[] nuts, String[] bolts, NBComparator compare, int start, int end) {
        // 给start - end的元素quicksort 
        if (start >= end) {
            return;
        }
        String pivot = nuts[start];
        int pos = partition2(bolts, pivot, compare, start, end);
        pivot = bolts[pos];
        pos = partition1(nuts, pivot, compare, start, end);
        quicksort(nuts, bolts, compare, start, pos - 1);
        quicksort(nuts, bolts, compare, pos + 1, end);
    }
    
    // 注意到这个虽然不能排序，但可以用bolt的元素给nut进行partition，类似quicksort过程
    // Nut :  |a| b c d e f g   ---------选第一个 作为pivot 给Bolt partition
    // Bolt : D E F G A B C     ---------这里对应的pivot是A，应该位于最开始或者最后
    
    // 另外要注意 compare.cmp(a, b) to compare nuts "a" and bolts "b"
    // 这里 a必须是nut，b必须是bolt
    // 可以写两个partition function，也可以像下面这样使用or操作
    // public int partition(String[] array, String pivot, NBComparator compare, int start, int end) {
    //     // return the position of the pivot
    //     if (start == end) {
    //         return start;
    //     }
    //     for (int i = start; i <= end; i++) {
    //         // 找到pivot 交换到第一个位置
    //         if (compare.cmp(array[i], pivot) == 0 || compare.cmp(pivot, array[i]) == 0) {
    //             swap(array, i, start);
    //             break;
    //         }
    //     }
    //     int i = start + 1, j = end;
    //     while (i < j) {
    //         while (i < j && (compare.cmp(array[i], pivot) == -1 || compare.cmp(pivot, array[i]) == 1) ) {
    //             i++;
    //         }
    //         while (i < j && (compare.cmp(array[j], pivot) == 1 || compare.cmp(pivot, array[j]) == -1)) {
    //             j--;
    //         }
    //         if (i < j) {
    //             swap(array, i, j);
    //         }
    //     }
    //     if (compare.cmp(array[i], pivot) == -1 || compare.cmp(pivot, array[i]) == 1) {
    //         swap(array, i, start);
    //         return i;
    //     } else {
    //         swap(array, i - 1, start);
    //         return i - 1;
    //     }
    // }
    
    public int partition1(String[] nuts, String pivot, NBComparator compare, int start, int end) {
        // return the position of the pivot
        if (start == end) {
            return start;
        }
        for (int i = start; i <= end; i++) {
            // 找到pivot 交换到第一个位置
            if (compare.cmp(nuts[i], pivot) == 0) {
                swap(nuts, i, start);
                break;
            }
        }
        int i = start + 1, j = end;
        while (i < j) {
            while (i < j && (compare.cmp(nuts[i], pivot) == -1)) {
                i++;
            }
            while (i < j && (compare.cmp(nuts[j], pivot) == 1)) {
                j--;
            }
            if (i < j) {
                swap(nuts, i, j);
            }
        }
        if (compare.cmp(nuts[i], pivot) == -1) {
            swap(nuts, i, start);
            return i;
        } else {
            swap(nuts, i - 1, start);
            return i - 1;
        }
    }
    public int partition2(String[] bolts, String pivot, NBComparator compare, int start, int end) {
        // return the position of the pivot
        if (start == end) {
            return start;
        }
        for (int i = start; i <= end; i++) {
            // 找到pivot 交换到第一个位置
            if (compare.cmp(pivot, bolts[i]) == 0) {
                swap(bolts, i, start);
                break;
            }
        }
        int i = start + 1, j = end;
        while (i < j) {
            while (i < j && (compare.cmp(pivot, bolts[i]) == 1)) {
                i++;
            }
            while (i < j && (compare.cmp(pivot, bolts[j]) == -1)) {
                j--;
            }
            if (i < j) {
                swap(bolts, i, j);
            }
        }
        if (compare.cmp(pivot, bolts[i]) == 1) {
            swap(bolts, i, start);
            return i;
        } else {
            swap(bolts, i - 1, start);
            return i - 1;
        }
    }
    
    
    public void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
};
```