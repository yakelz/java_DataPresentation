package Set.CircularLinkedSet;

import Set.LinkedSet.LinkedSet;

public class Set {
    public class Node {
        public int value;
        public Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node tail;


    public Set(){
        tail = null;
    }

    // Возвращает множество в котором есть все неповторяющиеся элементы
    public Set Union (Set input) {
        return null;
    }

    //Возвращает множество общих элементов
    public Set Intersection(Set input){
        return null;
    }

    //Возвращает множество с элементами которых нет в исходном множестве
    public Set Difference (Set input) {
        return null;
    }

    // Возвращает множество с которым есть все неповторяющиеся элементы
    // Только если множества не пересекаются
    public Set Merge(Set input){
        return null;
    }

    // Возвращает true - если value есть в множестве
    // false - если его нет
    public boolean Member(int value){
        return false;
    }

    // Вставка значения в множество
    public void Insert(int value){
        // Если tail = null (список пустой)
            // Добавляем просто элемент
        if (tail == null) {
            tail = new Node(value, null);
            tail.next = tail;
            return;
        }

        // Если элемент > последнего, вставляем в последний
        if (value > tail.value) {
            System.out.println("Элемент > последнего, вставляю:" +  value);
            Node insert = new Node(value, tail.next);
            tail.next = insert;
            tail = insert;
            return;
        }

        Node current = tail.next;
        Node prev = tail;
        //Проходимся по списку и ищем нужную позицию
            // Если элемент > чем head.value
                //добавляем элемент
        while (current != tail && value > current.value) {
            prev = current;
            current = current.next;
        }
        // Всталяем
        prev.next = new Node(value, current);
    }

    // Удаление элемента из множества
    public void Delete(int value){
        //Если список пустой - выход.
        if (tail == null) {
            System.out.println("Список пустой" + value);
            return;
        }
        //Если один элемент - удаляем последний
        if (tail == tail.next) {
            System.out.println("В списке один элемент, удаляю " + value);
            tail = null;
            return;
        }

        //Ищем нужный элемент
        Node current = tail.next;
        Node prev = tail;
        while (current != tail && current.value != value) {
            prev = current;
            current = current.next;
        }

        //Если не нашли в списке значение - выход.
        if (current == tail && current.value != value) {
            System.out.println("Не нашли значение " + value);
            return;
        }

        prev.next = current.next;
    }

    // Присваивание нового множества
    public void Assign(Set input){

    }

    // Возвращает минимальное значение в множестве
    public int Min(){
        //Минимальное значение это начало списка
        return tail.next.value;
    }

    // Возвращает максимальное значение в множестве
    public int Max(){
        //Максимальное значение это конец списка
        return tail.value;
    }

    // Возвращает true - если множества одинаковые.
    public boolean Equal(){
        return false;
    }

    // Возвращает множество, где найдет X
    // A и B - непересекающиеся множества
    // Если нет такого элемента, возвращаем пустое множество.
    public Set Find(Set input, int x){
        return null;
    }

    // Очистить множество
    public void MakeNull(){
    }

    public void Print(){
        if (tail == null) {
            System.out.println("Список пуст");
            return;
        }
        Node h = tail.next;
        int i = 0;
        while (h != tail) {
            System.out.print("[" + i + "] ");
            System.out.print(h.value + " -> " + h.next.value + "\n");
            i++;
            h = h.next;
        }
        System.out.print("[" + i + "] ");
        System.out.print(h.value + " " + "\n");

        System.out.println();
    }
}
