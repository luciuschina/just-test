package lucius.justtest.java.concurrency.chapter3.semaphore.exp1;

/**
 * Created by Lucius on 8/30/18.
 */
public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread_"+i);
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}
