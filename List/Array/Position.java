package List.Array;

public class Position {
    public int p;

    public Position (int p) {
        this.p = p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return p == position.p;
    }
}
