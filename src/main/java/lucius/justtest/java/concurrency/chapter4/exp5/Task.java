package lucius.justtest.java.concurrency.chapter4.exp5;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by Lucius on 9/2/18.
 */
public class Task implements Callable<String> {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("%s: Starting at : %s\n", name, new Date());
        return "Hello world!";
    }
}
