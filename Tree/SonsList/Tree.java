package Tree.SonsList;

import java.util.NoSuchElementException;

public class Tree {
    private static class Node {
        public int number; //для space и вывода
        public char label;
        public Son next; //ссылка на сына

        Node(int number) {
            this.number = number;
        }
    }

    private static class Son {
        public int index;
        public Son sibling;

        Son(int index) {
            this.index = index;
        }

        Son(int index, Son sibling) {
            this.index = index;
            this.sibling = sibling;
        }

    }

    private int root;
    private final static int SIZE = 10;
    private static Node[] array;
    private static int SPACE;
    private final static int EMPTY_TREE = -1;

    public static void init() {
        array = new Node[SIZE];
        for (int i = 0; i < SIZE - 1; i++) {
            array[i] = new Node(i + 1);
        }
        array[SIZE - 1] = new Node(-1);
        SPACE = 0;
    }

    public Tree() {
        root = EMPTY_TREE;
    }

    //возвращает родителя узла n в дереве t
    //eсли n - корень, возвращаем нулевой узел.
    public int parent(int n) {
        if (n == root) {
            return EMPTY_TREE;
        }
        return findParent(n, root);
    }

    //прямой обход
    private int findParent(int n, int root) {
        Node current = array[root];

        if (current.next == null) {
            return EMPTY_TREE;
        }

        if (current.next.index == n) {
            return root;
        }

        int p = findParent(n, current.next.index);
        if (p != EMPTY_TREE) {
            return p;
        }

        Son sibling = current.next.sibling;
        while (sibling != null) {
            if (sibling.index == n) {
                return root;
            }
            p = findParent(n, sibling.index);

            if (p != EMPTY_TREE) {
                return p;
            }
            sibling = sibling.sibling;
        }

        return EMPTY_TREE;
    }

    //возвращает самого левого ребенка сына узла n в дереве t
    //если n - лист, возвращается нулевой узел.
    public int leftMostChild(int n) {
        if (array[n].next == null || findParent(n, root) == EMPTY_TREE) {
            return EMPTY_TREE;
        }

        return array[n].next.index;
    }

    //возвращает правого брата узла n в дереве t.
    //eсли его нет, возвращается нулевой узел.
    public int rightSibling(int n) {
        if (n == root) {
            return EMPTY_TREE;
        }

        int parent = findParent(n, root);
        if (parent == EMPTY_TREE) {
            return EMPTY_TREE;
        }

        Son sibling = array[parent].next;
        while (sibling.index != n) {
            sibling = sibling.sibling;
        }
        if (sibling.sibling == null) {
            return EMPTY_TREE;
        }

        return sibling.sibling.index;
    }

    //возвращает метку узла n в дереве T.
    public char label(int n) {
        if (n == root) {
            return array[root].label;
        }
        if (findParent(n, root()) == EMPTY_TREE) {
            throw new NoSuchElementException();
        }
        return array[n].label;
    }

    //создание, когда дерева нет, и когда узел и одно дерево
    public Tree create(char v) {
        //если нет места
        if (SPACE == -1) {
            return this;
        }
        //если дерево не пустое,
        if (root != EMPTY_TREE) {
            array[SPACE].next = new Son(root);
        }
        array[SPACE].label = v;
        root = SPACE;
        SPACE = array[SPACE].number;
        return this;
    }

    //создание для узла и двух деревьев
    public Tree create(char v, Tree t1) {
        //если нет места
        if (SPACE == -1) {
            return this;
        }
        //если вызвали на том же самом дереве
        if (this == t1){
            return create(v);
        }
        //если вызываемое дерево пустое - создаем узел
        if (root == EMPTY_TREE) {
            return t1.create(v);
        }
        //если дерево пустое, тогда создаем узел в исходном
        if (t1.root() == EMPTY_TREE) {
            return create(v);
        }
        array[SPACE].label = v;
        array[SPACE].next = new Son(root, new Son(t1.root));
        root = SPACE;
        SPACE = array[SPACE].number;

        return this;
    }


    public int root() {
        return root;
    }

    public void makenull() {
        if (root == EMPTY_TREE) {
            return;
        }
        makenull(root);
    }

    //обратный проход
    private void makenull(int root) {
        Node current = array[root];

        //дойти до листа
        if (current.next != null) {
            makenull(current.next.index);
        } else {
            array[root].next = null;
            array[root].number = SPACE;
            SPACE = root;
            return;
        }

        Son sibling = current.next.sibling;
        while (sibling != null) {
            makenull(sibling.index);
            sibling = sibling.sibling;
        }
        array[root].next = null;
        array[root].number = SPACE;
        SPACE = root;
    }

    public static void printArray() {
        int a, b;
        for (int i = 0; i < SIZE; i++) {
            if ((int) array[i].label == 0) {
                System.out.print("[" + array[i].number + "] *");
            } else {
                System.out.print("[" + array[i].number + "] " + array[i].label);
            }
            if (array[i].next != null) {
                a = array[i].next.index + 1;
                System.out.print(" -> [" + a + "]");
                if (array[i].next.sibling != null) {
                    b = array[i].next.sibling.index + 1;
                    System.out.print(" -> [" + b + "]");
                }
            }
            System.out.println();
        }
    }

    public void inorder(int index) {
        if(array[index].next == null) {
            System.out.print(array[index].label + " ");
        }
        else {
            inorder(array[index].next.index);
            System.out.print(array[index].label + " ");
            Son child = array[index].next.sibling;
            while(child != null) {
                inorder(child.index);
                child = child.sibling;
            }
        }

    }

}
