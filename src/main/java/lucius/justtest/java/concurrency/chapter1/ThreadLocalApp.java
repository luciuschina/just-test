package lucius.justtest.java.concurrency.chapter1;

/**
 * Created by Lucius on 8/7/18.
 */
public class ThreadLocalApp {
    public static void main(String[] args) {
        SafeTask task = new SafeTask();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
    }
}
