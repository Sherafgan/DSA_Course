package Assignment1;

import java.util.Iterator;

/**
 * This is a template code for Assignment #1
 * for DSA course: Bachelor-3
 *
 * @author Stanislav I. Protasov
 * @company Innopolis University
 */
public class MySuperBrowserEngineB3 {

    /**
     * This is a class that represents a real 2D point
     */
    public static class Point2D {
        private double x, y;

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public String toString() {
            return "[" + x + ", " + y + "]";
        }

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * This is a class that represents rectangle
     * in Cartesian coordinates
     */
    public static class Rectangle {
        /**
         * these are corner points of rectangle
         */
        private Point2D lowerLeft, upperTop;
        /**
         * this tag is used to store additional info
         * in our case - to save a word represented by rectangle
         */
        private String tag;

        public String getTag() {
            return tag;
        }

        /**
         * Constructor for rectangle
         *
         * @param lowerLeft lower left angle of rectangle
         * @param upperTop  upper top angle or rectangle
         * @param tag       word that is represented by rectangle
         */
        public Rectangle(Point2D lowerLeft, Point2D upperTop, String tag) {
            this.lowerLeft = lowerLeft;
            this.upperTop = upperTop;
            this.tag = tag;
        }

        @Override
        public String toString() {
            return lowerLeft.toString() + "-" + upperTop.toString();
        }

        /**
         * Methods returns 4 pairs of points. Each pair represents an edge or rectangle
         *
         * @return returns Point2D[4][2] array
         */
        public Point2D[][] getEdges() {
            Point2D[][] edges = new Point2D[4][2];
            edges[0][0] = lowerLeft;
            edges[0][1] = new Point2D(lowerLeft.getX(), upperTop.getY());
            edges[1][0] = edges[0][1];
            edges[1][1] = upperTop;
            edges[2][0] = upperTop;
            edges[2][1] = new Point2D(upperTop.getX(), lowerLeft.getY());
            edges[3][0] = edges[2][1];
            edges[3][1] = lowerLeft;
            return edges;
        }
    }

    /**
     * TODO implement at least these methods
     */
    public static class MyList implements Iterable {
        private Elem<Object> head;
        private Elem<Object> tail;

        public void add(int index, Object o) {
            Elem<Object> newElem = new Elem<>(o);

            if (index > size()) {
                throw new IndexOutOfBoundsException("SIZE: " + size() + "INDEX: " + index);
            } else if (index == 0) {
                newElem.setNext(head.getNext());
                head = newElem;
            } else {
                Elem<Object> cursor;
                cursor = head;

                for (int i = 0; i < size(); i++) {
                    if ((i + 1) == index) {
                        newElem.setNext(cursor.getNext().getNext());
                        cursor.setNext(newElem);
                    }
                    cursor = cursor.getNext();
                }
            }
        }

        public void addLast(Object o) {
            Elem<Object> newElem = new Elem<Object>(o);
            tail.setNext(newElem);
            tail = newElem;
        }

        public Object get(int index) {
            if (index > size()) {
                throw new IndexOutOfBoundsException("SIZE: " + size() + "INDEX: " + index);
            } else if (index == 0) {
                return head;
            } else {
                Elem<Object> cursor = head;
                for (int i = 0; i < index; i++) {
                    cursor = cursor.getNext();
                }
                return cursor;
            }
        }

