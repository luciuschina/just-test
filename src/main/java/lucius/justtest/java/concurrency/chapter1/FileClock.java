package lucius.justtest.java.concurrency.chapter1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 8/1/18.
 */
public class FileClock implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\n", new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.printf("The FileClock has been interrupted");
            }
        }
    }
}
