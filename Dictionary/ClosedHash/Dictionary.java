package Dictionary.ClosedHash;

//
public class Dictionary {
    // Массив символьных массивов

    // Размер массива
    public final int SIZE;
    // Массив
    public char[][] array;

    // default конструктор
    public Dictionary(int size){
        this.SIZE = size;
        array = new char[SIZE][10];
    }

    // Функция хеширования
    // Cумма кодов всех символов % 10
    private int hashFunction(int value) {
        return value % 10;
    }

    // Вставить
    public void insert() {

    }

    // Удалить
    public void delete() {

    }

    public void member() {}

    public void print() {}

    public void makenull() {

    }

}
