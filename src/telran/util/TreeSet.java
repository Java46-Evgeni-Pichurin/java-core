package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.naming.OperationNotSupportedException;

public class TreeSet<T> implements SortedSet<T> {
    private static class Node<T> {
        T obj;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        Node(T obj) {
            this.obj = obj;
        }
    }

    private Node<T> root;
    int size;
    Comparator<T> comp;

    private Node<T> getLeastNodeFrom(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private class TreeSetIterator implements Iterator<T> {
        Node<T> current = root == null ? null : getLeastNodeFrom(root);
        Node<T> prevNode = null;
        boolean flNext = false;

        @Override
        public boolean hasNext() {

            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prevNode = current;
            T res = current.obj;
            updateCurrent();
            flNext = true;
            return res;
        }

        private void updateCurrent() {
            current = current.right != null ? getLeastNodeFrom(current.right) : getGreaterParent(current);

        }

        private Node<T> getGreaterParent(Node<T> node) {

            while (node.parent != null && node.parent.left != node) {
                node = node.parent;
            }
            return node.parent;
        }

        @Override
        public void remove() {
            if (!flNext) {
                throw new IllegalStateException();
            }
            TreeSet.this.remove(prevNode.obj);
            prevNode = current;
            flNext = false;
        }
    }

    public TreeSet(Comparator<T> comp) {
        this.comp = comp;
    }

    @SuppressWarnings("unchecked")
    public TreeSet() {
        this((Comparator<T>) Comparator.naturalOrder());
    }

    @Override
    public boolean add(T obj) {
        Node<T> parent = getNodeOrParent(obj);
        boolean res = false;
        int compRes = 0;
        if (parent == null || (compRes = comp.compare(obj, parent.obj)) != 0) {
            //obj doesn't exist
            Node<T> newNode = new Node<>(obj);
            if (parent == null) {
                //added first element that is the root
                root = newNode;
            } else if (compRes > 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
            res = true;
            newNode.parent = parent;
            size++;
        }
        return res;
    }

    private Node<T> getNodeOrParent(T obj) {
        Node<T> current = root;
        Node<T> parent = null;
        int compRes = 0;
        while (current != null) {
            parent = current;
            compRes = comp.compare(obj, current.obj);
            if (compRes == 0) {
                break;
            }
            current = compRes > 0 ? current.right : current.left;
        }
        return parent;
    }

    @Override
    public boolean remove(Object pattern) {
        Node<T> cur = findObj(pattern);
        if (cur == null) {
            return false;
        }
        boolean isJunction = isJunction(cur);
        if (isJunction) {
            removeJunctionNode(cur);
        } else {
            removeNonJunctionNode(cur);
        }
        size--;
        return true;
    }

    private void removeJunctionNode(Node<T> cur) {
        Node<T> tmp = cur.right.left == null ? cur.right : getLeastNodeFrom(cur.right);
        cur.obj = tmp.obj;
        removeNonJunctionNode(cur);
    }

    private void removeNonJunctionNode(Node<T> cur) {
        int child = findChild(cur);
        Node<T> tmp = cur.parent;
        if (tmp == null) {
            root = root.left != null ? root.left : root.right;
            return;
        }
        if (child == 0) {
            if (tmp.left == cur) {
                tmp.left = null;
            } else {
                tmp.right = null;
            }
        }
        if (child == 1) {
            Node<T> curChild = cur.left != null ? cur.left : cur.right;
            if (tmp.left == cur) {
                tmp.left = curChild;
            } else {
                tmp.right = curChild;
            }
        }
    }

    private int findChild(Node<T> cur) {
        int child = 0;
        if (cur.left != null) {
            child++;
        }
        if (cur.right != null) {
            child++;
        }
        return child;
    }

    private boolean isJunction(Node<T> cur) {
        return cur.left != null && cur.right != null;
    }


    @Override
    public boolean contains(Object pattern) {
        return findObj(pattern) != null;
    }

    private Node<T> findObj(Object pattern) {
        Node<T> cur = root;
        int compRes;
        if (size == 0) {
            return null;
        }
        while (true) {
            compRes = comp.compare((T) pattern, cur.obj);
            if (compRes == 0) {
                return cur;
            }
            cur = compRes < 0 ? cur.left : cur.right;
            if (cur == null) {
                return null;
            }
        }
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public Iterator<T> iterator() {

        return new TreeSetIterator();
    }

    @Override
    public T first() {
        if (root == null) {
            return null;
        }
        return getLeastNodeFrom(root).obj;
    }


    @Override
    public T last() {
        if (root == null) {
            return null;
        }
        return getMostNodeFrom(root).obj;
    }

    private Node<T> getMostNodeFrom(Node<T> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

}
