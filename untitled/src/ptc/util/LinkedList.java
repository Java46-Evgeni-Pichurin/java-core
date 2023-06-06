package ptc.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractCollection<T> implements List<T>{
    Node<T> head;
    Node<T> tail;

    private static class Node<T>{
        T obj;
        Node<T> next;
        Node<T> prev;
        Node (T obj){
            this.obj = obj;
        }
    }

    private class LinkedListIterator implements Iterator<T> {

        boolean flNext;
        Node<T> current;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T obj = current.obj;
            current = current.next;
            flNext = true;
            return obj;
        }

        @Override
        public void remove() {
            if (!flNext) {
                throw new IllegalStateException();
            }
            if (current == null) {
                removeNode(tail);
            } else {
                removeNode(current.prev);
            }
        }
    }

    private void removeNode(Node<T> node) {
        if (node == head) {
            removeHead();
        } else if(node == tail) {
            removeTail();
        } else {
            removeMiddle(node);
        }
        size--;
    }

    private void removeMiddle(Node<T> node) {
        Node<T> nodeBefore = node.prev;
        Node<T> nodeAfter = node.next;
        nodeBefore.next = nodeAfter;
        nodeAfter.prev = nodeBefore;
    }

    private void removeTail() {
        tail = tail.prev;
        tail.next = null;
    }

    private void removeHead() {
        if (head == tail) {
            head = tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }
    }


    @Override
    public boolean add(T object) {
        if (object == null) {
            throw new NullPointerException();
        }
        Node<T> node = new Node<>(object);
        if (head == null) {
            head = tail = node;
        }
        else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        boolean res = false;
        if (index >= 0 && index <= size) {
            if (index == 0) {
                addHead(obj);
            } else if (index == size) {
                add(obj);
            } else {
                addMiddle(index, obj);
            }
        }
        return res;
    }

    private void addMiddle(int index, T obj) {
        size++;
        Node<T> node = new Node<>(obj);
        Node<T> nodeAfter = getNodeByIndex(index);
        Node<T> nodeBefore = nodeAfter.prev;
        nodeBefore.next = node;
        node.prev = nodeBefore;
        nodeAfter.prev = node;
        node.next = nodeAfter;
    }

    private void addHead(T obj) {
        size++;
        Node<T> node = new Node<>(obj);
        node.next = head;
        head.prev = node;
        head = node;
    }

    @Override
    public boolean remove(Object pattern) {
        boolean res = false;
        int index = indexOf(pattern);
        if (index >= 0) {
            res = true;
            Node<T> node = getNodeByIndex(index);
            removeNode(node);
        }
        return res;
    }

    private Node<T> getNodeByIndex(int index) {
        return index > size / 2 ? nodeFromRightToLeft(index) : nodeFromLeftToRight(index);
    }

    private Node<T> nodeFromLeftToRight(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private Node<T> nodeFromRightToLeft(int index) {
        Node<T> current = tail;
        for (int i = size - 1; i > index; i--) {
            current = current.prev;
        }
        return current;
    }

    @Override
    public T remove(int index) {
        T res = null;
        if(checkingExistingIndex(index)) {
            Node<T> node = getNodeByIndex(index);
            res = node.obj;
            removeNode(node);
        }
        return res;
    }

    @Override
    public T get(int index) {
        T res = null;
        if(checkingExistingIndex(index)) {
            Node<T> node = getNodeByIndex(index);
            res = node.obj;
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public void reverse() {
        if (head != null) {
            reverseList(head, tail);
        }
    }

    private void reverseList(Node<T> left, Node<T> right) {
        if (left != right && left.prev != right) {
            swap(left, right);
            reverseList(left.next, right.prev);
        }
    }

    private void swap(Node<T> left, Node<T> right) {
        T tmp = left.obj;
        left.obj = right.obj;
        right.obj = tmp;
    }

    public void reverseNoRecursion() {
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
    }
}
