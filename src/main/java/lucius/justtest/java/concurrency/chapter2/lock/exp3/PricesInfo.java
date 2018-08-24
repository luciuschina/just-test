package lucius.justtest.java.concurrency.chapter2.lock.exp3;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Lucius on 8/24/18.
 */
public class PricesInfo {
    private double price1;
    private double price2;
    private ReadWriteLock lock;

    public PricesInfo() {
        this.lock = new ReentrantReadWriteLock();
        this.price2 = 2.0;
        this.price1 = 1.0;
    }

    public double getPrice1() {
        lock.readLock().lock();
        double value = this.price1;
        lock.readLock().unlock();
        return value;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double value = this.price2;
        lock.readLock().unlock();
        return value;
    }

    public void setPrices(double price1,double price2) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "start write");
        this.price1 = price1;
        this.price2 = price2;
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "finish write");
        lock.writeLock().unlock();

    }
}
