package ManyToMany;

public class StudentsDictionary {
    // Вместимость массива по умолчанию
    private int CAPACITY = 10;
    // Массив символьных массивов
    public Student[] array;
    // Символ удаленного элемента
    private final char DELETED = '0';

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


    // Проверяет, есть ли свободное место в массиве
    private boolean hasSpace(){
        // Если размер массива == вместимости
        // Значит места нет
        return size != CAPACITY;
    }


    // Вставить
    public void insert(char[] name) {

        // Если нет места в массиве
        if (!hasSpace()) {
            throw new RuntimeException("insert(): Нет места для вставки");
        }

        int hashCode = hashCode(name);
        // Хэш индекс
        int index = hashFunction(hashCode);

        // Если объект другой - ищем свободное место
        int space = findSpace(index, name);
        if (space == -1) {
            return;
        }
        // Вставляем элемент
        array[space] = new Student(copyArray(name));
        size++;
    }

    private char[] copyArray(char[] input) {
        char[] output = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];
        }
        return output;
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
        int space = hashFunction(startIndex + ++i);
        int deletedSpace = -1;

        while (array[space] != null) {
            // Пока ищем сразу же проверяем ячейки
            // Если находит одинаковый элемент - возвращает -1;
            if (compareArrays(array[space].name, input)) {
                return -1;
            }
            // Если место удалено, мы его сохраняем
            // и проходимся по оставшимся элементам,
            // чтобы убедиться, что такого элемента больше нет
            if(deletedSpace == -1 && array[space].name[0] == DELETED) {
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

    // Поиск элемента в массиве
    private int findElement(char[] input) {
        int hashCode = hashCode(input);
        int start = hashFunction(hashCode);

        if (array[start] != null && array[start].name[0] != DELETED && compareArrays(array[start].name, input)) {
            return start;
        }

        // Следующий индекс после хэш индекса
        int i = 0;
        int next = hashFunction(start + ++i);

        // Проходится до тех пор, пока не встретит пустую (Null) ячейку
        while (array[next] != null) {

            // Если ячейка не удаленная, и это нужный элемент - возвращает индекс
            if (array[next].name[0] != DELETED && compareArrays(array[next].name, input)) {
                return next;
            }

            //Следующий элемент
            next = hashFunction(start + ++i);

            // Вернулись к начальному индексу - элемент не найден
            if (next == start) {
                break;
            }
        }

        return -1; // Элемент не найден
    }

    // Удалить
    public void delete(char[] input) {
        int index = findElement(input);
        if (index != -1) {
            array[index].name[0] = DELETED;
            size--;
        } else {
            System.out.println("Элемент не найден");
        }
    }

    public Student findStudent(char[] input) {
        int index = findElement(input);
        if (index != -1) {
            return array[index];
        }
        return null;
    }

    public void print() {
        for (int i = 0; i < CAPACITY; i++) {
            System.out.print(i + ": ");
            if (array[i] == null) {
                System.out.println("null");
            } else if (array[i].name[0] == DELETED) {
                System.out.println("DELETED");
            } else {
                for (int j = 0; j < array[i].name.length; j++) {
                    if(array[i].name[j] != '\0') {
                        System.out.print(array[i].name[j]);
                    }
                }
                System.out.println();
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
