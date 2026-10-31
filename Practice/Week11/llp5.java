public class llp5 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node splitListInHalf(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node secondHalf = slow.next;
        slow.next = null;
        return secondHalf;
    }

    static Node build(int... values) {
        Node head = null;
        Node tail = null;
        for (int value : values) {
            Node node = new Node(value);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }
        return head;
    }

    static String show(Node head) {
        if (head == null) {
            return "(empty list)";
        }
        StringBuilder line = new StringBuilder();
        for (Node current = head; current != null; current = current.next) {
            if (current != head) {
                line.append(" -> ");
            }
            line.append(current.data);
        }
        return line.toString();
    }

    static void run(Node head) {
        Node second = splitListInHalf(head);
        System.out.println("First half: " + show(head) + "      Returned: " + show(second));
    }

    public static void main(String[] args) {
        run(build(1, 2, 3, 4, 5));
        run(build(1, 2, 3, 4));
        run(build(1, 2));
        run(build(1));
        run(null);
    }
}
