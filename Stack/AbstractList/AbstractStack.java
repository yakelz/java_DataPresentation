package Stack.AbstractList;
import List.LinkedList.*;
import List.Element;
public class AbstractStack {
    private AbstractList list;
    public AbstractStack(){
        list = new AbstractList();
    }
    public void push(Element element){
        list.insert(element, list.first());
    }
    public Element pop(){
        Element temp = list.retrieve(list.first());
        list.delete(list.first());
        return temp;
    }
    public Element top(){
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
