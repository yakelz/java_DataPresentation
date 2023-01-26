package Stack.AbstractList;
import List.Element;

public class Main {
    public static void main(String[] args) {
        AbstractStack stack = new AbstractStack();
        stack.push(new Element("Bato", "Ulan-Ude"));
        stack.push(new Element("Nikita", "Chita"));
        while (!stack.isEmpty()){
            stack.pop().printElement();
        }
        stack.top().printElement();
    }
}
