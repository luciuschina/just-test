package lucius.justtest.java.concurrency.chapter2.lock.exp3;

/**
 * Created by Lucius on 8/24/18.
 */
public class Writer implements Runnable {
    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    public void run() {

            pricesInfo.setPrices(Math.random()*100,Math.random()*60);


    }
}
