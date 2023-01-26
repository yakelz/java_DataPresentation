package Map.AbstractList;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        map.assign("Bato".toCharArray(), "Ulan-Ude".toCharArray());
        map.assign("Nikita".toCharArray(), "Chita".toCharArray());
        map.assign("Egor".toCharArray(), "Moscow".toCharArray());


        map.print();
        System.out.println(map.compute("Bato".toCharArray(), "Ulan-Ude".toCharArray()));

    }
}
