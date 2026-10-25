public class lla5 {

    static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    static class PlayerCircle {
        private Node tail;

        void addLast(String name) {
            Node node = new Node(name);
            if (tail == null) {
                node.next = node;
            } else {
                node.next = tail.next;
                tail.next = node;
            }
            tail = node;
        }

        void printTurns(int turns) {
            if (tail == null) {
                return;
            }
            StringBuilder line = new StringBuilder();
            Node current = tail.next;
            for (int i = 0; i < turns; i++) {
                if (i > 0) {
                    line.append(' ');
                }
                line.append(current.name);
                current = current.next;
            }
            System.out.println(line);
        }
    }

    static PlayerCircle build(String... players) {
        PlayerCircle circle = new PlayerCircle();
        for (String player : players) {
            circle.addLast(player);
        }
        return circle;
    }

    public static void main(String[] args) {
        build("Asha", "Ravi", "Neha").printTurns(7);
        build("Solo").printTurns(4);
        build("A", "B").printTurns(1);
        build("P", "Q", "R", "S").printTurns(8);
    }
}
