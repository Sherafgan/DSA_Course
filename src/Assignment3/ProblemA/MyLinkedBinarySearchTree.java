package Assignment3.ProblemA;

/**
 * @author Sherafgan Kandov
 *         15.10.15
 */
public class MyLinkedBinarySearchTree {
    private Node root;
    private int size;

    public MyLinkedBinarySearchTree() {
        size = 0;
        root = null;
    }

    //returns data of type 'E' if exist, otherwise returns null
    public Node find(int key) {
        Node focusNode = root;

        if (root.getKey() == key) {
            return root;
        } else {
            while (focusNode.getKey() != key) {
                if (key < focusNode.getKey()) {
                    if (focusNode.getLeftChild() != null) {
                        focusNode = focusNode.getLeftChild();
                    } else {
                        return null;
                    }
                } else {
                    if (focusNode.getRightChild() != null) {
                        focusNode = focusNode.getRightChild();
                    } else {
                        return null;
                    }
                }
            }
            return focusNode;
        }
    }

    //insert data of type 'E' associated with 'key'
    //increment 'size' variable
    public void insert(int key) {
        Node newNode = new Node(key);

        if (root == null) {
            root = newNode;
            size++;
        } else {
            Node parent = root;
            Node focusNode = root;

            while (focusNode != null) {
                if (key < focusNode.getKey()) {
                    parent = focusNode;
                    focusNode = focusNode.getLeftChild();
                } else {
                    parent = focusNode;
                    focusNode = focusNode.getRightChild();
                }
            }

            if (key < parent.getKey()) {
                parent.setLeftChild(newNode);
            } else {
                parent.setRightChild(newNode);
            }

            size++;
        }
    }

    //removes the data associated with key and returns data, otherwise returns null
    public boolean delete(int key) {
        Node parent = root;
        Node focusNode = root;

        while (key != focusNode.getKey()) {
            if (key < focusNode.getKey()) {
                parent = focusNode;
                focusNode = focusNode.getLeftChild();
            } else if (key > focusNode.getKey()) {
                parent = focusNode;
                focusNode = focusNode.getRightChild();
            } else {
                return false;
            }
        }

        //case1: node is a leaf
        if (focusNode.getLeftChild() == null && focusNode.getRightChild() == null) {
            setLink(key, parent, null);
            size--;
            return true;
        }
        //case2: node has right child only
        else if (focusNode.getRightChild() != null && focusNode.getLeftChild() == null) {
            setLink(key, parent, focusNode.getRightChild());
            size--;
            return true;
        }
        //case3: node has left child only
        else if (focusNode.getRightChild() == null && focusNode.getLeftChild() != null) {
            setLink(key, parent, focusNode.getLeftChild());
            size--;
            return true;
        }
        //case4: node has both left and right children
        else {
            //finding inorder predecessor
            Node rightChildOfInorderPredecessor = focusNode.getLeftChild();
            Node inorderPredecessor = focusNode.getLeftChild();
            Node parentOfInorderPredecessor = focusNode.getLeftChild();

            while (rightChildOfInorderPredecessor != null) {
                parentOfInorderPredecessor = inorderPredecessor;
                inorderPredecessor = inorderPredecessor.getRightChild();
                rightChildOfInorderPredecessor = inorderPredecessor.getRightChild();
            }

            //getting data from inorder predecessor
            int dataToReturn2 = focusNode.getKey();

            //setting data to focusNode
            focusNode.setKey(inorderPredecessor.getKey());

            //setting the inorder predecessor to null (i.e. deleting inorder predecessorx)
            parentOfInorderPredecessor.setLeftChild(null);

            size--;
            return true;
        }
    }

    private void setLink(int key, Node parent, Node nodeOrNull) {
        if (key < parent.getKey()) {
            parent.setLeftChild(nodeOrNull);
        } else {
            parent.setRightChild(nodeOrNull);
        }
    }

    private class Node {
        private int key;

        private Node leftChild;
        private Node rightChild;

        public Node(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
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