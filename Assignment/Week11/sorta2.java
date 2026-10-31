public class sorta2 {

    static class Team {
        final String name;
        final int size;

        Team(String name, int size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public String toString() {
            return name + "(" + size + ")";
        }
    }

    static void sortTeams(Team[] teams) {
        for (int i = 1; i < teams.length; i++) {
            Team key = teams[i];
            int j = i - 1;
            while (j >= 0 && teams[j].size > key.size) {
                teams[j + 1] = teams[j];
                j--;
            }
            teams[j + 1] = key;
        }
    }

    static String join(Team[] teams) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < teams.length; i++) {
            if (i > 0) {
                line.append(", ");
            }
            line.append(teams[i]);
        }
        return line.toString();
    }

    static void run(Team... teams) {
        sortTeams(teams);
        System.out.println(join(teams));
    }

    public static void main(String[] args) {
        run(new Team("Pixel", 4), new Team("Byte", 2), new Team("Nova", 4), new Team("Echo", 3), new Team("Atom", 2));
        run(new Team("A", 5), new Team("B", 5), new Team("C", 5));
        run(new Team("X", 1), new Team("Y", 2), new Team("Z", 3));
        run(new Team("Solo", 9));
        run();
    }
}
