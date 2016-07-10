# Rotate List
```java
    // ֻ��Ҫ 1->2->3->(4->5) ���k = 2, 4->5ֱ��Ų��ǰ�漴�ɱ��  (4->5)->1->2->3
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) {
            return head;
        }
        // use a dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = getLength(head);
        k = k % length;
        // ֻ����k-1 steps������headһ��ָ��ĳnode
        for (int i = 0; i < k; i++) {
            head = head.next;
        }
        ListNode tail = dummy.next;
        // find the previous node of the kth node, which is "tail"
        while (head.next != null) {
            head = head.next;
            tail = tail.next;
        }
        // put the last k node in the front
        head.next = dummy.next;
        dummy.next = tail.next;
        tail.next = null;
        return dummy.next;
        
    }
    private int getLength(ListNode head) {
        int length = 0;
        if (head == null) {
            return length;
        }
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
```
## ����һ���������Լ��ʼ�ģ�������ת������������linked list�ĸ��Ӷ�ƫ��
```java
 // public ListNode rotateRight(ListNode head, int k) {
    //     // write your code here
    //     if (k == 0 || head == null) {
    //         return head;
    //     }
    //     int length = getLength(head);
    //     k = k % length;
    //     ListNode fast = head, slow = head;
    //     while (k >= 0) {
    //         fast = fast.next;
    //         k--;
    //     }
    //     while (fast != null) {
    //         fast = fast.next;
    //         slow = slow.next;
    //     }
    //     ListNode head2 = slow.next;
    //     slow.next = null;
    //     head = reverse(head);
    //     head2 = reverse(head2);
    //     ListNode tail = getTail(head);
    //     tail.next = head2;
    //     head = reverse(head);
    //     return head;
    // }
    // private int getLength(ListNode head) {
    //     int length = 0;
    //     if (head == null) {
    //         return length;
    //     }
    //     while (head != null) {
    //         length++;
    //         head = head.next;
    //     }
    //     return length;
    // }
    // private ListNode reverse(ListNode head) {
    //     ListNode prev = null;
    //     while (head != null) {
    //         ListNode temp = head.next;
    //         head.next = prev;
    //         prev = head;
    //         head = temp;
    //     }
    //     return prev;
    // }
    // private ListNode getTail(ListNode head) {
    //     while (head != null && head.next != null) {
    //         head = head.next;
    //     }
    //     return head;
    // }
```
