public class lla1 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class TokenList {
        private Node head;
        private Node tail;
        private int size;

        void addFirst(int token) {
            Node node = new Node(token);
            node.next = head;
            head = node;
            if (tail == null) {
                tail = node;
            }
            size++;
        }

        void addLast(int token) {
            Node node = new Node(token);
            if (tail == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
            size++;
        }

        void insertAt(int index, int token) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("index " + index + ", size " + size);
            }
            if (index == 0) {
                addFirst(token);
                return;
            }
            if (index == size) {
                addLast(token);
                return;
            }
            Node previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }
            Node node = new Node(token);
            node.next = previous.next;
            previous.next = node;
            size++;
        }

        void print() {
            StringBuilder line = new StringBuilder();
            for (Node current = head; current != null; current = current.next) {
                line.append(current.data).append(" -> ");
            }
            line.append("null");
            System.out.println(line);
        }
    }

    static TokenList build(int... tokens) {
        TokenList list = new TokenList();
        for (int token : tokens) {
            list.addLast(token);
        }
        return list;
    }

    public static void main(String[] args) {
        TokenList list = build(101, 102, 103);
        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);
        list.print();

        TokenList empty = build();
        empty.insertAt(0, 7);
        empty.addFirst(5);
        empty.addLast(9);
        empty.insertAt(3, 11);
        empty.insertAt(1, 6);
        empty.print();

        TokenList single = build(42);
        single.insertAt(1, 43);
        single.insertAt(0, 41);
        single.print();
    }
}
