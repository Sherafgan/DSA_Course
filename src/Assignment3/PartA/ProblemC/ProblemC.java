package Assignment3.PartA.ProblemC;

import java.io.*;

/**
 * @author Sherafgan Kandov
 *         27.10.2015
 */
public class ProblemC {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static final String IN_FILE_NAME = "rbt.in";
    private static final String OUT_FILE_NAME = "rbt.out";

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
                root = new Node(key, BLACK); //root is black
            } else {
                Node parent = root;
                Node focusNode = root;

                Node newNode = new Node(key, RED); //new node is always red

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

                if (!isBalancedAfterInsertion(parent)) {
                    Node[] uncleAndGrandF = getUncle(parent);
                    balanceAfterInsertion(newNode, parent, uncleAndGrandF);
                }
            }
        }

        private void balanceAfterInsertion(Node newNode, Node parent, Node[] uncleAndGrandF) {
            Node uncle = uncleAndGrandF[0];
            Node grandF = uncleAndGrandF[1];

            if (isRed(uncle)) {
                //recoloring
                parent.setColor(BLACK);
                uncle.setColor(BLACK); //setting uncle's color black
                grandF.setColor(RED); // setting grandF's color red

                if (isRed(root)) {
                    root.setColor(BLACK);
                }
            } else {
                //rotations
                if (grandF.getLeftChild() == parent) {
                    if (newNode == parent.getLeftChild()) {
                        rightRotation(grandF);
                    } else if (newNode == parent.getRightChild()) {
                        leftRotation(parent);
                        rightRotation(grandF);
                    } else {
                        System.out.println("SOMETHING UNEXPECTED HAPPENED!");
                    }
                } else {
                    if (parent.getRightChild() == newNode) {
                        leftRotation(grandF);
                    } else {
                        rightRotation(parent);
                        leftRotation(grandF);
                    }
                }
            }
        }

        private void leftRotation(Node parent) {
            Node a = parent;
            Node x = parent.getRightChild();

            Node newANode = new Node(a.getKey(), a.getColor());
            newANode.setLeftChild(a.getLeftChild());
            newANode.setRightChild(x.getLeftChild());

            a.setKey(x.getKey());
            a.setRightChild(x.getRightChild());
            a.setLeftChild(newANode);
        }

        private void rightRotation(Node grandF) {
            Node b = grandF;
            Node a = grandF.getRightChild();

            b.setColor(a.getColor());
            a.setColor(!b.getColor());

            b.setLeftChild(a.getRightChild());
            a.setRightChild(b);
        }

        private Node[] getUncle(Node parent) {
            Node[] uncleAndGranF = new Node[2];

            Node grandF = root;
            Node focusNode = root;
            while (parent.getKey() != focusNode.getKey()) {
                if (parent.getKey() < focusNode.getKey()) {
                    grandF = focusNode;
                    focusNode = focusNode.getLeftChild();
                } else {
                    grandF = focusNode;
                    focusNode = focusNode.getRightChild();
                }
            }
            if (grandF.getRightChild() == focusNode) {
                uncleAndGranF[0] = grandF.getLeftChild();
                uncleAndGranF[1] = grandF;
                return uncleAndGranF;
            } else {
                uncleAndGranF[0] = grandF.getRightChild();
                uncleAndGranF[1] = grandF;
                return uncleAndGranF;
            }
        }

        private boolean isBalancedAfterInsertion(Node node) {
            if (!isRed(node)) {
                return true;
            } else {
                return false;
            }
        }

        private boolean isRed(Node node) {
            if (node == null) {
                return BLACK;
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
}
