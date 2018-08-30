package lucius.justtest.java.concurrency.chapter2.condition.exp1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lucius on 8/30/18.
 */
public class Depot {
    private int capacity; //仓库容量
    private int size; //仓库中现有数量
    private Lock lock; //可重入独占锁
    private Condition fullCondtion; //仓库满了
    private Condition emptyCondition; //仓库无货


    public Depot(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.lock = new ReentrantLock();
        this.fullCondtion = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    public void produce(int val) {
        this.lock.lock();
        try {
            int left = val;
            //当还有需要生产的货物
            while (left > 0) {
                while (size == capacity) {
                    /*
                    由于当前仓库中货物已经满了，所以只能暂停生产，等待消费者从仓库中取出一部分货物后再生产。
                    消费者取完货物后会调用this.fullCondtion.signal()，这时生产者会从这里醒来，接着执行。
                    当判断size已经小于capacity后，会接着生产货物。
                    关于await():
                    await会释放锁，调用await是为了能让其他线程访问竞争的资源，并等待在当前代码处，当其他线程处理好之后再唤醒，
                    */
                    this.fullCondtion.await(); //这里释放锁
                    System.out.println("fullCondtion醒来了");
                }
                int inc = (left + size) > capacity ? (capacity - size) : left;
                this.size += inc;
                left -= inc;

                System.out.println(String.format("%s capacity:%d,inc:%d,left:%d, size:%d", Thread.currentThread().getName(), capacity, inc, left, size));

                //有些消费者可能因为仓库无货而在await，告知消费者仓库已经有货了，可以取了
                emptyCondition.signal();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void consume(int val) {
        this.lock.lock();
        try {
            int left = val;
            while (left > 0) {
                while (size == 0) {
                    this.emptyCondition.await();
                    System.out.println("emptyCondition醒来了");
                }
                int dec = (size > left)  ? left : size;
                left -= dec;
                size -= dec;
                System.out.println(String.format("%s capacity:%d,dec:%d,left:%d, size:%d", Thread.currentThread().getName(), capacity, dec, left, size));
                fullCondtion.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }
}
