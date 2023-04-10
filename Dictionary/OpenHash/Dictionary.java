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

    public static int CAPACITY = 10;
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

    // Функция хеширования
    // Cумма кодов всех символов % 10
    private int hashFunction(char[] input) {
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            result += input[i];
        }
        return result % CAPACITY;
    }

    // Вставить
    public void insert(String key) {
        // Перевод String -> Char[]
        char[] input = key.toCharArray();
        // Хэш индекс
        int index = hashFunction(input);
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
        // Вставляю в голову новый элемент
        Node newNode = new Node(input, array[index]);
        array[index] = newNode;
    }

    // Удалить
    public void delete(String key) {
        // Перевод String -> Char[]
        char[] input = key.toCharArray();
        // Хэш индекс
        int index = hashFunction(input);

        // Если элемент пуcтой - сразу выходим
        if(array[index] == null) {
            return;
        }

        // Если надо удалить первый элемент - меняем ссылку в массиве
        if (array[index].compareArrays(input)) {
            array[index] = array[index].next;
            return;
        }

        // Ищем удаляемый элемент в цепочке
        Node h = array[index];
        while (h.next != null) {
            // Если следующий элемент нужный - меняем ссылку
            if (h.next.compareArrays(input)) {
                h.next = h.next.next;
                return;
            }
            h = h.next;
        }

    }

    public boolean member(String key) {
        // Перевод String -> Char[]
        char[] input = key.toCharArray();
        // Хэш индекс
        int index = hashFunction(input);

        // Если элемент пуcтой - сразу выходим
        if(array[index] == null) {
            return false;
        }

        // Если это первый элемент
        if (array[index].compareArrays(input)) {
            return true;
        }

        // Ищем нужный элемент в цепочке
        Node h = array[index];
        while (h.next != null) {
            // Если следующий элемент нужный - меняем ссылку
            if (h.next.compareArrays(input)) {
                return true;
            }
            h = h.next;
        }
        return false;
    }


    public void makenull() {
        for (int i = 0; i < CAPACITY; i++) {
            array[i] = null;
        }
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
