package lucius.justtest.java.concurrency.chapter4.exp1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 ThreadPoolExecutor类提供了许多方法来获取自身状态的信息。在范例中，已经使用了getPoolSize()来获取线程池的大小，
 用getActiveCount()方法获取线程池中活跃线程的数量，用getCompletedTaskCount()方法来获取执行器完成的任务数量。
 也可以用getLargestPoolSize()方法来返回曾经同时位于线程池中的最大线程数。

 ThreadPoolExecutor类也提供了结束执行器的相关方法。
 shutdownNow():这个方法会立即关闭执行器。执行器将不再执行那些正在等待执行的任务。
 这个方法将返回等待执行的任务列表。调用时，正在运行的任务将继续运行，但是这个方法并不等待这些任务完成。
 isTerminated():如果调用了shutdown()或者shutdownNow()方法，并且执行器完成了关闭的过程，这个方法将返回true。
 isShutdown():如果调用了shutdown()方法这个方法将返回true。
 awaitTermination(long timeout, TimeUnit unit): 这个方法将阻塞所调用的线程，直到执行器完成任务或者达到所指定的timeout值。

 对于Executors.newCachedThreadPool()的解释：
 Executors.newCachedThreadPool()
 */
public class Server {
    private ThreadPoolExecutor executor;  //重点！！

    public Server() {
        /*

        当使用Executors.newCachedThreadPool()方法创建基本的ThreadPoolExecutor时，执行器运行过程中将碰到线程数量的问题。
        如果线程池里没有空闲的线程可用，那么执行器将为接收到的每一个任务创建一个新线程，当发送大量的任务给执行器并且任务需要
        持续较长的时间时，系统将会超负荷，应用程序也将随之性能不佳。所以仅当线程的数量是合理的或者线程只会运行很短的时间时，
        才适合使用Executors.newCachedThreadPool()方法来创建执行器
        为了避免newCachedThreadPool()的这个问题。 可以使用Executors.newFixedThreadPool(5),创建一个最大线程数为5的线程池。
        如果发送超过这个最大值的任务执行器，执行器将不再创建额外的线程，剩下的任务将被阻塞直到执行器有空闲的线程可用。这个特性可用保证执行器
        不会给应用程序带来性能不佳的问题。
        Executors工厂类也提供了newSingleThreadExecutor()方法。这是一个创建固定大小线程执行器的极端场景，它将创建一个只有单个线程的执行器。
        因此，这个执行器只能在同一时间执行一个任务。

         */
        //this.executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();  //重点！！
        this.executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);  //重点！！
    }
    public void executeTask(MyTask task) {
        System.out.printf("Server: A new task has arrived\n");
        executor.execute(task);  //重点！！
        System.out.printf("Server: Pool size: %d\n", executor.getPoolSize());
        System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
        System.out.printf("Server: Completed Tasks %d\n", executor.getCompletedTaskCount());
        System.out.printf("Server: Task Count %d\n", executor.getTaskCount());
    }
    public void endServer() {
        System.out.println("call executor.shutdown");
        //重点！！在调用shutdown()方法后，ThreadPoolExecutor类的awaitTermination()方法会将线程休眠，直到所有任务执行结束。
        executor.shutdown();
    }
}
