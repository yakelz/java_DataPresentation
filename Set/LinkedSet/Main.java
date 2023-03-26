package Set.LinkedSet;

public class Main {
    public static void main(String[] args) {
        LinkedSet a = new LinkedSet();
        a.Insert(23);
        a.Insert(63);
        a.Insert(11);
        a.Insert(23);
        a.Insert(1);
        a.Print();
        System.out.println(a.Member(64));

        LinkedSet b = new LinkedSet();
        b.Insert(4);
        b.Insert(3);
        b.Insert(2);
        b.Insert(1);
        b.Insert(23);

        b.Print();
        LinkedSet c = a.Union(b);
        LinkedSet inter = a.Intersection(b);
        c.Print();
        inter.Print();

        LinkedSet dif = a.Difference(b);
        dif.Print();
    }
}
