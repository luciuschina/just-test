package lucius.justtest.java.concurrency.chapter4.exp2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 FactorialCalculator类实现了带有泛型参数Integer类型的Callable接口，因此，这个Integer类型将作为在调用call()方法时返回的类型。
 范例中的另一个关键点在Main主类中，我们通过submit()方法发送一个Callable对象给执行器去执行，这个submit()方法接收Callable对象作为
 参数，并返回Future对象。Future对象可以用于以下两个主要目的：
 1.控制任务的状态:可以取消任务或者检查任务是否已经完成。为了达到这个目的可以使用isDone()方法检查任务是否已经完成。
 2.通过call()方法获取返回的结果。为了达到这个目的，可以使用get()方法。这个方法一直等待直到Callable对象的call()方法执行完成并且返回结果。
 如果get()方法在等待过程中线程中断了，将抛出一个InterruptedException异常。如果call()方法抛出异常，那么get()方法将随之抛出ExecutionException异常。


 在调用Future对象的get()方法时，如果Future对象所控制的任务并没有完成，那么这个方法将一直阻塞到任务完成。Future接口也提供了get()方法的其他调用方式。
 get(long timeout, TimeUnit unit): 如果调用这个方法时，任务的结果并没有准备好，则方法等待所指定的timeout时间。如果等待超过了指定的时间而任务的结果还
 没有准备好，那么这个方法将返回java.util.concurrent.TimeoutException异常。
 */
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> resultList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(10);
            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }
        do {
            System.out.printf("DemosFromMyProjects: Number of Completed Tasks:%d\n", executor.getCompletedTaskCount());
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("DemosFromMyProjects： Task %d: %s\n", i, result.isDone());
//                try {
//                    System.out.println(result.get(1,TimeUnit.MILLISECONDS));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (TimeoutException e) {
//                    e.printStackTrace();
//                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (executor.getCompletedTaskCount() < resultList.size());
        System.out.printf("DemosFromMyProjects: Results\n");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);
            Integer number = null;
            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("DemosFromMyProjects: Task %d: %d\n", i, number);
        }
    }
}
