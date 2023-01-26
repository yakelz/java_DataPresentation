package Stack.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        String str = "Я люблю свою родину";
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (!stack.isFull()) {
                stack.push(array[i]);
            }
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }
}
