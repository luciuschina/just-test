package lucius.justtest.java.concurrency.chapter4.exp5;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在执行器中延迟执行任务
 * Created by Lucius on 9/2/18.
 */
public class Main {
    public static void main(String[] args) {

        /*
        ScheduledThreadPoolExecutor类是ThreadPoolExecutor类的子类，因而继承了ThreadPoolExecutor类的所有特性。
        但是Java推荐仅在开发定时任务程序时采用ScheduledThreadPoolExecutor类
         */
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        /*
        这个值如果设置为ture,那么shutdown()方法调用后，会等待已经提交但还没执行的任务执行完再结束。
        如果设置为false，那么shutdown()方法调用后，已经提交但还没执行的任务将不再执行，直接结束。
        这个值默认是true。
         */
        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);

        System.out.printf("Main: Starting at: %s\n", new Date());
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task_"+i);
            executor.schedule(task, i+1, TimeUnit.SECONDS);//重点！！ 轮到该任务执行时，需要等待1s钟再执行
            System.out.println("！！！！");
        }
        executor.shutdown(); //executor.shutdownNow() 立即关闭
        try {
            /*
            当executor中已经提交的任务执行完后，结束等待，往下执行
            或者 executor中已经提交的任务还没执行完，但是已经等待了5秒钟，也结束等待，往下执行
             */
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Ends at:%s\n", new Date());
    }
}
