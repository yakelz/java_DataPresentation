package ManyToMany;

public class StudentsDictionary {
    // Вместимость массива по умолчанию
    private int CAPACITY = 10;
    // Массив символьных массивов
    public Student[] array;
    // Символ удаленного элемента
    private final String DELETED = "0";

    // Размер массива
    private int size = 0;

    // default конструктор
    public StudentsDictionary(){
        array = new Student[CAPACITY];
    }

    // Конструктор с заданной вместимостью
    public StudentsDictionary(int capacity){
        this.CAPACITY = capacity;
        array = new Student[CAPACITY];
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
    public void insert(Student key) {
        // Если нет места в массиве
        if (!hasSpace()) {
            throw new RuntimeException("insert(): Нет места для вставки");
        }

        // Хэш индекс
        int index = hashFunction(key.name.toCharArray());

        // Если объект другой - ищем свободное место
        int space = findSpace(index, key.name);
        if (space == -1) {
            return;
        }
        // Вставляем элемент
        array[space] = key;
        size++;
    }

    // Нахождение свободного места
    // Принимает в себя первоначальный хэш индекс
    // и элемент, который нужно вставить, для параллельной проверки одинакового элемента
    // Проверяет сразу ячейки, которые идут ПОСЛЕ первоначального индекса
    // Если находит одинаковый элемент - возвращает -1;
    // Если места нет - возвращает -1;
    // Возвращает место для вставки
    private int findSpace(int startIndex, String input){
        int i = 0;
        int space = linearHashFunction(startIndex, ++i);
        while (array[space] != null) {
            // Пока ищем сразу же проверяем ячейки
            // Если находит одинаковый элемент - возвращает -1;
            if (array[space].name.equals(input)) {
                return -1;
            }

            // Если место удалено, мы его сохраняем
            // и проходимся по оставшимся элементам,
            // чтобы убедиться, что такого элемента больше нет
            if(array[space].name.equals(DELETED)) {
                int deletedSpace = space;
                while (array[space] != null) {
                    // Если находит одинаковый элемент - возвращает -1;
                    if (array[space].name.equals(input)) {
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
        return space;
    }


    // Удалить
    public void delete(String input) {

        // Первый хэш индекс
        int start = hashFunction(input.toCharArray());

        // Сразу удаляем если по хэш индексу находится нужный элемент
        // Проверяем, что ячейка не пустая и не удаленная
        // И по символьно проверяем массив, чтобы убедится, что это нужный элемент
        if (array[start] != null && !array[start].name.equals(DELETED) && array[start].name.equals(input)) {
            array[start].name = DELETED;
            size--;
            return;
        }

        // Следующий индекс после хэш индекса
        int i = 0;
        int next = linearHashFunction(start, ++i);

        // Проходится до тех пор, пока не встретит пустую (Null) ячейку
        while (array[next] != null) {

            // Если ячейка не удаленная, и это нужный элемент - удаляем
            if (!array[next].name.equals(DELETED) && array[next].name.equals(input)) {
                array[next].name = DELETED;
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

    public Student findStudent(String input) {

        // Первый хэш индекс
        int start = hashFunction(input.toCharArray());

        // Сразу true если по хэш индексу находится нужный элемент
        // Проверяем, что ячейка не пустая и не удаленная
        // И по символьно проверяем массив, чтобы убедится, что это нужный элемент
        if (array[start] != null && !array[start].name.equals(DELETED) && array[start].name.equals(input)) {
            return array[start];
        }

        // Следующий индекс после хэш индекса
        int i = 0;
        int next = linearHashFunction(start, ++i);

        // Проходится до тех пор, пока не встретит пустую (Null) ячейку
        while (array[next] != null) {

            // Если ячейка не удаленная, и это нужный элемент - true
            if (!array[next].name.equals(DELETED) && array[next].name.equals(input)) {
                return array[next];
            }

            //Следующий элемент
            next = linearHashFunction(start, ++i);

            // Вернулись к начальному индексу - элемент не найден
            if (next == start) {
                break;
            }
        }
        return null;
    }

    public void print() {
        for (int i = 0; i < CAPACITY; i++) {
            System.out.print(i + ": ");
            if (array[i] == null) {
                System.out.println("null");
            } else if (array[i].name == DELETED) {
                System.out.println("DELETED");
            } else {
                System.out.println(array[i].name);
            }
        }
    }

    public void makenull() {
        for (int i = 0; i < CAPACITY; i++) {
            array[i] = null;
        }
    }

    public int size(){
        return size;
    }

}
