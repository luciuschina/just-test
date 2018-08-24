package lucius.justtest.java.concurrency.chapter2.lock.exp2;

/**
 * Created by Lucius on 8/24/18.
 */
public class Job implements Runnable {
    private IPrintQueue printQueue;

    public Job(IPrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() +" 开始打印");
        printQueue.printJob();
        System.out.println(Thread.currentThread().getName() +" 打印完成");
    }
}
