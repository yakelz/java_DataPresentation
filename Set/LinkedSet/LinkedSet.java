package Set.LinkedSet;

// Абстрактный тип данных Множество
// реализуемый на Упорядоченном связном списке
public class LinkedSet {
    public class Node {
        public int value;
        public Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;

    //default конструктор
    public LinkedSet(){
        head = null;
    }

    //Копирующий конструктор
    public LinkedSet(LinkedSet input){
        copySet(input);
    }

    //Копирующий метод
    private void copySet(LinkedSet input) {
        //Если множество пустое
        if (input.head == null) {
            head = null;
            return;
        }
        //Копируем значение с головы
        head = new Node(input.head.value, null);
        Node current = head;

        //Проходимся по всему input списку
        Node h = input.head;
        while (h.next != null) {

            //И копируем значения в исходный список
            current.next = new Node(h.next.value, null);
            current = current.next;

            h = h.next;
        }
    }

    // Возвращает множество в котором есть все неповторяющиеся элементы
    public LinkedSet Union (LinkedSet input) {
        // Проверить this = input -> return копию списка
        if (this == input) {
            return new LinkedSet(input);
        }
        // Если списки пустые -> возвращаем пустой список
        if (head == null && input.head == null) {
            return new LinkedSet();
        }
        // Если первый список пустой -> Возвращаем второй список
        if (head == null) {
            return new LinkedSet(input);
        }
        // Если второй список пустой -> Возвращаем первый список
        if (input.head == null) {
            return new LinkedSet(this);
        }

        Node currentHead = head;
        Node inputHead = input.head;

        //Создаем новый общий список
        LinkedSet union = new LinkedSet();
        Node unionTail = null;

        //Проходимся по обоим спискам одновременно
        while (currentHead != null && inputHead != null) {
            Node newNode = null;
            // Если элементы равны, добавляем любой из них
            // И двигаемся дальше по двум спискам сразу
            if (currentHead.value == inputHead.value) {
                newNode = new Node(currentHead.value, null);
                currentHead = currentHead.next;
                inputHead = inputHead.next;
            }
            // Если элемент из ПЕРВОГО списка меньше, чем элемент из ВТОРОГО списка,
            // то добавляем его в общий список и двигаемся дальше по ПЕРВОМУ списку
            else if (currentHead.value < inputHead.value) {
                newNode = new Node(currentHead.value, null);
                currentHead = currentHead.next;
            }
            // Если элемент из ВТОРОГО списка меньше, чем элемент из ПЕРВОГО списка,
            // то добавляем его в общий список и двигаемся дальше по ВТОРОМУ списку
            else {
                newNode = new Node(inputHead.value, null);
                inputHead = inputHead.next;
            }

            if(union.head == null) {
                union.head = newNode;
                unionTail = newNode;
            } else {
                unionTail.next = newNode;
                unionTail = newNode;
            }
        }
        //Проходимся по первому списку до конца
        if(currentHead != null) {
            unionTail = appendRemaining(currentHead, unionTail);
        }

        //Проходимся по второму списку до конца
        if(inputHead != null) {
            unionTail = appendRemaining(inputHead, unionTail);
        }

        return union;
    }

    private Node appendRemaining(Node start, Node unionTail) {
        while (start != null) {
            unionTail.next = new Node(start.value, null);
            unionTail = unionTail.next;
            start = start.next;
        }
        return unionTail;
    }

