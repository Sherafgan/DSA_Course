package Practice.Trees;

/**
 * @author Sherafgan Kandov
 *         27.10.2015
 */
public class MySimpleAVLTree {
    private Node root;
    private int size;

    public MySimpleAVLTree() {
        root = null;
        size = 0;
    }


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
            balance(root);
        }
    }

    public boolean delete(int key) {
        if (remove(root, key) == null) {
            return false;
        } else {
            size--;
            return true;
        }
    }

    private Node findMin(Node node) {
        return node.getLeftChild() != null ? findMin(node.getLeftChild()) : node;
    }

    private Node removeMin(Node node) {
        if (node.getLeftChild() == null) {
            return node.getRightChild();
        }
        node.setLeftChild(removeMin(node.getLeftChild()));
        return balance(node);
    }

    private Node remove(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.getKey()) {
            node.setLeftChild(remove(node.getLeftChild(), key));
        } else if (key > node.getKey()) {
            node.setRightChild(remove(node.getRightChild(), key));
        } else {
            Node q = node.getLeftChild();
            Node r = node.getRightChild();
            node = null;
            if (r == null) {
                return q;
            }

            Node min = findMin(r);
            min.setRightChild(removeMin(r));
            min.setLeftChild(q);
            return balance(min);
        }
        return balance(node);
    }


    public Node find(int key) {
        //in case root is the key
        if (key == root.getKey()) {
            return root;
        }

        //otherwise
        boolean end = false;
        Node focusNode = root;

        while (!end) {
            if (key < focusNode.getKey()) {
                if (focusNode.getLeftChild() != null) {
                    focusNode = focusNode.getLeftChild();
                    if (focusNode.getKey() == key) {
                        return focusNode;
                    }
                } else {
                    end = true;
                }
            } else {
                if (focusNode.getRightChild() != null) {
                    focusNode = focusNode.getRightChild();
                    if (focusNode.getKey() == key) {
                        return focusNode;
                    }
                } else {
                    end = true;
                }
            }
        }
        return null;
    }

    //balancing tree
    private Node balance(Node p) {
        fixHeight(p);
        if (balanceFactor(p) == 2) {
            if (balanceFactor(p.getRightChild()) < 0) {
                p.setRightChild(rightRotation(p.getRightChild()));
            }
            return leftRotation(p);
        }
        if (balanceFactor(p) == -2) {
            if (balanceFactor(p.getLeftChild()) > 0) {
                p.setLeftChild(leftRotation(p.getLeftChild()));
            }
            return rightRotation(p);
        }

        return p;
    }

    //small right rotation around p
    private Node rightRotation(Node p) {
        Node q = p.getLeftChild();

        p.setLeftChild(q.getRightChild());
        q.setRightChild(p);

        fixHeight(p);
        fixHeight(q);
        return q;
    }

    //small left rotation around q
    private Node leftRotation(Node q) {
        Node p = q.getRightChild();

        q.setRightChild(p.getLeftChild());
        p.setLeftChild(q);

        fixHeight(q);
        fixHeight(p);
        return p;
    }

    public int size() {
        return size;
    }

    public int height(Node node) {
        return node != null ? node.getHeight() : 0;
    }

    private int balanceFactor(Node node) {
        return height(node.getRightChild()) - height(node.getLeftChild());
    }

    public void fixHeight(Node node) {
        int leftHeight = height(node.getLeftChild());
        int rightHeight = height(node.getRightChild());
        node.setHeight((leftHeight > rightHeight ? leftHeight : rightHeight) + 1);
    }

    private class Node {
        private int key;
        private int height;

        private Node rightChild;
        private Node leftChild;

        public Node(int key) {
            this.key = key;
            height = 1;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }
    }
}
