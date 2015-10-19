package Practice.Trees.MyBinarySearchTree;

/**
 * @author Sherafgan Kandov
 */
public class Tree implements ITree {
    private Node root;

    private int size;

    public Tree() {
        size = 0;
    }

    @Override
    public void add(int key) {
        Node newNode = new Node(key);

        if (root == null) {
            root = newNode;
            size++;
        } else {

            Node focusNode = root;
            Node parent;

            while (true) {
                parent = focusNode;

                if (newNode.getKey() < focusNode.getKey()) {

                    focusNode = focusNode.getLeftChild();

                    if (focusNode == null) {
                        parent.setLeftChild(newNode);
                        size++;
                        return;
                    }
                } else {

                    focusNode = focusNode.getRightChild();

                    if (focusNode == null) {
                        parent.setRightChild(newNode);
                        size++;
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void delete(int key) {

        //in case we have to delete root
        if (key == root.getKey()) {
            Node subParent = root.getLeftChild();
            Node subFocusNode = subParent;

            while (subFocusNode != null) {
                subFocusNode = subParent.getRightChild();
            }

            subParent.setRightChild(null);
            root = subParent;
            size--;
        } else {
            Node parent = root;
            Node focusNode = parent;

            while (focusNode.getKey() != key) {
                if (key < focusNode.getKey()) {
                    parent = focusNode;
                    focusNode = focusNode.getLeftChild();
                } else {
                    parent = focusNode;
                    focusNode = focusNode.getRightChild();
                }
            }

            //in case we have to delete leaf
            if (focusNode.getLeftChild() == null && focusNode.getRightChild() == null) {
                if (parent.getLeftChild() == focusNode) {
                    parent.setLeftChild(null);
                    size--;
                } else {
                    parent.setRightChild(null);
                    size--;
                }
            } //in case we have to delete a node, which has one child
            else if ((focusNode.getLeftChild() != null && focusNode.getRightChild() == null) ||
                    (focusNode.getRightChild() != null && focusNode.getLeftChild() == null)) {

                if (parent.getLeftChild() == focusNode) {
                    if (focusNode.getLeftChild() != null) {
                        parent.setLeftChild(focusNode.getLeftChild());
                    } else {
                        parent.setLeftChild(focusNode.getRightChild());
                    }
                    size--;
                } else {
                    parent.setRightChild(focusNode.getLeftChild());
                    size--;
                }
            } //in case we have to delete a node, which has two children
            else if (focusNode.getLeftChild() != null && focusNode.getRightChild() != null) {
                
            }
        }
    }

    @Override
    public int find(int key) {

        //in case root is the key
        if (key == root.getKey()) {
            return root.getKey();
        }

        //otherwise
        boolean end = false;
        Node focusNode = root;

        while (!end) {
            if (key < focusNode.getKey()) {
                if (focusNode.getLeftChild() != null) {
                    focusNode = focusNode.getLeftChild();
                    if (focusNode.getKey() == key) {
                        return focusNode.getKey();
                    }
                } else {
                    end = true;
                }
            } else {
                if (focusNode.getRightChild() != null) {
                    focusNode = focusNode.getRightChild();
                    if (focusNode.getKey() == key) {
                        return focusNode.getKey();
                    }
                } else {
                    end = true;
                }
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public void displayTree(Node node) {
        Node focusNode = node;
        String space = "    ";

        for (int i = 0; i < size / 2; i++) {
            System.out.print(space);
            System.out.println(focusNode);
            if (focusNode.getLeftChild() != null) {
                System.out.print(focusNode.getLeftChild() + space);
            }
            if (focusNode.getRightChild() != null) {
                System.out.print(focusNode.getRightChild());
            } else {
                System.out.println();
            }
            System.out.println();
            System.out.println();

            if (focusNode.getLeftChild() != null) {
                displayTree(focusNode.getLeftChild());
            }
            if (focusNode.getRightChild() != null) {
                displayTree(focusNode.getRightChild());
            }
        }
    }

    public Node getRoot() {
        return root;
    }
}
