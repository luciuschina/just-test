package lucius.justtest.java.concurrency.chapter1;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by root on 8/1/18.
 */
public class ThreadDaemonApp {
    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<Event>();
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }
}
