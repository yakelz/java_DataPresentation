package ManyToMany;

public class Course extends Entity{

    public String name;
    public Relation students;

    public Course(String name){
        this.name = name;
    }

    @Override
    boolean isRelation() {
        return false;
    }
}
