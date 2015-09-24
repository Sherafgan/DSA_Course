package Seminar_15_09_15;

import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(binarySearch(array, 5));
//        OutputStream output = new FileOutputStream("c:\\data\\system.out.txt");
//        PrintStream printOut = new PrintStream(output);


//        System.setOut(printOut);
    }

    private void StackAndLoop(int x, int y) {
        int size = 0;
        int[] arr = {x, y};
        Stack<int[]> stack = new Stack<>();
        stack.push(arr);
        while (!stack.isEmpty()) {
            int xTemp = stack.pop()[0];
            int yTemp = stack.pop()[1];
            if(xTemp != 0){

            }
        }
    }

    private static boolean binarySearch(int[] array, int target) {
        //Thread.dumpStack();
        return binaryS(array, target, array[0], array[array.length - 1]);
    }

    private static boolean binaryS(int[] arr, int val, int lo, int hi) {
        if (lo > hi) return false;

        int mid = lo + (hi - lo) / 2;

        if (val < arr[mid]) {
            return binaryS(arr, val, lo, mid - 1);
        } else if (val > arr[mid]) {
            return binaryS(arr, val, mid + 1, hi);
        } else {
            return true;
        }
    }


}
