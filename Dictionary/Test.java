package Dictionary;

import Dictionary.OpenHash.Dictionary;

public class Test {

    private static String StringResizer(String str, int n) {
        if (str == null) {
            return null;
        }
        return str.substring(0, Math.min(str.length(), n));
    }

    public static void main(String[] args) {
        Dictionary cl = new Dictionary(5);
        cl.insert(StringResizer("Бато", 10));
        cl.insert(StringResizer("Захар", 10));
        cl.insert(StringResizer("Матвей", 10));
        cl.insert(StringResizer("Степан", 10));
        cl.insert(StringResizer("Павел", 10));
        cl.delete("Бато");
        cl.insert(StringResizer("Павел", 10));
        cl.print();
        System.out.println();
        cl.insert(StringResizer("Павел", 10));
        cl.print();

        cl.makenull();

        cl.print();
//        System.out.println();
//        cl.delete("Павел");
//        cl.print();
//        System.out.println();
//        cl.insert("Алан");
//        cl.print();
    }
}
