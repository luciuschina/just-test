package lucius.justtest.java.concurrency.chapter3.semaphore.exp1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lucius on 8/30/18.
 */
public class PrintQueue {
    private final Semaphore semaphore;

    public PrintQueue() {
        this.semaphore = new Semaphore(4);
    }

    public void printJob(Object document) {
        try {
            semaphore.acquire(); //这里还可以传入一个值，如 semaphore.acquire(2); 表示一次性获取2个信号量
            long duration = (long)(Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
