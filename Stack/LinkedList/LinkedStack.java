package Stack.LinkedList;

public class LinkedStack {
    public class Node{
        public char c;
        public Node next;
        public Node(char c, Node next){
            this.c = c;
            this.next = next;
        }
    }
    public Node head;
    public LinkedStack(){
        head = null;
    }

    public void push(char c){
        head = new Node(c, head);
    }
    public char pop(){
        char c = head.c;
        head = head.next;
        return c;
    }
    public char top(){
        return head.c;
    }
    public boolean isEmpty(){
        return head == null;
    }
    public boolean isFull(){
        return false;
    }
    public void makenull(){
        head = null;
    }
}