    // Возвращает множество общих элементов
    // Cоздать копию и от туда убирать
    // Первый элемент в копии первого списка ищем во втором
    public LinkedSet Intersection(LinkedSet input){
        // Проверить this = input -> return копию списка
        if (this == input) {
            return new LinkedSet(input);
        }

        // Если списки пустые -> возвращаем пустой список
        if (head == null && input.head == null) {
            return new LinkedSet();
        }

        Node currentHead = head;
        Node inputHead = input.head;

        //Создаем новый общий список
        LinkedSet intersection = new LinkedSet();
        Node intersectionTail = null;

        while (currentHead != null && inputHead != null) {
            Node newNode = null;
            if (currentHead.value == inputHead.value) {
                newNode = new Node(currentHead.value, null);
                currentHead = currentHead.next;
                inputHead = inputHead.next;
            } else if (currentHead.value < inputHead.value) {
                currentHead = currentHead.next;
            } else {
                inputHead = inputHead.next;
            }

            if (newNode != null) { // Нашли ли мы что добавить?
                if (intersection.head == null) { // Добавляли ли мы уже что-то?
                    intersection.head = newNode;
                    intersectionTail = newNode;
                } else {
                    intersectionTail.next = newNode;
                    intersectionTail = newNode;
                }
            }
        }

        return intersection;
    }

    //Возвращает множество с элементами которых нет в исходном множестве
    //Создать копию и Выбрасывать значения?
    public LinkedSet Difference(LinkedSet input) {
        // Проверка на то, что это те же самые списки
        if (this == input) {
            return new LinkedSet();
        }
        if (head == null) {
            return new LinkedSet(input);
        }
        // Создаем новый связанный список для разности
        LinkedSet difference = new LinkedSet();
        Node differenceTail = null;

        Node currentHead = head;
        Node inputHead = input.head;

        // Идем по двум связным спискам и заполняем новое множество
        // Если значения одинаковые пропускаем, если разные, то добавляем в разность
        while (inputHead != null) {
            Node newNode = null;
            if (currentHead.value == inputHead.value) {
                currentHead = currentHead.next;
                inputHead = inputHead.next;
            } else if (currentHead.value < inputHead.value) {
                currentHead = currentHead.next;
            } else {
                newNode = new Node(inputHead.value, null);
                inputHead = inputHead.next;
            }

            if (newNode != null) { // Нашли ли мы что добавить?
                if (difference.head == null) { // Добавляли ли мы уже что-то?
                    difference.head = newNode;
                    differenceTail = newNode;
                } else {
                    differenceTail.next = newNode;
                    differenceTail = newNode;
                }
            }
        }
        return difference;
    }


    // Возвращает множество с которым есть все неповторяющиеся элементы
    // Только если множества не пересекаются

    // Тот же самый Union только без проверки на одинаковые элементы
    public LinkedSet Merge(LinkedSet input){
        // Проверить this = input -> return копию списка
        if (this == input) {
            return new LinkedSet(input);
        }
        // Если списки пустые -> возвращаем пустой список
        if (head == null && input.head == null) {
            return new LinkedSet();
        }
        // Если первый список пустой -> Возвращаем второй список
        if (head == null) {
            return new LinkedSet(input);
        }
        // Если второй список пустой -> Возвращаем первый список
        if (input.head == null) {
            return new LinkedSet(this);
        }

        Node currentHead = head;
        Node inputHead = input.head;

        //Создаем новый общий список
        LinkedSet union = new LinkedSet();
        Node unionTail = null;

        //Проходимся по обоим спискам одновременно
        while (currentHead != null && inputHead != null) {
            Node newNode = null;
            if (currentHead.value < inputHead.value) {
                newNode = new Node(currentHead.value, null);
                currentHead = currentHead.next;
            }
            else {
                newNode = new Node(inputHead.value, null);
                inputHead = inputHead.next;
            }

            if(union.head == null) {
                union.head = newNode;
                unionTail = newNode;
            } else {
                unionTail.next = newNode;
                unionTail = newNode;
            }
        }
        //Проходимся по первому списку до конца
        if(currentHead != null) {
            unionTail = appendRemaining(currentHead, unionTail);
        }

        //Проходимся по второму списку до конца
        if(inputHead != null) {
            unionTail = appendRemaining(inputHead, unionTail);
        }

        return union;
    }

    public boolean Member (int value){
        return isMember(value);
    }

