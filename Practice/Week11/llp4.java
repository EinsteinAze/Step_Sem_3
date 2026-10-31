public class llp4 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node deleteNthNode(Node head, int n) {
        if (head == null || n < 1) {
            return head;
        }
        if (n == 1) {
            return head.next;
        }
        Node current = head;
        for (int i = 0; i < n - 2 && current.next != null; i++) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
        return head;
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
        System.out.println(show(deleteNthNode(build(10, 20, 30, 40), 3)));
        System.out.println(show(deleteNthNode(build(10, 20, 30, 40), 1)));
        System.out.println(show(deleteNthNode(build(10, 20, 30, 40), 4)));
        System.out.println(show(deleteNthNode(build(10), 1)));
    }
}
