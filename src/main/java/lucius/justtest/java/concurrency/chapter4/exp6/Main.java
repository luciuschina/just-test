package lucius.justtest.java.concurrency.chapter4.exp6;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 在执行器中周期性执行任务
 * Created by Lucius on 9/2/18.
 */
public class Main {
    public static void main(String[] args) {
        //ScheduledExecutorService是一个interface，ScheduledThreadPoolExecutor是一个class
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s \n", new Date());
        Task task = new Task("Task");
        System.out.printf("=============\n", new Date());
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);//第一次延迟1s，后面每隔2s执行一次
        for (int i = 0; i < 10; i++) {
            //getDelay()方法返回任务到下一次执行时还需要的等待时间
            System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.printf("Main: Finished at: %s \n", new Date());
    }
}
