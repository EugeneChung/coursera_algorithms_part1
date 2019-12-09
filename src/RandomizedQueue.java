import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int count;

    public RandomizedQueue() {
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node<Item> node = new Node<Item>();
        node.item = item;

        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        count++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = StdRandom.uniform(count);
        Node<Item> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        if (node == first) {
            first = first.next;
        }
        if (node == last) {
            last = last.prev;
        }

        Node<Item> result = node;
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        count--;
        return result.item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = StdRandom.uniform(count);
        Node<Item> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int iterationCount;
        private final int[] randomizedIndices;

        private RandomizedQueueIterator() {
            randomizedIndices = new int[count];

            boolean doAgain = false;
            int i = 0;
            while (i < count) {
                int index = StdRandom.uniform(count);
                for (int j = i - 1; j >= 0; j--) {
                    if (randomizedIndices[j] == index) {
                        doAgain = true;
                        break;
                    }
                }
                if (!doAgain) {
                    randomizedIndices[i] = index;
                    i++;
                }
                doAgain = false;
            }
        }

        public boolean hasNext() {
            return count > 0 && iterationCount < count;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int index = randomizedIndices[iterationCount++];
            Node<Item> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] s) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        System.out.println(randomizedQueue.isEmpty());
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        randomizedQueue.enqueue(5);
        System.out.println(randomizedQueue.size());
        System.out.println(randomizedQueue.isEmpty());

        Iterator<Integer> iterator1 = randomizedQueue.iterator();
        while (iterator1.hasNext()) {
            int next = iterator1.next();
            System.out.println(next);
        }
        Iterator<Integer> iterator2 = randomizedQueue.iterator();
        while (iterator2.hasNext()) {
            int next = iterator2.next();
            System.out.println(next);
        }
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());

        randomizedQueue.enqueue(10);
        randomizedQueue.enqueue(11);
        randomizedQueue.enqueue(12);
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
    }
}