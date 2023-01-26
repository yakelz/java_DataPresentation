package List.DoublyLinkedList;
import List.Element;

public class AbstractList {
    public class Node {
        public Element element;
        public Node next;
        public Node prev;
        public Node(Node prev, Element element, Node next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }
    private Node head; //start
    private Node tail; //end

    public AbstractList() {
        head = null;
        tail = null;
    }

    public Position end() {
        return new Position(null);
    }

    public void insert(Element x, Position p) {
        //если список пустой
        if (head == null) {
            System.out.println("зашел если список пустой");
            head = new Node(null, x, null);
            tail = head;
            return;
        }
        //после последнего
        if (p.node == null) {
            System.out.println("зашел если после последнего");
            tail.next = new Node(tail, x, null);
            tail = tail.next;
            return;
        }

        //в начало
        if (p.node == head) {
            System.out.println("зашел если в начало");
            Node temp = new Node (null, x, head);
            head.prev = temp;
            head = temp;
            return;
        }

        //в конец
        if (p.node == tail) {
            System.out.println("зашел если в tail");
            Node temp = new Node(tail, tail.element, null);
            tail.element = x;
            tail.next= temp;
            tail = temp;
            return;
        }

        if (!isExist(p)) {
            System.out.println("not exist");
            return;
        }

        //если определенная позиция
        System.out.println("Если определенная позиция");
        Node temp = p.node.next;
        p.node.next = new Node(p.node, p.node.element, temp);
        p.node.element = x;
        temp.prev = p.node.next;
    }

    public Position locate(Element x){
        Node h = head;
        while (h != null) {
            if (h.element.equals(x)) {
                return new Position(h);
            }
            h = h.next;
        }
        return new Position(null);
    }
    public Element retrieve(Position p){
        if (p == null || !isExist(p)) {
            throw new RuntimeException("retrieve(): неправильно выбрана позиция");
        }
        return p.node.element;
    }
    public void delete(Position p){
        if (p == null || head == null || tail == null) {
            return;
        }
        if (head == tail && p.node == head){
            head = null;
            tail = null;
        }
        if (p.node == head) {
            head = head.next;
            head.prev = null;
            return;
        }
        if (p.node == tail) {
            tail = tail.prev;
            tail.next = null;
            p.node = null;
            return;
        }

        if (!isExist(p)) {
            System.out.println("not exist");
            return;
        }
//        p.node.next.prev = p.node.prev;
//        p.node.prev.next = p.node.next;

        Node temp = p.node;
        Node prev = temp.prev;
        prev.next = temp.next;
        temp.next.prev = prev;
        p.node = prev.next;

    }

    //проверка существовании позиции
    private boolean isExist(Position p){
        Node h = head;
        while (h != null) {
            if (p.node == h) {
                return true;
            }
            h = h.next;
        }
        return false;
    }

    public Position next(Position p){
        // || p.node == tail
        if (p == null || !isExist(p)) {
            throw new RuntimeException("next(): неправильно выбрана позиция");
        }
        return new Position(p.node.next);
    }

    public Position previous(Position p) {
        // || p.node == head
        if (p == null || !isExist(p)) {
            throw new RuntimeException("previous(): неправильно выбрана позиция");
        }
        return new Position(p.node.prev);
    }

    public Position first(){
        return new Position(head);
    }

    public void makenull(){
        head = null;
        tail = null;
    }

    public void printList(){
        if (head == null) {
            System.out.println("Список пуст");
            return;
        }
        Node h = head;
        int i = 0;
        while (h != null) {
            System.out.print("[" + i + "] ");
            h.element.printElement();
            i++;
            h = h.next;
        }
        System.out.println();
    }
    public void printReverse(){
        if (head == null) {
            System.out.println("Список пуст");
            return;
        }
        Node h = tail;
        int i = 0;
        while (h != null) {
            System.out.print("[" + i + "] ");
            h.element.printElement();
            i++;
            h = h.prev;
        }
        System.out.println();
    }
    public static void init(){}
}
