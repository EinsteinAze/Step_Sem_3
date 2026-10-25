public class lla2 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node removeAll(Node head, int code) {
        while (head != null && head.data == code) {
            head = head.next;
        }
        Node previous = head;
        while (previous != null && previous.next != null) {
            if (previous.next.data == code) {
                previous.next = previous.next.next;
            } else {
                previous = previous.next;
            }
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

    static void print(Node head) {
        StringBuilder line = new StringBuilder();
        for (Node current = head; current != null; current = current.next) {
            line.append(current.data).append(" -> ");
        }
        line.append("null");
        System.out.println(line);
    }

    public static void main(String[] args) {
        print(removeAll(build(5, 3, 5, 8, 5), 5));
        print(removeAll(build(5, 5, 5), 5));
        print(removeAll(build(), 5));
        print(removeAll(build(1, 2, 3), 9));
        print(removeAll(build(7, 1, 7, 7, 2, 7), 7));
    }
}