        public int size() {
            int size = 0;
            Elem<Object> cursor = head;
            while (cursor != null) {
                size++;
                cursor = cursor.getNext();
            }
            return size;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void remove(int index) {
            Elem<Object> cursor = head;
            if (index == 0) {
                head = head.getNext();
            } else if (index == (size() - 1)) {
                for (int i = 0; i < (size() - 1); i++) {
                    cursor = cursor.getNext();
                }
                cursor.setNext(null);
            } else {
                for (int i = 0; i < (index - 1); i++) {
                    cursor = cursor.getNext();
                }
                Elem<Object> cursor2 = cursor.getNext();
                cursor.setNext(cursor2.getNext());
            }
        }

        @Override
        public Iterator iterator() {
            return new MyListIterator();
        }

        private class MyListIterator implements Iterator {
            Elem<Object> cursor = head;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public Object next() {
                Object object = cursor.getNext();
                cursor = cursor.getNext();
                return object;
            }
        }

        private class Elem<E> {
            private E e;
            private Elem<E> next;

            public Elem(E e) {
                this.e = e;
            }

            public Elem(E e, Elem<E> next) {
                this.e = e;
                this.next = next;
            }

            public E getE() {
                return e;
            }

            public void setE(E e) {
                this.e = e;
            }

            public Elem<E> getNext() {
                return next;
            }

            public void setNext(Elem<E> next) {
                this.next = next;
            }
        }
    }

    /**
     * TODO implement at least these methods
     */
    public static class MyStack extends MyList {
        MyList myList;

        MyStack() {
            myList = new MyList();
        }

        public void push(Object o) {
            myList.add(0, o);
        }

        public Object pop() {
            Object o = myList.get(0);
            myList.remove(0);
            return o;
        }
    }

    /**
     * TODO implement
     * Intersection method checks if section ab intersects with section cd.
     *
     * @param a section 1 point 1
     * @param b section 1 point 2
     * @param c section 2 point 1
     * @param d section 2 point 2
     * @return true if sections intersect
     */
    public static boolean intersects(Point2D a, Point2D b, Point2D c, Point2D d) {
        double[][] A = new double[2][2];
        A[0][0] = b.getX() - a.getX();
        A[1][0] = b.getY() - a.getY();
        A[0][1] = c.getX() - d.getX();
        A[1][1] = c.getY() - d.getY();

        double det0 = A[0][0] * A[1][1] - A[1][0] * A[0][1];

        double detU = (c.getX() - a.getX()) * A[1][1] - (c.getY() - a.getY()) * A[0][1];
        double detV = A[0][0] * (c.getY() - a.getY()) - A[1][0] * (c.getX() - a.getX());

        double u = detU / det0;
        double v = detV / det0;
        return u > 0 && u < 1 && v > 0 && v < 1;
    }

    /**
     * TODO implement
     * gets a stack that contains all rectangles that have intersection with the line
     *
     * @param rects      rectangles to check
     * @param lineStart  starting point of the line
     * @param lineFinish end point of the line
     * @return
     */
    public static MyStack getIntersected(MyList rects, Point2D lineStart, Point2D lineFinish) {
        return new MyStack();
    }

    /**
     * Method takes text and calculates rectangles that represent words
     *
     * @param text          input string
     * @param oneLetterSize size of one letter in monospace font
     * @param startCorner   corner where text starts
     * @return list of rectangles associated with words in text
     */
    public static MyList getRectangles(String text, Point2D oneLetterSize, Point2D startCorner) {
        MyList list = new MyList();
        String[] words = text.split(" ");
        int position = 0;
        for (String word : words) {
            if (word.length() > 0) {
                Point2D ll = new Point2D(
                        startCorner.getX() + position * oneLetterSize.getX(),
                        startCorner.getY());
                Point2D ut = new Point2D(
                        ll.getX() + word.length() * oneLetterSize.getX(),
                        startCorner.getY() + oneLetterSize.getY());
                list.addLast(new Rectangle(ll, ut, word));
            }
            position += word.length() + 1;
        }
        return list;
    }


    public static void main(String[] args) {
        String inputData = "";
        Point2D textCorner, letterSize, lineStart, lineEnd;

        textCorner = letterSize = lineEnd = lineStart = new Point2D(0, 0);
        // TODO read input data here


        MyList list = getRectangles(inputData, letterSize, textCorner);
        MyStack stack = getIntersected(list, lineStart, lineEnd);
        while (!stack.isEmpty()) {
            String word = ((Rectangle) stack.pop()).getTag();
            // TODO write output data
        }
    }

}
/**
 * guid 5bd29cec-2e7a-4c93-9112-7f0ad68865c4
 */
