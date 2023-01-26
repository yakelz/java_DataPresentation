package Map.Array;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        char a = Map.RangeType.FIRST, z = Map.RangeType.LAST;
        int i, size = z - a + 1;
        Map.RangeType r = new Map.RangeType(Map.RangeType.NODEF);
        Random rand = new Random();
        while (a <= z) {
            i = Math.abs(rand.nextInt() % size);
            if (!map.compute(i, r))
                map.assign(i, a++);
        }
        map.print();

        a = Map.RangeType.FIRST;
        for (i = 0; i < size; i++) {
            map.assign(i, a++);
        }
        map.print();

        map.makenull();
        map.print();
        map.assign(1,'#');
        map.assign(3,'#');
        map.print();
    }
}
