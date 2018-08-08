package lucius.justtest.java.concurrency.chapter1;

/**
 * Created by Lucius on 8/7/18.
 */
public class SafeTask implements Runnable {
    //线程局部变量分别为每个线程存储了各自的属性值，供每个线程使用.这里static加与不加,打印出的结果一样
    private static ThreadLocal<Long> count = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return 0L;
        }
    };

    public void run() {
        System.out.printf("Starting Thread: %s , count: %s\n", Thread.currentThread().getId(), count.get());
        count.set(count.get()+1);
    }
}
