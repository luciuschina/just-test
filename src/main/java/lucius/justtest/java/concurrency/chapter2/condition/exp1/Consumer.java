package lucius.justtest.java.concurrency.chapter2.condition.exp1;

/**
 * Created by Lucius on 8/30/18.
 */
public class Consumer {
    private Depot depot;

    public Consumer(Depot depot) {
        this.depot = depot;
    }

    public void run() {

    }

    public void consumer(final int val) {
        new Thread() {
            public void run() {
                depot.consume(val);
            }
        }.start();
    }
}
