abstract class ArtPiece {
    private static int counter = 4000;
    private final String pieceId;
    protected String title;

    public ArtPiece(String title) {
        counter++;
        this.pieceId = "ART-" + counter;
        this.title = title;
    }

    public abstract String describe();

    String getPieceId() {
        return pieceId;
    }
}

class Painting extends ArtPiece {
    public Painting(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    public Sculpture(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}

public class week7a2 {
    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println(p.describe());

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(s.describe());
    }
}
