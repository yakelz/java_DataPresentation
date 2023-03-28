package Tree;

import Tree.LeftSonsRightSiblings.Tree;

public class Main {
    public static void main(String[] args) {
        Tree.init();
        Tree t1 = new Tree();
        t1.create('a');
        t1.create('b');
        Tree t2 = new Tree();
        t2.create('d');
        t2.create('e');
        t1.create('f', t2);

        Tree.printArray();
        t1.inorder(t1.root());
        System.out.println();
        symmetricOrderPrint(t1, t1.root());

    }

    //Симетричный обход бинарного дерева
    public static void symmetricOrderBinaryPrint(Tree t, int root) {
        if (root == -1) return;
        int leftChild = t.leftMostChild(root);
        if (leftChild == -1) {
            System.out.print(t.label(root) + " ");
        }
        else {
            symmetricOrderPrint(t, leftChild);
            System.out.print(t.label(root) + " ");
            symmetricOrderPrint(t, t.rightSibling(leftChild));
        }

    }


    //Симетричный обход произвольного дерева
    private static void symmetricOrderPrint(Tree t, int root) {
        if (root == -1) return;
        int leftChild = t.leftMostChild(root);
        if (leftChild != -1) {
            int rightSibling = t.rightSibling(leftChild);
            while (rightSibling != -1) {
                symmetricOrderPrint(t, leftChild);
                leftChild = rightSibling;
                rightSibling = t.rightSibling(leftChild);
            }
        }
        System.out.print(root + " ");
        symmetricOrderPrint(t, leftChild);
    }

}
