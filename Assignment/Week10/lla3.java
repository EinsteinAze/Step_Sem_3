public class lla3 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverse(Node head) {
        Node previous = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
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

    static void print(Node head) {
        StringBuilder line = new StringBuilder();
        for (Node current = head; current != null; current = current.next) {
            line.append(current.data).append(" -> ");
        }
        line.append("null");
        System.out.println(line);
    }

    public static void main(String[] args) {
        print(reverse(build(1, 2, 3, 4)));
        print(reverse(build(9)));
        print(reverse(build()));
        print(reverse(build(10, 20)));
    }
}
