package Practice.DataStructures;

import java.lang.reflect.Constructor;

/**
 * @author Sherafgan Kandov
 */
public class MyStack<E> {
    private E top;
    private E[] storage;
    private final static int CAPACITY = 10000;

    public MyStack() {
    }

    /*
    * public E pop() {
        E e = top;
        top = storage;
        return e;
    }*/

    public E peek() {
        return top;
    }

    public boolean empty() {
        return storage.length == 0;
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
