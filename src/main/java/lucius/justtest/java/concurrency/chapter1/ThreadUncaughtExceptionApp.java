package lucius.justtest.java.concurrency.chapter1;

/**
 * Created by root on 8/3/18.
 */
public class ThreadUncaughtExceptionApp {


    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
        //Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main finish");


    }
}
