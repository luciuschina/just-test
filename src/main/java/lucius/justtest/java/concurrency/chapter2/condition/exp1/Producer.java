package lucius.justtest.java.concurrency.chapter2.condition.exp1;

/**
 * Created by Lucius on 8/30/18.
 */
public class Producer {
    private Depot depot;

    public Producer(Depot depot) {
        this.depot = depot;
    }

    public void producer(final int val) {
        new Thread() {
            public void run() {
                depot.produce(val);
            }
        }.start();
    }
}
