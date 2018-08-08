package lucius.justtest.java.concurrency.chapter2.waitnotify.exp1;

/**
 * Created by Lucius on 8/8/18.
 */
public class Producer implements Runnable {
    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    public void run() {
        //循环100次调用set()方法
        for (int i = 0; i < 100; i++) {
            storage.set();
        }
    }
}
