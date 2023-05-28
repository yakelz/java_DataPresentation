package Set.CircularLinkedSet;

import java.lang.reflect.Member;

public class Main {
    public static void main(String[] args) {
        Set a = new Set();
        a.Insert(25);
        a.Insert(17);
        a.Insert(12);
        a.Insert(1);
        a.Print();

        Set b = new Set();
        b.Insert(17);
        b.Insert(2);
        b.Insert(3);
        b.Insert(4);
        b.Insert(6);
        b.Insert(7);
        b.Insert(566);
        b.Print();

        System.out.println(b.Member(23));
        System.out.println(b.Member(2));
        System.out.println(b.Member(5));

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
