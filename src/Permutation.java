import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] s) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        int k = Integer.parseInt(s[0]);
        if (k == 0) return;

        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }

        Iterator<String> iterator = randomizedQueue.iterator();
        for (int printed = 0; printed < k; printed++) {
            System.out.println(iterator.next());
        }
    }
}
