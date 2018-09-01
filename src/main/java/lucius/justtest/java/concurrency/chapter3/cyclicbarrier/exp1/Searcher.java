package lucius.justtest.java.concurrency.chapter3.cyclicbarrier.exp1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Lucius on 8/31/18.
 */
public class Searcher implements Runnable {
    private int firstRow;
    private int lastRow;
    private MatrixMock mock;
    private Result result;
    private int number;
    private final CyclicBarrier barrier;

    public Searcher(int firstRow, int lastRow, MatrixMock mock, Result result, int number, CyclicBarrier barrier) {
        this.barrier = barrier;
        this.number = number;
        this.result = result;
        this.mock = mock;
        this.lastRow = lastRow;
        this.firstRow = firstRow;
    }

    public void run() {
        int counter;
        System.out.printf("%s:开始处理第%d行到%d行\n", Thread.currentThread().getName(), firstRow, lastRow);
        for (int i = firstRow; i < lastRow ; i++) {
            int[] row = mock.getRow(i);
            counter = 0;
            for (int j = 0; j <row.length  ; j++) {
                if(row[j] == number) {
                    counter ++;
                }
            }
            result.setData(i,counter);

        }

        try {
            System.out.printf("%s:已经处理完,调用barrier.await()进入等待状态\n", Thread.currentThread().getName());
            barrier.await();
            System.out.printf("%s:已经被唤醒，进程结束\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
