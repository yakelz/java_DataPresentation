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

    public LinkedSet(){
        head = null;
    }

    // Возвращает множество в котором есть все неповторяющиеся элементы
    public LinkedSet Union (LinkedSet input) {
        //проверить this = input - return this

        Node currentHead = head;
        Node inputHead = input.head;

        //Создаем новый общий список
        LinkedSet union = new LinkedSet();
        //Проходимся по обоим спискам одновременно
            // Если элемент из ПЕРВОГО списка меньше, чем элемент из ВТОРОГО списка,
                // то добавляем его в общий список и двигаемся дальше по ПЕРВОМУ списку
                    //Проверяем есть ли элемент уже в списке методом Member()
                    //и вставляем его методом add()

            // Если элемент из ВТОРОГО списка меньше, чем элемент из ПЕРВОГО списка,
                // то добавляем его в общий список и двигаемся дальше по ВТОРОМУ списку
                    //Проверяем есть ли элемент уже в списке методом Member()
                    //и вставляем его методом add()

        //Проходимся по первому списку до конца
            //Проверяем есть ли элемент уже в списке методом Member()
            //и вставляем его методом add()

        //Проходимся по второму списку до конца
            //Проверяем есть ли элемент уже в списке методом Member()
            //и вставляем его методом add()

        return union;
    }

    //Возвращает множество общих элементов
    public LinkedSet Intersection(LinkedSet input){
        //проверить this = input - return this

        //Создаем новый общий список
        LinkedSet intersect = new LinkedSet();
        Node currentHead = head;

        // Перебираем элементы первого списка и добавляем те, которые есть во втором списке, в новый общий список
        // Проходимся по первому списку до конца
            // Вызываем на input метод Member() чтобы проверить, есть ли элемент первого списка во втором
                    //Если элемент присутствует во втором списке, добавляем в новый общий список методом add()
        return intersect;
    }

    //Возвращает множество с элементами которых нет в исходном множестве
    public LinkedSet Difference (LinkedSet input) {
        //проверить this = input - return this

        //Создаем новый список
        LinkedSet difference = new LinkedSet();
        Node inputHead = input.head;
        //Проходимся по второму списку и добавляем значения, если они присутствуют в исходном списке
            //Вызываем add() для добавления
        return difference;
    }

    // Возвращает множество с которым есть все неповторяющиеся элементы
    // Только если множества не пересекаются
    public LinkedSet Merge(LinkedSet input){
        // проверить на одинаковые значения через Equal?
        // вызвать Union()?
        return null;
    }

    // Возвращает true - если value есть в множестве
    // false - если его нет

    // Используется в Union(), Difference(), Intersection(),
    // когда проходимся по списку и проверяем есть ли элмент в множестве
    public boolean Member(int value){
        // Проверить границу
            // если value меньше head.value - false

        // Проходимся по каждому элементу
            // Если нашли value - true

        return false;
    }

    // Вставка значения в множество
    public void Insert(int value){
        // проверяем Member(), есть ли значение уже в списке
        // и добавляем методом add();
    }

    // Тоже вставка значения в множестве только без внутренней проверки Member();
    // Используется в Union(), Difference(), Intersection(),
    // когда добавляем элементы одного списка в созданный общий список
    private void add(int value) {
        //Если список пустой, тогда value становится головой списка
        //Если value < head.value, то он становится новой головой

        //Ищем место для вставки
        //Проходимся пока next != null и next.value < value
        //Вставляем
    }

    // Удаление элемента из множества
    public void Delete(int value){
        // Если список пустой - return

        // Если head.value = value - то удаляем голову

        // Проходимся по каждому элементу пока не найдем value
        // Если не нашли - return
        // Если нашли - удаляем
    }

    // Присваивание нового множества
    public void Assign(LinkedSet input){
        //копирующий метод

        //Чистим исходное множество
        head = null;
        //И копируем все значения с input
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

        //потом проверяем дошли ли мы до конца (head == null && input head == null)
        //если они оба равны Null и значит возвращаем true
        return false;
    }

    // Возвращает множество, где найдет X
    // A и B - непересекающиеся множества
    // Если нет такого элемента, возвращаем пустое множество.
    public LinkedSet Find(LinkedSet input, int x){
        //Вызвать Member() на обоих списках
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
            System.out.print(h.value + " ");
            i++;
            h = h.next;
        }
        System.out.println();
    }
}
