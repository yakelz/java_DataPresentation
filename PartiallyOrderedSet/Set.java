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
    public void addElement(int left, int right) {
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

    public void sort() {
        // Создаем новый список
        // В который будем выставлять элементы в правильной последовательности
        Element newList = new Element(0, null);
        Element newHead = newList;

        Element h = head;
        while (h != null) {
            if (h.count == 0) {
                newHead.id = h;
                newHead = newHead.id;
                removeElement(h);
                h = head;
            } else
                h = h.id;
        }
        if (head != null) {
            throw new RuntimeException("sort(): Не удалось найти элемент на который никто не ссылается");
        }

        newList = newList.id;
        head = newList;
    }

    private void removeElement(Element input){
        // Проходимся и убираем всем связи элемента
        Trail start = input.next;
        while (start != null) {
            start.id.count--;
            start = start.next;
        }

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
