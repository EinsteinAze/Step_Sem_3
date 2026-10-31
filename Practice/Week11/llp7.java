public class llp7 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static int countNodes(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
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

    public static void main(String[] args) {
        System.out.println(countNodes(build(5, 10, 15)));
        System.out.println(countNodes(null));
        System.out.println(countNodes(build(1)));
        System.out.println(countNodes(build(1, 2, 3, 4, 5, 6, 7)));
    }
}
