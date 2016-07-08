# Add Two Numbers II

```java
    // 九章的解法 - 比较美观
    // 值得借鉴, 这里应该是假设了无符号加法
    // 对于十进制按位运算，如果两个加数该位上的和为sum，那么:
    // 所得结果该位为 sum % 10
    // carry的结果是 sum / 10
    public ListNode addLists2(ListNode l1, ListNode l2) {
        // write your code here
        ListNode l1_r = reverseList(l1);
        ListNode l2_r = reverseList(l2);
        ListNode dummy = new ListNode(0);
        ListNode curt = dummy;
        int carry = 0;
        while (l1_r != null && l2_r != null) {
            int sum = l1_r.val + l2_r.val + carry;
            curt.next = new ListNode(sum % 10);
            carry = sum / 10;
            curt = curt.next;
            
            l1_r = l1_r.next;
            l2_r = l2_r.next;
        }
        if (l1_r == null) {
            while (l2_r != null) {
                int sum = l2_r.val + carry;
                curt.next = new ListNode(sum % 10);
                carry = sum / 10;
                curt = curt.next;
                
                l2_r = l2_r.next;
            } 
        } else {
            while (l1_r != null) {
                int sum = l1_r.val + carry;
                curt.next = new ListNode(sum % 10);
                carry = sum / 10;
                curt = curt.next;
                
                l1_r = l1_r.next;
            } 
        }
        
        if (carry == 1) {
            curt.next = new ListNode(1);
        }
        return reverseList(dummy.next);
    }  
    
    private ListNode reverseList(ListNode l) {
        ListNode prev = null;
        while (l != null) {
            ListNode temp = l.next;
            l.next = prev;
            prev = l;
            l = temp;
        }
        return prev;
    }
```