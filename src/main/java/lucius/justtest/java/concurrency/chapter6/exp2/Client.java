package lucius.justtest.java.concurrency.chapter6.exp2;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lucius on 9/7/18.
 */
public class Client implements Runnable {
    private LinkedBlockingDeque<String> requestList;

    public Client(LinkedBlockingDeque<String> requestList) {
        this.requestList = requestList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                StringBuilder request = new StringBuilder();
                request.append(i).append(";").append(j);
                try {
                    requestList.put(request.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Master: %s at %s.\n", request, new Date());
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Clinet: End.\n");
    }
}
