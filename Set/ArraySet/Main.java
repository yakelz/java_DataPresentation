package Set.ArraySet;

public class Main {
    public static void main(String[] args) {
        ArraySet a = new ArraySet(-30, 35);
        a.Insert(3);
        a.Insert(-3);
        a.Insert(-29);
        a.Print();
        ArraySet b = new ArraySet(-40, 10);
        b.Insert(5);
        b.Insert(8);
        b.Insert(-39);
        b.Print();
        ArraySet c = a.Union(b);
        c.Print();
        System.out.println("@");

        int len = 35 / 32 - (-30 + 1) / 32 + 2;
        int len2 = (35-(-30)-1)/32 + 2;
        System.out.println(len + " " + len2);


        a.Intersection(b);
    }
}
