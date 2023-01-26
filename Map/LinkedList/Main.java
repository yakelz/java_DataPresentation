package Map.LinkedList;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        map.assign("Bato".toCharArray(), "Ulan-Ude".toCharArray());
        map.assign("Nikita".toCharArray(), "Chita".toCharArray());
        map.assign("Egor".toCharArray(), "Moscow".toCharArray());
        map.print();

        
    }
}
