package lucius.justtest.java.concurrency.chapter4.exp6;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by Lucius on 9/2/18.
 */
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Starting at : %s\n", name, new Date());
    }

}
