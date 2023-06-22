package Dictionary.OpenHash;
public class Dictionary {
    private class Node {
        char[] name;
        Node next;

        public Node(char[] name, Node next) {
            this.name = name;
            this.next = next;
        }

        @Override
        public String toString() {
            String result = "[";
            for (int i = 0; i < name.length; i++) {
                if ((int)name[i] == 0) {
                    break;
                }
                result += name[i];
            }
            return result + "]";
        }

        public boolean compareArrays(char[] input) {
            for (int i = 0; i < this.name.length; i++) {
                if (this.name[i] != input[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public int CAPACITY = 10;
    public Node[] array;

    // default конструктор
    public Dictionary(){
        array = new Node[CAPACITY];
    }

    // Конструктор с заданной вместимостью
    public Dictionary(int capacity){
        this.CAPACITY = capacity;
        array = new Node[CAPACITY];
    }

    // Хэш значение в массиве
    private int hashFunction(int hashCode) {
        return hashCode % CAPACITY;
    }

    // Хэш значение
    private int hashCode(char[] input) {
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            result += input[i];
        }
        return result;
    }

    // Вставить
    public void insert(char[] input) {
        int hashCode = hashCode(input);
        int index = hashFunction(hashCode);
        // Если элемент массива пустой, тогда сразу вставляем
        if(array[index] == null) {
            array[index] = new Node(input, null);
            return;
        }
        // Ищем свободное место для вставки
        Node h = array[index];
        while (h != null) {
            // Если такое имя уже есть в списке - просто выходим
            if (h.compareArrays(input)) {
                return;
            }
            h = h.next;
        }
        // Вставляю новый элемент
        Node newNode = new Node(copyArray(input), array[index]);
        array[index] = newNode;
    }

    private Node findNode(Node startNode, char[] input) {
        Node prev = startNode;
        Node h = prev.next;
        while (h != null) {
            if (h.compareArrays(input)) {
                return prev; // возвращаем предыдущий узел
            }
            prev = h;
            h = h.next;
        }
        return null; // если не нашли, возвращаем null
    }

    public void delete(char[] input) {
        int hashCode = hashCode(input);
        // Хэш индекс
        int index = hashFunction(hashCode);

        // Если элемент пуcтой - сразу выходим
        if(array[index] == null) {
            return;
        }

        // Если надо удалить первый элемент - меняем ссылку в массиве
        if (array[index].compareArrays(input)) {
            array[index] = array[index].next;
            return;
        }

        // Ищем нужный элемент в цепочке
        Node prev = findNode(array[index], input);
        if (prev != null && prev.next != null) {
            prev.next = prev.next.next;
        }
    }

    public boolean member(char[] input) {
        int hashCode = hashCode(input);
        // Хэш индекс
        int index = hashFunction(hashCode);

        // Если элемент пустой - сразу выходим
        if(array[index] == null) {
            return false;
        }

        // Если это первый элемент
        if (array[index].compareArrays(input)) {
            return true;
        }

        // Ищем нужный элемент в цепочке
        Node prev = findNode(array[index], input);
        return prev != null && prev.next != null;
    }


    public void makenull() {
        for (int i = 0; i < CAPACITY; i++) {
            array[i] = null;
        }
    }

    private char[] copyArray(char[] input) {
        char[] output = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];
        }
        return output;
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + ": ");
            Node node = array[i];
            if (node == null) {
                System.out.println("null");
                continue;
            }
            System.out.print(node);
            if (node.next == null) {
                System.out.println();
                continue;
            }
            node = node.next;
            while (node != null) {
                System.out.print(" -> " + node);
                node = node.next;
            }
            System.out.println();
        }
    }
}
