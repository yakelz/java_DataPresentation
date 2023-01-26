package Queue.AbstactList;
import List.Element;
import List.LinkedList.*;

public class AbstractQueue {
    private AbstractList list;
    public AbstractQueue(){
        list = new AbstractList();
    }
    public void enqueue(Element x){
        list.insert(x, list.end());
    }
    public Element dequeue(){
        if (isEmpty()) {
            throw new RuntimeException("dequeue(): Queue isEmpty");
        }
        Element temp = list.retrieve(list.first());
        list.delete(list.first());
        return temp;
    }

    public Element front(){
        if (isEmpty()) {
            throw new RuntimeException("dequeue(): Queue isEmpty");
        }
        return list.retrieve(list.first());
    }

    public boolean isEmpty(){
        return list.first().node == null;
    }
    public boolean isFull(){
        return false;
    }
    public void makenull(){
        list.makenull();
    }
}
