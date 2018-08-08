package lucius.justtest.java.concurrency.chapter2.waitnotify.exp1;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lucius on 8/8/18.
 */
public class EventStorage {
    private int maxSize;
    private List<Date> storage;

    public EventStorage() {
        this.storage = new LinkedList<Date>();
        this.maxSize = 10;
    }

    /**
     * 实现同步方法set(), 它保存数据到存储列表storage中。首先，它将检查列表是不是满的，如果已满，
     * 就调用wait()方法挂起线程并等待空余空间的出现。在这个方法的最后，我们调用notifyAll()方法
     * 唤醒因所有调用wait()方法而进入休眠的进程。
     */
    public synchronized void set() {
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("Set: %d\n", storage.size());
        //个人认为是为了唤醒get()方法中因wait而挂起的线程
        notifyAll();

    }

    /**
     * 实现同步方法get(),它从存储列表storage中获取数据。首先它将检查列表中是否有数据，如果没有，就调用
     * wait()方法挂起线程并等待列表中数据的出现。在这个方法最后，我们调用notifyAll()方法唤起所有因调用
     * wait()方法进入休眠的线程。
     */
    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get: %d: %s\n",storage.size(),((LinkedList<Date>)storage).poll());
        //个人认为是为了唤醒set()方法中因wait而挂起的线程
        notifyAll();
    }
}
