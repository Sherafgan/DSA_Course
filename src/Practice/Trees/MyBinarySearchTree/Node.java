package Practice.Trees.MyBinarySearchTree;

/**
 * @author Sherafgan Kandov
 */
public class Node {
    private int key;

    private Node leftChild;
    private Node rightChild;

    public Node(int key) {
        this.key = key;
    }

    public String toString() {
        return "" + key;
    }

    public int getKey() {
        return key;
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
