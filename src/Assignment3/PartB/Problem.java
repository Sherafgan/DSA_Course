package Assignment3.PartB;

import java.io.*;

/**
 * @author Sherafgan Kandov
 *         03.11.2015
 */
public class Problem {
    private static final String IN_FILE_NAME = "data.in";
    private static final String OUT_FILE_NAME = "data.out";


    public static void main(String[] args) throws IOException {
        long timeTaken = System.currentTimeMillis();
        //reading data to one string line
        String inputDataLine = readInData();

        if (inputDataLine.length() != 0) {

            //splitting it to array
            String[] inputDataArrayOfStrings = inputDataLine.trim().split("\\s");

            //parsing to integer
            int[] inputDataArray = stringToInteger(inputDataArrayOfStrings);

            MyRBTree myRBTree = new MyRBTree();

            int distance = 0; //instantiated here because we will need last distance

            String lineToPrint = "";


            for (int i = 0; i < inputDataArray.length; i += 2) {
                distance = getDistanceBetween(0, 0, inputDataArray[i], inputDataArray[i + 1]);
                myRBTree.insert(distance);
                if (i > 1 && (i % 60000 == 0) || (i > 1 && i % 60000 == 999)) { // one minute passed
                    lineToPrint = lineToPrint + " " + myRBTree.minValue() + " " + myRBTree.maxValue();
                    lineToPrint = lineToPrint.trim();
                    System.out.println(i + " : " + lineToPrint);
                }
            }

            if (inputDataArray.length % 60000 != 0) {
                lineToPrint = lineToPrint + " " + myRBTree.minValue() + " " + myRBTree.maxValue();
                lineToPrint = lineToPrint.trim();
            }

            //now 'distance' is the distance to the last point added
            int nearestDistance = getNearestDistanceTo(myRBTree, distance);

            lineToPrint = lineToPrint + " " + nearestDistance;
            lineToPrint = lineToPrint.trim();

            //writing data to file
            writeOutData(lineToPrint);

            System.out.println(lineToPrint);

            timeTaken = System.currentTimeMillis() - timeTaken;
            System.out.println("=================================");
            System.out.println("MS: " + timeTaken);
            System.out.println("Second(s): " + timeTaken / 1000);
        } else {
            writeOutData(" ");
        }
    }

    private static int[] stringToInteger(String[] inputDataArrayOfStrings) {
        int[] inputDataArray = new int[inputDataArrayOfStrings.length];
        for (int i = 0; i < inputDataArrayOfStrings.length; i++) {
            inputDataArray[i] = Integer.parseInt(inputDataArrayOfStrings[i]);
        }
        return inputDataArray;
    }

    private static int getNearestDistanceTo(MyRBTree myRBTree, int distance) {
        int determinatorOfNearestDistance;
        int successorOfLastDistance = myRBTree.getSuccessorOf(distance);
        int predecessorOfLastDistance = myRBTree.getPredecessorOf(distance);

        determinatorOfNearestDistance = Math.min(Math.abs(distance - successorOfLastDistance), Math.abs(distance - predecessorOfLastDistance));

        return determinatorOfNearestDistance + distance;
    }

    private static int getDistanceBetween(int xA, int yA, int xB, int yB) {
        int distance;
        distance = (int) (Math.round(Math.sqrt((xB - xA) * (xB - xA) + (yB - yA) * (yB - yA))));
        return distance;
    }

    private static void writeOutData(String lineToPrint) throws IOException {
        PrintWriter outFile = new PrintWriter(new FileWriter(OUT_FILE_NAME));
        outFile.write(lineToPrint);
        outFile.close();
    }

