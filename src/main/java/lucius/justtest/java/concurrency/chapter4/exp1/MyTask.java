package lucius.justtest.java.concurrency.chapter4.exp1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lucius on 9/1/18.
 */
public class MyTask implements Runnable {
    private Date initDate;
    private String name;

    public MyTask(String name) {
        this.initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: MyTask %s: Created on: %s\n", Thread.currentThread().getName(), name, initDate);
        System.out.printf("%s: MyTask %s: Started on: %s\n", Thread.currentThread().getName(), name, new Date());
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: MyTask %s: Doing a task during %d seconds\n", Thread.currentThread().getName(), name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: MyTask %s: Finished on: %s\n", Thread.currentThread().getName(), name, new Date());
    }
}
