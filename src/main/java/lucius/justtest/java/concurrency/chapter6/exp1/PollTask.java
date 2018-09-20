package lucius.justtest.java.concurrency.chapter6.exp1;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by Lucius on 9/7/18.
 */
public class PollTask implements Runnable {
    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }
}
