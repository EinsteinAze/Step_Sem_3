abstract class KitchenTool {
    private int speedLevel;

    public abstract String prepare();

    int getSpeedLevel() {
        return speedLevel;
    }

    void setSpeedLevel(int speedLevel) {
        if (speedLevel >= 1 && speedLevel <= 5) {
            this.speedLevel = speedLevel;
        }
    }
}

interface Washable {
    String clean();
}

class Blender extends KitchenTool implements Washable {
    @Override
    public String prepare() {
        return "Blending at speed " + getSpeedLevel();
    }

    @Override
    public String clean() {
        return "Blender rinsed and dried";
    }
}

public class week7p4 {
    public static void main(String[] args) {
        Blender b = new Blender();
        b.setSpeedLevel(3);
        System.out.println(b.getSpeedLevel());

        b.setSpeedLevel(9);
        System.out.println("rejected, speed level stays " + b.getSpeedLevel());

        System.out.println(b.prepare());
        System.out.println(b.clean());
    }
}
