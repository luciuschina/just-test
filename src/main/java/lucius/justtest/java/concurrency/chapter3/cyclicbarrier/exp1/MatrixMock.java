package lucius.justtest.java.concurrency.chapter3.cyclicbarrier.exp1;

import java.util.Random;

/**
 * Created by Lucius on 8/31/18.
 */
public class MatrixMock {
    private int[][] data;

    public MatrixMock(int size, int length, int number) {
        data = new int[size][length];
        long count = 0;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    count++;
                }
            }
        }
        System.out.printf("需要查找的数字是%d，这个数字在矩阵中有%d个\n", number, count);
    }


    public int[] getRow(int row) {
        if (row >= 0 && row < data.length) {
            return data[row];
        }
        return null;
    }


}
