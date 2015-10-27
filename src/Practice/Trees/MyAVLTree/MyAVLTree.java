package Practice.Trees.MyAVLTree;

/**
 * @author Sherafgan Kandov
 *         20.10.2015
 */
public class MyAVLTree<E> {
    private Node<E> root;
    private int size;

    public MyAVLTree() {
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

    //insert data of type 'E' associated with 'key'
    //increment 'size' variable
    public void insert(int key, E data) {
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
            balance(root);
        }
    }

    private void balance(Node<E> root) {
        Node<E> unbalancedNode = findUnbalancedNode(root);

        Node<E> parentZ = root;
        Node<E> leftchildY = root;
        Node<E> rightchildX = root;

        while (rightchildX != unbalancedNode) {
            if (parentZ.getLeftChild() != null) {
                parentZ = parentZ.getLeftChild();
            }
            if (parentZ.getLeftChild() != null) {
                leftchildY = parentZ.getLeftChild();
            }
            if (leftchildY.getRightChild() != null) {
                rightchildX = leftchildY.getRightChild();
            }
        }

        parentZ.setLeftChild(rightchildX.getRightChild());
        leftchildY.setRightChild(rightchildX.getLeftChild());
        rightchildX.setRightChild(parentZ);
        rightchildX.setLeftChild(leftchildY);
    }

    private Node<E> findUnbalancedNode(Node<E> node) {
        if (height(node.getLeftChild()) - height(node.getRightChild()) > 1 &&
                height(node.getLeftChild()) + height(node.getRightChild()) == 3) {
            return node;
        } else {
            if (node.getLeftChild() != null && node.getRightChild() != null) {
                Node<E> result1 = findUnbalancedNode(node.getLeftChild());
                Node<E> result2 = findUnbalancedNode(node.getRightChild());
                if (result1 != null) {
                    return result1;
                } else {
                    return result2;
                }
            } else if (node.getLeftChild() != null && node.getRightChild() == null) {
                return findUnbalancedNode(node.getLeftChild());
            } else if (node.getRightChild() != null && node.getLeftChild() == null) {
                return findUnbalancedNode(node.getRightChild());
            } else {
                return null;
            }
        }
    }

    //removes the data associated with key and returns data, otherwise returns null
    public E delete(int key) {
        //TODO
        return null;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node<E> parent) {
        if (parent == null) {
            return -1;
        } else {
            return 1 + Math.max(height(parent.getLeftChild()), height(parent.getRightChild()));
        }
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
