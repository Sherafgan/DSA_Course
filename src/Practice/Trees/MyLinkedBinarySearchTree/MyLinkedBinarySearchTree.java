package Practice.Trees.MyLinkedBinarySearchTree;

import java.util.Stack;

/**
 * @author Sherafgan Kandov
 *         15.10.15
 */
public class MyLinkedBinarySearchTree<E> {
    private Node<E> root;
    private int size;

    public MyLinkedBinarySearchTree() {
        size = 0;
        root = null;
    }

    //returns data of type 'E' if exist, otherwise returns null
    public E find(int key) {
        Node<E> focusNode = root;

        if (root.getKey() == key) {
            return root.getData();
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
            return focusNode.getData();
        }
    }

    public E findRecursive(int key) {
        return findRecursive(root, key);
    }

    private E findRecursive(Node<E> parent, int key) {
        if (parent == null) {
            return null;
        } else if (key == parent.getKey()) {
            return parent.getData();
        } else if (key < parent.getKey()) {
            return findRecursive(parent.getLeftChild(), key);
        } else {
            return findRecursive(parent.getRightChild(), key);
        }
    }

    //add data of type 'E' associated with 'key'
    //increment 'size' variable
    public void add(int key, E data) {
        Node<E> newNode = new Node<>(key, data);

        if (root == null) {
            root = newNode;
            size++;
        } else {
            Node<E> parent = root;
            Node<E> focusNode = root;

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
    public E delete(int key) {
        Node<E> parent = root;
        Node<E> focusNode = root;

        while (key != focusNode.getKey()) {
            if (key < focusNode.getKey()) {
                parent = focusNode;
                focusNode = focusNode.getLeftChild();
            } else if (key > focusNode.getKey()) {
                parent = focusNode;
                focusNode = focusNode.getRightChild();
            } else {
                return null;
            }
        }

        size--;

        //case1: node is a leaf
        if (focusNode.getLeftChild() == null && focusNode.getRightChild() == null) {
            setLink(key, parent, null);
            return focusNode.getData();
        }
        //case2: node has right child only
        else if (focusNode.getRightChild() != null && focusNode.getLeftChild() == null) {
            setLink(key, parent, focusNode.getRightChild());
            return focusNode.getData();
        }
        //case3: node has left child only
        else if (focusNode.getRightChild() == null && focusNode.getLeftChild() != null) {
            setLink(key, parent, focusNode.getLeftChild());
            return focusNode.getData();
        }
        //case4: node has both left and right children
        else {
            //finding inorder successor
            Node<E> leftChildOfInorderSuccessor = focusNode.getRightChild();
            Node<E> inorderSuccessor = focusNode.getRightChild();
            Node<E> parentOfInorderSuccessor = focusNode.getRightChild();

            while (leftChildOfInorderSuccessor != null) {
                parentOfInorderSuccessor = inorderSuccessor;
                inorderSuccessor = inorderSuccessor.getLeftChild();
                leftChildOfInorderSuccessor = inorderSuccessor.getLeftChild();
            }

            E dataToReturn = focusNode.getData();

            focusNode.setData(inorderSuccessor.getData());

            parentOfInorderSuccessor.setLeftChild(null);

            return dataToReturn;
        }
    }

    private void setLink(int key, Node<E> parent, Node<E> nodeOrNull) {
        if (key < parent.getKey()) {
            parent.setLeftChild(nodeOrNull);
        } else {
            parent.setRightChild(nodeOrNull);
        }
    }

    public int size() {
        return size;
    }

    private class Node<T> {
        private int key;
        private T data;

        private Node<T> leftChild;
        private Node<T> rightChild;

        public Node(int key, T data) {
            this.key = key;
            this.data = data;
        }

        public String toString() {
            return key + " : " + data;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<T> leftChild) {
            this.leftChild = leftChild;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<T> rightChild) {
            this.rightChild = rightChild;
        }
    }
}
