package lucius.justtest.java.concurrency.chapter2.condition.exp1;

/**
 * 参考地址
 * http://www.cnblogs.com/skywang12345/p/3496101.html
 */
public class Main {
    public static void main(String[] args) {
        Depot depot = new Depot(110);
        Consumer consumer = new Consumer(depot);
        Producer producer = new Producer(depot);
        consumer.consumer(20);
        producer.producer(130);
        consumer.consumer(80);
        producer.producer(50);
    }
}
