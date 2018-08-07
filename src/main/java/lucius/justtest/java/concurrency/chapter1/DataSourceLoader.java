package lucius.justtest.java.concurrency.chapter1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 8/1/18.
 */
public class DataSourceLoader implements Runnable {
    public void run() {
        System.out.printf("Beginning data sources loading: %s \n", new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Data Sources loading has finished: %s\n", new Date());
    }
}
