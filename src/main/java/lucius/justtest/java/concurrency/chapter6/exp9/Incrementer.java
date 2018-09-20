package lucius.justtest.java.concurrency.chapter6.exp9;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Lucius on 9/7/18.
 */
public class Incrementer implements Runnable {
    private AtomicIntegerArray vector;

    public Incrementer(AtomicIntegerArray vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        for (int i = 0; i < vector.length(); i++) {
            vector.getAndIncrement(i);
        }
    }
}
