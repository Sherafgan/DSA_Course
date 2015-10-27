package Assignment3.ProblemB;

import java.io.*;

/**
 * @author Sherafgan Kandov
 *         27.10.2015
 */
public class ProblemB {
    private static final String IN_FILE_NAME = "avl.in";
    private static final String OUT_FILE_NAME = "avl.out";

    public static void main(String[] args) throws IOException {
        //read data from input file
        String[] inputDataOfThreeLines = readInData();

        String[] firstLineStrings = inputDataOfThreeLines[0].trim().split("\\s");
        String[] secondLineStrings = inputDataOfThreeLines[1].trim().split("\\s");
        String[] thirdLineStrings = inputDataOfThreeLines[2].trim().split("\\s");

        MyAVLTree myAVLTree = new MyAVLTree();

        if (firstLineStrings[0].length() != 0) {
            int[] firstLineNumbers = parseStringsIntoIntegers(firstLineStrings);

            //1st step: reading values of first line to tree one-by-one
            for (int i : firstLineNumbers) {
                myAVLTree.insert(i);
            }
        }

        if (secondLineStrings[0].length() != 0) {
            int[] secondLineNumbers = parseStringsIntoIntegers(secondLineStrings);

            //2nd step: deleting values of second from tree one-by-one
            for (int i : secondLineNumbers) {
                myAVLTree.delete(i);
            }
        }

        String lineToPrint = "";
        if (thirdLineStrings[0].length() != 0) {
            int[] thirdLineNumbers = parseStringsIntoIntegers(thirdLineStrings);

            //3rd step: find nodes with keys of third line and print its right child
            for (int i : thirdLineNumbers) {
                MyAVLTree.Node nodeToFind = myAVLTree.find(i);
                if (nodeToFind != null) {
                    if (nodeToFind.getRightChild() == null) {
                        lineToPrint = lineToPrint + " null";
                    } else {
                        lineToPrint = lineToPrint + " " + nodeToFind.getRightChild().getKey();
                    }
                } else {
                    lineToPrint = lineToPrint + " null";
                }
            }
        }

        //clean 'lineToPrint'
        lineToPrint = lineToPrint.trim();
        //write data to output file
        writeData(lineToPrint);
    }


    private static void writeData(String lineToPrint) throws IOException {
        PrintWriter outFile = new PrintWriter(new FileWriter(OUT_FILE_NAME));
        outFile.write(lineToPrint);
        outFile.close();
    }

    private static String[] readInData() throws IOException {
        String[] inputDataOfThreeLines = new String[3];

        BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_NAME));

        String tmp = inFile.readLine();
        if (tmp == null) {
            inputDataOfThreeLines[0] = "";
        } else {
            inputDataOfThreeLines[0] = tmp;
        }

        String tmp2 = inFile.readLine();
        if (tmp2 == null) {
            inputDataOfThreeLines[1] = "";
        } else {
            inputDataOfThreeLines[1] = tmp2;
        }

        String tmp3 = inFile.readLine();
        if (tmp3 == null) {
            inputDataOfThreeLines[2] = "";
        } else {
            inputDataOfThreeLines[2] = tmp3;
        }
        inFile.close();

        return inputDataOfThreeLines;
    }

    private static int[] parseStringsIntoIntegers(String[] lineStrings) {
        int[] lineNumbers = new int[lineStrings.length];

        for (int i = 0; i < lineNumbers.length; i++) {
            lineNumbers[i] = Integer.parseInt(lineStrings[i]);
        }

        return lineNumbers;
    }

    public static class MyAVLTree {
        private Node root;
        private int size;

        public MyAVLTree() {
            root = null;
            size = 0;
        }

        public void insert(int key) {
            root = insert(root, key);
            size++;
        }

        private Node insert(Node p, int key) {
            if (p == null) {
                return new Node(key);
            }
            if (key < p.getKey()) {
                p.setLeftChild(insert(p.getLeftChild(), key));
            } else {
                p.setRightChild(insert(p.getRightChild(), key));
            }
            return balance(p);
        }

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

            size--;

            //case1: node is a leaf
            if (focusNode.getLeftChild() == null && focusNode.getRightChild() == null) {
                setLink(key, parent, null);
                root = balance(root);
                return true;
            }
            //case2: node has right child only
            else if (focusNode.getRightChild() != null && focusNode.getLeftChild() == null) {
                setLink(key, parent, focusNode.getRightChild());
                root = balance(root);
                return true;
            }
            //case3: node has left child only
            else if (focusNode.getRightChild() == null && focusNode.getLeftChild() != null) {
                setLink(key, parent, focusNode.getLeftChild());
                root = balance(root);
                return true;
            }
            //case4: node has both left and right children
            else {
                int inorderPredecessorKey = getInorderPredecessorKey(root, root.getLeftChild());
                focusNode.setKey(inorderPredecessorKey);
                root = balance(root);
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

        private int getInorderPredecessorKey(Node parent, Node node) {
            if (node.getRightChild() == null) {
                int keyToReturn = node.getKey();
                if (node.getLeftChild() == null) {
                    parent.setRightChild(null);
                } else {
                    parent.setRightChild(node.getLeftChild());
                }
                return keyToReturn;
            } else {
                return getInorderPredecessorKey(node, node.getRightChild());
            }
        }

        public Node find(int key) {
            return find(root, key);
        }

        public Node find(Node node, int key) {
            if (node == null || key == node.getKey()) {
                return node;
            } else if (key < node.getKey()) {
                return find(node.getLeftChild(), key);
            } else {
                return find(node.getRightChild(), key);
            }
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
}
