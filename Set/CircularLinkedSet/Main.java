package Set.CircularLinkedSet;


public class Main {
    public static void main(String[] args) {
        Set a = new Set();
        a.Insert(23);
        a.Insert(63);
        a.Insert(11);
        a.Insert(23);
        a.Insert(1);
        a.Insert(1);
        a.Insert(1);
        a.Insert(23);
        a.Print();

        Set b = new Set();
        b.Insert(4);
        b.Insert(3);
        b.Insert(2);
        b.Insert(1);
        b.Insert(1);
        b.Insert(1);
        b.Insert(23);
        b.Print();

        Set c = a.Union(b);
        c.Print();

        Set inter = a.Intersection(b);
        inter.Print();

        Set diff = a.Difference(b);
        diff.Print();

        System.out.println(a.Equal(a));

        char av = '0';
        System.out.println((int)av);
    }
}
