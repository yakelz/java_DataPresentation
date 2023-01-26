package Queue.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedQueue q = new LinkedQueue();
        String str = "Я люблю свою родину";
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (!q.isFull()) {
                q.enqueue(array[i]);
            }
        }
        while (!q.isEmpty()) {
            System.out.print(q.dequeue());
        }
    }
}
