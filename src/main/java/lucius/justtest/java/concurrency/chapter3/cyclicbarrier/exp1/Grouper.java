package lucius.justtest.java.concurrency.chapter3.cyclicbarrier.exp1;

/**
 * Created by Lucius on 8/31/18.
 */
public class Grouper implements Runnable {
    private Result result;

    public Grouper(Result result) {
        this.result = result;
    }

    public void run() {
        int finalResult = 0;
        System.out.printf("%s: Grouper:Processing Result...\n", Thread.currentThread().getName());
        int data[] = result.getData();
        for(int number: data){
            finalResult += number;
        }
        System.out.printf("Grouper: total result: %d\n", finalResult);
        System.out.printf("%s:结束\n", Thread.currentThread().getName());
    }
}
