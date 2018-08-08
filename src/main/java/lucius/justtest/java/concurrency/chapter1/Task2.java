package lucius.justtest.java.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lucius on 8/7/18.
 */
public class Task2 implements Runnable {
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
