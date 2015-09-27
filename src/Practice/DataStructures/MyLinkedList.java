package Practice.DataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sherafgan Kandov
 */
public class MyLinkedList<E> implements Iterable<E> {
    private Elem<E> head;
    private Elem<E> tail;

    public MyLinkedList() {
        head = null;
        tail = null;
    }

    public void add(E e) {
        Elem<E> newElem = new Elem<>(e);
        if (head == null) {
            newElem.setNext(head);
            head = newElem;
            tail = newElem;
        } else {
            tail.setNext(newElem);
            tail = newElem;
        }
    }

    public void addToHead(E e) {
        Elem<E> newElem = new Elem<>(e);
        newElem.setNext(head);
        head = newElem;
    }

    public void addToTail(E e) {
        add(e);
    }

    public void removeFirstValue() {
        head = head.getNext();
    }

    public void removeLastValue() {
        Elem<E> cursor;
        cursor = head;
        while (cursor.getNext() != tail) {
            cursor = cursor.getNext();
        }
        tail = cursor;
        tail.setNext(null);
    }

    public E get(int index) {
        if ((index + 1) > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size());
        } else {
            Elem<E> cursor;
            cursor = head;
            int i = 0;
            if (i == index) {
                return cursor.getE();
            } else {
                while (i != index) {
                    cursor = cursor.getNext();
                    i++;
                }
                return cursor.getE();
            }
        }
    }

    public int size() {
        Elem<E> cursor;
        cursor = head;
        int size = 0;
        while (cursor != null) {
            size++;
            cursor = cursor.getNext();
        }
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<E> {
        private Elem<E> cursor;

        public MyLinkedListIterator() {
            cursor = head;
        }


        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("NO SUCH ELEMENT!!!");
            }
            E value = cursor.getE();
            cursor = cursor.getNext();
            return value;
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
