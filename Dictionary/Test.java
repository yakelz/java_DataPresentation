package Dictionary;

import Dictionary.ClosedHash.Dictionary;

public class Test {
    public static void main(String[] args) {
        Dictionary cl = new Dictionary();
        cl.insert("Бато");
        cl.insert("Бато");
        cl.print();
    }
}
