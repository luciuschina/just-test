package lucius.justtest.java.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * Created by root on 8/1/18.
 */
public class ThreadInterruptedExceptionApp {
    public static void main(String[] args) {
        FileSearch searcher = new FileSearch("/data","pom.xml");
        Thread thread = new Thread(searcher);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
