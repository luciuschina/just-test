package lucius.justtest.java.concurrency.chapter2.lock.exp1;

/**
 * Created by Lucius on 8/8/18.
 */
public class Job implements Runnable {
    private PrintQueue printQueue;
    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}
