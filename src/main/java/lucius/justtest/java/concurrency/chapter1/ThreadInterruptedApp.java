package lucius.justtest.java.concurrency.chapter1;

/**
 * Created by root on 7/31/18.
 */
public class ThreadInterruptedApp {
    public static void main(String[] args) {
        Thread task = new PrimeGenetor();
        task.start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        task.interrupt();
    }
}
