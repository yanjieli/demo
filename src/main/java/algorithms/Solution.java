package algorithms;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Solution {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = null;

        printNode(node);
        log.info("*******************************");
        ListNode newnode = reverseList(node);
        printNode(newnode);
    }

    public static void printNode(ListNode node) {
        do {
            log.info("{}", node.val);
            node = node.next;
        } while (node.next != null);
        log.info("{}", node.val);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode temp = head.next;
        ListNode newHead = reverseList(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
