package Queue.AbstactList;

import List.Element;

public class Main {
    public static void main(String[] args) {
        AbstractQueue q = new AbstractQueue();
        q.enqueue(new Element("Bato", "Ulan-Ude"));
        q.enqueue(new Element("Nikita", "Chita"));
        q.front().printElement();
        System.out.println("Queue: ");
        while (!q.isEmpty()) {
            q.dequeue().printElement();
        }
        q.front().printElement();
    }
}
