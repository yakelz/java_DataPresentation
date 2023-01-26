package Queue.Array;

public class ArrayQueue {
    private char[] array;
    private int head;
    private int tail;
    private int SIZE = 10;

    public ArrayQueue(){
        this.array = new char[SIZE];
        this.tail = SIZE-1;
        this.head = 0;
    }

    private int next(int position){
        return ((position+1) % SIZE);
    }

    public void enqueue(char x){
        tail = next(tail);
        array[tail] = x;
    }
    public char dequeue(){
        char temp = array[head];
        head = next(head);
        return temp;
    }
    public char front(){
        return array[head];
    }
    public boolean isFull(){
        return head == next(next(tail));
    }
    public boolean isEmpty(){
        return head == next(tail);
    }
    public void makenull(){
        tail = SIZE-1;
        head = 0;
    }
}
