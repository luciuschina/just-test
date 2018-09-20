package lucius.justtest.java.concurrency.chapter6.exp3;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Lucius on 9/7/18.
 */
public class Main {
    public static void main(String[] args) {
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
        Thread taskThread[] = new Thread[5];
        for (int i = 0; i < taskThread.length; i++) {
            Task task = new Task(i, queue);
            taskThread[i] = new Thread(task);
        }

        for (int i = 0; i < taskThread.length; i++) {
            taskThread[i].start();
        }
        for (int i = 0; i < taskThread.length; i++) {
            try {
                taskThread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("DemosFromMyProjects: Queue Size: %d\n", queue.size());
        for (int i = 0; i < taskThread.length * 1000; i++) {
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());

        }
        System.out.printf("DemosFromMyProjects: Queue size: %d\n", queue.size());
        System.out.printf("DemosFromMyProjects: End of the program\n");
    }
}
