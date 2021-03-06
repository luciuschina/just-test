package lucius.justtest.java.concurrency.chapter6.exp1;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by Lucius on 9/7/18.
 */
public class Main {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
       // ArrayList<String> list = new ArrayList<>();
        Thread threads[] = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("DemosFromMyProjects:%d AddTask Thread have been launched\n", threads.length);
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("DemosFromMyProjects: Size of the list:%d\n", list.size());

        for (int i = 0; i < threads.length; i++) {
            PollTask task = new PollTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("DemosFromMyProjects: %d PollTask threads have been launched\n", threads.length);
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("DemosFromMyProjects: size of the List: %d\n",list.size());

    }
}
