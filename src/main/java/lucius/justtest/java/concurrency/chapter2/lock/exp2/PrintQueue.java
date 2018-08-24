package lucius.justtest.java.concurrency.chapter2.lock.exp2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lucius on 8/24/18.
 */
public class PrintQueue implements IPrintQueue {
    private final Lock queueLock = new ReentrantLock();

    public void printJob() {
        queueLock.lock();
        try {
            Long duration = (long)(Math.random()* 10000);
            System.out.println(Thread.currentThread().getName() + " print a Job " + duration/1000 + " s");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }
}
