package lucius.justtest.java.concurrency.chapter1;

import java.util.Date;

/**
 * Created by root on 8/1/18.
 */
public class ThreadJoinApp {
    public static void main(String[] args) {
        DataSourceLoader dsLoader = new DataSourceLoader();
        Thread thread1 = new Thread(dsLoader, "DataSourceThread");
        NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
        Thread thread2 = new Thread(ncLoader, "NetworkThread");
        thread1.start();
        thread2.start();

        try {
            System.out.println("call thread1.join();");
            thread1.join();
            System.out.println("call thread2.join();");
            thread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}
