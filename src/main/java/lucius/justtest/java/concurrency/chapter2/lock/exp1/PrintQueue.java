package lucius.justtest.java.concurrency.chapter2.lock.exp1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lucius on 8/8/18.
 */
public class PrintQueue {

    private final Lock queueLock = new ReentrantLock();

    public void printJob(Object document) {
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
            //该线程获取锁后,在sleep的过程中,其他进程都在等待.如果不加锁,在sleep的这段时间其他进程不会等待.
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //unlock()释放对锁对象的控制
            queueLock.unlock();
        }
    }
}