    private static String readInData() throws IOException {
        String inputDataLine;

        BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_NAME));

        String tmp = inFile.readLine();
        if (tmp == null) {
            inputDataLine = "";
        } else {
            inputDataLine = tmp;
        }

        inFile.close();

        return inputDataLine;
    }

    private static class MyRBTree {
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
                root = new Node(key, BLACK);
            } else {
                Node focusNode = root;
                Node child = root;

                Node newNode = new Node(key, RED);

                while (child != null) {
                    if (key < child.getKey()) {
                        focusNode = child;
                        child = child.getLeftChild();
                    } else if (key > child.getKey()) {
                        focusNode = child;
                        child = child.getRightChild();
                    } else {
                        return;
                    }
                }

                if (key < focusNode.getKey()) {
                    focusNode.setLeftChild(newNode);
                } else {
                    focusNode.setRightChild(newNode);
                }

                if (!isBalanced(focusNode)) {
                    balance(newNode);
                }
            }
        }

        public int maxValue() {
            return max(root).getKey();
        }

        private Node max(Node node) {
            if (node.getRightChild() == null) {
                return node;
            } else {
                return max(node.getRightChild());
            }
        }

        public int minValue() {
            return min(root).getKey();
        }

        private Node min(Node node) {
            if (node.getLeftChild() == null) {
                return node;
            } else {
                return max(node.getLeftChild());
            }
        }

        private void balance(Node child) {
            Node[] parentUncleAndGrandF = getParentUncleAndGrandF(child); //returns array{parent, uncle, grandF}
            Node parent = parentUncleAndGrandF[0];
            Node uncle = parentUncleAndGrandF[1];
            Node grandF = parentUncleAndGrandF[2];

            if (isRed(uncle)) {
                //recoloring
                recolor(parent, uncle, grandF);
            } else {
                //restructuring
                restructure(child, parent, grandF);
            }
        }

        private boolean isBalanced(Node parent) {
            if (isRed(parent)) {
                return false;
            } else {
                return true;
            }
        }

        private void restructure(Node x, Node y, Node z) {
            //case 1:
            if (z.getLeftChild() == y && y.getLeftChild() == x) {
                //changing data
                int tmp = z.getKey();
                z.setKey(y.getKey());
                y.setKey(tmp);
                //changing links
                y.setLeftChild(y.getRightChild());
                y.setRightChild(z.getRightChild());
                z.setRightChild(y);
                z.setLeftChild(x);
            }
            //case 2:
            else if (z.getLeftChild() == y && y.getRightChild() == x) {
                //changing data
                int tmp = z.getKey();
                z.setKey(x.getKey());
                x.setKey(tmp);
                //changing links
                y.setRightChild(x.getLeftChild());
                x.setLeftChild(x.getRightChild());
                x.setRightChild(z.getRightChild());
                z.setRightChild(x);
            }
            //case 3:
            else if (z.getRightChild() == y && y.getRightChild() == x) {
                //changing data
                int tmp = z.getKey();
                z.setKey(y.getKey());
                y.setKey(tmp);
                //changing links
                y.setRightChild(y.getLeftChild());
                y.setLeftChild(z.getLeftChild());
                z.setLeftChild(y);
                z.setRightChild(x);
            }
            //case 4:
            else if (z.getRightChild() == y && y.getLeftChild() == x) {
                //changing data
                int tmp = z.getKey();
                z.setKey(x.getKey());
                x.setKey(tmp);
                //changing links
                y.setLeftChild(x.getRightChild());
                x.setRightChild(x.getLeftChild());
                x.setLeftChild(z.getLeftChild());
                z.setLeftChild(x);
            }
        }

        private void recolor(Node y, Node s, Node z) {
            if (z != root) {
                y.setColor(BLACK);
                s.setColor(BLACK);
                z.setColor(RED);

                Node parentOfZ = getParentUncleAndGrandF(z)[0];

                if (!isBalanced(parentOfZ)) {
                    balance(z);
                }
            } else {
                y.setColor(BLACK);
                s.setColor(BLACK);
            }
        }

        private Node[] getParentUncleAndGrandF(Node child) {
            Node[] parentUncleAndGrandF = new Node[3];

            Node grandF = root;
            Node parent = root;
            Node focusNode = root;

            while (child.getKey() != focusNode.getKey()) {
                grandF = parent;
                if (child.getKey() < focusNode.getKey()) {
                    parent = focusNode;
                    focusNode = focusNode.getLeftChild();
                } else {
                    parent = focusNode;
                    focusNode = focusNode.getRightChild();
                }
            }

            if (grandF != parent) {
                if (grandF.getRightChild() == parent) {
                    parentUncleAndGrandF[0] = parent;
                    parentUncleAndGrandF[1] = grandF.getLeftChild();
                    parentUncleAndGrandF[2] = grandF;
                    return parentUncleAndGrandF;
                } else if (grandF.getLeftChild() == parent) {
                    parentUncleAndGrandF[0] = parent;
                    parentUncleAndGrandF[1] = grandF.getRightChild();
                    parentUncleAndGrandF[2] = grandF;
                    return parentUncleAndGrandF;
                }
            }
            parentUncleAndGrandF[0] = parent;
            parentUncleAndGrandF[1] = null;
            parentUncleAndGrandF[2] = null;

            return parentUncleAndGrandF;
        }

        private boolean isRed(Node node) {
            if (node == null) {
                return false;
            } else {
                return node.getColor();
            }
        }

        public int getSuccessorOf(int key) {
            Node parentOfNeededNode = find(key);
            Node neededNode = parentOfNeededNode.getLeftChild();
            if (neededNode != null) {
                return max(neededNode).getKey();
            } else {
                Node[] parentUncleAndGrandF = getParentUncleAndGrandF(parentOfNeededNode);
                return parentUncleAndGrandF[0].getKey(); //returning parent's key
            }
        }

        public int getPredecessorOf(int key) {
            Node parentOfNeededNode = find(key);
            Node neededNode = parentOfNeededNode.getRightChild();
            if (neededNode != null) {
                return min(neededNode).getKey();
            } else {
                Node[] parentUncleAndGrandF = getParentUncleAndGrandF(parentOfNeededNode);
                return parentUncleAndGrandF[0].getKey(); //returning parent's key
            }
        }

        public int getSuccessor() {
            if (root.getRightChild() != null) {
                return min(root.getRightChild()).getKey();
            } else {
                return root.getKey();
            }
        }

        public int getPredecessor() {
            if (root.getLeftChild() != null) {
                return max(root.getLeftChild()).getKey();
            } else {
                return root.getKey();
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
