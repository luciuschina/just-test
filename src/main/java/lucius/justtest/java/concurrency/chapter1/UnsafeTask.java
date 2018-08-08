package lucius.justtest.java.concurrency.chapter1;

/**
 * Created by root on 8/3/18.
 */
public class UnsafeTask implements Runnable {
    private int count = 0;

    public void run() {
        System.out.printf("Starting Thread: %s , count: %s\n", Thread.currentThread().getId(), count ++);
    }

}
