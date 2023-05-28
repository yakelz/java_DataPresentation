package ManyToMany;

public class Student extends Entity {
    public String name;
    public Relation courses;

    public Student(String name) {
        this.name = name;
    }

    @Override
    boolean isRelation() {
        return false;
    }
}
