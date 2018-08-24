package lucius.justtest.java.concurrency.chapter2.lock.exp3;

/**
 * Created by Lucius on 8/24/18.
 */
public class Reader implements Runnable {
    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: Price1: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s: Price2: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
        }
    }
}
