package List.Array;
import List.Element;

public class AbstractList {

    private Element [] array;
    private Position last;
    private int SIZE = 10;

    public AbstractList(){
        array = new Element [SIZE];
        last = new Position(0);    //Первый свободный элемент
    }

    //Возвращает позицию после последнего
    public Position end(){
        return last;
    }

    //Вставка объекта "Х" в позицию "Р" в список "L"
    public void insert (Element x, Position p){
        Element input = new Element(x);
        if (last.p == SIZE || p.p == SIZE)
            throw new ArrayIndexOutOfBoundsException("insert(): В списке нету места");
        if (p.p > last.p || p.p < 0)
            throw new ArrayIndexOutOfBoundsException("insert(): Выход за границы массива");
//            return; // Проверка выхода за границы
        for (int i = last.p; i > p.p ; i--) {
            array[i] = array[i-1];
        }
        array[p.p] = input;
        last.p++;
    }

    //Возвращает позицию значения совпавщего с "Х" в списке "L"
    public Position locate (Element x){
//        int position = end(l);
        for (int i = 0; i < last.p; i++) {
            if (array[i].equals(x)){
                return new Position(i);
            }
        }
        return new Position(last.p);
    }

    //Возвращает элемент в позиции "P" в списке "L"
    public Element retrieve (Position p) {
        if (p.p < 0 || p.p >= last.p )
            throw new ArrayIndexOutOfBoundsException("retrieve(): выход за переделы массива");
        return array[p.p];
    }

    //Удаляет элемент списка "L" в позиции "Р"
    public void delete (Position p) {
        if (p.p >= last.p || p.p < 0)
            return;
        for (int i = p.p; i < last.p - 1; i++)
            array[i] = array[i+1];
        last.p--;
    }

    //Возвращает следующую позицию за "P" в списке "L"
    public Position next(Position p){
        if (p.p < 0 || p.p >= last.p)
            throw new ArrayIndexOutOfBoundsException("next(): выход за переделы массива");
        return new Position(p.p + 1);
    }

    //Возвращает предыдущую позицию для "P" в списке "L"
    public Position previous(Position p){
        if (p.p >= last.p || p.p <= 0)
            throw new ArrayIndexOutOfBoundsException("previous(): выход за переделы массива");
        return new Position(p.p - 1);
    }

    //Делает список пустым
    public void makenull(){
        last.p = 0;
    }

    //Возвращает позицию первого в списке "L"
    public Position first(){
        return new Position(0);
    }

    //Вывод на экран значений списка в соответсвий с их позицией
    public void printList(){
        if (last.p == 0) {
            System.out.println("Список пуст");
            return;
        }
        for (int i = 0; i < last.p; i++) {
            System.out.print("[" + i + "] ");
            array[i].printElement();
        }
        System.out.println();
    }
//    public static void init(){}

}
