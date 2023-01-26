package Queue.LinkedList;

public class LinkedQueue {
    public class Node {
        public char c;
        public Node next;
        public Node(char c, Node next){
            this.c = c;
            this.next = next;
        }
    }
    private Node tail;
    public LinkedQueue(){
        tail = null;
    }
    public void enqueue(char x){
        if (tail == null) { //очередь пуста
            tail = new Node(x, null);
            tail.next = tail;
            return;
        }
        Node temp = new Node(x, tail.next);
        tail.next = temp;
        tail = temp;
    }
    public char dequeue(){
        char c;
        //если один элемент
        if (tail == tail.next) {
            c = tail.c;
            tail = null;
            return c;
        }
        c = tail.next.c;
        tail.next = tail.next.next;
        return c;
    }
    public char front(){
        return tail.next.c;
    }
    public boolean isEmpty(){
        return tail == null;
    }
    public boolean isFull(){
        return false;
    }
    public void makenull(){
        tail = null;
    }
}
