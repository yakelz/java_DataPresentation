package ManyToMany;

public class Relation extends Entity{
    public Entity student;
    public Entity course;

    public Relation(Entity student, Entity course){
        this.student = student;
        this.course = course;
    }

    @Override
    boolean isRelation() {
        return true;
    }
}
