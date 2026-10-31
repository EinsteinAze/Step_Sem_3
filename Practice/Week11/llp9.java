public class llp9 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node insertAtEndCircular(Node head, int value) {
        Node node = new Node(value);
        if (head == null) {
            node.next = node;
            return node;
        }
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        node.next = head;
        last.next = node;
        return head;
    }

    static Node buildCircular(int... values) {
        if (values.length == 0) {
            return null;
        }
        Node head = new Node(values[0]);
        Node tail = head;
        for (int i = 1; i < values.length; i++) {
            tail.next = new Node(values[i]);
            tail = tail.next;
        }
        tail.next = head;
        return head;
    }

    static String show(Node head) {
        if (head == null) {
            return "(empty list)";
        }
        StringBuilder line = new StringBuilder();
        Node current = head;
        do {
            line.append(current.data).append(" -> ");
            current = current.next;
        } while (current != head);
        line.append("(back to ").append(head.data).append(")");
        return line.toString();
    }

    public static void main(String[] args) {
        System.out.println(show(insertAtEndCircular(buildCircular(1, 2, 3), 4)));
        System.out.println(show(insertAtEndCircular(null, 9)));
        System.out.println(show(insertAtEndCircular(buildCircular(5), 6)));
    }
}
