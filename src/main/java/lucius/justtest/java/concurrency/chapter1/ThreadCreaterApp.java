package lucius.justtest.java.concurrency.chapter1;

/**
 * Created by root on 7/31/18.
 */
public class ThreadCreaterApp {
    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            //只有调用start()方法时，才会创建一个执行线程
            thread.start();
        }
    }
}
