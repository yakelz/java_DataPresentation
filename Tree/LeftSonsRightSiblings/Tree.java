package Tree.LeftSonsRightSiblings;

public class Tree {
    public static class Node {
        private int number;
        private int leftSon;
        private char label;
        private int rightSibling;

        Node(int number, int leftSon, char label, int rightSibling) {
            this.number = number;
            this.leftSon = leftSon;
            this.label = label;
            this.rightSibling = rightSibling;
        }
    }

    private int root;
    private static final int SIZE = 10;
    private static Node[] array;
    private static int SPACE;
    private final static int EMPTY_TREE = -1;

    public static void init() {
        array = new Node[SIZE];
        for (int i = 0; i < SIZE - 1; i++) {
            array[i] = new Node(i + 1, -1, ' ', -1);
        }
        array[SIZE - 1] = new Node(-1, -1, ' ', -1);
        SPACE = 0;
    }

    public Tree() {
        root = EMPTY_TREE;
    }

    public int parent(int n){
        if (n == root){
            return EMPTY_TREE;
        }
        return findParent(n,root);
    }
    public int leftMostChild(int n){
        if (n == root) {
            return array[root].leftSon;
        }
        if (findParent(n, root) == EMPTY_TREE || array[n].leftSon == EMPTY_TREE) {
            return EMPTY_TREE;
        }

        return array[n].leftSon;
    }
    public int rightSibling(int n){
        int parent = findParent(n, root);
        if (parent == EMPTY_TREE || array[n].rightSibling == -1){
            return EMPTY_TREE;
        }

        int sibling = array[n].rightSibling;
        while (sibling != n && sibling != -1 ) {
            if (parent(sibling) == parent) {
                return sibling;
            }
            sibling = array[sibling].rightSibling;
        }

        return array[sibling].rightSibling;
    }


    public char label(int n) {
        return array[n].label;
    }

    //прямой проход
    private int findParent(int n, int root) {
        Node current = array[root];

        if (current.leftSon == -1) {
            return EMPTY_TREE;
        }

        if (current.leftSon == n){
            return root;
        }

        int p = findParent(n, current.leftSon);
        if (p != EMPTY_TREE) {
            return p;
        }


        int sibling = array[current.leftSon].rightSibling;
        while (sibling != EMPTY_TREE) {
            if (sibling == n) {
                return root;
            }
            p = findParent(n, sibling);
            if (p != EMPTY_TREE) {
                return p;
            }
            sibling = array[sibling].rightSibling;
        }
        return EMPTY_TREE;
    }

    public Tree create(char v) {
        //если нет места
        if (SPACE == -1) {
            return this;
        }
        //если дерево не пустое,
        if (root != EMPTY_TREE) {
            array[SPACE].leftSon = root;
        }
        array[SPACE].label = v;
        root = SPACE;
        SPACE = array[SPACE].number;
        return this;
    }

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

        array[SPACE].leftSon = root;
        array[SPACE].label = v;
        array[root].rightSibling = t1.root;
        root = SPACE;
        SPACE = array[SPACE].number;
        return this;
    }

    public int root() {
        return root;
    }

    public void makenull() {
        //если пустое дерево
        if (root == EMPTY_TREE) {
            return;
        }
        makenull(root);
    }

    //обратный проход
    private void makenull(int root) {
        Node cur = array[root];
        //дойти до листа
        if (cur.leftSon != -1)
            makenull(cur.leftSon);
        else {
            array[root].leftSon = -1;
            array[root].rightSibling = SPACE;
            SPACE = root;
            return;
        }

        int sibling = cur.rightSibling;
        while (sibling != -1) {
            makenull(sibling);
            sibling = array[sibling].rightSibling;
        }

        array[root].label = ' ';
        array[root].leftSon = -1;
        array[root].rightSibling = SPACE;
        SPACE = root;
    }

//    public static void printArray() {
//        System.out.printf("%2s  %5s  %s  %n","LS", "Label", "RS");
//        for (int i = 0; i < SIZE - 1; i++) {
//            System.out.printf("%2d |%3c  | %2d %n",
//                    array[i].leftSon,
//                    array[i].label,
//                    array[i].rightSibling
//            );
//        }
//        System.out.printf("%2d |%3s  | %2d %n",
//                array[SIZE - 1].leftSon,
//                array[SIZE - 1].label,
//                array[SIZE - 1].rightSibling
//        );
//        System.out.println();
//    }

    public static void printArray() {
        System.out.printf("%8s   %2s  %5s  %s  %n", "N", "LS", "Label", "RS");
        for (int i = 0; i < SIZE - 1; i++) {
            System.out.printf("%8d | %2d |%3c  | %2d %n",
                    i,
                    array[i].leftSon,
                    array[i].label,
                    array[i].rightSibling
            );
        }
        System.out.printf("%8d | %2d |%3s  | %2d %n",
                array[SIZE - 1].number,
                array[SIZE - 1].leftSon,
                array[SIZE - 1].label,
                array[SIZE - 1].rightSibling
        );
        System.out.println();
    }


    public void inorder(int index) {
        if (index == EMPTY_TREE) {
            return;
        }
        if(array[index].leftSon == -1) {
            System.out.print(array[index].label + " ");
        }
        else {
            inorder(array[index].leftSon);
            System.out.print(array[index].label + " ");
            inorder(array[array[index].leftSon].rightSibling);

        }

    }

}
