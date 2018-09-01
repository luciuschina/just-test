package lucius.justtest.java.concurrency.chapter3.cyclicbarrier.exp1;

/**
 * Created by Lucius on 8/31/18.
 */
public class Result {
    private int[] data;

    public Result(int size) {
        this.data = new int[size];
    }

    public int[] getData() {
        return data;
    }

    public void setData(int position, int value) {
        data[position] = value;
    }
}
