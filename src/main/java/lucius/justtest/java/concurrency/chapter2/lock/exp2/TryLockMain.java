package lucius.justtest.java.concurrency.chapter2.lock.exp2;

/**
 * Created by Lucius on 8/24/18.
 */
public class TryLockMain {
    public static void main(String[] args) {
        IPrintQueue printQueue = new PrintQueue2();
        Thread [] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue));
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
