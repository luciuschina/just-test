package lucius.justtest.java.concurrency.chapter4.exp7;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 在执行器中取消任务
 使用执行器时，不需要管理现场，只需要实现Runnable和Callable任务并发送任务给执行器即可。执行器负责创建线程，
 管理线程池中的线程，当线程不再需要时就销毁它们。有时候，我们可能需要取消已经发送给执行器的任务。在这种情况下，
 可以使用Future接口的cancel()方法来执行取消操作。
 */
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Task task = new Task();
        System.out.printf("Main: Executing the Task\n");
        Future<String> result = executor.submit(task);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: Canceling the Task\n");
        /*
        如果想取消一个已经发送给执行器的任务，可以使用Future接口的cancel()方法。
        根据调用cancel()方法所传递的参数以及任务的状态，这个方法写得行为有些不同。
        1.如果任务已经完成，或者之前已经取消，或者由于某种原因而不能被取消，那么这个方法将返回false并且任务也不能被取消。
        2.如果任务在执行器中等待分配Thread对象执行它，那么任务被取消，并且不会开始执行。
        3.如果任务已经在运行，那么它依赖于调用cancel()方法所传递的参数。
            如果传递的参数为true并且任务正在运行，那么任务将被取消。
            如果传递的参数为false，并且任务在运行，那么任务不会被取消。
         */
        result.cancel(true); //重点！！！
        System.out.printf("Main: cancelled:%s\n", result.isCancelled());
        System.out.printf("Main: Done:%s\n", result.isDone());
        executor.shutdown();
        System.out.printf("Main: The executor has finished\n");
    }
}
