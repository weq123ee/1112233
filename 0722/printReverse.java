public class printReverse {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void printReverse(ListNode head) {
        if (head == null) return;
        printReverse(head.next);
        System.out.print(head.val + " ");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        System.out.print("Reverse: ");
        printReverse(head);
        System.out.println();
    }
}
