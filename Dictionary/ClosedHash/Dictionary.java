package Dictionary.ClosedHash;

// Сделать, чтобы нельзя было добавлять одинаковые элементы

public class Dictionary {
    // Вместимость массива по умолчанию
    private static int CAPACITY = 10;
    // Массив символьных массивов
    public char[][] array;
    // Символ удаленного элемента
    private final char DELETED = '0';

    // default конструктор
    public Dictionary(){
        array = new char[CAPACITY][];
    }

    // Конструктор с заданной вместимостью
    public Dictionary(int capacity){
        this.CAPACITY = capacity;
        array = new char[CAPACITY][];
    }

    // Функция хеширования
    // Cумма кодов всех символов % 10
    private int hashFunction(char[] input) {
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            result += input[i];
        }
//        System.out.println("Result: " + result);
        return result % CAPACITY;
    }

    // Линейный хэш индекс (следующее значение)
    private int linearHashFunction(int index, int i) {
        return (index + i) % CAPACITY;
    }

    // Проверяет, есть ли свободное место в массиве
    private boolean hasSpace(){
        // Проходит по каждому массиву в массиве
        for (char[] name : array) {
            // И проверяет пустой он ИЛИ удаленный
            if (name == null || name[0] == DELETED) {
                return true;
            }
        }
        return false;
    }

    // Вставить
    public void insert(String key) {
        // Если нет места в массиве
        if (!hasSpace()) {
            throw new RuntimeException("insert(): Нет места для вставки");
        }

        // Вызвать member() чтобы проверить есть ли такой элемент?

        // Перевод String -> Char[]
        char[] input = key.toCharArray();
        // Хэш индекс
        int index = hashFunction(input);

        //Если Хэш индекс пустой или удаленный - сразу вставляем
        if (array[index] == null || array[index][0] == DELETED) {
//            System.out.println("Свободный индекс " + index);
            array[index] = input;
            return;
        }

        //Если нет ищем свободное место
        int i = 0;
        int space = linearHashFunction(index, ++i);
        while (array[space] != null && array[space][0] != DELETED ) {
//            System.out.println(space);
            space = linearHashFunction(index, ++i);
        }
        array[space] = input;
    }

    // По символьное сравнение массивов (для удаления элемента)
    private boolean compareArrays(char[] a, char[] b){
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    // Удалить
    public void delete(String key) {
        // Перевод String -> Char[]
        char[] input = key.toCharArray();
        // Первый хэш индекс
        int start = hashFunction(input);

        // Сразу удаляем если по хэш индексу находится нужный элемент
        // Проверяем, что ячейка не пустая и не удаленная
        // И по символьно проверяем массив, чтобы убедится, что это нужный элемент
        if (array[start] != null && array[start][0] != DELETED && compareArrays(array[start], input)) {
            array[start][0] = DELETED;
            return;
        }

        // Следующий индекс после хэш индекса
        int i = 0;
        int next = linearHashFunction(start, ++i);

        // Проходится до тех пор, пока не встретит пустую (Null) ячейку
        while (array[next] != null) {

            // Если ячейка не удаленная, и это нужный элемент - удаляем
            if (array[next][0] != DELETED && compareArrays(array[next], input)) {
                array[next][0] = DELETED;
                return;
            }

            //Следующий элемент
            next = linearHashFunction(start, ++i);

            // Вернулись к начальному индексу - элемент не найден
            if (next == start) {
                System.out.println("Элемент не найден");
                break;
            }

        }
    }

    public boolean member(String key) {
        // Перевод String -> Char[]
        char[] input = key.toCharArray();
        // Первый хэш индекс
        int start = hashFunction(input);

        // Сразу true если по хэш индексу находится нужный элемент
        // Проверяем, что ячейка не пустая и не удаленная
        // И по символьно проверяем массив, чтобы убедится, что это нужный элемент
        if (array[start] != null && array[start][0] != DELETED &&  compareArrays(array[start], input)) {
            return true;
        }

        // Следующий индекс после хэш индекса
        int i = 0;
        int next = linearHashFunction(start, ++i);

        // Проходится до тех пор, пока не встретит пустую (Null) ячейку
        while (array[next] != null) {

            // Если ячейка не удаленная, и это нужный элемент - true
            if (array[next][0] != DELETED && compareArrays(array[next], input)) {
                return true;
            }

            //Следующий элемент
            next = linearHashFunction(start, ++i);

            // Вернулись к начальному индексу - элемент не найден
            if (next == start) {
                break;
            }
        }
        return false;
    }

    public void print() {
        for (int i = 0; i < CAPACITY; i++) {
            System.out.print(i + ": ");
            if (array[i] == null) {
                System.out.println("null");
            } else if (array[i][0] == DELETED) {
                System.out.println("DELETED");
            } else {
                System.out.println(array[i]);
            }
        }
    }

    public void makenull() {
        for (int i = 0; i < CAPACITY; i++) {
            array[i] = null;
        }
    }

}
