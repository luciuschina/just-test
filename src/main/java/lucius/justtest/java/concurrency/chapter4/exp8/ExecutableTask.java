package lucius.justtest.java.concurrency.chapter4.exp8;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lucius on 9/2/18.
 */
public class ExecutableTask implements Callable<String> {
    private String name;

    public String getName() {
        return name;
    }

    public ExecutableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        try {
            long duration = (long)(Math.random() * 10);
            System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {

        }
        return "Hello, world. I am " + name;
    }
}
