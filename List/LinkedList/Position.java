package List.LinkedList;

public class Position {
    public AbstractList.Node node;
    public Position (AbstractList.Node node) {
        this.node = node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return node == position.node;
    }

}
