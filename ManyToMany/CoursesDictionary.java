package ManyToMany;

public class CoursesDictionary {
    // Вместимость массива по умолчанию
    private int CAPACITY = 10;
    // Массив символьных массивов
    public Course[] array;
    // Символ удаленного элемента
    private final int DELETED = -1;

    // Размер массива
    private int size = 0;

    // default конструктор
    public CoursesDictionary(){
        array = new Course[CAPACITY];
    }

    // Конструктор с заданной вместимостью
    public CoursesDictionary(int capacity){
        this.CAPACITY = capacity;
        array = new Course[CAPACITY];
    }

    // Функция хеширования
    // Cумма кодов всех символов % 10
    private int hashFunction(int input) {
        return input % CAPACITY;
    }


    // Проверяет, есть ли свободное место в массиве
    private boolean hasSpace(){
        // Если размер массива == вместимости
        // Значит места нет
        return size != CAPACITY;
    }


    // Вставить
    public void insert(int id) {

        // Если нет места в массиве
        if (!hasSpace()) {
            throw new RuntimeException("insert(): Нет места для вставки");
        }

        // Хэш индекс
        int index = hashFunction(id);

        // Если объект другой - ищем свободное место
        int space = findSpace(index, id);
        if (space == -1) {
            return;
        }
        // Вставляем элемент
        array[space] = new Course(id);
        size++;
    }

    // Нахождение свободного места
    // Принимает в себя первоначальный хэш индекс
    // и элемент, который нужно вставить, для параллельной проверки одинакового элемента
    // Проверяет сразу ячейки, которые идут ПОСЛЕ первоначального индекса
    // Если находит одинаковый элемент - возвращает -1;
    // Если места нет - возвращает -1;
    // Возвращает место для вставки
    private int findSpace(int startIndex, int input){
        int i = 0;
        int space = hashFunction(startIndex + ++i);
        boolean deletedSpaceFound = false;
        int deletedSpace = -1;
        while (array[space] != null) {
            // Пока ищем сразу же проверяем ячейки
            // Если находит одинаковый элемент - возвращает -1;
            if (array[space].id == input) {
                return -1;
            }

            // Если место удалено, мы его сохраняем
            // и проходимся по оставшимся элементам,
            // чтобы убедиться, что такого элемента больше нет
            if(deletedSpace == -1 && array[space].id == DELETED) {
                deletedSpace = space;
            }
            if (space == startIndex) {
                if (deletedSpace != -1) {
                    return deletedSpace;
                }
                return -1;
            }
            space = hashFunction(startIndex + ++i);
        }
        return space;
    }


    // Удалить
    public void delete(int input) {

        // Первый хэш индекс
        int start = hashFunction(input);

        // Сразу удаляем если по хэш индексу находится нужный элемент
        // Проверяем, что ячейка не пустая и не удаленная
        // И по символьно проверяем массив, чтобы убедится, что это нужный элемент
        if (array[start] != null && array[start].id != DELETED && array[start].id == input) {
            array[start].id = DELETED;
            size--;
            return;
        }

        // Следующий индекс после хэш индекса
        int i = 0;
        int next = hashFunction(start + ++i);

        // Проходится до тех пор, пока не встретит пустую (Null) ячейку
        while (array[next] != null) {

            // Если ячейка не удаленная, и это нужный элемент - удаляем
            if (array[next].id != DELETED && array[next].id == input) {
                array[next].id = DELETED;
                size--;
                return;
            }

            //Следующий элемент
            next = hashFunction(start + ++i);

            // Вернулись к начальному индексу - элемент не найден
            if (next == start) {
                System.out.println("Элемент не найден");
                break;
            }

        }
    }

    public Course findCourse(int input) {
        // Первый хэш индекс
        int start = hashFunction(input);

        // Сразу true если по хэш индексу находится нужный элемент
        // Проверяем, что ячейка не пустая и не удаленная
        // И по символьно проверяем массив, чтобы убедится, что это нужный элемент
        if (array[start] != null && array[start].id != DELETED && array[start].id == input) {
            return array[start];
        }

        // Следующий индекс после хэш индекса
        int i = 0;
        int next = hashFunction(start + ++i);

        // Проходится до тех пор, пока не встретит пустую (Null) ячейку
        while (array[next] != null) {

            // Если ячейка не удаленная, и это нужный элемент - true
            if (array[next].id != DELETED && array[next].id == input) {
                return array[next];
            }

            //Следующий элемент
            next = hashFunction(start + ++i);

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
            } else if (array[i].id == DELETED) {
                System.out.println("DELETED");
            } else {
                System.out.println(array[i].id);
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
