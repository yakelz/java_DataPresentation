package Map.LinkedList;

import List.Element;

public class Map {
    public static class Node {
        public Element element;
        public Node next;

        public Node (char[] d, char[] r, Node next) {
            //d - name
            //r - address
            this.element = new Element(d,r);
            this.next = next;
        }
    }
    private Node head;
    public Map(){
        head = null;
    }
    public void makenull(){
        head = null;
    }
    public void assign(char[] d, char[] r){
        if (head == null) {
            head = new Node(d,r, null);
            return;
        }
        Node temp = locate(d);
        if (temp != null) {
            temp.element.address = r;
            return;
        }

        temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(d,r,null);
    }

    private Node locate(char[] d){
        Node h = head;
        while (h != null) {
            if(h.element.compareName(d)){
                return h;
            }
            h = h.next;
        }
        return null;
    }

    public boolean compute(char[] d, char[] r){
        Node temp = locate(d);
        if (temp != null) {
            r = copyArray(temp.element.address);
            return true;
        }
        return false;
    }

    private char[] copyArray(char[] input){
        char[] temp = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            temp[i] = input[i];
        }
        return temp;
    }

    public void print(){
        if(head == null) {
            System.out.println("Map is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            temp.element.printElement();
            temp = temp.next;
        }
    }
}
