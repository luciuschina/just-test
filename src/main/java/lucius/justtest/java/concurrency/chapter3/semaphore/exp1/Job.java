package lucius.justtest.java.concurrency.chapter3.semaphore.exp1;

/**
 * Created by Lucius on 8/30/18.
 */
public class Job implements Runnable {
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    public void run() {
        System.out.printf("%s going to print a job\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has benn printed\n", Thread.currentThread().getName());
    }
}
