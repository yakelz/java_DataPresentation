package Set.CircularLinkedSet;

public class Main {
    public static void main(String[] args) {
        Set a = new Set();
        a.Insert(12);
        a.Insert(14);
        a.Insert(13);
        a.Print();
        a.Delete(13);
        a.Print();
        a.Delete(123);
        a.Print();
        a.Insert(123);
        a.Print();

        Set b = new Set(a);
        b.Print();
        b.Delete(123);
        a.Print();
        b.Print();

        char av = '0';
        System.out.println((int)av);
    }
}
