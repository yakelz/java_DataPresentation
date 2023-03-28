package List.CursorsList;
import List.Element;

public class AbstractList {
    public static class Node {
        public Element element;
        public int next;
        public Node (Element element, int next) {
            this.element = element;
            this.next = next;
        }
    }
    public static final int SIZE = 10;
    public static Node[] array = new Node[SIZE];
    public static int SPACE = 0;
    public int head;

    public static void init() {
        for (int i = 0; i < SIZE; i++) {
            array[i] = new Node(null, i+1);
        }
        array[SIZE-1].next = -1;
    }

    public AbstractList(){
        head = -1;
    }

//    public static void print(){
//        for (int i = 0; i < SIZE; i++) {
//            System.out.println(array[i].next);
//        }
//    }

    public Position end() {
        return new Position(-1);
    }

    public void insert(Element x, Position p) {
        Element input = new Element(x);

        //если список полон
        if (SPACE == -1) {
            return;
        }
        int spaceNext = array[SPACE].next;
        //список пустой
        if (head == -1) {
            head = SPACE;
            array[head] = new Node(input, -1);
        }
        //после последнего
        else if (p.p == -1) {
            array[SPACE] = new Node(input, -1);
            array[getLast()].next = SPACE;
        }
        //вставка в начало
        else if (p.p == head) {
            array[SPACE] = new Node(array[head].element, array[head].next);
            array[head].element = input;
            array[head].next = SPACE;
        }
        else {
            int prev = getPrevious(p.p);
            if (prev == -1){
                return;
            }
            array[SPACE] = new Node(array[p.p].element, array[p.p].next);
            array[p.p].element = input;
            array[p.p].next = SPACE;
        }
        SPACE = spaceNext;
    }

    private int getLast(){
        int current = head;
        int last = -1;
        while (current != -1){
            last = current;
            current = array[current].next;
        }
        return last;
    }

    public Position locate(Element x){
        int current = head;
        while (current != -1){
            if (array[current].element.equals(x)){
                return new Position(current);
            }
            current = array[current].next;
        }
        return new Position(-1);
    }
    public Element retrieve(Position p){
        if (p == null) {
            throw new RuntimeException("retrieve(): неправильно выбрана позиция");
        }
        if (p.p == head)
            return array[head].element;
        if (getPrevious(p.p) == -1) {
            throw new RuntimeException("retrieve(): такой позиции нет");
        }
        return array[p.p].element;
    }

    public void delete(Position p){
        if (head == -1) {
            return;
        }
        if (p.p == head){
            int next = array[head].next;
            array[head].element = null;
            array[head].next = SPACE;
            SPACE = head;
            head = next;
            return;
        }

        int prev = getPrevious(p.p);
        if (prev == -1){
            return;
        }

        int current = array[prev].next;
        p.p = array[current].next;
        array[prev].next = array[current].next;

        array[current].element = null;
        array[current].next = SPACE;
        SPACE = current;
    }

    private int getPrevious(int p){
        int current = head;
        int prev = -1;
        while (current != -1){
            if (current == p) {
                return prev;
            }
            prev = current;
            current = array[current].next;
        }
        return prev; //-1
    }
    public Position next(Position p){
        if (p == null) {
            throw new RuntimeException("next(): неправильно выбрана позиция");
        }
        if (p.p == head)
            return new Position(array[head].next);
        if (getPrevious(p.p) == -1) {
            throw new RuntimeException("next(): такой позиции нет");
        }
        return new Position(array[p.p].next);
    }

    public Position previous(Position p) {
        if (p == null || p.p == head) {
            throw new RuntimeException("previous(): неправильно выбрана позиция");
        }
        int prev = getPrevious(p.p);
        if (prev == -1) {
            throw new RuntimeException("previous(): такой позиции нет");
        }
        return new Position(prev);
    }
    public Position first(){
        return new Position(head);
    }
    public void makenull(){
        if (head == -1) {
            return;
        }
        array[getLast()].next = SPACE;
        SPACE = head;
        head = -1;
    }
    public void printList(){
        if (head == -1) {
            System.out.println("Список пуст");
            return;
        }
        int current = head;
        int i = 1;
        while (current != -1){
            System.out.print("[" + i + "] ");
            i++;
            array[current].element.printElement();
            current = array[current].next;
        }
        System.out.println();
    }

}
