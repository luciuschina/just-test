package lucius.justtest.java.concurrency.chapter4.exp9;

import java.util.concurrent.*;

/**
 * 在执行器中分离任务的启动与结果的处理。
 * 通常情况下，使用执行器来执行并发任务时，将Runable或者Callable任务发送给执行器，并获得Future对象来控制任务。
 * 此外，还会碰到如下情形，需要在一个对象里发送任务给执行器，然后在另一个对象里处理结果。对于这种情况，Java并发API
 * 提供了CompletionService类。CompletionService类有一个方法用来发送任务给执行器，还有一个方法为下一个已经执行
 * 结束的任务获取Future对象。从内部实现机制来看，CompletionServices类使用Executor对象来执行任务。这个行为的优势
 * 是可以共享CompletionService对象，并发送任务到执行器。然后其他对象可以处理任务的结果。第二个方法有一个不足之处，
 * 它只能为执行结束的任务获取Future对象，因此，这些Future对象只能被用来获取任务的结果。
 *
 *
 * 在范例的主类中，我们调用Executors工厂类的newCachedThreadPool()方法创建ThreadPoolExecutor执行器对象。
 * 然后，使用这个对象初始化了CompletionService对象，因为Completion Service(完成服务)使用执行器来执行任务。
 * 然后调用ReportRequest类中的submit()方法，利用Completion Service来执行任务。
 * Completion Service中存储着Future对象，用来控制它在队列中的执行。
 * 调用poll()方法访问这个队列，查看是否有任务已经完成，如果有，则返回队列中的第一个元素(即一个任务完成后的Future对象)。
 * 在这个示例中，我们调用poll()方法时传递了两个参数，表示当队列里完成任务的结果为空时，想要等待任务执行结束的时间。
 * CompletionService类可以执行Callable或者Runnable类型的任务。在这个范例中，使用的是Callable类型的任务，但是，也可以发送
 * Runnable对象给它。由于Runnable对象不能产生结果，因此CompletionService基本不适用于Runnable对象。
 * CompletionService类提供了其他两种方法来获取任务已经完成的Future对象。这些方法如下：
 * 1.poll(): 无参数的poll()方法用于检测队列中是否有Future对象。如果队列为空，则立即返回null。否则，它将返回队列中的第一个元素，并移除这个元素。
 * 2.take(): 这个方法也没有参数，它检查队列中是否有Future对象。如果队列为空，它将阻塞线程直至队列中有可用的元素。如果队列中有元素，
 * 它将返回队列中的第一个元素，并移除这个元素。
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
        //new 2个需要产生报告的Runnable对象
        ReportRequest faceRequest = new ReportRequest("Face", completionService);
        ReportRequest onlineRequest = new ReportRequest("Online",completionService);

        Thread faceThread = new Thread(faceRequest); // 产生报告的线程
        Thread onlineThread = new Thread(onlineRequest); // 产生报告的线程
        ReportProcessor processor = new ReportProcessor(completionService);
        Thread receiveThread = new Thread(processor); //接收报告的线程

        System.out.printf("DemosFromMyProjects: Starting the Threads\n");
        faceThread.start();
        onlineThread.start();
        receiveThread.start();
        try {
            System.out.println("DemosFromMyProjects: waiting for the report generators");
            faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("DemosFromMyProjects: Shtting down the executor. \n");
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processor.setEnd(true);
        System.out.printf("DemosFromMyProjects: Ends");
    }
}
