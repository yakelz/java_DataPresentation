package Set;

public class Main {
    public static void main(String[] args) {
//        System.out.println(80/32);
//        System.out.println(80%32);
//        int i = -1;
//        System.out.println(Integer.toBinaryString(i));
//        i |= 1 << 5;
//        i |= 1 << 6;
//        System.out.println(Integer.toBinaryString(i));
//        i = i & -(1 << 5);
//        System.out.println(Integer.toBinaryString(i));

        ArraySet set1 = new ArraySet(-40,-30);
        ArraySet set2 = new ArraySet(-35, 0);
        set1.Insert(-35);
        set2.Insert(-5);
        set1.Print();
        set2.Print();

        ArraySet set3 = set1.Union(set2);
    }
}
