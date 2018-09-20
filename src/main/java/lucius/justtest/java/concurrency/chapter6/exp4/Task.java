package lucius.justtest.java.concurrency.chapter6.exp4;

import java.util.concurrent.DelayQueue;
import java.util.Date;
/**
 * Created by Lucius on 9/7/18.
 */
public class Task implements Runnable {
    private int id;
    private DelayQueue<Event> queue;

    public Task(DelayQueue<Event> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime() + (id * 1000));
        System.out.printf("Thread %s: %s\n",id ,delay);
        for (int i = 0; i < 100; i++) {
            Event event = new Event(delay);
            queue.add(event);
        }
    }
}
