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
        b.Insert(-29);
        b.Print();
        ArraySet union = a.Union(b);
        union.binaryPrint();
        union.Print();
        union.Delete(8);
        union.Print();
        System.out.println("Max: " + union.Max());
        System.out.println("Min: " + union.Min());
        ArraySet inter = a.Intersection(b);
        inter.Print();

        ArraySet n = new ArraySet(-10, 10);
        ArraySet m = new ArraySet(-100, -80);
        ArraySet l = n.Intersection(m);
        l.Print();

        System.out.println(a.Equal(b));

//        ArraySet diff = a.Difference(b);
//        diff.Print();


    }
}
