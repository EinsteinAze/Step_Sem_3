public class lla4 {

    static class Node {
        String name;
        Node prev;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    static class Train {
        private Node head;
        private Node tail;

        void addLast(String name) {
            Node node = new Node(name);
            if (tail == null) {
                head = node;
            } else {
                node.prev = tail;
                tail.next = node;
            }
            tail = node;
        }

        boolean remove(String name) {
            Node target = head;
            while (target != null && !target.name.equals(name)) {
                target = target.next;
            }
            if (target == null) {
                return false;
            }
            if (target.prev == null) {
                head = target.next;
            } else {
                target.prev.next = target.next;
            }
            if (target.next == null) {
                tail = target.prev;
            } else {
                target.next.prev = target.prev;
            }
            target.prev = null;
            target.next = null;
            return true;
        }

        void printForward() {
            StringBuilder line = new StringBuilder("Forward: ");
            if (head == null) {
                line.append("(empty)");
            }
            for (Node current = head; current != null; current = current.next) {
                line.append(current.name);
                if (current.next != null) {
                    line.append(" <-> ");
                }
            }
            System.out.println(line);
        }

        void printBackward() {
            StringBuilder line = new StringBuilder("Backward: ");
            if (tail == null) {
                line.append("(empty)");
            }
            for (Node current = tail; current != null; current = current.prev) {
                line.append(current.name);
                if (current.prev != null) {
                    line.append(" <-> ");
                }
            }
            System.out.println(line);
        }
    }

    static Train build(String... coaches) {
        Train train = new Train();
        for (String coach : coaches) {
            train.addLast(coach);
        }
        return train;
    }

    static void run(Train train, String coach) {
        if (!train.remove(coach)) {
            System.out.println("Coach " + coach + " not found");
        }
        train.printForward();
        train.printBackward();
    }

    public static void main(String[] args) {
        run(build("Engine", "A1", "B1", "B2", "Guard"), "B1");
        System.out.println();
        run(build("Engine", "A1", "B1", "B2", "Guard"), "Engine");
        System.out.println();
        run(build("Engine", "A1", "B1", "B2", "Guard"), "Guard");
        System.out.println();
        run(build("Engine"), "Engine");
        System.out.println();
        run(build("Engine", "A1"), "Z9");
    }
}
