package lucius.justtest.java.concurrency.chapter2.waitnotify.exp1;

/**
 * Created by Lucius on 8/8/18.
 */
public class Main {
    public static void main(String[] args) {

        EventStorage storage = new EventStorage();
        Consumer consumer = new Consumer(storage);
        Thread consumerThread = new Thread( consumer);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);

        producerThread.start();
        consumerThread.start();

    }
}
