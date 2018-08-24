package lucius.justtest.java.concurrency.chapter2.lock.exp3;

/**
 * Created by Lucius on 8/24/18.
 */
public class Main {
    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();
        Thread[] threads = new Thread[200];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread( new Writer(pricesInfo));

        }

        for (int i = 100; i < 200; i++) {
            threads[i] = new Thread(new Reader(pricesInfo));
        }

        for (int i = 0; i < 200 ; i++) {
            threads[i].start();
        }


    }
}
