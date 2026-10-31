public class llp3 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node removeFirstKNodes(Node head, int k) {
        for (int i = 0; i < k && head != null; i++) {
            head = head.next;
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
        System.out.println(show(removeFirstKNodes(build(1, 2, 3, 4, 5), 2)));
        System.out.println(show(removeFirstKNodes(build(1, 2), 5)));
        System.out.println(show(removeFirstKNodes(build(1, 2, 3), 0)));
        System.out.println(show(removeFirstKNodes(build(1, 2, 3), 3)));
        System.out.println(show(removeFirstKNodes(null, 2)));
    }
}
