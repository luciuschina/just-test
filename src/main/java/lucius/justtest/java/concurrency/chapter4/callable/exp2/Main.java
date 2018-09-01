package lucius.justtest.java.concurrency.chapter4.callable.exp2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *运行多个任务并处理第一个结果
 *
 并发编程比较常见的一个问题是，当采用多个并发任务来解决一个问题时，往往只关心这些任务中的第一个结果。
 比如，对一个数组排序有很多种算法，可以并发启动这些算法，但是对于一个给定的数组，第一个得到排序结果的
 算法就是最快的排序算法。
 在本节，我们将学习如何使用ThreadPoolExecutor类来实现这个场景。范例允许用户可以通过两种验证机制进行验证，
 但是，只要有一种机制验证成功，那么这个用户就可以验证通过。
 */
public class Main {
    public static void main(String[] args) {
        String username = "test";
        String password = "test";
        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DataBase");
        TaskValidator ldapTask = new TaskValidator(ldapValidator,username,password);
        TaskValidator dbTask = new TaskValidator(dbValidator, username, password);
        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);
        ExecutorService executor = Executors.newCachedThreadPool();
        String result;
        try {
            result = executor.invokeAny(taskList); //重点！！返回第一个成功执行完的任务的结果
            System.out.printf("Main: Result: %s\n", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.printf("Main: End of Execution");
    }
}
