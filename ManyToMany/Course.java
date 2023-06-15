package ManyToMany;

public class Course extends Entity{

    public int id;
    public Relation students;

    public Course(int id){
        this.id = id;
    }

    @Override
    boolean isRelation() {
        return false;
    }
}
