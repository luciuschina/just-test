package lucius.justtest.java.concurrency.chapter2.waitnotify.exp1;

/**
 * Created by Lucius on 8/8/18.
 */
public class Consumer implements Runnable {
    EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.get();
        }
    }
}
