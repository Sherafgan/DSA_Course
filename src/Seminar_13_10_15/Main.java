package Seminar_13_10_15;

import Practice.Trees.MyLinkedBinarySearchTree.MyLinkedBinarySearchTree;

/**
 * @author Sherafgan Kandov
 */
public class Main {
    public static void main(String[] args) {
        MyLinkedBinarySearchTree<String> myLinkedBinarySearchTree = new MyLinkedBinarySearchTree<>();
        int[] array = new int[]{30, 15, 5, 20, 35, 30, 32, 31, 40};

        String[] alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};

        String s = "A";
        for (int i = 0; i < array.length; i++) {
            myLinkedBinarySearchTree.add(array[i], s);

            s = s + alphabet[i + 1];
        }

        System.out.println(myLinkedBinarySearchTree.size());

        System.out.println(myLinkedBinarySearchTree.find(32));
        System.out.println(myLinkedBinarySearchTree.delete(32));
        System.out.println(myLinkedBinarySearchTree.find(32));

        System.out.println(myLinkedBinarySearchTree.size());

        myLinkedBinarySearchTree.add(31, "ABCDEFG");
        myLinkedBinarySearchTree.add(33, "me");

        System.out.println(myLinkedBinarySearchTree.size());

        System.out.println(myLinkedBinarySearchTree.height());

        int[] sortedArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        myLinkedBinarySearchTree.add(sortedArray, alphabet);
    }
}
