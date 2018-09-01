package lucius.justtest.java.concurrency.chapter3.cyclicbarrier.exp1;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by Lucius on 8/31/18.
 */
public class Main {
    public static void main(String[] args) {
        final int rows = 10000;
        final int columns = 1000;
        final int search = 5;
        final int participants = 5;
        final int lineParticipants = 2000;
        MatrixMock mock = new MatrixMock(rows, columns, search);
        mock.getRow(0);
        Result result = new Result(rows);
        Grouper grouper = new Grouper(result);
        //等待participants个Searcher线程执行完后，执行grouper线程。 因为在Searcher线程的run()方法执行完的最后都调用了barrier.await()
        CyclicBarrier barrier = new CyclicBarrier(participants, grouper);
        Searcher[] searchers = new Searcher[participants];
        for (int i = 0; i < participants; i++) {
            searchers[i] = new Searcher(i * lineParticipants, (i + 1) * lineParticipants, mock, result, search, barrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("finish!");
    }
}
