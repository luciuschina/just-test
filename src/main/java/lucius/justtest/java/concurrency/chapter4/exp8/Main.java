package lucius.justtest.java.concurrency.chapter4.exp8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask类提供了一个名为done()的方法，允许在执行器中的任务执行结束后还可以执行一些代码。
 * 这个方法可以被用来执行一些后期处理操作。默认情况下，done()方法的实现
 * 为空，即没有任何具体的代码实现。我们可以覆盖FutureTask类并实现done()方法来改变这种行为。
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        ResultTask[] resultTasks = new ResultTask[5];
        for (int i = 0; i < 5; i++) {
            ExecutableTask executableTask = new ExecutableTask("Task_" + i); //实现Callable<String>
            resultTasks[i] = new ResultTask(executableTask); //继承FutureTask<String>，覆盖done()
            executor.submit(resultTasks[i]);
        }

        try {
            TimeUnit.SECONDS.sleep(5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 还在等待或者还没执行完的任务就取消，
        for (int i = 0; i < resultTasks.length; i++) {
            resultTasks[i].cancel(true);
        }

        for (int i = 0; i < resultTasks.length; i++) {
            try {
                if (resultTasks[i].isCancelled()) {
                    System.out.printf("!!! task_%d has been cancelled\n", i);
                } else {
                    System.out.printf("!!! %s\n", resultTasks[i].get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        executor.shutdown();
    }
}
