package lucius.justtest.java.concurrency.chapter1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Lucius on 8/7/18.
 */
public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String name;
    private List<String> stats;

    public MyThreadFactory(String name) {
        this.stats = new ArrayList<String>();
        this.counter = 0;
        this.name = name;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name+"-Thread_"+counter);
        counter ++;
        stats.add(String.format("Created thread %d with name %s on %s\n",t.getId(),t.getName(), new Date()));
        return t;
    }

    public String getStats() {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> it = stats.iterator();
        while(it.hasNext()) {
            buffer.append(it.next());
            buffer.append("\n");
        }
        return buffer.toString();
    }
    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        Task2 task = new Task2();
        Thread thread;
        System.out.printf("Starting the Threads\n");
        for (int i = 0; i < 10; i++) {
            thread = factory.newThread(task);
            thread.start();
        }
        System.out.println("Factory stats:\n");
        System.out.printf("%s", factory.getStats());
    }
}
