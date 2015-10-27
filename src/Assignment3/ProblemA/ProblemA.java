package Assignment3.ProblemA;

import java.util.Scanner;

/**
 * @author Sherafgan Kandov
 *         27.10.2015
 */
public class ProblemA {
    private static final String IN_FILE_NAME = "in1.txt"; //TODO change name to bst.in
    private static final String OUT_FILE_NAME = "out1.txt"; //TODO change name to bst.out

    public static void main(String[] args) {
        //read data from input file
        String[] inputDataOfThreeLines = readInData();

        String[] firstLineStrings = inputDataOfThreeLines[0].trim().split("\\s");
        String[] secondLineStrings = inputDataOfThreeLines[1].trim().split("\\s");
        String[] thirdLineStrings = inputDataOfThreeLines[2].trim().split("\\s");

        int[] firstLineNumbers = parseStringsIntoIntegers(firstLineStrings);
        int[] secondLineNumbers = parseStringsIntoIntegers(secondLineStrings);
        int[] thirdLineNumbers = parseStringsIntoIntegers(thirdLineStrings);

        //1st step: reading values of first line to tree one-by-one

        //2nd step: deleting values of second from tree one-by-one

        //3rd step: find nodes with keys of third line and print its right child

        //write data to output file
    }

    private static String[] readInData() {
        String[] inputDataOfThreeLines = new String[3];
        Scanner scanner = new Scanner(IN_FILE_NAME);
        for (int i = 0; i < inputDataOfThreeLines.length; i++) {
            inputDataOfThreeLines[i] = scanner.nextLine().trim();
        }

        return inputDataOfThreeLines;
    }

    private static int[] parseStringsIntoIntegers(String[] lineStrings) {
        int[] lineNumbers = new int[lineStrings.length];

        for (int i = 0; i < lineNumbers.length; i++) {
            lineNumbers[i] = Integer.parseInt(lineStrings[i]);
        }

        return lineNumbers;
    }

    class MyLinkedBinarySearchTree {
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
}
