public class llp6 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverseFirstKNodes(Node head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        Node previous = null;
        Node current = head;
        for (int i = 0; i < k && current != null; i++) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head.next = current;
        return previous;
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

    public static void main(String[] args) {
        System.out.println(show(reverseFirstKNodes(build(1, 2, 3, 4, 5), 3)));
        System.out.println(show(reverseFirstKNodes(build(1, 2, 3, 4, 5), 5)));
        System.out.println(show(reverseFirstKNodes(build(1, 2, 3, 4, 5), 1)));
        System.out.println(show(reverseFirstKNodes(build(1, 2, 3, 4, 5), 2)));
        System.out.println(show(reverseFirstKNodes(build(7), 1)));
    }
}
