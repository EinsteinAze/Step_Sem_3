public class llp10 {

    static class DNode {
        int data;
        DNode prev;
        DNode next;

        DNode(int data) {
            this.data = data;
        }
    }

    static DNode deleteLastNodeDoubly(DNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        DNode last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.prev.next = null;
        last.prev = null;
        return head;
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

    static void run(DNode head) {
        DNode result = deleteLastNodeDoubly(head);
        System.out.println(forward(result));
        System.out.println("backward: " + backward(result));
    }

    public static void main(String[] args) {
        run(build(1, 2, 3));
        run(build(5));
        run(null);
        run(build(8, 9));
    }
}
