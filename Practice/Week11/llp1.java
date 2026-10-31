public class llp1 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static void printCircular(Node head) {
        if (head == null) {
            System.out.println();
            return;
        }
        StringBuilder line = new StringBuilder();
        Node current = head;
        do {
            if (line.length() > 0) {
                line.append(' ');
            }
            line.append(current.data);
            current = current.next;
        } while (current != head);
        System.out.println(line);
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

    public static void main(String[] args) {
        printCircular(buildCircular(10, 20, 30));
        printCircular(null);
        printCircular(buildCircular(5));
        printCircular(buildCircular(1, 2));
    }
}
