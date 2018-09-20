package lucius.justtest.java.concurrency.chapter6.exp1;

import java.util.ArrayList;

/**
 * Created by Lucius on 9/7/18.
 */
public class AddTask2 implements Runnable {
    private ArrayList<String> list;

    public AddTask2(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(name+": Element "+i);
        }
    }

}
