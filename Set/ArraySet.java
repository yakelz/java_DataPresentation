package Set;
import java.util.Arrays;

public class ArraySet {
    int x; // Левая граница
    int y; // Права граница
    int[] array; // Массив

    // Класс позиции
    private class Position{
        int index; // Адрес в массиве
        int bit; // Номер бита
        Position (int index, int bit) {
            this.index = index;
            this.bit = bit;
        }
    }

    public ArraySet(int x, int y) {
        this.x = x;
        this.y = y;
        // Если y < x, тогда ошибка
        if (y < x) {
            throw new RuntimeException("ArraySet(): не правильно указаны границы");
        }
        // Инициализация массива
        // (y - x) / 32 + 1   -  длина массива
        array = new int[(y - x) / 32 + 1];

    }

    // Нахождение нужной ячейки массива
    private Position findInArray(int value) {
        int bitNumber = value - x;
        return new Position(bitNumber/32, bitNumber%32);
    }

    // Возвращает множество с которым есть все неповторяющиеся элементы
    public ArraySet Union (ArraySet input) {
        // Если input это тоже самое множество - выход
        if (input == this) {
            return this;
        }
        // Вычисляем Минимальной левую границу, и максимально правую границу
        // Создаем новый массив c новыми границами

        // Копируем биты первого массива в новый массив с правильным смещением
        // Этот массив побитово умножаем на элементы второго массива
        return null;
    }

    //Возвращает множество общих элементов
    public ArraySet Intersection(ArraySet input){
        // Если input это тоже самое множество - выход
        // Если пересечений границ нет - возвращаем пустой массив

        // Вычисляем Минимальной левую границу, и максимально правую границу
        // Создаем новый массив c новыми границами

        // Копируем биты первого массива в новый массив с правильным смещением

        // Те значения которые не входят в диапазон - их обнуляем

        // Этот массив побитово умножаем на элементы второго массива
        return null;
    }

    //Возвращает множество с элементами которых нет в input
    public ArraySet Difference (ArraySet input) {
        // Если input это тоже самое множество - выход

        // Вычисляем Минимальной левую границу, и максимально правую границу
        // Создаем новый массив c новыми границами

        // Копируем биты первого массива в новый массив с правильным смещением

        // Те элементы которые пересекаются - их исключить?
        return null;
    }

    // Возвращает множество с которым есть все неповторяющиеся элементы
    // Если множества не пересекаются
    public ArraySet Merge(ArraySet input){
        //Пров
        return null;
    }

    // Возвращает true - если value есть в множестве
    // false - если его нет
    public boolean Member(int value){
        // Проверить выход за границы
        // Найти нужную ячейку массива
        // Вычислить и вставить нужный бит, и побитовым И сравнить c ячейкой
        // Если ячейка != 0 - true
        return false;
    }

    // Добавление элемента в множество
    // Если элемент уже присутствует - выход.
    public void Insert(int x){
        // Проверить выход за границы
        // Найти нужную ячейку массива
        Position position = findInArray(x);
        // Вычислить и вставить нужный бит, и побитовым ИЛИ сравнить с ячейкой
        array[position.index] |= 1 << 31 - position.bit;
    }

    // Удаление элемента из множества
    // Если элемента нет - выход.
    public void Delete(){
        // Проверить выход за границы
        // Найти нужную ячейку массива
        // Вычислить и вставить нужный бит, и побитовым И с ИНВЕРСИЕЙ сравнить с ячейкой
    }

    // Присваивание двух разных множеств
    public void Assign(ArraySet input){
        // Копирующий метод?
        // проверяет, не совпадают ли объекты, если нет, вызывает копирующий конструктор
    }

    // Возвращает минимальное значение в множестве
    public int Min(){
        // Найти самый левый бит с 1
        return 0;
    }

    // Возвращает максимальное значение в множестве
    public int Max(){
        // Найти самый правый бит с 1
        return 0;
    }

    // Возвращает true - если множества одинаковые.
    public boolean Equal(){
        // Как по каждому элементу пройтись то? аывываыва
        return false;
    }
    // Возвращает множество, где найдет X
    // A и B - непересекающиеся множества
    // Если нет такого элемента, возвращаем пустое множество.
    public ArraySet Find(int x){
        return null;
    }

    // Очистить множество
    public void MakeNull(){
        //Обнулить ячейки массива
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
    }

    public void Print(){
        for (int i = 0; i < array.length; i++) {
//            System.out.println(toBinaryString(array[i]));
//            System.out.println(Integer.toBinaryString(i));
        }
    }
    public String toBinaryString(int value) {
        StringBuilder result = new StringBuilder(32);
        for (int i = 0; i < 32; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i = 0; i < 32; ++i) {
            if ((value & 1) == 1) {
                System.out.print("1 ");
            } else {
                System.out.print("0 ");
            }
            value >>>= 1;
        }
        return result.reverse().toString();
    }

    @Override
    public String toString() {
        return "ArraySet{" +
                "x=" + x +
                ", y=" + y +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}
