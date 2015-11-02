package Assignment3.PartA.ProblemA;

import java.io.*;

/**
 * @author Sherafgan Kandov
 *         27.10.2015
 */
public class ProblemA {
    private static final String IN_FILE_NAME = "bst.in";
    private static final String OUT_FILE_NAME = "bst.out";

    public static void main(String[] args) throws IOException {
        //read data from input file
        String[] inputDataOfThreeLines = readInData();

        String[] firstLineStrings = inputDataOfThreeLines[0].trim().split("\\s");
        String[] secondLineStrings = inputDataOfThreeLines[1].trim().split("\\s");
        String[] thirdLineStrings = inputDataOfThreeLines[2].trim().split("\\s");

        MyLinkedBinarySearchTree myLinkedBinarySearchTree = new MyLinkedBinarySearchTree();

        if (firstLineStrings.length > 1) {
            int[] firstLineNumbers = parseStringsIntoIntegers(firstLineStrings);

            //1st step: reading values of first line to tree one-by-one
            for (int i : firstLineNumbers) {
                myLinkedBinarySearchTree.insert(i);
            }
        }

        if (secondLineStrings.length > 1) {
            int[] secondLineNumbers = parseStringsIntoIntegers(secondLineStrings);

            //2nd step: deleting values of second from tree one-by-one
            for (int i : secondLineNumbers) {
                myLinkedBinarySearchTree.delete(i);
            }
        }

        String lineToPrint = "";
        if (thirdLineStrings.length > 1) {
            int[] thirdLineNumbers = parseStringsIntoIntegers(thirdLineStrings);

            //3rd step: find nodes with keys of third line and print its right child
            for (int i : thirdLineNumbers) {
                MyLinkedBinarySearchTree.Node nodeToFind = myLinkedBinarySearchTree.find(i);
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

    static class MyLinkedBinarySearchTree {
        private Node root;

        public MyLinkedBinarySearchTree() {
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
            }
        }

        //removes the data associated with key and returns data, otherwise returns null
        public boolean delete(int key) {
            Node parent = root;
            Node focusNode = root;

            while (key != focusNode.getKey()) {
                if (key < focusNode.getKey() && focusNode.getLeftChild() != null) {
                    parent = focusNode;
                    focusNode = focusNode.getLeftChild();
                } else if (key > focusNode.getKey() && focusNode.getRightChild() != null) {
                    parent = focusNode;
                    focusNode = focusNode.getRightChild();
                } else {
                    return false;
                }
            }

            //case1: node is a leaf
            if (focusNode.getLeftChild() == null && focusNode.getRightChild() == null) {
                setLink(key, parent, null);
                return true;
            }
            //case2: node has right child only
            else if (focusNode.getRightChild() != null && focusNode.getLeftChild() == null) {
                setLink(key, parent, focusNode.getRightChild());
                return true;
            }
            //case3: node has left child only
            else if (focusNode.getRightChild() == null && focusNode.getLeftChild() != null) {
                setLink(key, parent, focusNode.getLeftChild());
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
                int dataToSet = inorderPredecessor.getKey();

                //setting data to focusNode
                focusNode.setKey(inorderPredecessor.getKey());

                //setting the inorder predecessor to null (i.e. deleting inorder predecessorx)
                parentOfInorderPredecessor.setRightChild(null);

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

        public class Node {
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
}
