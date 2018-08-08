package lucius.justtest.java.concurrency.chapter1;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lucius on 8/7/18.
 */
public class SearchTask implements Runnable {
    private Result result;

    public SearchTask(Result result) {
        this.result = result;
    }

    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s: start\n", name);
        try {
            doTask();
            result.setName(name);

        } catch (InterruptedException e) {
            System.out.printf("Thread %s: Interrupted", name);
            return;
        }
        System.out.printf("Thread %s: End\n", name);

    }

    public void doTask() throws InterruptedException {
        Random random = new Random(new Date().getTime());
        int value = (int) (random.nextDouble() * 100);
        System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
        TimeUnit.SECONDS.sleep(value);
    }


}
