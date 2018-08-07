package lucius.justtest.java.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

/**
 *  共享数据是并发程序最核心的问题之一，对于继承Thread 类或者实现了Runnable接口的对象来说，尤为重要。
 *  如果创建的对象是实现了Runnable接口的类的实例，用它作为传入参数，创建多个线程对象。并且启动这些线程，那么所有的线程将共享相同的属性。
 *  如果创建的对象是实现了Runnable接口的类的实例，但是每次都是new一个新的实例，将这个实例作为参数，传入一个线程对象，并启动这个线程。这些线程不会共享相同的属性。
 *  如果创建的对象是继承Thread类的线程，多个线程不会共享相同的属性。
 *
 *  例如下面程序会有以下输出：
 *  Threads which use the same runnable task instance: 
 *  Starting Thread: 9 , count: 0
 *  Starting Thread: 10 , count: 1
 *  Starting Thread: 11 , count: 2
 *
 *  Threads which use different runnable task instances:
 *  Starting Thread: 12 , count: 0
 *  Starting Thread: 13 , count: 0
 *  Starting Thread: 14 , count: 0
 *
 *  Threads which extends Thread class test:
 *  Starting Thread: 15 , count: 0
 *  Starting Thread: 16 , count: 0
 *  Starting Thread: 17 , count: 0
 */
public class ThreadSharedVariableApp {
    public static void main(String[] args) {
        System.out.println("\nThreads which use the same runnable task instance: ");
        RunnableTask task = new RunnableTask();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nThreads which use different runnable task instances: ");
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new RunnableTask());
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nThreads which extends Thread class test: ");
        for (int i = 0; i < 3; i++) {
            Thread thread = new ExtendsThreadTask();
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
