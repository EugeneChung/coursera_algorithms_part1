import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int count;

    public Deque() {
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node<Item> node = new Node<Item>();
        node.item = item;

        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
        count++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node<Item> node = new Node<Item>();
        node.item = item;

        if (isEmpty()) {
            first = node;
        } else {
            node.prev = last;
            last.next = node;
        }
        last = node;
        count++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        count--;
        if (count == 0) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        count--;
        if (count == 0) {
            first = null;
            last = null;
        } else {
            last = last.prev;
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;
    }

    private class DequeIterator implements Iterator<Item> {
        private int current;
        private Node<Item> currentNode;

        public boolean hasNext() {
            return count > 0 && current < count;
        }

        public Item next() {
            if (count == 0 || current == count) {
                throw new NoSuchElementException();
            }
            if (currentNode == null) {
                currentNode = first;
            } else {
                currentNode = currentNode.next;
            }
            current++;
            return currentNode.item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] s) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(0);

        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();

        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.isEmpty());

        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(7);
        System.out.println(deque.isEmpty());
        System.out.println(deque.removeLast());

        Deque<Integer> deque2 = new Deque<Integer>();
        for (int i = 0; i < 50; i++) {
            int uniform = StdRandom.uniform(4);
            try {
                switch (uniform) {
                    case 0:
                        deque2.addFirst(1);
                        break;
                    case 1:
                        deque2.addLast(2);
                        break;
                    case 2:
                        deque2.removeFirst();
                        break;
                    case 3:
                        deque2.removeLast();
                        break;
                }
            } catch (NoSuchElementException e) {
                //ignored
            }
        }
    }
}