    // Возвращает true - если value есть в множестве
    // false - если его нет
    // Используется в Union(), Difference(), Intersection(),
    // когда проходимся по списку и проверяем есть ли элмент в множестве
    private boolean isMember(int value){
        // Если список пустой -> false
        if (head == null) {
            return false;
        }
        // Проверить границу
        if (value < head.value) {
            return false;
        }
        // Проходимся по каждому элементу
        Node h = head;
        while (h.next != null && h.next.value != value) {
            h = h.next;
        }
        // Если элемент не найден -> false
        if (h.next == null){
            return false;
        }
        return true;
    }
    
    // Когда добавляем элементы одного списка в созданный общий список
    public void Insert(int value) {
        //Если список пустой, тогда value становится головой списка
        if (head == null) {
            head = new Node(value, null);
            return;
        }
        //Если новый элемент меньше чем голова, то он становится новой головой
        if (value < head.value) {
            head = new Node(value, head);
            return;
        }
        // Если значение равно голове, ничего не делаем
        if (value == head.value) {
            return;
        }
        //Ищем место для вставки
        Node h = head;
        while (h.next != null && h.next.value < value) {
            h = h.next;
        }

        //Проверка на наличие элемента в списке. Если он уже есть, просто выходим из метода
        if (h.next != null && h.next.value == value) {
            return;
        }

        //Вставляем
        h.next = new Node(value, h.next);
    }

    // Удаление элемента из множества
    public void Delete(int value){
        // Если список пустой - return
        if (head == null) {
            return;
        }
        // Если элемент, который нужно удалить, находится в голове списка - то удаляем голову
        if (head.value == value) {
            head = head.next;
            return;
        }
        // Проходимся по каждому элементу пока не найдем value
        Node h = head;
        while (h.next != null && h.next.value != value) {
            h = h.next;
        }
        // Если элемент не найден, то ничего не делаем
        if (h.next == null){
            return;
        }
        // Если нашли, удаляем
        h.next = h.next.next;
    }

    // Присваивание нового множества
    public void Assign(LinkedSet input){
        if (this == input) {
            return;
        }
        //копирующий метод
        copySet(input);
    }

    // Возвращает минимальное значение в множестве
    public int Min(){
        //минимальный элемент это голова
        //проверяем пустой ли список
        if (head == null) {
            throw new RuntimeException("Min(): Пустой список");
        }
        return head.value;
    }

    // Возвращает максимальное значение в множестве
    public int Max(){
        if (head == null) {
            throw new RuntimeException("Max(): Пустой список");
        }
        //Проходимся до конца списка и вовзращаем конец списка
        return tail().value;
    }

    //Возвращает конец списка
    private Node tail() {
        Node h = head;
        while (h.next != null) {
            h = h.next;
        }
        return h;
    }

    // Возвращает true - если множества одинаковые.
    public boolean Equal(LinkedSet input){
        Node currentHead = head;
        Node inputHead = input.head;

        //Проходимся по каждому списку одновременно и проверяем
        // т.к список упорядоченный, проверяем соответсвующий элмент первого со вторым
        // и если они не равны, сразу выкидываем false
        while (currentHead != null && inputHead != null) {
            if (currentHead.value != inputHead.value) {
                return false;
            }
            currentHead = currentHead.next;
            inputHead = inputHead.next;
        }

        //потом проверяем дошли ли мы до конца (head == null && input head == null)
        //если они оба равны Null и значит возвращаем true
        return currentHead == null && inputHead == null;
    }

    // Возвращает множество, где найдет X
    // A и B - непересекающиеся множества
    // Если нет такого элемента, возвращаем пустое множество.
    public LinkedSet Find(LinkedSet input, int x){
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
        head = null;
    }

    public void Print(){
        if (head == null) {
            System.out.println("Список пуст");
            return;
        }
        Node h = head;
        int i = 0;
        while (h != null) {
            System.out.print("[" + i + "] ");
            System.out.print(h.value + " " + "\n");
            i++;
            h = h.next;
        }
        System.out.println();
    }
}
