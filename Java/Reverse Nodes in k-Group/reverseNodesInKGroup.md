#  Reverse Nodes in k-Group
Code
```java
    // 可以像九章那样，分离功能；提供如下function
    // reverse head->n1->..->nk->next..
    // to head->nk->..->n1->next..
    // return n1
    // private ListNode reverseNextK(ListNode head, int k) {}
    // reverseNextK的作用：
    // 1. 当后面node数目足够，则reverse后面k个node，返回未被reverse的nodes的前一个node
    // 2. 当node数目不够的时候，直接返回最后一个node；此时该node.next为null，可以在主程序中退出
    // 不过感觉最好把 private ListNode reverse(ListNode head) {} 也单独列出来？
    
        
    public ListNode reverseKGroup(ListNode head, int k) {
        // Write your code here
        if (head == null || k == 1) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode p_s = dummy, p_e = dummy;
        dummy.next = head;
        for (int i = 0; i < k; i++) {
            p_e = p_e.next;
            // if the Linked list is shorter than k, do nothing
            if (p_e == null) {
                return head;
            }
        } // now p_e is k steps ahead
        while (p_e != null) {
            // reverse the linked list between p_s and p_e
            ListNode headNext = p_e.next;
            p_e.next = null;
            ListNode headOldCurt = p_s.next;
            p_s.next = reverse(p_s.next);
            headOldCurt.next = headNext;
            
            p_e = headOldCurt;
            
            for (int i = 0; i < k; i++) {
                p_s = p_s.next;
                p_e = p_e.next;
                // if the Linked list is shorter than k, do nothing
                if (p_e == null) {
                    return dummy.next;
                }
            }
        }
        return dummy.next;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curt = head;
        while (curt != null) {
            ListNode temp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = temp;
        }
        return prev;
    }
```