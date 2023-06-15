package ManyToMany;

public class Student extends Entity {
    public char[] name;
    public Relation courses;

    public Student(char[] name) {
        this.name = name;
    }

    @Override
    boolean isRelation() {
        return false;
    }
}
