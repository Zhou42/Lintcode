# Palindrome Linked List
```java
// 仿照九章 － 更好看
public boolean isPalindrome(ListNode head) {
    if (head == null) {
        return true;
    }
    // 拆成两条链比较，头节点分别是head和head2
    ListNode middle = findMiddle(head);
    ListNode head2 = reverse(middle.next);
    middle.next = null;
    while (head != null && head2 != null && head.val == head2.val) {
        head = head.next;
        head2 = head2.next;
    }
    return head2 == null;
}
private ListNode findMiddle(ListNode head) {
    // 如果是奇数node，找到最中间的；如果是偶数node，找到中间的前一个
    // 如 1 -> 2 -> 3 -> null 找到2
    //    1 -> 2 -> 3 -> 4 -> null 找到2
    if (head == null) {
        return head;
    }
    ListNode slow = head, fast = head.next;
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
    }
    return slow;
}
private ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
        ListNode temp = head.next;
        head.next = prev;
        prev = head;
        head = temp;
    }
    return prev;
}
```