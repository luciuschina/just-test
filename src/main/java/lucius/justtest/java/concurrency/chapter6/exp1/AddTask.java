package lucius.justtest.java.concurrency.chapter6.exp1;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by Lucius on 9/7/18.
 */
public class AddTask implements Runnable {
    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            list.add(name+": Element "+i);
        }
    }

}
