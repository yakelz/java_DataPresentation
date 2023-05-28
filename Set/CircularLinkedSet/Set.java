package Set.CircularLinkedSet;

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

    // default конструктор
    public Set(){
        tail = null;
    }

    // Копирующий конструктор
    public Set(Set input) {
        copySet(input);
    }
    private void copySet(Set input){
        //Если множество пустое
        if (input.isEmpty()) {
            tail = null;
            return;
        }
        //Копируем значение с хвоста
        tail = new Node(input.tail.value, null);
        tail.next = tail;

        Node current = tail;
        //Проходимся по всему input списку
        Node h = input.tail.next;
        System.out.println(h.value);
        System.out.println(input.tail.value);
        while (h != input.tail) {

            //И копируем значения в исходный список
            current.next = new Node(h.value, tail);
            h = h.next;
            current = current.next;
        }
    }


    // Возвращает множество в котором есть все неповторяющиеся элементы
    public Set Union (Set input) {
        // Проверить this = input -> return копию списка
        if (this == input) {
            return new Set(input);
        }

        // Если списки пустые -> возвращаем пустой список
        if (this.isEmpty() && input.isEmpty()) {
            return new Set();
        }

        // Если первый список пустой -> Возвращаем второй список
        if (this.isEmpty()) {
            return new Set(input);
        }
        // Если второй список пустой -> Возвращаем первый список
        if (input.isEmpty()) {
            return new Set(this);
        }

        //Создаем новый общий список
        Set union = new Set();
        Node currentHead = tail.next;
        Node inputHead = input.tail.next;

        //Проходимся по обоим спискам одновременно
        while (currentHead != tail && inputHead != input.tail) {
            // Если элементы равны, добавляем любой из них
            // И двигаемся дальше по двум спискам сразу
            if (currentHead.value == inputHead.value) {
                union.Insert(currentHead.value);
                currentHead = currentHead.next;
                inputHead = inputHead.next;
                continue;
            }
            // Если элемент из ПЕРВОГО списка меньше, чем элемент из ВТОРОГО списка,
            // то добавляем его в общий список и двигаемся дальше по ПЕРВОМУ списку
            if (currentHead.value < inputHead.value) {
                union.Insert(currentHead.value);
                currentHead = currentHead.next;
            }
            // Если элемент из ВТОРОГО списка меньше, чем элемент из ПЕРВОГО списка,
            // то добавляем его в общий список и двигаемся дальше по ВТОРОМУ списку
            else {
                union.Insert(inputHead.value);
                inputHead = inputHead.next;
            }
        }


        // Добавляем оставшиеся элементы из ПЕРВОГО списка, если они не были добавлены ранее
        while (currentHead != tail) {
            union.Insert(currentHead.value);
            currentHead = currentHead.next;
        }

        // Добавляем оставшиеся элементы из ВТОРОГО списка, если они не были добавлены ранее
        while (inputHead != input.tail) {
            union.Insert(inputHead.value);
            inputHead = inputHead.next;
        }

        // Добавляем последний элемент из ПЕРВОГО списка, если он не был добавлен ранее
        union.Insert(currentHead.value);

        // Добавляем последний элемент из ВТОРОГО списка, если он не был добавлен ранее
        union.Insert(input.tail.value);


        return union;
    }

    //Возвращает множество общих элементов
    public Set Intersection(Set input){
        // Проверить this = input -> return копию списка
        if (this == input) {
            return new Set(input);
        }

        // Если списки пустые -> возвращаем пустой список
        if (this.isEmpty() && input.isEmpty()) {
            return new Set();
        }

        Set intersection = new Set();

        Node currentHead = tail.next;
        Node inputHead = input.tail.next;

        while (currentHead != tail && inputHead != input.tail) {
            if (currentHead.value == inputHead.value) {
                intersection.Insert(currentHead.value);
                currentHead = currentHead.next;
                inputHead = inputHead.next;
            } else if (currentHead.value < inputHead.value) {
                currentHead = currentHead.next;
            } else {
                inputHead = inputHead.next;
            }
        }

        if (currentHead.value == inputHead.value) {
            intersection.Insert(currentHead.value);
        }

        return intersection;
    }

    //Возвращает множество с элементами которых нет в исходном множестве
    public Set Difference (Set input) {
        if (this == input || input.isEmpty()) {
            return new Set();
        }
        if (this.isEmpty()) {
            return new Set(input);
        }

        Set difference = new Set();
        Node currentHead = tail.next;
        Node inputHead = input.tail.next;

        while (currentHead != tail && inputHead != input.tail) {
            if (currentHead.value < inputHead.value) {
                difference.Insert(currentHead.value);
                currentHead = currentHead.next;
            } else if (currentHead.value > inputHead.value) {
                inputHead = inputHead.next;
            } else {
                currentHead = currentHead.next;
                inputHead = inputHead.next;
            }
        }

        while (currentHead != tail) {
            difference.Insert(currentHead.value);
            currentHead = currentHead.next;
        }

        if (currentHead.value != input.tail.value) {
            difference.Insert(currentHead.value);
        }

        return difference;
    }

    // Возвращает множество с которым есть все неповторяющиеся элементы
    // Только если множества не пересекаются
    public Set Merge(Set input){
        // Проверить this = input -> return копию списка
        if (this == input) {
            return new Set(input);
        }

        // Если списки пустые -> возвращаем пустой список
        if (this.isEmpty() && input.isEmpty()) {
            return new Set();
        }

        // Если первый список пустой -> Возвращаем второй список
        if (this.isEmpty()) {
            return new Set(input);
        }
        // Если второй список пустой -> Возвращаем первый список
        if (input.isEmpty()) {
            return new Set(this);
        }

        //Создаем новый общий список
        Set union = new Set();
        Node currentHead = tail.next;
        Node inputHead = input.tail.next;

        //Проходимся по обоим спискам одновременно
        while (currentHead != tail && inputHead != input.tail) {
            // Если элемент из ПЕРВОГО списка меньше, чем элемент из ВТОРОГО списка,
            // то добавляем его в общий список и двигаемся дальше по ПЕРВОМУ списку
            if (currentHead.value < inputHead.value) {
                union.Insert(currentHead.value);
                currentHead = currentHead.next;
            }
            // Если элемент из ВТОРОГО списка меньше, чем элемент из ПЕРВОГО списка,
            // то добавляем его в общий список и двигаемся дальше по ВТОРОМУ списку
            else {
                union.Insert(inputHead.value);
                inputHead = inputHead.next;
            }
        }


        // Добавляем оставшиеся элементы из ПЕРВОГО списка, если они не были добавлены ранее
        while (currentHead != tail) {
            union.Insert(currentHead.value);
            currentHead = currentHead.next;
        }

        // Добавляем оставшиеся элементы из ВТОРОГО списка, если они не были добавлены ранее
        while (inputHead != input.tail) {
            union.Insert(inputHead.value);
            inputHead = inputHead.next;
        }

        // Добавляем последний элемент из ПЕРВОГО списка, если он не был добавлен ранее
        union.Insert(currentHead.value);

        // Добавляем последний элемент из ВТОРОГО списка, если он не был добавлен ранее
        union.Insert(input.tail.value);


        return union;
    }

    // Возвращает true - если value есть в множестве
    // false - если его нет
    public boolean Member(int value){
        return isMember(value);
    }

    private boolean isMember(int value){
        // Если список пустой -> false
        if (isEmpty()) {
            return false;
        }
        // Проверить границу
        if (value < tail.next.value || value > tail.value) {
            return false;
        }

        // Проходимся по каждому элементу
        Node h = tail.next;

        while (h != tail) {
            if (h.value == value) {
                return true;
            }
            h = h.next;
        }

        return false;

    }

    public void Insert(int value) {
        if (isMember(value)) {
            return;
        }
        add(value);
    }

    // Вставка значения в множество
    private void add(int value){
        // Если tail = null (список пустой)
            // Добавляем просто элемент
        if (isEmpty()) {
            tail = new Node(value, null);
            tail.next = tail;
            return;
        }

        // Если элемент > последнего, вставляем в последний
        if (value > tail.value) {
            //System.out.println("Элемент > последнего, вставляю:" +  value);
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
        if (isEmpty()) {
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
        // Копирующий метод
        copySet(input);
    }

    // Возвращает минимальное значение в множестве
    public int Min(){
        //Минимальное значение это начало списка

        if (isEmpty()) {
            throw new RuntimeException("Min(): множество пустое");
        }
        return tail.next.value;
    }

    // Возвращает максимальное значение в множестве
    public int Max(){
        //Максимальное значение это конец списка

        if (isEmpty()) {
            throw new RuntimeException("Max(): множество пустое");
        }
        return tail.value;
    }

    // Возвращает true - если множества одинаковые.
    public boolean Equal(Set input){

        //Проходимся по каждому списку одновременно и проверяем
        // т.к список упорядоченный, проверяем соответсвующий элмент первого со вторым
        // и если они не равны, сразу выкидываем false
        Node currentTail = tail;
        Node inputTail = input.tail;
        while (currentTail.next != tail && inputTail.next != input.tail) {
            if (currentTail.value != inputTail.value) {
                return false;
            }
            currentTail = currentTail.next;
            inputTail = inputTail.next;
        }
        return currentTail.next.value == inputTail.next.value;
    }

    // Возвращает множество, где найдет X
    // A и B - непересекающиеся множества
    // Если нет такого элемента, возвращаем пустое множество.
    public Set Find(Set input, int x){
        // Вызвать Member() на обоих множествах
        if (isMember(x)) {
            return this;
        }
        if (input.isMember(x)) {
            return input;
        }
        return null;
    }

    // Очистить множество
    public void MakeNull(){
        tail = null;
    }

    private boolean isEmpty(){
        return tail == null;
    }
    public void Print(){
        if (isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        Node h = tail.next;
        int i = 0;
        while (h != tail) {
            System.out.print("[" + i + "] ");
            System.out.print(h.value + "\n");
            i++;
            h = h.next;
        }
        System.out.print("[" + i + "] ");
        System.out.print(h.value + " " + "\n");

        System.out.println();
    }
}
