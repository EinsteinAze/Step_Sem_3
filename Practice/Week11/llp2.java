public class llp2 {

    static class DNode {
        int data;
        DNode prev;
        DNode next;

        DNode(int data) {
            this.data = data;
        }
    }

    static DNode insertAtTailDoubly(DNode head, int value) {
        DNode node = new DNode(value);
        if (head == null) {
            return node;
        }
        DNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        node.prev = tail;
        return head;
    }

    static String forward(DNode head) {
        if (head == null) {
            return "(empty list)";
        }
        StringBuilder line = new StringBuilder();
        for (DNode current = head; current != null; current = current.next) {
            if (current != head) {
                line.append(" <-> ");
            }
            line.append(current.data);
        }
        return line.toString();
    }

    static String backward(DNode head) {
        if (head == null) {
            return "(empty list)";
        }
        DNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        StringBuilder line = new StringBuilder();
        for (DNode current = tail; current != null; current = current.prev) {
            if (current != tail) {
                line.append(" <-> ");
            }
            line.append(current.data);
        }
        return line.toString();
    }

    static DNode build(int... values) {
        DNode head = null;
        DNode tail = null;
        for (int value : values) {
            DNode node = new DNode(value);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
        }
        return head;
    }

    static void run(DNode head, int value) {
        DNode result = insertAtTailDoubly(head, value);
        System.out.println(forward(result));
        System.out.println("backward: " + backward(result));
    }

    public static void main(String[] args) {
        run(build(1, 2, 3), 4);
        run(null, 7);
        run(build(5), 6);
    }
}
