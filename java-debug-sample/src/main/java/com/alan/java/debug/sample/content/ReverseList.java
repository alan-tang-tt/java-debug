package com.alan.java.debug.sample.content;

/**
 * 反转链表：
 * 1->2->3->4->5    5->4->3->2->1
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 1->2->3->4->5   1<-2<-3<-4<-5
        // 记录下下个节点和当前节点，将下一个节点的next指向上一个
        ListNode cur = head, next = cur.next, nextnext = next.next;
        cur.next = null;
        while (next != null) {
            next.next = cur;

            cur = next;
            next = nextnext;
            if (nextnext != null) {
                nextnext = nextnext.next;
            }
        }

        return cur;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseList().reverseList(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))))));
    }
}
