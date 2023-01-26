package Map.AbstractList;
import List.Element;
import List.LinkedList.*;

public class Map {
    AbstractList map;
    public Map (){
        map = new AbstractList();
    }
    public void makenull(){
        map.makenull();
    }

    public void print(){
        map.printList();
    }

    public void assign(char[] name, char[] address){
        Element element = new Element(name,address);
        if (map.first().equals(map.end())) {
            map.insert(element, map.first());
            return;
        }
        Position p = locate(name);
        if (p.node != null) {
            map.retrieve(p).address = address;
            return;
        }
        map.insert(element, map.first());

    }
    public boolean compute(char[] name, char[] address){
        Element element = new Element(name,address);
        Position p = locate(element.name);
        if (p.node == null) {
            return false;
        }
        map.retrieve(p).address = address;
        return true;
    }

    private Position locate(char[] d){
        Position h = map.first();
        Element temp;
        while (!h.equals(map.end())) {
            temp = map.retrieve(h);
            if(temp.compareName(d)){
                return h;
            }
            h = map.next(h);
        }
        return h;
    }

}
