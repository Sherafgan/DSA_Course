package Practice.Trees.MyRBTree;

/**
 * @author Sherafgan Kandov
 *         03.11.2015
 */
public class MyRBTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public MyRBTree() {
        root = null;
    }

    public Node find(int key) {
        return find(root, key);
    }

    private Node find(Node node, int key) {
        if (key == node.getKey()) {
            return node;
        } else if (key < node.getKey()) {
            return find(node.getLeftChild(), key);
        } else {
            return find(node.getRightChild(), key);
        }
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(key, BLACK);
        } else {
            Node focusNode = root;
            Node child = root;

            Node newNode = new Node(key, RED);

            while (child != null) {
                if (key < focusNode.getKey()) {
                    focusNode = child;
                    child = child.getLeftChild();
                } else if (key > focusNode.getKey()) {
                    focusNode = child;
                    child = child.getRightChild();
                }
            }

            if (key < focusNode.getKey()) {
                focusNode.setLeftChild(newNode);
            } else {
                focusNode.setRightChild(newNode);
            }

            if (!isBalanced(focusNode)) {
                balance(newNode);
            }
        }
    }

    private void balance(Node child) {
        Node[] parentUncleAndGrandF = getParentUncleAndGrandF(child); //returns array{parent, uncle, grandF}
        Node parent = parentUncleAndGrandF[0];
        Node uncle = parentUncleAndGrandF[1];
        Node grandF = parentUncleAndGrandF[2];

        if (isRed(uncle)) {
            //recoloring
            recolor(parent, uncle, grandF);
        } else {
            //restructuring
            restructure(child, parent, grandF);
        }
    }

    private boolean isBalanced(Node parent) {
        if (isRed(parent)) {
            return false;
        } else {
            return true;
        }
    }

    private void restructure(Node x, Node y, Node z) {
        //case 1:
        if (z.getLeftChild() == y && y.getLeftChild() == x) {
            z.setLeftChild(y.getRightChild());
            y.setRightChild(z);
            flipColors(y, z);
        }
        //case 2:
        else if (z.getLeftChild() == y && y.getRightChild() == x) {
            z.setLeftChild(x.getRightChild());
            y.setRightChild(x.getLeftChild());
            x.setRightChild(z);
            x.setLeftChild(y);
            flipColors(x, z);
        }
        //case 3:
        else if (z.getRightChild() == y && y.getRightChild() == x) {
            z.setRightChild(y.getLeftChild());
            y.setLeftChild(z);
            flipColors(y, z);
        }
        //case 4:
        else if (z.getRightChild() == y && y.getLeftChild() == x) {
            y.setLeftChild(x.getRightChild());
            z.setRightChild(x.getLeftChild());
            x.setLeftChild(z);
            x.setRightChild(y);
            flipColors(x, z);
        }
    }

    private void recolor(Node y, Node s, Node z) {
        if (z != root) {
            y.setColor(BLACK);
            s.setColor(BLACK);
            z.setColor(RED);

            if (!isBalanced(z)) {
                balance(z);
            }
        } else {
            y.setColor(BLACK);
            s.setColor(BLACK);
        }
    }

    private Node[] getParentUncleAndGrandF(Node child) {
        Node[] parentUncleAndGrandF = new Node[3];

        Node grandF = root;
        Node focusNode = root;
        while (child.getKey() != focusNode.getKey()) {
            if (child.getKey() < focusNode.getKey()) {
                grandF = focusNode;
                focusNode = focusNode.getLeftChild();
            } else {
                grandF = focusNode;
                focusNode = focusNode.getRightChild();
            }
        }
        if (grandF.getRightChild() == focusNode) {
            parentUncleAndGrandF[0] = grandF.getRightChild();
            parentUncleAndGrandF[1] = grandF.getLeftChild();
            parentUncleAndGrandF[2] = grandF;
            return parentUncleAndGrandF;
        } else {
            parentUncleAndGrandF[0] = grandF.getLeftChild();
            parentUncleAndGrandF[1] = grandF.getRightChild();
            parentUncleAndGrandF[2] = grandF;
            return parentUncleAndGrandF;
        }
    }

    private void flipColors(Node redNode, Node blackNode) {
        boolean tmp = redNode.getColor();
        redNode.setColor(blackNode.getColor());
        blackNode.setColor(tmp);
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        } else {
            return node.getColor();
        }
    }

    private class Node {
        private int key;
        private boolean color;

        private Node leftChild;
        private Node rightChild;

        public Node(int key, boolean color) {
            this.key = key;
            this.color = color;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public boolean getColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }
}
