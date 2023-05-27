package Dictionary.ClosedHash;

public class Dictionary {
    // Вместимость массива по умолчанию
    private static int CAPACITY = 10;
    // Массив символьных массивов
    public char[][] array;
    // Символ удаленного элемента
    private final char DELETED = '0';

    // Размер массива
    private int size = 0;

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
        return result % CAPACITY;
    }

    // Линейный хэш индекс (следующее значение)
    private int linearHashFunction(int index, int i) {
        return (index + i) % CAPACITY;
    }

    // Проверяет, есть ли свободное место в массиве
    private boolean hasSpace(){
        // Если размер массива == вместимости
        // Значит места нет
        return size != CAPACITY;
    }


    // Вставить
    public void insert(String key) {
        // Если нет места в массиве
        if (!hasSpace()) {
            throw new RuntimeException("insert(): Нет места для вставки");
        }

        // Перевод String -> Char[]
        char[] input = key.toCharArray();
        // Хэш индекс
        int index = hashFunction(input);

        //Если массив по Хэш индексу пустой - сразу вставляем
        if (array[index] == null) {
            array[index] = input;
            size++;
            return;
        }

        // Если объект другой - ищем свободное место
        int space = findSpace(index, input);
        if (space == -1) {
            return;
        }
        // Вставляем элемент
        array[space] = input;
        size++;
    }

    // Нахождение свободного места
    // Принимает в себя первоначальный хэш индекс
    // и элемент, который нужно вставить, для параллельной проверки одинакового элемента
    // Проверяет сразу ячейки, которые идут ПОСЛЕ первоначального индекса
    // Если находит одинаковый элемент - возвращает -1;
    // Если места нет - возвращает -1;
    // Возвращает место для вставки
    private int findSpace(int startIndex, char[] input){
        int i = 0;
        int space = linearHashFunction(startIndex, ++i);
        while (array[space] != null) {
            System.out.println("Смотрю место: " + space);
            // Пока ищем сразу же проверяем ячейки
            // Если находит одинаковый элемент - возвращает -1;
            if (compareArrays(array[space], input)) {
                return -1;
            }

            // Если место удалено, мы его сохраняем
            // и проходимся по оставшимся элементам,
            // чтобы убедиться, что такого элемента больше нет
            if(array[space][0] == DELETED) {
                int deletedSpace = space;
                while (array[space] != null) {
                    // Если находит одинаковый элемент - возвращает -1;
                    if (compareArrays(array[space], input)) {
                        return -1;
                    }
                    space = linearHashFunction(startIndex, ++i);
                    // Если мы пришли обратно, то одинакового элемента нет
                    // и можем вставлять в сохраненную (удаленную) ячейку
                    if (space == startIndex) {
                        return deletedSpace;
                    }
                }
            }
            space = linearHashFunction(startIndex, ++i);
            if (space == startIndex) {
                return -1;
            }
        }
        System.out.println("Возвращаю: " + space);
        return space;
    }

    // По символьное сравнение массивов (для удаления элемента)
    private boolean compareArrays(char[] a, char[] b){
        // Если массивы разного размера, то даже сравнивать не нужно - они не равны
        if (a.length != b.length) {
            return false;
        }
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
            size--;
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
                size--;
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
