package lucius.justtest.java.concurrency.chapter4.exp7;

import java.util.concurrent.Callable;

/**
 * Created by Lucius on 9/2/18.
 */
public class Task implements Callable<String> {
    private String name;

    @Override
    public String call() throws Exception {
        while(true) {
            System.out.printf("Task: DemosFromMyProjects\n");
            Thread.sleep(100);
        }
    }
}
