package lucius.justtest.java.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * Created by root on 8/1/18.
 */
public class ThreadSleepApp {
    public static void main(String[] args) {
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }
}
