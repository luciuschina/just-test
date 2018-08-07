package lucius.justtest.java.concurrency.chapter1;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * 1. Thread Priority: 1~10. the min: 1, max: 10
 * 2. Six status: new, runnable, blocked, waiting, time waiting, terminated
 */
public class ThreadPriorityApp {
    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main: Id %d - %s\n", thread.getId(), thread.getName());
        pw.printf("Main: Priority: %d\n", thread.getPriority());
        pw.printf("Main: Old State: %s\n", state);
        pw.printf("Main: New State: %s\n", thread.getState());
        pw.printf("Main: ********************** \n");
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        //Store running status for these Threads
        Thread.State[] status = new Thread.State[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread_" + i);
        }

        try {
            FileWriter file = new FileWriter("./src/main/tmp/threadPriorityApp.log");
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < 10; i++) {
                pw.println("Main: Status of Thread" + i + " : " + threads[i].getState());
                status[i] = threads[i].getState();
            }

            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }

            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != status[i]) {
                        writeThreadInfo(pw, threads[i], status[i]);
                        status[i] = threads[i].getState();
                    }
                }

                finish = true;
                for (int i = 0; i < 10; i++) {
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                }
            }
            pw.flush();
            pw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
