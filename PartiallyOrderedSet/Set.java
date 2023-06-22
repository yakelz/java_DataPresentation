package PartiallyOrderedSet;

public class Set {
    private class Element extends Trail {
        // Значение элемента
        private int key;
        // Кол-во отношений элемента
        private int count;
        public Element(int key,Element next) {
            super(next, null);
            this.key = key;
            this.count = 0;
        }
    }

    private class Trail {
        // Ссылка на элемент, который имеет связь с исходным
        public Element next;
        // Ссылка на следующий такой же элемент
        public Trail trail;

        public Trail(Element next, Trail trail) {
            this.next = next;
            this.trail = trail;
        }
    }
    Element head;
    public Set(){
        head = null;
    }


    // Добавить элемент в множество
    // Сразу принимает два значения (например: a<b; left = a; right = b)
    public void addElements(int left, int right) {

        Element h = head;
        Element leftEl = null;
        Element rightEl = null;

        // Смотрим, находятся ли значения в списке уже?
        while (h != null) {
            if (h.key == left) {
                leftEl = h;
            }
            if (h.key == right) {
                rightEl = h;
            }
            h = h.next;
        }

        //Если значение пустое (значит его нет в списке)
        // => добавляем новый элемент
        if (leftEl == null) {
            head = new Element(left, head);
            leftEl = head;
        }

        if (rightEl == null) {
            head = new Element(right, head);
            rightEl = head;
        }

        // Сразу в левый элемент вписываем связь с правым
        leftEl.trail = new Trail(rightEl, leftEl.trail);
        // И у правого элемента увеличиваем кол-во ссылающихся на него элементов
        rightEl.count++;
    }

    public void sort() {
        // Создаем новый список
        // В который будем выставлять элементы в правильной последовательности
        Element newList = new Element(0, null);
        Element newHead = newList;

        while (head != null) {
            Element deleteNode;
            if (head.count == 0) {
                deleteNode = head;
                head = head.next;
                removeSuccessors(deleteNode);
            } else {
                Element prevFromZero = findZeroPrev();
                if (prevFromZero == null) {
                    throw new RuntimeException("sort(): Не удалось отсортировать");
                }
                deleteNode = prevFromZero.next;
                prevFromZero.next = deleteNode.next;
                removeSuccessors(deleteNode);
            }
            // добавляем обработанный элемент в конец нового списка
            newHead.next = deleteNode;
            newHead = newHead.next;
        }

        newList = newList.next;
        head = newList;
    }

    private Element findZeroPrev(){
        Element prev = head;
        Element h = head;
        while (h != null) {
            if (h.count == 0) {
                return prev;
            }
            prev = h;
            h = h.next;
        }
        return null;
    }


    // Удаление списка последователей для заданного элемента
    private void removeSuccessors(Element input){
        Trail start = input.trail;
        while (start != null) {
            start.next.count--;
            start = start.trail;
        }
        input.next = null;
        input.trail = null;
    }

    public void print() {
        Element h = head;
        Trail trail;
        while (h != null) {
            System.out.print(h.key + "[" + h.count + "] - ");
            trail = h.trail;
            while (trail != null) {
                System.out.print(trail.next.key + " ");
                trail = trail.trail;
            }
            h = h.next;
            System.out.println();
        }

    }
}
