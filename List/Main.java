package List;
//import List.Array.*;
//import List.LinkedList.*;
import List.DoublyLinkedList.*;
//import List.CursorsList.*;

public class Main {
    public static void main(String[] args) {
        AbstractList.init();
        AbstractList list = new AbstractList();
        init(list);
        list.printList();
        RemoveDublicates(list);
        list.printList();
//        list.retrieve(list.locate(new Element("Bato", "Ulan-Ude"))).printElement();
        list.makenull();
        list.printList();
    }
    private static void RemoveDublicates(AbstractList list) {
        Position p = list.first();
        Position q;
        while (!p.equals(list.end())) {
            q = list.next(p);
            while (!q.equals(list.end())) {
                if (list.retrieve(p).equals(list.retrieve(q)))
                    list.delete(q);
                else
                    q = list.next(q);
            }
            p = list.next(p);
        }
    }

    private static void init(AbstractList list) {
        list.insert(new Element("Bato", "Ulan-Ude"),list.end());
        list.insert(new Element("Bato", "Ulan-Ude"),list.first());
        list.insert(new Element("Bato", "Ulan-Ude"),list.end());
        list.insert(new Element("Bato", "Ulan-Ude"),list.end());
        list.insert(new Element("Nikita", "Chita"),list.end());
        list.insert(new Element("Bato", "Ulan-Ude"),list.end());
        list.insert(new Element("Egor", "Moscow"),list.end());
        list.insert(new Element("Nikitaa", "Moscow"),list.first());
        list.insert(new Element("Nikitaa", "Moscow"),list.first());

//        System.out.println(list.locate(new Element("Nikitaa", "Moscoww")).object.element.name[0]);
//        list.insert(new Element("Nikita", "Moscow"),new Position(1));
//        System.out.println(list.previous(new Position(1)).p);
//        System.out.println(list.end().p);
//        System.out.println(list.locate(new Element("Nikita", "Moscow")).p);
    }
}
