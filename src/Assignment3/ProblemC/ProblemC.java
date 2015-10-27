package Assignment3.ProblemC;

import java.io.*;

/**
 * @author Sherafgan Kandov
 *         27.10.2015
 */
public class ProblemC {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static final String IN_FILE_NAME = "rbt_in_1.txt";
    private static final String OUT_FILE_NAME = "rbt_out_1.txt";

    public static void main(String[] args) throws IOException {
        //read data from input file
        String[] inputDataOfThreeLines = readInData();

        String[] firstLineStrings = inputDataOfThreeLines[0].trim().split("\\s");
        String[] secondLineStrings = inputDataOfThreeLines[1].trim().split("\\s");

        MyRBTree myRBTree = new MyRBTree();

        if (firstLineStrings[0].length() != 0) {
            int[] firstLineNumbers = parseStringsIntoIntegers(firstLineStrings);

            //1st step: reading values of first line to tree one-by-one
            for (int i : firstLineNumbers) {
                myRBTree.insert(i);
            }
        }

        String lineToPrint = "";
        if (secondLineStrings[0].length() != 0) {
            int[] thirdLineNumbers = parseStringsIntoIntegers(secondLineStrings);

            //2nd step: find nodes with keys of third line and print its right child
            for (int i : thirdLineNumbers) {
                MyRBTree.Node nodeToFind = myRBTree.find(i);
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
        String[] inputDataOfThreeLines = new String[2];

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

    public static class MyRBTree {
        private Node root;
        private int size;

        public MyRBTree() {
            root = null;
            size = 0;
        }

        public void insert(int key) {
            root = insert(root, key);
            root.color = BLACK;
            // assert check();
        }

        // insert the key-value pair in the subtree rooted at h
        private Node insert(Node node, int key) {
            if (node == null) return new Node(key, RED, 1);

            if (key < node.getKey()) {
                node.setLeftChild(insert(node.getLeftChild(), key));
            } else if (key > node.getKey()) {
                node.setRightChild(insert(node.getRightChild(), key));
            } else {
                node.key = key;
            }

            // fix-up any right-leaning links
            if (isRed(node.getRightChild()) && !isRed(node.getLeftChild())) node = rotateLeft(node);
            if (isRed(node.getLeftChild()) && isRed(node.getLeftChild().getLeftChild())) node = rotateRight(node);
            if (isRed(node.getLeftChild()) && isRed(node.getRightChild())) flipColors(node);
            node.setN(size(node.getLeftChild()) + size(node.getRightChild()) + 1);
            return node;
        }

        private int size(Node x) {
            if (x == null) return 0;
            return x.getN();
        }

        private void flipColors(Node node) {
            // node must have opposite color of its two children
            // assert (node != null) && (node.left != null) && (node.right != null);
            // assert (!isRed(node) &&  isRed(node.left) &&  isRed(node.right))
            //    || (isRed(node)  && !isRed(node.left) && !isRed(node.right));
            node.color = !node.color;
            node.getLeftChild().setColor(!node.getLeftChild().getColor());
            node.getRightChild().setColor(!node.getRightChild().getColor());
        }

        // make a left-leaning link lean to the right
        private Node rotateRight(Node h) {
            // assert (h != null) && isRed(h.left);
            Node x = h.getLeftChild();
            h.setLeftChild(x.getRightChild());
            x.setRightChild(h);
            x.setColor(x.getRightChild().getColor());
            x.getRightChild().setColor(RED);
            x.setN(h.getN());
            h.setN(size(h.getLeftChild()) + size(h.getRightChild()) + 1);
            return x;
        }

        // make a right-leaning link lean to the left
        private Node rotateLeft(Node h) {
            // assert (h != null) && isRed(h.right);
            Node x = h.getRightChild();
            h.setRightChild(x.getLeftChild());
            x.setLeftChild(h);
            x.setColor(x.getLeftChild().getColor());
            x.getLeftChild().setColor(RED);
            x.setN(h.getN());
            h.setN(size(h.getLeftChild()) + size(h.getRightChild()) + 1);
            return x;
        }

        public boolean delete(int key) {
            //TODO implement: low priority
            return false;
        }

        public Node find(int key) {
            return find(root, key);
        }

        //returns node founded with the same key, null otherwise
        private Node find(Node node, int key) {
            while (node != null) {
                if (key < node.getKey()) {
                    node = node.getLeftChild();
                } else if (key > node.getKey()) {
                    node = node.getRightChild();
                } else {
                    return node;
                }
            }
            return null;
        }

        //checks whether the node is red or not
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
            private int n;

            private Node rightChild;
            private Node leftChild;

            public Node(int key, boolean color, int n) {
                this.key = key;
                this.color = color;
                this.n = n;
            }

            public int getKey() {
                return key;
            }

            public void setKey(int key) {
                this.key = key;
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

            public void setColor(boolean color) {
                this.color = color;
            }

            public boolean getColor() {
                return color;
            }

            public int getN() {
                return n;
            }

            public void setN(int n) {
                this.n = n;
            }
        }
    }
}
