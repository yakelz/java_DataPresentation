package List.LinkedList;

import List.Element;

public class AbstractList {

    public class Node {
        public Element element;
        public Node next;
        public Node(Element element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node head;
    public AbstractList () {
        head = null;
    }

    public Position end() {
        return new Position(null);
    }
    public void insert(Element x, Position p) {

        // Если list пустой
        if (head == null && p.node == null) {
            head = new Node(x,null);
            return;
        }
        // Если вставка в конец
        if (p.node == null) {
            Position last = new Position(getLast());
            last.node.next = new Node(x, null);
            return;
        }

        //если вставка в голову
        if (p.node == head) {
            System.out.println("head");
            Node temp = new Node(x, head);
            head = temp;
            return;
        }

        //проверить есть ли такая позиция
        Position previous = new Position(getPrevious(p));
        if (p.node != head && previous.node == null){
            return;
        }

        //Если определенная позиция
        Node temp = p.node.next;
        p.node.next = new Node(p.node.element, temp);
        p.node.element = x;
    }

    private Node getLast() {
        Node h = head;
        Node last = null;
        while (h != null) {
            last = h;
            h = h.next;
        }
        return last;
    }

    private Node getPrevious(Position p){
        Node h = head;
        Node prev = null;
        while (h != null) {
            if (p.node == h) {
                return prev;
            }
            prev = h;
            h = h.next;
        }
        return null;
    }

    public Position locate(Element x){
        Node h = head;
        while (h != null) {
            if (h.element.equals(x)){
                return new Position(h);
            }
            h = h.next;
        }
        return new Position(null);
    }
    public Element retrieve(Position p){
        if (p == null) {
            throw new RuntimeException("retrieve(): неправильно выбрана позиция");
        }
        if (p.node == head) {
            return head.element;
        }
        if (p.equals(getPrevious(p))) {
            throw new RuntimeException("retrieve(): такой позиции нет");
        }
        return p.node.element;
    }

    public void delete(Position p){
        if (p == null || head == null) {
            return;
        }
        if (p.node == head) {
            head = head.next;
            return;
        }
        Position temp = new Position(getPrevious(p));
        if (p.node != null) {
            Node pNext = temp.node.next;
            temp.node.next = pNext.next;
            p.node = temp.node.next;
        }
    }
    public Position next(Position p){
        if (p == null) {
            throw new RuntimeException("next(): неправильно выбрана позиция");
        }
        if (p.node == head) {
            return new Position(head.next);
        }
        Node prev = getPrevious(p);
        if (prev != null) {
            return new Position(p.node.next);
        } else {
            throw new RuntimeException("previous(): неправильно выбрана позиция");
        }

    }

    public Position previous(Position p) {
        if (p == null || p.node == head) {
            throw new RuntimeException("previous(): неправильно выбрана позиция");
        }
        Node prev = getPrevious(p);
        if (prev != null) {
            return new Position(prev);
        } else {
            throw new RuntimeException("previous(): неправильно выбрана позиция");
        }
    }

    public Position first(){
        return new Position(head);
    }
    public void makenull(){
        head = null;
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

//    public static void init(){}

}
