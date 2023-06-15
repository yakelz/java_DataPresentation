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
        public Element id;
        // Ссылка на следующий такой же элемент
        public Trail next;

        public Trail(Element next, Trail trail) {
            this.id = next;
            this.next = trail;
        }
    }
    Element head;
    public Set(){
        head = null;
    }


    // Добавить элемент в множество
    // Сразу принимает два значения (например: a<b; left = a; right = b)
    public void addElements(int left, int right) {

        // Если пришло два одинаковых значения, то добавляем только один из них
        if (left == right) {
            addElement(left);
            return;
        }

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
            h = h.id;
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
        leftEl.next = new Trail(rightEl, leftEl.next);
        // И у правого элемента увеличиваем кол-во ссылающихся на него элементов
        rightEl.count++;
    }

    //Если left = right -> то вызывается этот метод, который добавляет только один элемент
    private void addElement(int input){
        Element h = head;
        Element inputEl = null;

        // Смотрим, находятся ли значения в списке уже?
        while (h != null) {
            if (h.key == input) {
                inputEl = h;
            }
            h = h.id;
        }

        //Если значение пустое (значит его нет в списке)
        // => добавляем новый элемент
        if (inputEl == null) {
            head = new Element(input, head);
            inputEl = head;
        }
    }

    public void sort() {
        // Создаем новый список
        // В который будем выставлять элементы в правильной последовательности
        Element newList = new Element(0, null);
        Element newHead = newList;

        findAndRemoveZeros(newHead);
        if (head != null) {
            throw new RuntimeException("sort(): Не удалось отсортировать");
        }

        newList = newList.id;
        head = newList;
    }

    private void findAndRemoveZeros(Element newHead) {
        Element h = head;
        while (h != null) {
            if (h.count == 0) {
                //убрать хвост
                newHead.id = h;
                newHead = newHead.id;
                //удалить элемент
                removeElement(h);
                h = head;
            } else {
                h = h.id;
            }
        }
    }

    // Удаление списка последователей для заданного элемента
    private void removeSuccessors(Element element) {
        Trail start = element.next;
        while (start != null) {
            start.id.count--;
            start = start.next;
        }
    }

    private void removeElement(Element input){
        removeSuccessors(input);

        // Удаляем элемент из списка
        if (input == head) {
            head = head.id;
        } else {
            // get prev
            Element h = head;
            while (h.id != input) {
                h = h.id;
            }
            h.id = input.id;
        }
    }

    public void print() {
        Element h = head;
        Trail trail;
        while (h != null) {
            System.out.print(h.key + "[" + h.count + "] - ");
            trail = h.next;
            while (trail != null) {
                System.out.print(trail.id.key + " ");
                trail = trail.next;
            }
            h = h.id;
            System.out.println();
        }

    }
}
